import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ServerWindow extends JFrame {

    private JTextArea textArea;
    private List<ClientGUI> clients = new ArrayList<>();
    private boolean isRunning = false;



    public ServerWindow() {
        setTitle("Chat Server");
        setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");

        panel.add(startButton);
        panel.add(stopButton);

        add(panel,BorderLayout.SOUTH);

        setVisible(true);
    }

    //метод включения сервера
    void startServer() {




    }

    //метод выключающий сервер
    void stopServer() {

    }

    // Метод для проверки запущен ли сервер
    boolean isRunning() {
        return false;
    }

    //добавляет клиента который успешно подключился
    void addClient(ClientGUI clientGUI) {

    }

    //удаляем отключивщигося клиента
    void removeClient() {

    }

    //метод отправки сообщений в общий чат
    void broadcastMessage() {

    }

    //логирования истории сообщений
    void logMessage() {

    }

    //метод загружающий чат из файла
    void loadChatHistory(){

    }

}

