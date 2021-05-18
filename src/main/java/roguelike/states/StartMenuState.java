package roguelike.states;

import roguelike.controller.Controller;
import roguelike.controller.menu.StartMenuController;
import roguelike.model.menu.Menu;
import roguelike.viewer.Viewer;
import roguelike.viewer.menu.MenuViewer;

public class StartMenuState extends State<Menu> {

    public StartMenuState(Menu model) { super(model); }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new StartMenuController(getModel());
    }
}
