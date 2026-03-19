import java.awt.*;
import java.awt.event.*;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;


public class Main extends JFrame{

    //System
    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static int screenWidth = toolkit.getScreenSize().width;
    static int screenHeight = toolkit.getScreenSize().height;

    static String operatingSystem = System.getProperty("os.name");


    //Mouse
    public static int mX; public static int mY;


    //Graphics
    static JFrame window = new JFrame("You can't close me!");
    static int windowWidth = 400; static int windowHeight = 300;
    static int windowX = 250; static int windowY = 200;

    static JButton closeButton = new JButton();


    public static void main(String[] args) {
        setOperatingSystemName();


        Timer timer = new Timer();
        TimerTask periodic = new TimerTask() {
            @Override
            public void run() {
                PointerInfo pointerInfo = MouseInfo.getPointerInfo();
                Point mousePos = pointerInfo.getLocation();
                mX = (int) mousePos.getX();
                mY = (int) mousePos.getY();
//                System.out.println(mX + " " + mY);

                windowX = window.getX();
                windowY = window.getY();
//                System.out.println(windowX + " " + windowY);
                checkWindow();

            }
        };
        timer.schedule(periodic,0,50);

        window.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                int newState = e.getNewState();

                if ((newState & Frame.MAXIMIZED_BOTH) != 0 || (e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
                    System.out.println("Edit detected!");
                    window.setState(Frame.NORMAL);
                    moveWindow();
                } else if ((newState & Frame.NORMAL) != 0) {
                    ;
                }
            }
        });



        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setBounds(windowX,windowY,windowWidth,windowHeight);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                System.out.println("test");
                moveWindow();
            }
        });
        window.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int winMX = e.getX() + windowX;
                int winMY = e.getY() + windowY;
//                mX = (int) mousePos.getX();
//                mY = (int) mousePos.getY();

//                System.out.println(mX);
//                System.out.println(windowX + "," + windowY + " ;  " + winMX + "," + winMY);
                if (winMX >= windowX + 5 && winMX <= windowX + 22){
                    System.out.println("aaaaaa");
                }

            }
        });

        window.setVisible(true);

        while (true){

        }
    }

    public static void checkMousePosition(int x, int y){
        System.out.println(x + " " + y);
    }

    public static void moveWindow(){
        int newX = (int)(Math.random() * (screenWidth - windowWidth) );
        int newY = (int)(Math.random() * (screenHeight - windowHeight) );
        window.setBounds(newX,newY,windowWidth,windowHeight);
        windowX = window.getX();
        windowY = window.getY();
    }

    public static void setOperatingSystemName(){
        if (operatingSystem.toLowerCase().contains("windows")){
            operatingSystem = "windows";
        }
        else if (operatingSystem.toLowerCase().contains("mac")){
            operatingSystem = "mac";
        }
        else{
            operatingSystem = "other";
        }
    }

    public static void checkWindow(){
        window.setBounds(windowX,windowY,windowWidth,windowHeight);
    }

}
