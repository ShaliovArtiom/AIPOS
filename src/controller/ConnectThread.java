package controller;

/**
 * Класс, описывающий подключение потока к серверу
 * @author ShaliovArtiom, TruntsVitalij
 */
public class ConnectThread extends Thread {
    /**
     * массив строк, переданный как аргумент командной строкой
     */
    private static String[] argc;
    /**
     * Запрос, который обрабатывает сервер
     */
    private static String request;

    /**
     * Конструктор, инициализирующий поток.
     * @param argc массив строк, переданный как аргумент командной строкой
     * @param request запрос, который обрабатывает сервер
     */
    public ConnectThread(String[] argc, String request) {
        this.argc = argc;
        this.request = request;
    }

    /**
     * Функция, вызывающая функцию подключения к серверу.
     */
    @Override
    public void run(){
        try {
            new ConnectServer(argc, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
