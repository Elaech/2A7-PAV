package compulsory;

import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * configuration panel contine 3 butoane si labels pentru acestea sub layout de grid
 * butoanele au functionalitate de a schimba dimensiunea , numarul de laturi si tipul de colorare
 * al figurii ce va urma fi desenate
 */
public class ConfigurationPanel extends JPanel {
    private Integer size = 20;
    private Integer edges = 3;
    private Boolean randomColor = true;
    private boolean rubberset = false;
    private Color rubberColor = Color.WHITE;
    private int shape;
    int indexShape = 1;
    List<String> availableShapes = new ArrayList<>(Arrays.asList(new String[]{"Polygon", "Circle"}));

    public ConfigurationPanel() {
        setBackground(Color.GRAY);
        setLayout(new GridLayout(2, 4, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Button sizeButton = new Button("Size");
        Button edgesButton = new Button("Edges");
        Button colorButton = new Button("Color");
        JLabel sizeLabel = new JLabel(size.toString(), SwingConstants.CENTER);
        JLabel edgesLabel = new JLabel(edges.toString(), SwingConstants.CENTER);
        JLabel colorLabel = new JLabel(randomColor.toString(), SwingConstants.CENTER);
        sizeLabel.setBackground(Color.WHITE);
        edgesLabel.setBackground(Color.WHITE);
        colorLabel.setBackground(Color.WHITE);
        sizeLabel.setOpaque(true);
        edgesLabel.setOpaque(true);
        colorLabel.setOpaque(true);
        setPreferredSize(new Dimension(0, 100));


        sizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int aux = getUserIntInput();
                if (aux >= 20) {
                    size = aux;
                    sizeLabel.setText(size.toString());
                }
            }
        });

        edgesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int aux = getUserIntInput();
                if (aux >= 3) {
                    edges = aux;
                    edgesLabel.setText(edges.toString());
                }
            }
        });
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean aux = getUserColorInput();
                randomColor = aux;
                colorLabel.setText(randomColor.toString());
            }
        });

        add(sizeButton);
        add(edgesButton);
        add(colorButton);


/**
 * Optional - added rubber with color option to delete shapes via dragging
 */
        Button rubberButton = new Button("Rubber Off");
        rubberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rubberset) {
                    rubberset = false;
                    rubberButton.setLabel("Rubber Off");

                } else {
                    rubberset = true;
                    rubberButton.setLabel("Rubber On");
                    rubberColor = JColorChooser.showDialog(null,"Choose color to rub with",Color.WHITE);
                }
            }
        });
        add(rubberButton);
        /**
         * end of the optional
         */

        add(sizeLabel);
        add(edgesLabel);
        add(colorLabel);
/**
 * Optional- option to change shape
 */
        JComboBox shapeButton = new JComboBox(availableShapes.toArray());
        shapeButton.setSelectedIndex(0);
        shapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox aux = (JComboBox) e.getSource();
                shape = availableShapes.indexOf((String) aux.getSelectedItem());
                switch (shape) {
                    case 0: {
                        edgesButton.setVisible(true);
                        edgesLabel.setVisible(true);
                        break;
                    }
                    case 1: {
                        edgesButton.setVisible(false);
                        edgesLabel.setVisible(false);
                        break;
                    }
                    default: {
                        edgesButton.setVisible(true);
                        sizeButton.setVisible(true);
                        colorButton.setVisible(true);
                        edgesLabel.setVisible(true);
                        sizeLabel.setVisible(true);
                        colorLabel.setVisible(true);
                    }
                }
            }
        });
        add(shapeButton);


    }

    public Integer getTheSize() {
        return size;
    }

    public Integer getEdges() {
        return edges;
    }

    public Boolean getRandomColor() {
        return randomColor;
    }

    /**
     * Optional get shape
     */
    public int getShape() {
        return shape;
    }

    /**
     * Optional get rubber + rubber color
     */
    public boolean isRubberset() {
        return rubberset;
    }
    public Color getRubberColor(){
        return rubberColor;
    }

    /**
     * metoda folosita pentru a citit printr-un popup un int
     * in caz de orice inafara de int se re emite pop up ul
     *
     * @return
     */
    public int getUserIntInput() {
        int value = -1;
        boolean ok = false;
        while (!ok) {
            String number = JOptionPane.showInputDialog("Enter the draw size:");
            try {
                if (number == null) {
                    ok = true;
                } else {
                    value = Integer.parseInt(number);
                    ok = true;
                }
            } catch (NumberFormatException ignored) {
            }
        }
        return value;
    }

    /**
     * metoda folosita pentru a specifica intr-un pop up metoda de colorare
     *
     * @return
     */
    private boolean getUserColorInput() {

        int res = JOptionPane.showConfirmDialog(null, "Random color?");
        if (res == JOptionPane.YES_OPTION)
            return true;
        return false;
    }
}
