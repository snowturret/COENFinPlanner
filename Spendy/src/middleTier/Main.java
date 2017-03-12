package middleTier;
/**
 * Created by raoyinchen on 3/5/17.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
//        Spendy s = new Spendy();

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
                            MainScreen screen = new MainScreen();
                        }
                    }
                });

        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setSize(300, 100);
        board.setLayout(new FlowLayout());
        board.getContentPane().add(btnLogin);
        board.setLocationRelativeTo(null);
        board.setVisible(true);
    }
}