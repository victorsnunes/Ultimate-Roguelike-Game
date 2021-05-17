package states;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final com.aor.hero.controller.Controller<T> controller;
    private final com.aor.hero.viewer.Viewer<T> viewer;

    public State(T model) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract com.aor.hero.viewer.Viewer<T> getViewer();

    protected abstract com.aor.hero.controller.Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, time);
        viewer.draw(gui);
    }
}
