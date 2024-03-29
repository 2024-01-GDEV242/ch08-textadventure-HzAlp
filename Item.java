
/**
 *
 * Represents an item in the game, with a description and weight.
 * @author Alper Hiz
 * @version 3/28/2024
 */
public class Item
{
    private String description;
    private int weight;
    
    /**
     * Creates a new item with the given description and weight.
     * @param description The description of the item.
     * @param weight The weight of the item.
     */
    public Item(String description, int weight) {
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Returns the description of the item.
     * @return The description of the item.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns the weight of the item.
     * @return The weight of the item.
     */
    public int getWeight() {
        return weight;
    }
    
    
}
