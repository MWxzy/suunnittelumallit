package memento;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoryWindow extends JFrame {

    private static final DateTimeFormatter TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private final Controller controller;
    private final DefaultListModel<IMemento> listModel;
    private final JList<IMemento> historyList;
    private boolean updating;

    public HistoryWindow(Controller controller, Window owner) {
        super("Model History");
        this.controller = controller;

        listModel = new DefaultListModel<>();
        historyList = new JList<>(listModel);
        historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        historyList.setVisibleRowCount(10);
        historyList.setCellRenderer(new HistoryCellRenderer());
        historyList.addListSelectionListener(event -> {
            if (updating || event.getValueIsAdjusting()) {
                return;
            }
            int index = historyList.getSelectedIndex();
            if (index >= 0) {
                controller.restoreFromHistory(index);
            }
        });

        JLabel instruction = new JLabel(
                "Select a state to restore the model to that state.");
        instruction.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        setLayout(new BorderLayout());
        add(instruction, BorderLayout.NORTH);
        add(new JScrollPane(historyList), BorderLayout.CENTER);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(680, 320);
        setLocationRelativeTo(owner);
        installKeyBindings();

        refresh();
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

    public void refresh() {
        updating = true;
        try {
            List<IMemento> history = controller.getHistory();
            listModel.clear();
            for (IMemento memento : history) {
                listModel.addElement(memento);
            }

            int currentIndex = controller.getCurrentIndex();
            if (currentIndex >= 0 && currentIndex < listModel.size()) {
                historyList.setSelectedIndex(currentIndex);
                historyList.ensureIndexIsVisible(currentIndex);
            }
        } finally {
            updating = false;
        }
    }

    private class HistoryCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            IMemento memento = (IMemento) value;
            String marker = index == controller.getCurrentIndex() ? "-> " : "   ";
            setText(String.format("%s#%d  %s  %s",
                    marker,
                    index + 1,
                    memento.getTimestamp().format(TIMESTAMP_FORMAT),
                    memento.getDescription()));
            return this;
        }
    }
}
