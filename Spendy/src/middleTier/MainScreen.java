package middleTier;

/**
 * Created by raoyinchen on 3/5/17.
 */
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by guoyiruan on 3/5/17.
 */
public class MainScreen extends JFrame {
    JButton spending, income, tracking;
    private SpendBoard toSpend;
    private incomeBoard toIncome;
    private TrackBoard toTrack;

    //Constructor
    public MainScreen() {
        createAndShowGUI();
    }

    public void draw(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        pane.setPreferredSize(new Dimension(400,100));

        JLabel greeting = new JLabel("Hi " + Spendy.getCurrentUser().getName() + "!");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 3;
        pane.add(greeting, c);

        spending = new JButton("Add Spending");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        pane.add(spending, c);

        spending.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.print("spend");
                toSpend = new SpendBoard();
                dispose();
            }
        });
        income = new JButton("Add income");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        pane.add(income, c);

        income.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.print("income");
                toIncome = new incomeBoard();
                dispose();
            }
        });

        tracking = new JButton("Tracking");
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 3;
        pane.add(tracking, c);

        tracking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.print("tracking");
                toTrack = new TrackBoard();
                dispose();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Main Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        draw(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}

