package middleTier;

/**
 * Created by guoyiruan on 3/9/17.
 */
/**
 * Created by xiwang on 3/7/17.
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

import static oracle.jrockit.jfr.events.Bits.floatValue;

/**
 * generate XY chart. for this part; because each entry is a point; so it needs at least two entries in the same category,
 * to be a line; otherwise the point will not be shown in the graph; because XY chart is used for display the trend of
 * the spending; but user could use bar chart or pie chart to display it.
 */
public class XYGenerator extends JFrame {

    public XYGenerator() {
        super("XY Line Chart Example with JFreechart");

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static JPanel createChartPanel(ArrayList<Entry> entries, EntryType type) {
        String chartTitle = "Objects Movement Chart";
        String xAxisLabel = "Category";
        String yAxisLabel = "Spent";

        XYDataset dataset = createDataset(entries,type);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle, xAxisLabel, yAxisLabel, dataset,true,true,false);

        return new ChartPanel(chart);
    }


    public static XYDataset createDataset(ArrayList<Entry> entries , EntryType type) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries trackingEntries = new TimeSeries(type.toString(), Day.class);

        if (type != EntryType.ALL) {
            for(int i = 0; i < entries.size(); i++) {
                Day day = new Day(entries.get(i).getEntryDate());
                trackingEntries.addOrUpdate(day, floatValue(trackingEntries.getValue(day)) + entries.get(i).getValue());
            }

            dataset.addSeries(trackingEntries);
            return dataset;
        } else {
            for (EntryType et : EntryType.values()){
                if(et != EntryType.ALL) {
                    TimeSeries ts = new TimeSeries(et.toString(), Day.class);
                    for(Entry entry : entries) {
                        if (entry.getCategory() == et) {
                            Day day = new Day(entry.getEntryDate());
                            ts.addOrUpdate(day,floatValue(ts.getValue(day)) + entry.getValue());
                        }
                    }
                    dataset.addSeries(ts);
                }
            }
            return dataset;
        }
    }

}
