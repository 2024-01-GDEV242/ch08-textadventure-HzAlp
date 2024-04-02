import java.util.Stack;


/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player; // New field to store the player object

    //private Room previousRoom; // New field to store the previous room
    private Stack<Room> roomHistory;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        roomHistory = new Stack<>();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office,park, cafeteria, library,
        gym, dormitory, courtyard, hallway, classroom1, classroom2, lab2;
        
        
          
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        park = new Room("in the campus park");
        cafeteria = new Room("in the university cafeteria");
        library = new Room("in the university library");
        gym = new Room("in the university gym");
        dormitory = new Room("in the student dormitory");
        courtyard = new Room("in the university courtyard");
        hallway = new Room("in a hallway");
        classroom1 = new Room("in a classroom");
        classroom2 = new Room("in another classroom");
        lab2 = new Room("in another computing lab"); 
            
        
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
    
        theater.setExit("west", outside);
    
        pub.setExit("east", outside);
    
        lab.setExit("north", outside);
        lab.setExit("east", office);
    
        office.setExit("west", lab);
        
        lab.setExit("west", courtyard);
        courtyard.setExit("east", lab);
        courtyard.setExit("north", park);
        courtyard.setExit("south", hallway);
        courtyard.setExit("west", library);
        
        park.setExit("south", courtyard);
        
        hallway.setExit("north", courtyard);
        hallway.setExit("east", gym);
        hallway.setExit("west", cafeteria);
        
        cafeteria.setExit("east", hallway);
        
        gym.setExit("west", hallway);
        
        library.setExit("east", courtyard);
        library.setExit("north", dormitory);
        
        dormitory.setExit("south", library);
        
        lab2.setExit("east", courtyard);
        
        currentRoom = outside;  // start game outside
        // Initialize the player object with a starting room (e.g., outside)
        player = new Player(outside);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    
    /**
     * Handles the "back" command, which moves the player back to the previous room.
     */
    private void back(Command command) {
        int steps = 1;
        
        if (command.hasSecondWord()) {
            try {
                steps = Integer.parseInt(command.getSecondWord());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number of steps.");
                return;
            }
        }
        
        for (int i = 0; i < steps; i++) {
            if (!roomHistory.isEmpty()) {
                currentRoom = roomHistory.pop();
            } else {
                System.out.println("You can't go back any further.");
                break;
            }
        }
        System.out.println(currentRoom.getLongDescription());
        
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case EAT:
                eat();
                break;
                
            case BACK:
                back(command);
                break;   
        }
        return wantToQuit;
    }
    
    /**
     * Handles the "eat" command, which prints a message indicating that the player has eaten.
     */
    private void eat() 
    {
        System.out.println("You have eaten now and you are not hungry anymore.");
    }
    
    /**
     * Displays the long description of the current room.
     */
    private void look() 
    {
        System.out.println(currentRoom.getLongDescription());
    }
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        String direction = command.getSecondWord();

        if (direction == null) {
            System.out.println("Go where?");
            return;
        }

        // Try to leave current room using player object
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            // Update room history before changing currentRoom
            roomHistory.push(player.getCurrentRoom());
            player.moveToRoom(direction); // Call player method to move (step 3 will define this method)
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }


    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
