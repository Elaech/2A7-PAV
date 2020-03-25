//package compulsory;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
///**
// * class that implements the functionality of the bottom panel
// */
//public class ZeBottomPanel extends JPanel {
//    private int width;
//    private int height;
//    ZeMiddlePanel canvas;
//    ZeFrame zeFrame;
//    public ZeBottomPanel(int width,int height,java.awt.Color color,ZeMiddlePanel zeMiddlePanel,ZeFrame zeFrame) {
//        this.width =width;
//        this.height=height;
//        canvas = zeMiddlePanel;
//        setMinimumSize(new Dimension(width,height));
//        this.zeFrame = zeFrame;
//        setMinimumSize(new Dimension(width,height));
//        setBackground(color);
//
//        Button saveButton = new Button("Save");
//        saveButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                saveImage();
//            }
//        });
//        Button loadButton = new Button("Load");
//        loadButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                loadImage();
//            }
//        });
//        Button resetButton = new Button("Reset");
//        resetButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                resetImage();
//            }
//        });
//        Button exitButton = new Button("Exit");
//        exitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                closeApp();
//            }
//        });
//
//        add(saveButton);
//        add(loadButton);
//        add(resetButton);
//        add(exitButton);
//    }
//
//    private void loadImage() {
//        canvas.load();
//    }
//
//    private void saveImage() {
//        canvas.save();
//    }
//    private void resetImage() {
//        canvas.clear();
//    }
//    private void closeApp(){
//        zeFrame.setVisible(false);
//        zeFrame.dispose();
//    }
//}
