package middleTier;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by raoyinchen on 3/13/17.
 */
public class RecommendBoard extends JFrame {

    public RecommendBoard() throws IOException {

        ArrayList<String> records = new ArrayList<>();

        Scanner scan = new Scanner(new File("DB.txt"));



        while(scan.hasNextLine()){
            String line = scan.nextLine();


            if (line.startsWith(Spendy.getCurrentUser().getName())) {
                String[] subrecords = line.split(",");
                StringBuilder subres = new StringBuilder();
                for(int i = 2; i < subrecords.length;i++) {
                   subres.append(subrecords[i]);
                }
                records.add(subres.toString());
            }

        }
        StringBuilder showString = new StringBuilder();
        for(String each: records) {
            showString.append(each + "\n");
        }

        JTextArea textArea = new JTextArea("Here is your spending history  \n   " + showString.toString() +

              "\nend of the recored");
        textArea.setSize(700,700);

        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setVisible(true);

        JScrollPane scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        add(scroll);
        setVisible(true);
        setSize(400,300);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) throws IOException {

        RecommendBoard show = new RecommendBoard();

    }
}


