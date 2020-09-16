
public class Stopwatch {

    private long start; //tempo attuale in cui parte
    private long delta = 0; //tempo già salvato
    private boolean running = false; //stato

    public void start(){
        if(!running) {
            running = true;
            start = System.nanoTime() - delta;
        }
    }

    public void stop(){
        if(running) {
            delta = System.nanoTime() - start;
            running = false;
        }
    }

    public void reset(){
        running = false;
        delta = 0;
    }

    public long elapsedTime(){
        return running? System.nanoTime() - start : delta;
    }

}

