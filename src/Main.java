import view.Frame;
import view.MemoFrame;

import javax.swing.*;

/**
 * Точка входа в программу
 * @author ShaliovArtiom, TruntsVitalij
 * @version 1.0.0.
 */
public class Main {
    /**
     * Создаём главное окно
     * @param argc массив строк, переданный как аргумент командной строкой
     * @throws Exception проверка на наличие ошибки
     */
    public static void main(String[] argc) throws Exception {
        new Frame(new JFrame(), argc);
    }
}