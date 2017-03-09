package middleTier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by raoyinchen on 3/5/17.
 */
public class SpendBoard extends JFrame {
    private JTextField activityDateInput;
    private JTextField activityMoneyInput;
    private JTextField activityTypeInput;
    private JTextField activityCommentInput;
    private JLabel activityDate;
    private JLabel activityMoney;
    private JLabel activityType;
    private JLabel activityComment;
    private JMenuBar allTypesBar;
    private JMenu allTypes;
    private JMenuItem food;
    private JMenuItem entertainment;
    private JMenuItem income;
    Container container = getContentPane();
    HashMap<String,String> saveItems = new HashMap<>();
    MainScreen backToMain;

    public SpendBoard(){

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

//        activityDateInput = new JTextField(10);
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        final JFormattedTextField activityDateInput = new JFormattedTextField(format);
        activityMoneyInput = new JTextField(20);
        activityTypeInput = new JTextField(20);
        activityCommentInput = new JTextField(20);
        activityDate = new JLabel("Date('dd/mm/yyyy')");
        activityMoney = new JLabel("Money");
        activityType = new JLabel("Type");
        activityComment = new JLabel("Comment");

//        String[] choices = EntryType.type();
//
//        final JComboBox<String> cb = new JComboBox<String>(choices);
        final JComboBox<EntryType> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(EntryType.values()));

        date.add(activityDate);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;

        panel.add(date, cs);

        date.add(activityDateInput);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;

        panel.add(date, cs);


        money.add(activityMoney);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;

        panel.add(money, cs);
        money.add(activityMoneyInput);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;

        panel.add(money, cs);

        comment.add(activityComment);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;

        panel.add(comment, cs);
        comment.add(activityCommentInput);

        cs.gridx = 2;
        cs.gridy = 1;
        cs.gridwidth = 1;

        panel.add(comment, cs);

        type.add(activityType);
        cs.gridx = 2;
        cs.gridy = 0;
        cs.gridwidth = 1;

        panel.add(type, cs);

        type.add(comboBox);
        comboBox.setVisible(true);
        cs.gridx = 2;
        cs.gridy = 0;
        cs.gridwidth = 1;

        panel.add(type, cs);

        container.add(panel,BorderLayout.CENTER);
        JButton inputButton = new JButton("done & input");

        inputButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Date date = (Date)activityDateInput.getValue();
                EntryType type = (EntryType) comboBox.getSelectedItem();
                Float value = Float.valueOf(activityMoneyInput.getText());
                String description = activityCommentInput.getText();

                Spendy.createNewEntry(date,type,value,description);

                JOptionPane.showMessageDialog(SpendBoard.this,
                         "You have successfully saved the items.",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
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

}