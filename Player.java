import java.util.ArrayList;
import java.util.List;
/**
 * Represents the player in the game. The player can move between rooms, pick up and
 * drop items, and interact with the game world.
 */
public class Player {

    private Room currentRoom;
    private List<Item> inventory; // List to store items in the player's inventory

    /**
     * Constructor for creating a player with a starting room.
     * @param startingRoom The room where the player starts.
     */
    public Player(Room startingRoom) {
      this.currentRoom = startingRoom;
      this.inventory = new ArrayList<>();
      
    }

    // Getter method to access the player's current room
    public Room getCurrentRoom() {
      return currentRoom;
    }
    // (I will add more methods to the Player class in future steps)

    // New method to handle movement based on direction
    public void moveToRoom(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {

            currentRoom = nextRoom;
        }
    }
    
 
    
    /**
     * Drops an item from the player's inventory and adds it to the current room.
     * @param itemName The name of the item to drop.
     */
    public void dropItem(String itemName) {
        Item item = getItem(itemName);
        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
            System.out.println("You dropped the " + itemName + ".");
        } else {
            System.out.println("You don't have a " + itemName + " to drop.");
        }
    }
    
    /**
     * Retrieves an item from the player's inventory.
     * @param itemName The name of the item to retrieve.
     * @return The item if found, null otherwise.
     */
    private Item getItem(String itemName) {
        for (Item item : inventory) {
            if (item.getDescription().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
}
