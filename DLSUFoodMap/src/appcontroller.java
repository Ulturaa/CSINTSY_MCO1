public class appcontroller{

    private appview av;
    private appmodel am;
    private foodSpot[] nodes;

    public appcontroller(foodSpot[] nodes){
        this.av = new appview(nodes);
        this.am = new appmodel();
    }
}