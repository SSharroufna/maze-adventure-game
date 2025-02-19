package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.Polymorphia;

public class APIPlayer extends Adventurer {

    public APIPlayer(String name) {
        super(name, Character.DEFAULT_INITIAL_HEALTH, new HumanStrategy());
    }

    public APIPlayer(String name, Double initialHealth) {
        super(name, initialHealth, new HumanStrategy());
    }

    public APIPlayer(String name, Double initialHealth, Strategy strategy) {
        super(name, initialHealth, strategy);
    }

    @Override
    public Boolean isAPIPlayer() {
        return true;
    }
}
