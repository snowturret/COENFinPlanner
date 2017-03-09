package middleTier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by raoyinchen on 3/5/17.
 */
public class TrackBoard extends JFrame {
    private JTextField activityStartDateInput;
    private JTextField activityEndDateInput;
    private JTextField activityTypeInput;
    private JTextField activityTableInput;
    private JLabel activityStartDate;
    private JLabel activityEndDate;
    private JLabel activityType;
    private JLabel activityTable;
    private JMenuBar allTypesBar;
    private JMenu allTypes;
    private JMenuItem food;
    private JMenuItem entertainment;
    private JMenuItem income;
    Container container = getContentPane();
    HashMap<String,String> saveItems = new HashMap<>();
    MainScreen backToMain;

    public TrackBoard(){

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        JPanel money = new JPanel();
        JPanel date = new JPanel();
        JPanel type = new JPanel();
        JPanel comment = new JPanel();

        money.setLayout(new BoxLayout(money, BoxLayout.Y_AXIS));
        date.setLayout(new BoxLayout(date, BoxLayout.Y_AXIS));
        type.setLayout(new BoxLayout(type, BoxLayout.Y_AXIS));
        comment.setLayout(new BoxLayout(comment, BoxLayout.Y_AXIS));

        activityStartDateInput = new JTextField(10);
        activityEndDateInput = new JTextField(20);
        activityTypeInput = new JTextField(20);
        activityTableInput = new JTextField(20);
        activityStartDate = new JLabel("Start Date('dd/mm/yyyy')");
        activityEndDate = new JLabel("End Date('dd/mm/yyyy')");
        activityType = new JLabel("Type");
        activityTable = new JLabel("TableType");

//        String[] typeChoices = { "entertainment","food", "gas","income","rent","others"};
        String[] typeChoices = EntryType.type();

        final JComboBox<String> cb = new JComboBox<String>(typeChoices);

        String[] tableChoices = { "chart","graph", "x-y"};

        final JComboBox<String> tc = new JComboBox<String>(tableChoices);

        date.add(activityStartDate);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;

        panel.add(date, cs);

        date.add(activityStartDateInput);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;

        panel.add(date, cs);


        money.add(activityEndDate);
        cs.gridx = 2;
        cs.gridy = 0;
        cs.gridwidth = 1;

        panel.add(money, cs);
        money.add(activityEndDateInput);
        cs.gridx = 2;
        cs.gridy = 0;
        cs.gridwidth = 1;

        panel.add(money, cs);


        comment.add(activityTable);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;

        comment.add(tc);
        tc.setVisible(true);

        panel.add(comment, cs);

        comment.add(tc);
        tc.setVisible(true);

        cs.gridx = 2;
        cs.gridy = 1;
        cs.gridwidth = 1;

        panel.add(comment, cs);



        type.add(activityType);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;

        panel.add(type, cs);


        type.add(cb);
        cb.setVisible(true);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;

        panel.add(type, cs);


        allTypesBar = new JMenuBar();
        allTypes = new JMenu("types");
        food = new JMenuItem("food");
        entertainment = new JMenuItem("entertainment");
//
        allTypes.add(food);
        allTypes.add(entertainment);
        allTypesBar.add(allTypes);


        container.add(panel,BorderLayout.CENTER);
        JButton inputButton = new JButton("done & input");

        inputButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                saveItems.put(activityStartDate.getText(),activityStartDateInput.getText());
                saveItems.put(activityTable.getText(),String.valueOf(tc.getSelectedItem()));
                saveItems.put(activityEndDate.getText(),activityEndDateInput.getText());
                saveItems.put(activityType.getText(),String.valueOf(cb.getSelectedItem()));

                JOptionPane.showMessageDialog(TrackBoard.this,
                        "You have successfully saved the items.",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                getSpendInfo();
            }
        });

        JButton backButton = new JButton("go back to main menu");
        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                backToMain = new MainScreen();
                dispose();
            }
        });

        container.add(panel);
        container.add(inputButton,BorderLayout.PAGE_END);
        container.add(backButton,BorderLayout.PAGE_START);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }
    public HashMap<String, String> getSpendInfo() {
        for(String head : saveItems.keySet()) {
            System.out.println(head + ": " + saveItems.get(head));
        }
        return saveItems;

    }


    public static void main(String[] args) {
        TrackBoard track = new TrackBoard();

    }
}
