package roguelike.model.game.elements;

import roguelike.model.Position;

public class StrengthPotion extends Element {
    private final int strengthBonus;
    private final int timeBonus;

    public StrengthPotion(Position position, int strengthBonus, int timeBonus) {
        super(position);
        this.strengthBonus = strengthBonus;
        this.timeBonus = timeBonus;
    }

    public StrengthPotion(int x, int y, int strengthBonus, int timeBonus) {
        super(x, y);
        this.strengthBonus = strengthBonus;
        this.timeBonus = timeBonus;
    }

    public int getStrengthBonus() { return strengthBonus; }
    public int getTimeBonus() { return timeBonus; }
}
