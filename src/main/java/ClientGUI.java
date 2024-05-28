import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClientGUI extends JFrame {

    private ServerWindow serverWindow;
    private JTextArea textArea;    //Поле для сособщений чата
    private JTextField inputField;    // Поле для ввода сообщений
    private JTextField serverAddressField;
    private JTextField portField;
    private JTextField loginField;
    private JPasswordField passwordField;
    private boolean isConnected = false;


    public ClientGUI(ServerWindow serverWindow) {

        this.serverWindow = serverWindow;

        //setTitle - создает заголовки к окнам
        setTitle("Chat Client");

        setSize(400, 300);
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

        connectionPanel.add(new JLabel("Login:"));
        loginField = new JTextField();
        connectionPanel.add(loginField);

        connectionPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
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

        setVisible(true);
    }

    // Метод для подключения к серверу
    void connectToServer() {
        isConnected = true;
        serverWindow.addClient(this);
        textArea.setVisible(true);
        inputField.setVisible(true);
        inputField.getParent().setVisible(true);
    }

    // Метод  отключения от сервера
    void notifyDisconnection() {

    }


    // Метод для отправки сообщения на сервер
    void sendMessage() {

    }


    //метод выводит сообщения в чат
    void receiveMessage() {

    }


    //метод загрузки истории чата из файла
    void loadChatHistory() {

    }
}
