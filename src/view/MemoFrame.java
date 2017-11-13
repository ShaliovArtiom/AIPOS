package view;

import controller.ConnectServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Класс, который описывает работу окна, выводящего диалог с сервером
 * @author ShaliovArtiom, TruntsVitalij
 */
public class MemoFrame {
    /**
     * Поле ввода запроса
     */
    private JTextArea insertField = new JTextArea();
    /**
     * Объект, предназначенный для записи в файл
     */
    private FileOutputStream writer;

    /**
     * Конструктор для создания второстепенного окна
     * @param frame окно
     * @throws IOException ошибка записи в файл
     */
    public MemoFrame(JFrame frame) throws IOException {
        writer = new FileOutputStream("logFile.txt", true);
        writer.write("\n".getBytes());
        init(frame);

        insertField.setSize(350, 200);
        insertField.setLocation(10, 10);
        JScrollPane jScrollPane = new JScrollPane(insertField);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.setContentPane(jScrollPane);
    }

    /**
     * Функция для задания размеров окна
     * @param frame собственно окно
     */
    private void init(JFrame frame) {
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setLocation(333, 333);
    }

    /**
     * Функция для вывода диалога с сервером на второстепенное окно
     * @param info ответ сервера
     */
    public void printInfo(String info) {
        if (insertField.getText() != null) {
            insertField.setText(insertField.getText() + info);
        } else {
            insertField.setText(info + "\n\n");
        }
    }

    /**
     * Функция для записи диалога с сервером в файл
     * @throws IOException ошибка записи в файл
     */
    public void printInfoLogFail() throws IOException {
        writer.write(insertField.getText().getBytes());
    }
}
