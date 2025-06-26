import java.util.Random;            // For testing, remove later

public class driver {
    public static void main(String[] args) {

        foodSpot[] nodes = new foodSpot[21];

        nodes[0]  = new foodSpot("University Mall",         'A', 1, 980, 150);
        nodes[1]  = new foodSpot("McDonald's",              'B', 1, 1015, 200);
        nodes[2]  = new foodSpot("Perico's",                'C', 1, 1030, 250);
        nodes[3]  = new foodSpot("Bloemen Hall",            'D', 1, 960, 300);
        nodes[4]  = new foodSpot("WH Taft",                 'E', 1, 930, 350);
        nodes[5]  = new foodSpot("EGI Taft",                'F', 1, 900, 390);
        nodes[6]  = new foodSpot("Castro Street",           'G', 1, 870, 420);
        nodes[7]  = new foodSpot("Agno Food Court",         'H', 1, 810, 450);
        nodes[8]  = new foodSpot("One Archers",             'I', 1, 750, 460);
        nodes[9]  = new foodSpot("La Casita",               'J', 1, 700, 440);
        nodes[10] = new foodSpot("Green Mall",              'K', 1, 660, 400);
        nodes[11] = new foodSpot("Green Court",             'L', 1, 630, 350);
        nodes[12] = new foodSpot("Sherwood",                'M', 1, 600, 300);
        nodes[13] = new foodSpot("Jollibee",                'N', 1, 570, 260);
        nodes[14] = new foodSpot("Dagonoy Street",          'O', 1, 530, 230);
        nodes[15] = new foodSpot("Burgundy",                'P', 1, 500, 200);
        nodes[16] = new foodSpot("Estrada Street",          'Q', 1, 470, 170);
        nodes[17] = new foodSpot("D' Student's Place",      'R', 1, 440, 140);
        nodes[18] = new foodSpot("Leon Guinto Street",      'S', 1, 410, 110);
        nodes[19] = new foodSpot("Pablo Ocampo Street",     'T', 1, 380, 90);
        nodes[20] = new foodSpot("Fidel A. Reyes Street",   'U', 1, 350, 70);

        new appcontroller(nodes);
    }
}

