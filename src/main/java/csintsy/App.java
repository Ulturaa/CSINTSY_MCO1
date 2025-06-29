package csintsy;

import csintsy.graphrel.Graph;
import csintsy.graphrel.Node;
import csintsy.pathfinding_algo.UniformCost;
import csintsy.pathfinding_algo.AStar;
import csintsy.gui.appcontroller;
import csintsy.gui.appview;
import csintsy.gui.appmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {


        // Array is hard coded to 27 since there are only 27 letters in the alphabet
        // Thus, the program can only go up to 27 possible food spots


        /*
        ArrayList<Node> nodes = new ArrayList<Node>();

        nodes.add(new Node("University Mall",         'A'));
        nodes.add(new Node("McDonald's",              'B'));
        nodes.add(new Node("Perico's",                'C'));
        nodes.add(new Node("Bloemen Hall",            'D'));
        nodes.add(new Node("WH Taft",                 'E'));
        nodes.add(new Node("EGI Taft",                'F'));
        nodes.add(new Node("Castro Street",           'G'));
        nodes.add(new Node("Agno Food Court",         'H'));
        nodes.add(new Node("One Archers",             'I'));
        nodes.add(new Node("La Casita",               'J'));
        nodes.add(new Node("Green Mall",              'K'));
        nodes.add(new Node("Green Court",             'L'));
        nodes.add(new Node("Sherwood",                'M'));
        nodes.add(new Node("Jollibee",                'N'));
        nodes.add(new Node("Dagonoy Street",          'O'));
        nodes.add(new Node("Burgundy",                'P'));
        nodes.add(new Node("Estrada Street",          'Q'));
        nodes.add(new Node("D' Student's Place",      'R'));
        nodes.add(new Node("Leon Guinto Street",      'S'));
        nodes.add(new Node("Pablo Ocampo Street",     'T'));
        nodes.add(new Node("Fidel A. Reyes Street",   'U'));
        */

        new appcontroller();
    }
}

