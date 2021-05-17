package viewer.game;

public interface ElementViewer<T extends Element> {
    void draw(T element, GUI gui);
}
