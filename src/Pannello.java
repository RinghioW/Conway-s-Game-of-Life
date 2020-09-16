import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Pannello extends JFrame {

    final private Universe myuni;
    final private UniGUI griglia;
    private boolean playing;
    private JTextPane clock;
    private Stopwatch stopwatch;

    public static void main(String[] args){
        Pannello p = new Pannello(new Universe(50));
    }


    public Pannello(Universe myuni) {
        this.myuni = myuni;
        griglia = new UniGUI(myuni);
        initUI();
    }

    private void initUI() {
        setTitle("GAME OF LIFE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        initGriglia();
        initButtons();
        initClock();
        setExtendedState(MAXIMIZED_BOTH);

    }


    private void initGriglia() {

        griglia.setBounds(400, 20, 650, 650);
        griglia.setLayout(new GridLayout(myuni.getSize(), myuni.getSize(), 0, 0));
        griglia.setVisible(true);
        add(griglia);
    }


    private void initButtons() {

        // TASTO PER GIOCARE

        JButton bPlay = new JButton("PLAY");
        bPlay.addActionListener(e -> {
            if(!playing) {
                playing = true;
                playstate();
            }
        });
        bPlay.setVisible(true);
        bPlay.setBounds(100,100, 100, 50);
        bPlay.setBackground(Color.green);
        add(bPlay);

        // TASTO PER METTERE IN PAUSA

        JButton bPause = new JButton("PAUSE");
        bPause.addActionListener(e -> {
            if(playing) {
                playing = false;
                stopwatch.stop();
                griglia.update();
            }
        });
        bPause.setVisible(true);
        bPause.setBounds(100,300, 100, 50);
        bPause.setBackground(Color.yellow);
        add(bPause);

        //TASTO PER RESETTARE L'UNIVERSO

        JButton bReset = new JButton("RESET");
        bReset.addActionListener(e -> {
            playing = false;
            myuni.reset();
            griglia.update();
            stopwatch.reset();
            clock.setText("00:00:00");
        });
        bReset.setVisible(true);
        bReset.setBounds(100,500, 100, 50);
        add(bReset);

    }

    private void initClock(){
        clock = new JTextPane();
        stopwatch = new Stopwatch();
        clock.setBounds(1100, 300, 60, 20);
        clock.setBorder(BorderFactory.createEtchedBorder());
        clock.setVisible(true);
        clock.setEditable(false);
        clock.setText("00:00:00");
        add(clock);
    }



    private void playstate(){
        ActionListener play = e -> {
            if(!playing){
                ( (Timer) e.getSource() ).stop();
            }
            myuni.nextgen();
            griglia.update();
        };
        Timer t = new Timer(200, play);
        updateClock();
        t.start();
    }



    private void updateClock(){
        long[] time = new long[3];
        ActionListener clocking = e -> {
            if(!playing){
                ( (Timer)e.getSource() ).stop();
            }
            long lap = stopwatch.elapsedTime();
            time[2] = lap/10000000;
            time[1] = (time[2]/100);
            time[0] = (time[1]/60);
            clock.setText(String.format("%02d:%02d:%02d", time[0]%60, time[1]%60, time[2]%100));
        };
        Timer c = new Timer(10, clocking);
        stopwatch.start();
        c.start();

    }








}

