package roguelike.model.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {

    private final String title;
    private final List<String> options;
    private int currentOption = 0;

    public Menu(String title, List<String> options) {
        this.title = title;
        this.options = options;
    }

    public String getOption(int i) { return options.get(i); }
    public String getTitle() { return title; }

    public void nextOption() {
        currentOption++;
        if (currentOption > this.options.size() - 1)
            currentOption = 0;
    }

    public void previousOption() {
        currentOption--;
        if (currentOption < 0)
            currentOption = this.options.size() - 1;
    }

    public boolean isSelected(int i) {
        return currentOption == i;
    }

    public int getNumberOptions() {
        return this.options.size();
    }
}
