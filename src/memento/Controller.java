package memento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {

    private final Model model;
    private final StateChangeListener listener;
    private final List<IMemento> history;
    private int currentIndex;

    public Controller(StateChangeListener listener) {
        this.model = new Model();
        this.listener = listener;
        this.history = new ArrayList<>();

        this.history.add(model.createMemento());
        this.currentIndex = 0;
    }

    public void setOption(int optionNumber, int choice) {
        model.setOption(optionNumber, choice);
        saveState();
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        model.setIsSelected(isSelected);
        saveState();
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    public void undo() {
        if (canUndo()) {
            currentIndex--;
            restoreCurrentState();
        }
    }

    public void redo() {
        if (canRedo()) {
            currentIndex++;
            restoreCurrentState();
        }
    }

    public void restoreFromHistory(int index) {
        if (index >= 0 && index < history.size() && index != currentIndex) {
            currentIndex = index;
            restoreCurrentState();
        }
    }

    public boolean canUndo() {
        return currentIndex > 0;
    }

    public boolean canRedo() {
        return currentIndex < history.size() - 1;
    }

    public List<IMemento> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    private void saveState() {
        while (history.size() > currentIndex + 1) {
            history.remove(history.size() - 1);
        }

        history.add(model.createMemento());
        currentIndex = history.size() - 1;
        listener.onStateChanged();
    }

    private void restoreCurrentState() {
        model.restoreState(history.get(currentIndex));
        listener.onStateChanged();
    }
}
