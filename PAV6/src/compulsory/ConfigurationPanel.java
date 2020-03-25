package compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * configuration panel contine 3 butoane si labels pentru acestea sub layout de grid
 * butoanele au functionalitate de a schimba dimensiunea , numarul de laturi si tipul de colorare
 * al figurii ce va urma fi desenate
 */
public class ConfigurationPanel extends JPanel {
    private Integer size = 20;
    private Integer edges =3;
    private Boolean randomColor = true;
    public ConfigurationPanel() {
        setBackground(Color.GRAY);
        setLayout(new GridLayout(2,3,5,5));
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Button sizeButton = new Button("Size");
        Button edgesButton = new Button("Edges");
        Button colorButton = new Button("Color");
        JLabel sizeLabel = new JLabel(size.toString(),SwingConstants.CENTER);
        JLabel edgesLabel = new JLabel(edges.toString(),SwingConstants.CENTER);
        JLabel colorLabel = new JLabel(randomColor.toString(),SwingConstants.CENTER);
        sizeLabel.setBackground(Color.WHITE);
        edgesLabel.setBackground(Color.WHITE);
        colorLabel.setBackground(Color.WHITE);
        sizeLabel.setOpaque(true);
        edgesLabel.setOpaque(true);
        colorLabel.setOpaque(true);
        setPreferredSize(new Dimension(0,100));


        sizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int aux = getUserIntInput();
                if(aux>=20){
                    size=aux;
                    sizeLabel.setText(size.toString());
                }
            }
        });

        edgesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int aux = getUserIntInput();
                if(aux>=3){
                    edges=aux;
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
        add(sizeLabel);
        add(edgesLabel);
        add(colorLabel);
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
     * metoda folosita pentru a citit printr-un popup un int
     * in caz de orice inafara de int se re emite pop up ul
     * @return
     */
    public int getUserIntInput(){
        int value = -1;
        boolean ok = false;
        while(!ok) {
            String number = JOptionPane.showInputDialog("Enter the draw size:");
            try {
                if(number == null) {
                    ok = true;
                }
                else {
                    value = Integer.parseInt(number);
                    ok = true;
                }
            } catch (NumberFormatException ignored) { }
        }
        return value;
    }

    /**
     * metoda folosita pentru a specifica intr-un pop up metoda de colorare
     * @return
     */
    private boolean getUserColorInput() {

        int res= JOptionPane.showConfirmDialog(null,"Random color?");
        if(res== JOptionPane.YES_OPTION)
            return true;
        return false;
    }
}
