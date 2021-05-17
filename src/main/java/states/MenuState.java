package states;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected com.aor.hero.viewer.Viewer<Menu> getViewer() {
        return new com.aor.hero.viewer.menu.MenuViewer(getModel());
    }

    @Override
    protected com.aor.hero.controller.Controller<Menu> getController() {
        return new com.aor.hero.controller.menu.MenuController(getModel());
    }
}
