import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static jdk.internal.org.jline.utils.InfoCmp.Capability.buttons;

public class ButtonClickListener implements ActionListener {
    private int x, y;

    public ButtonClickListener(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (MineSweeper.revealed[x][y]) {
            return; // Если клетка уже открыта, игнорируем
        }

        MineSweeper.revealed[x][y] = true; // Отмечаем клетку как открытою
//      JButton button = (JButton) e.getSource();
        JButton button = MineSweeper.buttons[x][y];

        if(MineSweeper.field[x][y] == MineSweeper.mine) {
            button.setText("💣");
            JOptionPane.showMessageDialog(null, "Вы проиграли!");
            System.exit(0);
        } else if (MineSweeper.field[x][y] == 0) {
            // Открываем пустые клетки рекурсивно
            button.setText("");
            button.setEnabled(false);
            openEmptyCells(x, y);
        } else {
            button.setText(String.valueOf(MineSweeper.field[x][y]));
            button.setEnabled(false);
        }

        if (checkWin()) {
            JOptionPane.showMessageDialog(null, "Вы победили!");
            System.exit(0);
        }
    }

    public static boolean checkWin() {
        for (int i = 0; i < MineSweeper.size1; i++) {
            for (int j = 0; j < MineSweeper.size2; j++) {
                if (!MineSweeper.revealed[i][j] && MineSweeper.field[i][j] != MineSweeper.mine) {
                    return false;
                }
            }
        }
        System.out.println("Вы победили!");
        return true;
    }

    public static void openEmptyCells(int x, int y) {
        for (int i = -1; i <=1; i++) {
            for(int j = -1; j <=1; j++) {
                int newX = x + i;
                int newY = y + j;

                try {

                    if (newX >= 0 & newX < MineSweeper.size1 & newY >= 0 & newY < MineSweeper.size2 & !MineSweeper.revealed[newX][newY]) {
                        MineSweeper.revealed[newX][newY] = true;
                        JButton button = MineSweeper.buttons[newX][newY];

                        if (MineSweeper.field[newX][newY] == MineSweeper.emptyCage) {
                            button.setText(String.valueOf(MineSweeper.field[newX][newY]));
                            button.setEnabled(false);
                            openEmptyCells(newX, newY);
                        } else if (MineSweeper.field[newX][newY] != MineSweeper.mine) {
                            button.setText(String.valueOf(MineSweeper.field[newX][newY]));
                            button.setEnabled(false);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
