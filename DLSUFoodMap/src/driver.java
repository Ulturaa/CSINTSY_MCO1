import java.util.Random;            // For testing, remove later

public class driver {
    public static void main(String[] args){

        // For testing may remove later
        Random rand = new Random();
        char[] alphabet = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        // *** Create the values of the nodes here *** //
        foodSpot[] nodes = new foodSpot[20];

        for(int i=0; i<20; i++){        // Random is temporary, later make fixed values
            nodes[i] = new foodSpot("Resto " + (i+1), alphabet[i], rand.nextInt(50), rand.nextInt(200), rand.nextInt(200));
        }


        new appcontroller(nodes);
    }
}
