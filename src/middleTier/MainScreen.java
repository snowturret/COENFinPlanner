package middleTier;

/**
 * Created by xiwang on 3/5/17.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by guoyiruan on 3/5/17.
 */

/**
 * Once you successfully login, this menu will be shown; there are four operations user could do; add spending, add income
 * track spending and check financial history;
 */
public class MainScreen extends JFrame {
    JButton spending, income, tracking,recommend;
    private SpendBoard toSpend;
    private IncomeBoard toIncome;
    private TrackBoard toTrack;
    private RecommendBoard toRecommend;

    //Constructor
    public MainScreen() throws IOException {

        createAndShowGUI();
    }

    public void draw(Container pane) throws IOException {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        pane.setPreferredSize(new Dimension(500,300));

        JLabel greeting = new JLabel("Hi " + Spendy.getCurrentUser().getName() + "!");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 3;
        pane.add(greeting, c);

        spending = new JButton("Add Spending");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        pane.add(spending, c);

        spending.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.print("spend");
                try {
                    toSpend = new SpendBoard();
                }catch (IOException e1) {
                    e1.printStackTrace();
                }

                dispose();

            }
        });
        income = new JButton("Add income");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        pane.add(income, c);

        income.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.print("income");
                toIncome = new IncomeBoard();
                dispose();
            }
        });

        tracking = new JButton("Tracking");
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        pane.add(tracking, c);

        tracking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.print("tracking");
                toTrack = new TrackBoard();
                dispose();
            }
        });


        recommend = new JButton("Get Financial History");
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 5;
        pane.add(recommend, c);

        recommend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  {
//                System.out.print("tracking");

                try {
                    toRecommend = new RecommendBoard();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }

    private void createAndShowGUI() throws IOException {
        JFrame frame = new JFrame("Main Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        draw(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}

