package middleTier;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by xiwang on 3/13/17.
 */

/**
 * Financial history board; it will display all entries that the user had enter before.
 */
public class RecommendBoard extends JFrame {

    public RecommendBoard() throws IOException, ParseException {

        ArrayList<String> records = new ArrayList<>();

        Scanner scan = new Scanner(new File("DB.txt"));



        while(scan.hasNextLine()){
            String line = scan.nextLine();

            if (line.startsWith(Spendy.getCurrentUser().getName())) {
                String[] subrecords = line.split(",");
                StringBuilder subres = new StringBuilder();

                if (subrecords.length > 2) {
                    String start_dt = subrecords[2];
                    DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                    Date date = (Date)formatter.parse(start_dt);
                    SimpleDateFormat newFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String finalString = newFormat.format(date);
                    subres.append(finalString + " ");
                    for(int i = 3; i < subrecords.length;i++) {
                        if (!subrecords[i].equals("null")) {
                            subres.append(subrecords[i] + " ");
                        }
                    }
                    records.add(subres.toString());
                }
            }

        }
        StringBuilder showString = new StringBuilder();
        if (records == null || records.size() == 0 ){
            showString.append(" ");
        } else {
            for(String each: records) {
                showString.append(each + "\n");
            }
        }

        JTextArea textArea = new JTextArea("Here is your spending history  \n\n" + showString.toString() +

              "\nend of the record");
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


//    public static void main(String[] args) throws IOException {
//
////        RecommendBoard show = new RecommendBoard();
//
//    }
}


