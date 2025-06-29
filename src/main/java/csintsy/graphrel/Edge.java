package csintsy.graphrel;

/**
 * Edge
 */
public class Edge {
    int destUid;
    int weight;

    public Edge(int destUid, int weight) {
        this.destUid = destUid;
        this.weight = weight;
    }

    public int getdestUid() {
        return this.destUid;
    }

    public int getWeight() {
        return this.weight;
    }


}
