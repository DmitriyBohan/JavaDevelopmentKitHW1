import javax.swing.*;


public class ServerWindow extends JFrame {

    private boolean isConnected = false;



    public ServerWindow() {
        setTitle("Chat Server");
        setSize(400, 300);

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

