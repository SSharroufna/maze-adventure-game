package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.Armor;
import csci.ooad.polymorphia.ArtifactFactory;
import csci.ooad.polymorphia.Maze;
import csci.ooad.polymorphia.NoSuchRoomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArmorTest {
    final CharacterFactory characterFactory = new CharacterFactory();
    final ArtifactFactory artifactFactory = new ArtifactFactory();

    @Test
    void testFindingArmorAndFighting() throws NoSuchRoomException {
        // If a character picks up Armor, then he is more resistant to damage.
        // If, in a fight, if he loses the roll, this damage will be one less than normal.

        // Arrange - put creature in room with adventurer
        Adventurer bilbo = characterFactory.createAdventurer("Bilbo");
        Creature ogre = characterFactory.createCreature("Ogre");
        Armor bronzeArmor = artifactFactory.createArmorSuit("Bronze");
        Armor steelArmor = artifactFactory.createArmorSuit("Steel");

        Maze maze = Maze.getNewBuilder()
                .createFullyConnectedRooms("Adventurer Room", "Another Armor Room", "Ogre Room")
                .addToRoom("Adventurer Room", bilbo)
                .addToRoom("Adventurer Room", bronzeArmor)
                .addToRoom("Another Armor Room", steelArmor)
                .addToRoom("Ogre Room", ogre)
                .build();

        Command wearArmor = new WearCommand(bilbo, bronzeArmor);
        wearArmor.execute();

        // Get the armored adventurer
        assertEquals(1, maze.getLivingAdventurers().size());
        Adventurer armoredBilbo = maze.getLivingAdventurers().getFirst();

        System.out.println("armoredBilbo: " + armoredBilbo);
        assertTrue(armoredBilbo.toString().contains("Armor"));

        Maze.Room armorRoom = maze.getRoom("Another Armor Room");
        Command moveCommand = new MoveCommand(armoredBilbo, armorRoom);
        moveCommand.execute();
        assertEquals(1, maze.getLivingAdventurers().size());

        Command wearSecondArmor = new WearCommand(armoredBilbo, steelArmor);
        wearSecondArmor.execute();
        assertEquals(1, maze.getLivingAdventurers().size());

        Adventurer doublyArmoredBilbo = maze.getLivingAdventurers().getFirst();
        System.out.println("doublyArmoredBilbo: " + doublyArmoredBilbo);

        Maze.Room ogreRoom = maze.getRoom("Ogre Room");
        ogreRoom.enter(doublyArmoredBilbo);

        // Now fight the Ogre with two sets of Armor
        Command fightCommand = new FightCommand(doublyArmoredBilbo, ogre);
        fightCommand.execute();
    }

}
