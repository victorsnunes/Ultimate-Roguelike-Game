package roguelike.states;

import roguelike.controller.Controller;
import roguelike.controller.menu.MenuController;
import roguelike.model.menu.Menu;
import roguelike.viewer.Viewer;
import roguelike.viewer.menu.MenuViewer;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}
