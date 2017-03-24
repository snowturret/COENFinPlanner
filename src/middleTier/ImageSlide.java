package middleTier;

/**
 * Created by xiwang on 3/13/17.
 */
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * set image's height, weight and layout; for animation.
 */
public class ImageSlide extends JPanel
{

    ImageIcon m[];
    JLabel l;
    JButton b,b1;
    int i,l1;
    JPanel p;

    public ImageSlide() throws IOException {
        AutoSlide i1 = new AutoSlide();
        setLayout(new BorderLayout( ));
        setSize(900, 900);
        setVisible(true);
        JPanel p=new JPanel(new FlowLayout());
        add(p,BorderLayout.SOUTH);

        l = new JLabel();
        l.setBounds(400, 0, getWidth(), getHeight());
        add(l,BorderLayout.CENTER);
        add(i1);


    }


    public static void main(String args[]) throws IOException {
        ImageSlide i1 = new ImageSlide();
    }


}
