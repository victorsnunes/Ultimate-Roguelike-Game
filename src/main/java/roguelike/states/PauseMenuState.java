package roguelike.states;

import roguelike.controller.Controller;
import roguelike.controller.menu.PauseMenuController;
import roguelike.model.game.arena.Arena;
import roguelike.model.menu.Menu;
import roguelike.viewer.Viewer;
import roguelike.viewer.menu.MenuViewer;

public class PauseMenuState extends State<Menu> {

    public PauseMenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new PauseMenuController(getModel());
    }
}
