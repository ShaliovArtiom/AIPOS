package view;



import controller.ConnectServer;
import controller.ConnectThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


/**
 * Класс служит для отображения окна ввода запросов серверу
 * @author ShaliovArtiom, TruntsVitalij
 */
public class Frame {

    /**
     * Панель предназначенная для размещения поля ввода
     */
    private JPanel panel = new JPanel();
    /**
     * Поле ввода
     */
    private JTextArea insertField = new JTextArea();
    /**
     * Кнопка, отправляющая запрос на сервер
     */
    private JButton runButton = new JButton("Run");
    /**
     * Поток, предназначенный для отправки запросов
     */
    private ConnectThread connectThread;

    /**
     * Конструктор для создания окна
     * @param frame собственно окно
     * @param argc массив строк, переданный как аргумент командной строкой
     * @throws FileNotFoundException ошибка, сигнализирующая об отсутствии файла
     */
    public Frame(JFrame frame, String[] argc) throws FileNotFoundException {
        init(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        insertField.setSize(480, 320);
        insertField.setLocation(10, 10);
        panel.add(insertField);

        runButton.setSize(80, 30);
        runButton.setLocation(500, 330);
        panel.add(runButton);

        frame.setContentPane(panel);
        initListeners(argc);
    }

    /**
     * Функция, инициализирующая поток для обработки запроса
     * @param argc
     */
    private void initListeners(String[] argc)  {
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String request;
                request = insertField.getText();
                connectThread = new ConnectThread(argc, request);
                connectThread.run();
            }
        });
    }

    /**
     * Функция задающая размер окна
     * @param frame окно, размер которого нужно изменить
     */
    private void init(JFrame frame) {
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}
