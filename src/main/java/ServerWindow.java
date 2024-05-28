import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class ServerWindow extends JFrame {

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

        add(panel,BorderLayout.SOUTH);

        //слушатели кнопок

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              stopServer();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });


        setVisible(true);
    }

    //метод включения сервера
    void startServer() {
        isRunning = true;
        textArea.append("The server is running!\n");

    }

    //метод выключающий сервер
    void stopServer() {
        isRunning = false;
        textArea.append("The server is stopped.\n");
    }

    // Метод для проверки запущен ли сервер
    public boolean isRunning() {
        return isRunning;
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

