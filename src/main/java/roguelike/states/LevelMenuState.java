package roguelike.states;

import roguelike.controller.Controller;
import roguelike.controller.menu.LevelMenuController;
import roguelike.controller.menu.PauseMenuController;
import roguelike.model.menu.Menu;
import roguelike.viewer.Viewer;
import roguelike.viewer.menu.MenuViewer;

public class LevelMenuState extends State<Menu> {
    public LevelMenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new LevelMenuController(getModel());
    }
}
