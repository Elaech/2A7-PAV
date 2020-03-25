package compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    Canvas canvas;
    MainFrame mainFrame;
    public ControlPanel(Canvas c,MainFrame mf){
        canvas = c;
        mainFrame = mf;
        setBackground(Color.GRAY);
        setLayout(new GridLayout(1,4,5,5));
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
        add(loadButton);
        add(resetButton);
        add(exitButton);
    }

}
