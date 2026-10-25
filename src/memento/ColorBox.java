package memento;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorBox {

    private final int id;
    private final Controller controller;

    private final JPanel panel;
    private final Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW};
    private int colorIndex = 0;

    public ColorBox(int id, Controller controller) {
        this.id = id;
        this.controller = controller;

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setBackground(colors[colorIndex]);
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.setToolTipText("Click to change the color");

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                changeColor();
                controller.setOption(id, colorIndex);
            }
        });
    }

    public JComponent getComponent() {
        return panel;
    }

    public void setColor(int colorIndex) {
        if (colorIndex >= 0 && colorIndex < colors.length) {
            this.colorIndex = colorIndex;
            panel.setBackground(colors[colorIndex]);
        }
    }

    private void changeColor() {
        colorIndex = (colorIndex + 1) % colors.length;
        panel.setBackground(colors[colorIndex]);
    }
}
