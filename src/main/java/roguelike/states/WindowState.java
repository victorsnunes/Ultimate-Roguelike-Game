package roguelike.states;

import roguelike.controller.Controller;
import roguelike.controller.menu.WindowController;
import roguelike.model.menu.Window;
import roguelike.viewer.Viewer;
import roguelike.viewer.menu.WindowViewer;

public class WindowState extends State<Window> {
    public WindowState(Window model){ super(model); }

    @Override
    protected Viewer<Window> getViewer() {
        return new WindowViewer(getModel());
    }

    @Override
    protected Controller<Window> getController() {
        return new WindowController(getModel());
    }
}
