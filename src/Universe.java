public class Universe{

    boolean[][] universe;

    public Universe(int n){
        universe = new boolean[n][n];
    }

    public int getSize(){ return universe.length; }

    public boolean getCella(int i, int j){ return universe[i][j]; }

    private boolean inBound(int i){
        return i >= 0 && i < universe.length;
    }

    public void reset(){
        for (int i = 0; i < universe.length; i += 1)
        {
            for (int j = 0; j < universe[0].length; j += 1)
            {
                universe[i][j] = false;
            }
        }
    }


    public void makeSwitch(int i, int j){
        universe[i][j] = ! universe[i][j];
    }


    public void nextgen() {

        boolean[][] newGen = new boolean[universe.length][universe.length];
        int livNeigh; // numero di caselle vive adiacenti ad ogni casella

        for (int i = 0; i < universe.length; i += 1)
        {
            for (int j = 0; j < universe[0].length; j += 1)
            {
                livNeigh = checkliv(i, j); //controllo caselle adiacenti
                newGen[i][j] = fate(universe[i][j], livNeigh); //decido il destino della cella
            }
        }
        universe = newGen;
    }

    private boolean fate(boolean cell, int livNeigh) { return livNeigh == 3 || (livNeigh == 2 && cell); }

    private int checkliv(int i, int j)
    {
        int count = 0;
        for (int k = i - 1; k <= i + 1; k += 1)
        {
            for (int d = j - 1; d <= j + 1; d += 1)
            {
                if ((k != i || d != j) && inBound(k) && inBound(d) && universe[k][d])
                {
                    count += 1;
                }
            }
        }
        return count;
    }


}
