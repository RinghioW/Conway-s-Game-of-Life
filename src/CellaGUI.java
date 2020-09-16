import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellaGUI extends JPanel {

    private Universe myuni;
    private int i;
    private int j;

    public CellaGUI(Universe myuni, int i, int j) {
        super();
        this.myuni = myuni;
        this.i = i;
        this.j = j;
        update();
        setBorder(BorderFactory.createBevelBorder(0));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                myuni.makeSwitch(i, j);
                update();
            }
        });

    }

    public void update(){
        if(myuni.getCella(i, j)) {
            setBackground(Color.blue);
        }
        else {
            setBackground(Color.lightGray);
        }
    }




}