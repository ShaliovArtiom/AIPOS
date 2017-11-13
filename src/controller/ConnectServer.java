package controller;

import view.Frame;
import view.MemoFrame;

import javax.swing.*;
import java.io.*;

/**
 * Класс, реализующий соединение с сервером
 * @author ShaliovArtiom, TruntsVitalij
 */
public class ConnectServer {
    /**
     * Запрос, который обрабатывает сервер
     */
    private static Request request;
    /**
     * Окно, предназначенное для вывода диалога с сервером
     */
    private MemoFrame memoFrame;

    /**
     * Конструктор, реализующий отправку запроса серверу
     * @param argc массив строк, переданный как аргумент командной строкой
     * @param req запрос, обрабатываемый сервером
     * @throws Exception ошибка отправки запроса
     */
    public ConnectServer(String argc[], String req) throws Exception {
        memoFrame = new MemoFrame(new JFrame());
        request = new Request(memoFrame);
        try {
            String header = null;
            if (argc.length == 0) {
                header = req;
            }
            memoFrame.printInfo("Запрос: \n" + header);
            String answer = request.sendRequest(header);
            memoFrame.printInfo("Ответ от сервера: \n");
            memoFrame.printInfo(answer);
            memoFrame.printInfoLogFail();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getCause().printStackTrace();
        }
    }
}