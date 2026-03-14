import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class Main {
    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static int screenWidth = toolkit.getScreenSize().width;
    static int screenHeight = toolkit.getScreenSize().height;

    static int mX; static int mY;

    static JFrame window = new JFrame("You can't close me!");
    static int windowWidth = 400;
    static int windowHeight = 300;

    static JButton closeButton = new JButton();

    public static void main(String[] args) {
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point mousePos = pointerInfo.getLocation();
        mX = (int) mousePos.getX();
        mY = (int) mousePos.getY();

        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setSize(400,300);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("test");
                moveWindow();
            }
        });
        window.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int winMX = e.getX();
                int winMY = e.getY();
                mX = (int) mousePos.getX();
                mY = (int) mousePos.getY();


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
//        window.setBounds(0,0,windowWidth,windowHeight);
    }
}
