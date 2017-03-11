package middleTier;

/**
 * Created by guoyiruan on 3/9/17.
 */
/**
 * Created by raoyinchen on 3/7/17.
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import javax.swing.*;
import java.util.ArrayList;

public class XYGenerator extends JFrame {

    public XYGenerator() {
        super("XY Line Chart Example with JFreechart");

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static JPanel createChartPanel(ArrayList<Entry> entries, EntryType type) {
        String chartTitle = "Objects Movement Chart";
        String xAxisLabel = "X";
        String yAxisLabel = "Y";

        XYDataset dataset = createDataset(entries,type);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle, xAxisLabel, yAxisLabel, dataset,true,true,false);

        return new ChartPanel(chart);
    }


    public static XYDataset createDataset(ArrayList<Entry> entries , EntryType type) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries trackingEntries = new TimeSeries(type.toString(), Day.class);

        if (type != EntryType.ALL) {
            for(int i = 0; i < entries.size(); i++) {
                trackingEntries.add(new Day(entries.get(i).getEntryDate()),entries.get(i).getValue());
            }
            dataset.addSeries(trackingEntries);
            return dataset;
        } else {
            for (EntryType et : EntryType.values()){
                if(et != EntryType.ALL) {
                    TimeSeries ts = new TimeSeries(et.toString(), Day.class);
                    for(Entry entry : entries) {
                        if (entry.getCategory() == et) {
                            ts.add(new Day(entry.getEntryDate()),entry.getValue());
                        }
                    }
                    dataset.addSeries(ts);
                }
            }
            return dataset;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new XYGenerator().setVisible(true);
            }
        });

    }
}
