package middleTier;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by raoyinchen on 3/5/17.
 */
public class TrackBoard extends JFrame {
    private JFormattedTextField activityStartDateInput;
    private JFormattedTextField activityEndDateInput;
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
    public static String displayType = "All";
    public static String displayGraph = "x-y";

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

        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        activityStartDateInput = new JFormattedTextField(format);
        activityEndDateInput = new JFormattedTextField(format);
        activityTypeInput = new JTextField(20);
        activityTableInput = new JTextField(20);
        activityStartDate = new JLabel("Start Date('dd/mm/yyyy')");
        activityEndDate = new JLabel("End Date('dd/mm/yyyy')");
        activityType = new JLabel("Type");
        activityTable = new JLabel("TableType");

        final JComboBox<EntryType> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(EntryType.values()));

        String[] tableChoices = { "x-y","bar-chart"};//type of charts
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

        type.add(comboBox);
        comboBox.setVisible(true);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;

        panel.add(type, cs);


        allTypesBar = new JMenuBar();
        allTypes = new JMenu("types");
        food = new JMenuItem("food");
        entertainment = new JMenuItem("entertainment");

        allTypes.add(food);
        allTypes.add(entertainment);
        allTypesBar.add(allTypes);


        container.add(panel,BorderLayout.CENTER);
        JButton inputButton = new JButton("done & input");

        inputButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Date startDate = (Date)activityStartDateInput.getValue();
                Date endDate = (Date)activityEndDateInput.getValue();
                EntryType type = (EntryType) comboBox.getSelectedItem();
                String graph = String.valueOf(tc.getSelectedItem());

                ArrayList<Entry> results = Spendy.trackingResults(startDate,endDate,type);

                JOptionPane.showMessageDialog(TrackBoard.this,
                        "Graph generated!",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
//                displayType = String.valueOf(type);
                displayGraph = String.valueOf(tc.getSelectedItem());
                ChartPanel chart;
                if(displayGraph.equals("x-y")) {
                    chart = (ChartPanel)XYGenerator.createChartPanel(results, type);
                } else  {
                    chart = (ChartPanel)HistogramGenerator.generateBarChart(results, type);
                };

                JPanel chartPanel = new JPanel();
                chartPanel.add(chart);
                chartPanel.validate();
                container.add(chartPanel,BorderLayout.AFTER_LINE_ENDS);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                pack();
                setResizable(false);
                setVisible(true);
//                getTrackInfo();
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

//    public HashMap<String, String> getTrackInfo() {//where we can get/see the how we want to track: cgart type, time frame, etc
//        for(String head : saveItems.keySet()) {
//            System.out.println(head + ": " + saveItems.get(head));
//        }
//        return saveItems;
//
//    }
//    public String getDisplayType() {
//        return displayType;
//    }

//    public static void main(String[] args) {
//        TrackBoard track = new TrackBoard();
//    }
}
