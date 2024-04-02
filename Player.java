public class Player {

    private Room currentRoom;

    // Constructor to initialize the player's starting room
    public Player(Room startingRoom) {
      this.currentRoom = startingRoom;
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
}
