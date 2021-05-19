package roguelike.model.menu;

import java.util.List;

public class Window {
    private String title;
    private List<String> content;

    public Window (String title, List<String> content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() { return title; }
    public List<String> getContent() { return content; }
    public String getLineContent(int i) { return content.get(i); }
    public int getNumberOfLines() { return content.size(); }
}
