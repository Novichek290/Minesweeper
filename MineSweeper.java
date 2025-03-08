import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MineSweeper extends JFrame {
    static int size1 = 5;
    static int size2 = 5;
    static int mine = -1;
    static int emptyCage = 0;
    static int mineCount = 3;
    public static int[][] field;
    public static JButton[][] buttons; // Создаем двумерный массив кнопок
    public static boolean[][] revealed;


    public MineSweeper() { // Создаем конструктор класса MineSweeper
        setTitle("Сапёр");
        // Настройка окна
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(size1, size2 ));  // Устанавливаем менеджер компоновки GridLayout

        // Инициализация поля и кнопок
        field = new int[size1][size2];
        buttons = new JButton[size1][size2]; // Создаем двумерный массив кнопок
        revealed = new boolean[size1][size2];
        minePlacement(mineCount, createField(size1, size2));
        initializeButtons(); // Вызываем метод инициализации кнопок
        mineCounter(size1, size2, field);


        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40)); // Размер кнопки
                button.setFocusable(false); // Убираем фокус из кнопки

                buttons[i][j] = button;
                add(button);
                button.addActionListener(new ButtonClickListener(i, j));
                // Обработчик правого клика
                button.addMouseListener(new ButtonRightClickListener(i, j, button));
            }
        }
    }

    public static int[][] createField(int size1, int size2) {
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                field[i][j] = emptyCage;
                revealed[i][j] = false;
            }
        }
        return field;
    }

    public static void minePlacement(int mineCount, int[][] field) {
        while (mineCount > 0) {
            Random rand = new Random();
            int x = rand.nextInt(size1);
            int y = rand.nextInt(size2);
            if (field[x][y] != mine) field[x][y] = mine;
            mineCount--;
        }
    }

    public static void mineCounter(int size1, int size2, int[][] field) {
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                if (field[i][j] != mine) {

                    int minesAroundCount = 0;
                    for (int l = -1; l <= 1; l++) {
                        for (int m = -1; m <= 1; m++) {
                            int newX = i + l;
                            int newY = j + m;
                            if (newX >= 0 & newX < size1 & newY >= 0 & newY < size2)
                                if(field[newX][newY] == -1){
                                    minesAroundCount++;
                                }
                        }
                    }
                    field[i][j] = minesAroundCount;
                }
            }
        }
    }

//    public static void printField(int size1, int size2, int[][] field) {
//        for (int i = 0; i < size1; i++) {
//            for (int j = 0; j < size2; j++) {
//                System.out.print(field[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
}
