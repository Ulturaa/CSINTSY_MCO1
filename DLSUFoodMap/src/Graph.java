import java.util.*;

public class Graph{ //private?
    private Map<Character, List<Character>> graph = new HashMap<>(); //adjacency list
                  //ex. C -> B
                  //key             value
    private Map<List<Character>, Integer> costM = new HashMap<>(); //tracker for all costs/weights
    private Map<Character, foodSpot> nodes = new HashMap<>(); //tracker for all foodspots

    public Graph(){
        //?
    }
    
    // weighted graph and vertex
    public void addFS(foodSpot fs){ //add nodes
        char nodeID = fs.getID();
        graph.putIfAbsent(nodeID, new ArrayList<>()); //ts puts node to the map/graph
        nodes.put(nodeID, fs); //ts creates node and put to fs list
    }
    
    public void addEdge(char fr, char to, int cost){ //add edges + cost
        if(!graph.containsKey(fr)){
            graph.put(fr, new ArrayList<>());
        }

        if(!graph.containsKey(to)){
            graph.put(to, new ArrayList<>()); //these are like checking if have ts char if none add to vertex
        }
        
        if (!graph.get(fr).contains(to)) {
            graph.get(fr).add(to); //add the edge cost
        }

        costM.put(Arrays.asList(fr,to), cost); //add to cost mapper
    }
    
    public void removeFS(char fs){ //remove node
        graph.remove(fs);

        for(List<Character> n : graph.values()){
            n.removeIf(c -> c == fs); //remove all other edges?
        }

        costM.keySet().removeIf(p -> p.contains(fs)); //remove costs
        nodes.remove(fs); //remove from nodes list
    }
    
    public void removeEdge(char fr, char to){
        if(graph.containsKey(fr)){
            graph.get(fr).remove(Character.valueOf(to));
            //or
            //graph.get(fr).remove((Character) to);   
        }
        
        costM.remove(Arrays.asList(fr,to));
    }

    public int getCost(char fr, char to){
        return costM.getOrDefault(Arrays.asList(fr, to), -1); //finds the cost of fr - to else ret -1
    }


    //printer stuff -- gpted
    public void printGraph() {
        System.out.println("Graph structure:");
        for (char node : graph.keySet()) {
            System.out.print(node + " → " + graph.get(node) + "\n");
        }

        System.out.println("Edge costs:");
        for (Map.Entry<List<Character>, Integer> entry : costM.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}