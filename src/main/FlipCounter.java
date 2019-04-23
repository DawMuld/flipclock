/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.elemental.RenderPanel;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author daan-
 */
public class FlipCounter {

    public static final int DEFAULT_VALUE = 9035884;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int value;
        if (args != null && args.length >= 1) {
            try {
                value = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                value = DEFAULT_VALUE;
                System.out.println("Not able to intertret " + args[0]);
            }
        } else {
            value = DEFAULT_VALUE;
        }
        JFrame frame = new JFrame();
        frame.setExtendedState(6);
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().setLayout(new BorderLayout());
        RenderPanel panel = new RenderPanel(value);
        frame.getContentPane().add(panel);
        frame.validate();
        panel.setFramePeriod(24);
        frame.setVisible(true);
        panel.startPanel();
    }

}
