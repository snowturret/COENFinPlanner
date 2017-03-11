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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.util.ArrayList;


public class HistogramGenerator extends ApplicationFrame
{

    public HistogramGenerator( String applicationTitle)
    {
        super( applicationTitle );
//        generateBarChart(entris,type);
    }

    public static ChartPanel generateBarChart(ArrayList<Entry> entries, EntryType type) {

        CategoryDataset dataset = createDataset(entries,type);

        JFreeChart barChart = ChartFactory.createBarChart(
                "money spent during the period",
                "Category",
                "Score", dataset, PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 640 , 480 ) );
        return chartPanel;
    }

    private static CategoryDataset createDataset(ArrayList<Entry> entries, EntryType type) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (type != EntryType.ALL) {
            double sum = 0.0;
            for (Entry e : entries) {
                sum += (double)e.getValue();
            }
            dataset.setValue(sum,"how much has spent",type);
            return dataset;
        } else {
            for (EntryType et : EntryType.values()){
                if(et != EntryType.ALL) {
                    double sum = 0.0;
                    for(Entry entry : entries) {
                        if (entry.getCategory() == et) {
                            sum += (double)entry.getValue();
                        }
                    }
                    dataset.setValue(sum, "how much has spent",et);
                }
            }
            return dataset;
        }

    }


//    public static void main( String[ ] args )
//    {
//        HistogramGenerator chart = new HistogramGenerator("Money Usage Statistics");
//        chart.pack( );
//        RefineryUtilities.centerFrameOnScreen(chart);
//        chart.setVisible( true );
//    }

}
