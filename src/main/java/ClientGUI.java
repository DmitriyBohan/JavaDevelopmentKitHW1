import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ClientGUI extends JFrame {

    private ServerWindow serverWindow;
    private JTextArea textArea;    //Поле для сообщений чата
    private JTextField inputField;    // Поле для ввода сообщений
    private JTextField serverAddressField;
    private JTextField portField;
    private JTextField loginField;
    private JPasswordField passwordField;
    private boolean isConnected = false;


    public String getLogin() {
        return loginField.getText();
    }

    public ClientGUI(ServerWindow serverWindow) {

        this.serverWindow = serverWindow;

        //setTitle - создает заголовки к окнам
        setTitle("Chat Client");

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Панель компонентов подключения к серверу
        JPanel connectionPanel = new JPanel(new GridLayout(5, 2));
        connectionPanel.add(new JLabel("Server IP:"));
        serverAddressField = new JTextField("127.0.0.1"); // заполняю поле ip адрес . по умолчанию значение будет "127.0.0.1"
        connectionPanel.add(serverAddressField);

        connectionPanel.add(new JLabel("Port:"));
        portField = new JTextField("8189");  //по аналогии с ip адресом
        connectionPanel.add(portField);

//        connectionPanel.add(new JLabel("Login:"));
//        loginField = new JTextField();
//        connectionPanel.add(loginField);

        connectionPanel.add(new JLabel("Login:"));
        loginField = new JTextField("DIMA");
        connectionPanel.add(loginField);

//        connectionPanel.add(new JLabel("Password:"));
//        passwordField = new JPasswordField();
//        connectionPanel.add(passwordField);


        connectionPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField("111");
        connectionPanel.add(passwordField);

        JButton connectButton = new JButton("Connect");
        connectionPanel.add(connectButton);

        add(connectionPanel, BorderLayout.NORTH); // Добавление панели с полями ввода на верхнюю часть окна


        // Поле для сообщений чата
        textArea = new JTextArea();
        textArea.setEditable(false); // делаю поле для сообщений не редактируемым
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Поле для ввода сообщений

        inputField = new JTextField();
        JButton sendButton = new JButton("Send");
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        //слушатели кнопок

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        //addKeyListener- слушатель кнопок
        //KeyAdapter - https://javaswing.wordpress.com/2009/12/23/keylistener_using/
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });

        loadChatHistory();
        setVisible(true);
    }

    // Метод для подключения к серверу
    void connectToServer() {
        if (loginField.getText().isEmpty() || new String(passwordField.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(ClientGUI.this, "Please fill in the login and password fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!serverWindow.isRunning()) {
            //https://java-online.ru/swing-joptionpane.xhtml
            JOptionPane.showMessageDialog(ClientGUI.this, "The server is not running.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            isConnected = true;
            serverWindow.addClient(this);
            textArea.setVisible(true);
            inputField.setVisible(true);
            inputField.getParent().setVisible(true);
            textArea.append("You have successfully connected!\n");
            loadChatHistory();
        }
    }

    // Метод  отключения от сервера
    void notifyDisconnection() {
        isConnected = false;
        JOptionPane.showMessageDialog(ClientGUI.this, "You have been disconnected from the server.", "Information", JOptionPane.INFORMATION_MESSAGE);
        textArea.append("You have been disconnected from the server.\n");
    }


    // Метод для отправки сообщения на сервер
    void sendMessage() {
        if (!isConnected) {
            return;
        }
        String message = inputField.getText();
        if (!message.isEmpty()) {
            String login = loginField.getText();
            String fullMessage = login + ": " + message;
            serverWindow.broadcastMessage(fullMessage);
            inputField.setText("");
        }
    }


    //метод выводит сообщения в чат
    public void receiveMessage(String message) {
        textArea.append(message + "\n");
    }


    //метод загрузки истории чата из файла
    void loadChatHistory() {

        File logFile = new File("chat_log.txt");
        if (logFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException e) {
                textArea.append("Error reading the log file: " + e.getMessage() + "\n");
            }
        }
    }
}
