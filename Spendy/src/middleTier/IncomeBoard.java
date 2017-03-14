package middleTier;
/**
 * Created by raoyinchen on 3/5/17.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by raoyinchen on 3/5/17.
 */
public class IncomeBoard extends JFrame {
    private JFormattedTextField activityDateInput;
    private JTextField activityMoneyInput;
    private JTextField activityTypeInput;
    private JTextField activityCommentInput;
    private JLabel activityDate;
    private JLabel activityMoney;
    private JLabel activityType;
    private JLabel activityComment;
    Container container = getContentPane();
//    HashMap<String,String> saveItems = new HashMap<>();
    MainScreen backToMain;

    public IncomeBoard(){

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

        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        activityDateInput = new JFormattedTextField(format);
        activityMoneyInput = new JTextField(20);
        activityTypeInput = new JTextField(20);
        activityCommentInput = new JTextField(20);
        activityDate = new JLabel("Date('dd/mm/yyyy')");
        activityMoney = new JLabel("Money");
        activityType = new JLabel("Type");
        activityComment = new JLabel("Comment");

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

        container.add(panel,BorderLayout.CENTER);
        JButton inputButton = new JButton("done & input");

        inputButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try{
                    Float.valueOf(activityMoneyInput.getText());
                }catch(NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(IncomeBoard.this,
                            "Please enter a valid amount",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                if(activityDateInput.getText() == null || activityDateInput.getText().length() == 0 || !activityDateInput.isEditValid()) {
                    JOptionPane.showMessageDialog(IncomeBoard.this,
                            "Please enter a valid date",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }else {
                    Date date = (Date)activityDateInput.getValue();
                    Float value = Float.valueOf(activityMoneyInput.getText());
                    String description = activityCommentInput.getText();

                    Spendy.createNewEntry(date,EntryType.INCOME,value,description);

                    JOptionPane.showMessageDialog(IncomeBoard.this,
                            "You have successfully saved the items.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
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
        setLocationRelativeTo(null);
    }

}