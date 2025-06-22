public class foodSpot {

    private String name;      // Name of foodspot
    private char id;       // ID (Node id ex. A, B, C)
    private int value;       // Value of node
    private int x;           // X-coordinate
    private int y;           // Y-coordinate
    
    public foodSpot(String name, char id, int value, int x, int y) {
        this.name = name;
        this.id = id;
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public String getName(){
        return this.name;
    }

    public char getID(){
        return this.id;
    }

    public int getValue(){
        return this.value;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
