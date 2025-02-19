package csci.ooad.polymorphia.server.controllers;

import csci.ooad.polymorphia.Polymorphia;
import csci.ooad.polymorphia.Maze;
import csci.ooad.polymorphia.Maze.Room;
import csci.ooad.polymorphia.characters.*;
import csci.ooad.polymorphia.characters.Character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PolymorphiaJsonAdaptor {
    public final String name;
    public final int turn;
    public final boolean inMiddleOfTurn;
    public final boolean gameOver;
    public final String statusMessage;
    public final List<String> livingAdventurers;
    public final List<String> livingCreatures;
    public final List<RoomAdapter> rooms;
    public final List<String> availableCommands;

    HumanStrategy humanStrategy = new HumanStrategy();

    public String getGameName() {
        return name;
    }

    // Constructor for adapting the Polymorphia game state to JSON format
    public PolymorphiaJsonAdaptor(Polymorphia game) {
        this.name = game.getName();
        this.turn = game.getTurnNumber();
        this.inMiddleOfTurn = false; // To be implemented in Polymorphia
        this.gameOver = game.isOver();
        this.statusMessage = "Turn " + game.getTurnNumber() + " just ended.";

        // Get living adventurers
        this.livingAdventurers = game.getLivingAdventurers().stream()
                .map(Adventurer::getName)
                .collect(Collectors.toList());

        // Get living creatures
        this.livingCreatures = game.getLivingCreatures().stream()
                .map(Creature::getName)
                .collect(Collectors.toList());

        // Map rooms to RoomAdapter
        this.rooms = game.getMaze().getRooms().stream()
                .map(RoomAdapter::new)
                .collect(Collectors.toList());

        // Get available commands
        this.availableCommands = generateAvailableCommands(game);
    }

    private List<String> generateAvailableCommands(Polymorphia game) {
        List<String> commandNames = new ArrayList<>();

        for (Adventurer adventurer : game.getLivingAdventurers()) {
            if (adventurer instanceof APIPlayer) {
                Character apiPlayer = (Character) adventurer;

                List<HumanStrategy.CommandOption> commandOptions = humanStrategy.availableCommandOptions(apiPlayer);

                for (HumanStrategy.CommandOption option : commandOptions) {
                    commandNames.add(option.name());
                }
            }
        }

        // Return the list of command names
        return commandNames;
    }


    // Room Adapter to represent room data
    public static class RoomAdapter {
        public final String name;
        public final List<String> neighbors;
        public final List<String> contents;

        public RoomAdapter(Room room) {
            this.name = room.getName();
            this.neighbors = room.getNeighbors().stream()
                    .map(Room::getName)
                    .collect(Collectors.toList());
            this.contents = room.getContents().stream()
                    .map(item -> item.toString())
                    .collect(Collectors.toList());
        }
    }
}
