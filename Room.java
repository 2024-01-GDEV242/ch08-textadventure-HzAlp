import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private List<Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();

    }
    
    /**
     * Adds an item to the room.
     * @param item The item to add to the room.
     */
    public void addItem(Item item) {
        items.add(item);
    }
    
    /**
     * Removes an item to the room.
     * @param item The item to remove to the room.
     */
        public void removeItem(Item item) {
        items.remove(item);
    }
    
    /**
     * Sets the item in the room.
     * @param item The item to set in the room.
     */
    //public void setItem(Item item) {
    //    this.item = item;
    //}
    /*  Since I've modified the Room to allow multiple items,
    *   I don't need the setItem method anymore because it was
    *   used to set a single item in the room.
    */
    
    /**
     * Gets the item in the room.
     * @return The item in the room.
     */
    public List<Item> getItems() {
        return items;
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Returns a description of the room, including the item if it exists.
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription() {
        String desc = "You are " + description + ".\n" + getExitString();
        if (!items.isEmpty()) {
            desc += "\nItems:";
            for (Item item : items) {
                desc += "\n- " + item.getDescription();
            }
        }
        return desc;
    }


    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Returns a description of the room.
     * @return A description of the room.
     */
    public String getRoomDescription()
    {
        return description;
    }
}

