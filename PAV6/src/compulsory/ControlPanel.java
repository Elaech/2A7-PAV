package compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control Panel contains the specified buttons for save load reset and exit
 * that implement listeners that effectively call methods from the main frame
 * and canvas in order to save, load , reset, and exit.
 */
public class ControlPanel extends JPanel {
    Canvas canvas;
    MainFrame mainFrame;
    public ControlPanel(Canvas c,MainFrame mf){
        canvas = c;
        mainFrame = mf;
        setBackground(Color.GRAY);
        setLayout(new GridLayout(1,6,5,5));
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Button saveButton = new Button("Save");
        Button loadButton = new Button("Load");
        Button resetButton = new Button("Reset");
        Button exitButton = new Button("Exit");
        setPreferredSize(new Dimension(0,50));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.save();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.load();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.reset();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.exit();
            }
        });

        add(saveButton);

        /**
         * Optional Load via file chooser button
         */
        Button loadChooserButton = new Button("Load@Location");
        loadChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.loadChooser();
            }
        });
        add(loadChooserButton);
        /**
         * end of optional
         */
        add(loadButton);

        /**
         * Optional Save via file chooser button
         */
        Button saveChooserButton = new Button("Save@Location");
        saveChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               canvas.saveChooser();
            }
        });
        add(saveChooserButton);


        /**
         * end of optional
         */
        add(resetButton);
        add(exitButton);
    }

}
