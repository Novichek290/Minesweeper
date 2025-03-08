import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonRightClickListener extends MouseAdapter {
    private int x, y;
    private JButton button;

    public ButtonRightClickListener(int x, int y, JButton button) {
        this.x = x;
        this.y = y;
        this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            if (!button.isEnabled()) {
                return; // Если клетка уже открыта, игнорируем
            }

            if (button.getText().equals("🚩")) {
                button.setText(""); // Снимаем флаг
            } else {
                button.setText("🚩"); // Устанавливаем флаг
            }
            System.out.println("Флаг на клетке: [" + x + ", " + y + "]");
        }
    }
}
