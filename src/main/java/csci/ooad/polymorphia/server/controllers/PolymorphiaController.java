package csci.ooad.polymorphia.server.controllers;

import csci.ooad.polymorphia.Maze;
import csci.ooad.polymorphia.Polymorphia;
import csci.ooad.polymorphia.characters.APIPlayer;
import csci.ooad.polymorphia.characters.Adventurer;
import csci.ooad.polymorphia.characters.Character;
import csci.ooad.polymorphia.characters.Command;
import csci.ooad.polymorphia.characters.HumanStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PolymorphiaController {
    private static final Logger logger = LoggerFactory.getLogger(PolymorphiaController.class);

    private Map<String, Polymorphia> games = new HashMap<>();

    public PolymorphiaController() {
        // TODO: Create a default game here
        createDefaultGame();
    }

    private void createDefaultGame() {
        Maze twoByTwoMaze = Maze.getNewBuilder()
                .create2x2Grid()
                .createAndAddAdventurers("Frodo")
                .createAndAddAPIPlayer("API Player")
                .createAndAddCreatures("Ogre")
                .createAndAddFoodItems("Cookie")
                .build();
        Polymorphia defaultGame = new Polymorphia(twoByTwoMaze);

        games.put("default Game", defaultGame);
    }

    @GetMapping("/api/games")
    public ResponseEntity<?> getGames() {
        List<String> gameIds = new ArrayList<>(games.keySet());
        return new ResponseEntity<>(gameIds, HttpStatus.OK);
    }

    @GetMapping("/api/game/{gameId}")
    public ResponseEntity<?> getGame(@PathVariable(name = "gameId", required = false) String gameId) {
        Polymorphia game = games.get(gameId);
        if (game == null) {
            return new ResponseEntity<>("Game not found!", HttpStatus.NOT_FOUND);
        }
        PolymorphiaJsonAdaptor gameState = new PolymorphiaJsonAdaptor(game);
       return new ResponseEntity<>(gameState, HttpStatus.OK);
    }

    @PostMapping("/api/game/create")
    public ResponseEntity<?> createGame(@Validated @RequestBody PolymorphiaParameters params) {

        try {
            // Build the Maze with all the parameters from PolymorphiaParameters
            Maze maze = Maze.getNewBuilder()
                    .createFullyConnectedRooms(params.numRooms())
                    .createAndAddAPIPlayer(params.playerName())
                    .createAndAddAdventurers(params.numAdventurers())
                    .createAndAddCreatures(params.numCreatures())
                    .createAndAddKnights(params.numKnights())
                    .createAndAddCowards(params.numCowards())
                    .createAndAddGluttons(params.numGluttons())
                    .createAndAddDemons(params.numDemons())
                    .createAndAddFoodItems(params.numFood())
                    .createAndAddArmor(params.numArmor())
                    .build();

            // Create the Polymorphia
            Polymorphia game = new Polymorphia(params.name(), maze);

            // Store the game in the map
            games.put(params.name(), game);

            // Use PolymorphiaJsonAdaptor to convert the game to JSON format
            PolymorphiaJsonAdaptor gameState = new PolymorphiaJsonAdaptor(game);

            // Return the game state as a response
            return new ResponseEntity<>(gameState, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create game", HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @PutMapping("/api/game/{gameId}/playTurn/{command}")
    public ResponseEntity<?> playTurn(@PathVariable(name = "gameId") String gameId, @PathVariable(name = "command") String command) {
        Polymorphia game = games.get(gameId);

        if (game == null) {
            return new ResponseEntity<>("Game not found!", HttpStatus.NOT_FOUND);
        }

        // Check if the game has an APIPlayer
        if (game.getApiPlayer() != null) {

           //game.playTurnWithApiPlayer(apiPlayer, choice);
            //game.play();

            PolymorphiaJsonAdaptor gameState = new PolymorphiaJsonAdaptor(game);
            return new ResponseEntity<>(gameState, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No API player found in the game", HttpStatus.BAD_REQUEST);
        }
    }
}