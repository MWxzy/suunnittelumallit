package memento;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Gui extends JFrame implements StateChangeListener {

    private final Controller controller;

    private final ColorBox colorBox1;
    private final ColorBox colorBox2;
    private final ColorBox colorBox3;
    private final JCheckBox checkBox;

    private final JButton undoButton;
    private final JButton redoButton;
    private final JButton historyButton;

    private HistoryWindow historyWindow;
    private boolean updatingGui;

    public Gui() {
        super("Memento Pattern Example");

        controller = new Controller(this);

        colorBox1 = new ColorBox(1, controller);
        colorBox2 = new ColorBox(2, controller);
        colorBox3 = new ColorBox(3, controller);

        checkBox = new JCheckBox("Click me!");
        checkBox.addActionListener(event -> {
            if (!updatingGui) {
                controller.setIsSelected(checkBox.isSelected());
            }
        });

        undoButton = new JButton("Undo (Ctrl+Z)");
        undoButton.addActionListener(event -> controller.undo());

        redoButton = new JButton("Redo (Ctrl+Y)");
        redoButton.addActionListener(event -> controller.redo());

        historyButton = new JButton("Show history");
        historyButton.addActionListener(event -> showHistoryWindow());

        JPanel boxesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        boxesPanel.add(colorBox1.getComponent());
        boxesPanel.add(colorBox2.getComponent());
        boxesPanel.add(colorBox3.getComponent());
        boxesPanel.setAlignmentX(LEFT_ALIGNMENT);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        buttonPanel.add(historyButton);
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);

        JLabel label = new JLabel("Ctrl+Z = undo, Ctrl+Y = redo. "
                + "Open the history window to jump to an earlier state.");
        label.setAlignmentX(LEFT_ALIGNMENT);
        checkBox.setAlignmentX(LEFT_ALIGNMENT);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        content.add(boxesPanel);
        content.add(Box.createVerticalStrut(10));
        content.add(checkBox);
        content.add(Box.createVerticalStrut(10));
        content.add(buttonPanel);
        content.add(Box.createVerticalStrut(10));
        content.add(label);

        setContentPane(content);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        installKeyBindings();
        pack();
        setLocationRelativeTo(null);
        updateGui();
    }

    private void installKeyBindings() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "undo");
        actionMap.put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                controller.undo();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK), "redo");
        actionMap.put("redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                controller.redo();
            }
        });
    }

    private void showHistoryWindow() {
        if (historyWindow == null) {
            historyWindow = new HistoryWindow(controller, this);
        }
        historyWindow.refresh();
        historyWindow.setVisible(true);
        historyWindow.toFront();
    }

    @Override
    public void onStateChanged() {
        updateGui();
    }

    private void updateGui() {
        updatingGui = true;
        try {
            colorBox1.setColor(controller.getOption(1));
            colorBox2.setColor(controller.getOption(2));
            colorBox3.setColor(controller.getOption(3));
            checkBox.setSelected(controller.getIsSelected());

            undoButton.setEnabled(controller.canUndo());
            redoButton.setEnabled(controller.canRedo());

            if (historyWindow != null) {
                historyWindow.refresh();
            }
        } finally {
            updatingGui = false;
        }
    }
}
