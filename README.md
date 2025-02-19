# Maze Adventurer 9:

## Game Descriptions 

This is a maze based game. Characters are randomly placed on each room. We have adeventurers( Human, Knight, Gluton, Coward ) and creatures ( Dragons, Creatures ) . Each have different health, power, characteris. In each room, there might be food or Armour. Food can be eaten to increase health or defense level. 

## OOAD 

1- Polymorphism: Allows different types of characters (e.g., Knight, Dragon) to share behaviors through a common interface, but each can implement its behavior differently (e.g., attack or defense).

2- Inheritance: Common attributes and actions (e.g., health, power, attack) are inherited from a base class, simplifying the addition of new character or creature types.

3- Encapsulation: Objects are self-contained, with their own properties and methods, ensuring clean game logic and reducing complexity.

4- Abstraction: Hides complex processes (like maze generation or combat mechanics) behind simple interfaces, making it easier for players to focus on gamepl


## Design Patterns & Principles

This project leverages a variety of Object-Oriented Design Patterns to create a flexible and scalable system. Below are the key patterns used:

1. Factory Pattern:
The ArtifactFactory and CharacterFactory classes are responsible for creating objects such as characters (e.g., Knight, Adventurer) and items (e.g., Food, Armor).

3. Singleton Pattern:
The Dice class uses the Singleton pattern to ensure there is a single instance of the die-rolling mechanism.

5. Builder Pattern
The MazeBuilder class allows us to construct a maze with different room configurations step by step. Similarly, it is used to create complex character objects.

7. Strategy Pattern
Different strategies for character behavior (e.g., aggressive or defensive combat strategy) are encapsulated in strategy objects. This allows characters to switch their behavior based on the situation during gameplay.

9. Decorator Pattern
The ArmorDecorator allows for the addition of armor to characters at runtime. Instead of modifying the core character class, we decorate the character with additional attributes (like increased defense).

11. Observer Pattern
The Observer pattern is used to update the game state on the web service or UI when certain events (such as health changes or room progress) happen. This keeps the system's state in sync.


## Running the app

The app can be run either through the terminal or as a web service.

Steps to Run: Clone project, Start the Service, Visit the base URL http://localhost:9090 in your browser.

Example:
It should look like this:

![Screenshot 2025-02-19 at 2 23 26 AM](https://github.com/user-attachments/assets/dffb9ed0-164c-4259-a1d1-43d0f3bfbd6e)


## Testing 

All classes, methods, and characters are thoroughly tested using JUnit 5 to ensure expected behavior across the game. Following a Test-Driven Development (TDD) approach, every class, method, and character is tested for correctness, providing comprehensive coverage of the game's functionality.

![Screenshot 2025-02-19 at 2 53 34 AM](https://github.com/user-attachments/assets/e60637c4-e793-4304-94d8-b61471a86185)


## Endpoints 

Example request:

```json
{
  "name": "MyGame",
  "playerName": "Professor",
  "numRooms": 7,
  "numAdventurers": 2,
  "numCreatures": 2,
  "numKnights": 1,
  "numCowards": 1,
  "numGluttons": 1,
  "numDemons": 2,
  "numFood": 10,
  "numArmor": 3
}
```

Example response:

```json
{
  "name": "MyGame",
  "turn": 0,
  "inMiddleOfTurn": false,
  "gameOver": false,
  "statusMessage": "Turn 0 just ended.",
  "livingAdventurers": [
    "Arwen",
    "Professor",
    "Lady Brienne",
    "Sir Eats-a-lot",
    "Sir Lancelot",
    "Frodo",
    "Sir Robin"
  ],
  "livingCreatures": [
    "Ogre",
    "Beelzebub",
    "Satan",
    "Dragon"
  ],
  "rooms": [
    {
      "name": "Goblin's Fountain",
      "neighbors": [
        "Pool of Lava",
        "Troll Bridge"
      ],
      "contents": [
        "Lady Brienne(health: 8.00)",
        "Dragon(health: 3.00)",
        "eggs(1.9)",
        "plate-Armor"
      ]
    },
    {
      "name": "Pit of Despair",
      "neighbors": [
        "Stalactite Cave",
        "Troll Bridge"
      ],
      "contents": [
        "Frodo(health: 5.00)",
        "Sir Robin(health: 5.00)",
        "fries(2.0)",
        "pizza(1.4)"
      ]
    },
    {
      "name": "Pool of Lava",
      "neighbors": [
        "Goblin's Fountain",
        "Dungeon",
        "Stalactite Cave"
      ],
      "contents": [
        "Ogre(health: 3.00)",
        "Beelzebub(health: 15.00)"
      ]
    },
    {
      "name": "Dungeon",
      "neighbors": [
        "Pool of Lava",
        "Dragon's Den"
      ],
      "contents": [
        "cupcake(1.7)",
        "banana(1.7)"
      ]
    },
    {
      "name": "Dragon's Den",
      "neighbors": [
        "Dungeon",
        "Stalactite Cave"
      ],
      "contents": [
        "Sir Lancelot(health: 8.00)",
        "Sir Eats-a-lot(health: 5.00)",
        "salad(1.6)"
      ]
    },
    {
      "name": "Stalactite Cave",
      "neighbors": [
        "Pit of Despair",
        "Pool of Lava",
        "Dragon's Den"
      ],
      "contents": [
        "Arwen(health: 5.00)",
        "Satan(health: 15.00)",
        "Professor(health: 8.00)",
        "burger(1.4)",
        "chainmail-Armor"
      ]
    },
    {
      "name": "Troll Bridge",
      "neighbors": [
        "Goblin's Fountain",
        "Pit of Despair"
      ],
      "contents": [
        "apple(1.6)",
        "steak(1.8)",
        "bacon(1.2)",
        "leather-Armor"
      ]
    }
  ],
  "availableCommands": []
}
```

