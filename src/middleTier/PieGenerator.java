package middleTier;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.util.ArrayList;

/**
 * generate a pie chart; it will show the percentage of each category for particular time period.
 */
public class PieGenerator extends ApplicationFrame
{
    public PieGenerator( String title )
    {
        super( title );
        //setContentPane(createDemoPanel( entries, EntryType type  ));
    }
    private static PieDataset createDataset(ArrayList<Entry> entries, EntryType type)
    {
        DefaultPieDataset dataset = new DefaultPieDataset();
        if (type != EntryType.ALL) {
            double sum = 0.0;
            for (Entry e : entries) {
                sum += (double)e.getValue();
                dataset.setValue(e.toString(),sum);
            }
            return dataset;
        } else {
            for (EntryType et : EntryType.values()){
                if(et != EntryType.ALL) {
                    double sum = 0.0;
                    for(Entry entry : entries) {
                        if (entry.getCategory() == et) {
                            sum += (double)entry.getValue();
                            dataset.setValue(et.toString(),sum);
                        }
                    }

                }
            }
            return dataset;
        }
    }
    private static JFreeChart createChart( PieDataset dataset )
    {
        JFreeChart chart = ChartFactory.createPieChart(
                "Your spending",  // chart title
                dataset,        // data
                true,           // include legend
                true,
                false);

        return chart;
    }
    public static JPanel createDemoPanel(ArrayList<Entry> entries, EntryType type )
    {
        JFreeChart chart = createChart(createDataset( entries,type) );
        return new ChartPanel( chart );
    }
    public static void main( String[ ] args )
    {
        PieGenerator demo = new PieGenerator( "Your spending" );
        demo.setSize( 560 , 367 );
        RefineryUtilities.centerFrameOnScreen( demo );
        demo.setVisible( true );
    }
}
