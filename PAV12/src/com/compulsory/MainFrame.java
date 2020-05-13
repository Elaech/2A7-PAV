package com.compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MainFrame extends JFrame {
    Dimension frameDimension;
    ControlPanel controlPanel;
    DesignPanel designPanel;
    JButton instantiateButton;
    JTextField classInput;
    JTextField objectNameInput;
    public MainFrame(){
        frameDimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLayout(new BorderLayout());
        controlPanel = new ControlPanel(frameDimension);
        designPanel = new DesignPanel(frameDimension);
        setPreferredSize(frameDimension);
        //Creating the 3 input fields
        instantiateButton = new JButton("Instantiate");
        instantiateButton.setFont(new Font("Arial", Font.BOLD,20));


        classInput = new JTextField(50);
        classInput.setFont(new Font("Arial", Font.BOLD,20));

        objectNameInput = new JTextField(50);
        objectNameInput.setFont(new Font("Arial", Font.BOLD,20));



        //adding Labels for the inputs
        JLabel labelClass = new JLabel("Classname");
        labelClass.setHorizontalTextPosition(SwingConstants.CENTER);
        labelClass.setHorizontalAlignment(SwingConstants.CENTER);
        controlPanel.add(labelClass);
        JLabel labelObjectName = new JLabel("Object name");
        labelObjectName.setHorizontalAlignment(SwingConstants.CENTER);
        controlPanel.add(labelObjectName);
        JLabel labelButton = new JLabel("Create Object");
        labelButton.setHorizontalAlignment(SwingConstants.CENTER);
        controlPanel.add(labelButton);

        //adding the input fields
        controlPanel.add(classInput);
        controlPanel.add(objectNameInput);
        controlPanel.add(instantiateButton);

        //creating the design panel
        add(controlPanel,BorderLayout.NORTH);
        add(designPanel,BorderLayout.CENTER);
        setTitle("PAV11");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(frameDimension);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //The Instantiate action and adding the element to the DesignPanel
        instantiateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                try {
                    //The case in which the component accepts a constructor with string as parameter
                    Class randomClass = Class.forName(classInput.getText());
                    Class[] signature = new Class[] {String.class};
                    Constructor constructor = randomClass.getConstructor(signature);
                    Component component = (Component) constructor.newInstance(objectNameInput.getText());
                    component.setVisible(true);
                    designPanel.add(component);
                    designPanel.setVisible(true);
                    setVisible(true);
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                    //The case in which the Component does not accept such constructor with a string
                } catch (NoSuchMethodException e){
                    try {
                        Class randomClass = Class.forName(classInput.getText());
                        Component component = (Component) randomClass.newInstance();
                        component.setVisible(true);
                        designPanel.add(component);
                        designPanel.setVisible(true);
                        setVisible(true);
                    } catch (IllegalAccessException | InstantiationException | ClassNotFoundException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                }
            }
        });

        //Showing the MainFrame
        pack();
        setVisible(true);
    }

}
