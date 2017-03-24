package middleTier;
/**
 * Created by xiwang on 3/5/17.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * main class to start the program; run animation and log file.
 */
public class Main {
    public static void main(String[] args) {
        AutoSlide i1 = new AutoSlide();
        final String FILE_PATH = "DB.txt";
        try {
            Spendy.readFile(FILE_PATH);

        }catch (FileNotFoundException ex) {
            System.out.println("something wrong read file");
        }

        final JFrame board = new JFrame("Financial Recommendar");
        final JButton btnLogin = new JButton("Click to login");

        btnLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        LoginBox loginDlg = new LoginBox(board);
                        loginDlg.setVisible(true);
                        // if logon successfully
                        if(loginDlg.isSucceeded()){
                            btnLogin.setText("Hi " + loginDlg.getUsername() + "!");

                            try {
                                MainScreen screen = new MainScreen();
                            }catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });

        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setSize(1000, 900);
        board.setLayout(new FlowLayout());
        board.getContentPane().add(btnLogin);
        board.setLocationRelativeTo(null);
        board.setVisible(true);
        board.setLocationRelativeTo(null);
        board.add(i1);

        try {
            Spendy.saveFile();
//            System.out.println ("save in main");
        }catch (IOException io){
            System.out.println ("Error in saving file");
        }

    }
}