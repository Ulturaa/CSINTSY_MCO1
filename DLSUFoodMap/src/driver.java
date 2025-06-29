import java.util.ArrayList;

public class driver {
    public static void main(String[] args) {


        // Array is hard coded to 27 since there are only 27 letters in the alphabet
        // Thus, the program can only go up to 27 possible food spots


        ArrayList<foodSpot> nodes = new ArrayList<foodSpot>();

        nodes.add(new foodSpot("University Mall",         'A', 1, 980, 150));
        nodes.add(new foodSpot("McDonald's",              'B', 1, 1015, 200));
        nodes.add(new foodSpot("Perico's",                'C', 1, 1030, 250));
        nodes.add(new foodSpot("Bloemen Hall",            'D', 1, 960, 300));
        nodes.add(new foodSpot("WH Taft",                 'E', 1, 930, 350));
        nodes.add(new foodSpot("EGI Taft",                'F', 1, 900, 390));
        nodes.add(new foodSpot("Castro Street",           'G', 1, 870, 420));
        nodes.add(new foodSpot("Agno Food Court",         'H', 1, 810, 450));
        nodes.add(new foodSpot("One Archers",             'I', 1, 750, 460));
        nodes.add(new foodSpot("La Casita",               'J', 1, 700, 440));
        nodes.add(new foodSpot("Green Mall",              'K', 1, 660, 400));
        nodes.add(new foodSpot("Green Court",             'L', 1, 630, 350));
        nodes.add(new foodSpot("Sherwood",                'M', 1, 600, 300));
        nodes.add(new foodSpot("Jollibee",                'N', 1, 570, 260));
        nodes.add(new foodSpot("Dagonoy Street",          'O', 1, 530, 230));
        nodes.add(new foodSpot("Burgundy",                'P', 1, 500, 200));
        nodes.add(new foodSpot("Estrada Street",          'Q', 1, 470, 170));
        nodes.add(new foodSpot("D' Student's Place",      'R', 1, 440, 140));
        nodes.add(new foodSpot("Leon Guinto Street",      'S', 1, 410, 110));
        nodes.add(new foodSpot("Pablo Ocampo Street",     'T', 1, 380, 90));
        nodes.add(new foodSpot("Fidel A. Reyes Street",   'U', 1, 350, 70));

        new appcontroller(nodes);
    }
}

