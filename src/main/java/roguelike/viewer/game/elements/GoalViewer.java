package roguelike.viewer.game.elements;

import roguelike.gui.GUI;
import roguelike.model.game.elements.Goal;

public class GoalViewer implements ElementViewer<Goal>{
    @Override
    public void draw(Goal goal, GUI gui) { gui.drawGoal(goal.getPosition()); }
}
