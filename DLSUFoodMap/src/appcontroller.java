import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class appcontroller{

    private appview av;
    private appmodel am;
    private foodSpot[] nodes;

    public appcontroller(foodSpot[] nodes){
        this.av = new appview(nodes);
        this.am = new appmodel();

        this.av.clrBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                av.clearCB();
            }
        });

        this.av.rstBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                av.resetCB();
            }
        });

        this.av.mapBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Do something here later //
                // For running the algorithm //
            }
        });
    }
}