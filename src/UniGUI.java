import javax.swing.*;

public class UniGUI extends JPanel {

    private Universe myuni;
    private CellaGUI[][] cells;


    public UniGUI(Universe myuni){
        this.myuni = myuni;
        initCells();
        fill();
    }

    private void fill(){
        for (int i = 0; i < myuni.getSize(); i+=1) {
            for(int j=0; j<myuni.getSize(); j+=1) {
                add(cells[i][j]);
            }
        }
    }

    private void initCells(){
        cells = new CellaGUI[myuni.getSize()][myuni.getSize()];
        for(int i = 0; i< myuni.getSize(); i+=1){
            for(int j = 0; j< myuni.getSize(); j+=1){
                cells[i][j] = new CellaGUI(myuni, i, j);
            }
        }
    }

    public void update(){
        for(int i = 0; i< myuni.getSize(); i+=1){
            for(int j = 0; j< myuni.getSize(); j+=1){
                cells[i][j].update();
            }
        }
    }

}
