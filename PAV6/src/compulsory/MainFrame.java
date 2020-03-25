package compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * main frame-ul se ocupa cu aranjarea celor 3 componente in fereastra
 * si editarea ferestrei pentru a fi full screen si a se inchide cand se apasa pe (x) sau alt-f4
 * pozitionarea se face cu ajutorul unui border layout
 */
public class MainFrame extends JFrame {
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int heigth= Toolkit.getDefaultToolkit().getScreenSize().height;
    public void start(){
        setPreferredSize(new Dimension(width,heigth));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ConfigurationPanel configPanel = new ConfigurationPanel();
        Canvas canvas = new Canvas(configPanel);
        ControlPanel controlPanel = new ControlPanel(canvas,this);

        add(configPanel,BorderLayout.NORTH);
        add(canvas,BorderLayout.CENTER);
        add(controlPanel,BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * closes the app windows so the application process might finish
     */
    public void exit(){
        dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
    }
}
