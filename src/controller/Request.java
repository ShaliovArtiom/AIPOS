package controller;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import view.MemoFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Класс, описывающий запрос
 * @author ShaliovArtiom, TruntsVitalij
 */
public class Request {
    /**
     * Окно, выводящее диалог клиента с сервером
     */
    private static MemoFrame frame;

    /**
     * Инициализация запроса
     * @param frame второстепенное окно
     */
    public Request(MemoFrame frame){
        this.frame = frame;
    }

    /**
     * Функция, реализующая отправку запроса серверу
     * @param httpHeader отправляемый серверу запрос
     * @return результат работы сервера
     * @throws Exception ошибки, связанные с подключением к серверу или с отправкой запроса
     */
    public static String sendRequest(String httpHeader) throws Exception {
        String host = null;
        int port = 0;
        try {
            host = getHost(httpHeader);
            port = getPort(host);
            host = getHostWithoutPort(host);
        } catch (Exception e) {
            throw new Exception("Не удалось получить адрес сервера.", e);
        }
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            frame.printInfo("Создан сокет: " + host + " port:" + port + "\n\n");
            socket.getOutputStream().write(httpHeader.getBytes());
            frame.printInfo("Заголовок отправлен. \n\n");
        } catch (Exception e) {
            throw new Exception("Ошибка при отправке запроса: "
                    + e.getMessage(), e);
        }
        String answer = null;
        try {
            InputStreamReader isr = new InputStreamReader(socket
                    .getInputStream());
            BufferedReader bfr = new BufferedReader(isr);
            StringBuffer sbf = new StringBuffer(); //
            int ch = bfr.read();
            while (ch != -1) {
                sbf.append((char) ch);
                ch = bfr.read();
            }
            answer = sbf.toString();
        } catch (Exception e) {
            throw new Exception("Ошибка при чтении ответа от сервера.", e);
        }
        socket.close();
        return answer;
    }

    private static String getHostWithoutPort(String hostWithPort) {
        int portPosition = hostWithPort.indexOf(":", 0);
        if (portPosition < 0) {
            return hostWithPort;
        } else {
            return hostWithPort.substring(0, portPosition);
        }
    }

    private static int getPort(String hostWithPort) {
        int port = hostWithPort.indexOf(":", 0);
        port = (port < 0) ? 80 : Integer.parseInt(hostWithPort
                .substring(port + 1));
        return port;
    }

    private static String getHost(String header) {
        final String host = "Host: ";
        final String normalEnd = "\n";
        final String msEnd = "\r\n";

        int iterator = header.indexOf(host, 0);
        if (iterator < 0) {
            return "localhost";
        }

        iterator += host.length();
        int enumirate = header.indexOf(normalEnd, iterator);
        enumirate = (enumirate > 0) ? enumirate : header.indexOf(msEnd, iterator);
        if (enumirate < 0) {

            throw new ParseException(
                    "В заголовке запроса не найдено " +
                            "закрывающих символов после пункта Host.",
                    0);
        }

        String answer = header.substring(iterator, enumirate).trim();
        return answer;

    }
}
