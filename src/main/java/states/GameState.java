package states;

public class GameState extends State<Arena> {
    public GameState(Arena arena) {
        super(arena);
    }

    @Override
    protected com.aor.hero.viewer.Viewer<Arena> getViewer() {
        return new com.aor.hero.viewer.game.GameViewer(getModel());
    }

    @Override
    protected com.aor.hero.controller.Controller<Arena> getController() {
        return new com.aor.hero.controller.game.ArenaController(getModel());
    }
}
