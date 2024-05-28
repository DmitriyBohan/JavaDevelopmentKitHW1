import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ServerWindow extends JFrame {

    private static final String LOG_FILE = "chat_log.txt";;
    private JTextArea textArea;
    private List<ClientGUI> clients = new ArrayList<>();
    private boolean isRunning = false;


    public ServerWindow() {
        setTitle("Chat Server");
        setSize(400, 300);

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);  // Добавление JTextArea в центр окна с возможностью прокрутки

        JPanel panel = new JPanel(new GridLayout(5, 2));

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");

        panel.add(startButton);
        panel.add(stopButton);

        add(panel, BorderLayout.SOUTH);

        //слушатели кнопок

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopServer();
            }
        });


        setVisible(true);
    }

    //метод включения сервера
    void startServer() {
        isRunning = true;
        textArea.append("The server is running!\n");

    }

    // Метод для проверки запущен ли сервер
    public boolean getIsRunning(){
        return isRunning;
    }

    //метод выключающий сервер
    void stopServer() {
        isRunning = false;
        textArea.append("The server is stopped.\n");
        for (ClientGUI client : clients) {
            client.notifyDisconnection();
        }
        clients.clear();
    }

    // Метод для проверки запущен ли сервер
    public boolean isRunning() {
        return isRunning;
    }

    //добавляет клиента который успешно подключился
    void addClient(ClientGUI client) {
        clients.add(client);
        broadcastMessage(client.getLogin() + " подключился к беседе");
    }

    //удаляем отключивщигося клиента
    void removeClient(ClientGUI client) {
        clients.remove(client);
        broadcastMessage(client.getLogin() + " отключился от беседы");
    }

    //метод отправки сообщений в общий чат
    void broadcastMessage(String message) {
        logMessage(message);
        for (ClientGUI client : clients) {
            client.receiveMessage(message);
        }
    }

    //логирования истории сообщений
    private void logMessage(String message) {

        textArea.append(message + "\n");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            textArea.append("Error writing to the log file:" + e.getMessage() + "\n");
        }

    }

    //метод загружающий чат из файла
    void loadChatHistory() {
        if (Files.exists(Paths.get(LOG_FILE))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException e) {
                textArea.append("Ошибка чтения лог-файла: " + e.getMessage() + "\n"); // Вывод сообщения об ошибке в JTextArea
            }
        }
    }

}

