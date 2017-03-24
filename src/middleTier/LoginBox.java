package middleTier;
/**
 * Created by xiwang on 3/5/17.
 */
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Login interface; if user is a return user; enter username and password and click login; if it's a new user; enter
 * username and password, and click newUser; and then click register; In this section; it will check validation and
 * hash the password; If the username and password pass the validation it will navigate user to main menu, and set user
 * to current User; otherwise, it will popup error message.
 */
public class LoginBox extends JFrame {

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JButton btnNewuser;
    private boolean succeeded;
    Container container = getContentPane();
    MainScreen test;

    public LoginBox(Frame parent) {
        //super(parent, "Login", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Login");

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(btnLogin.getText().equals("register") ) {
                    if((getUsername() != null && !getUsername().isEmpty() &&  getPassword() != null && !getPassword().isEmpty())) {
                        if (Spendy.validUsername(getUsername())) {
                            Spendy.register(getUsername(), getPassword());
                            try {
                                Spendy.saveFile();
//                            System.out.println ("save register");
                            }catch (IOException io){
                                System.out.println ("Error in saving file1");
                            }

                            JOptionPane.showMessageDialog(LoginBox.this,
                                    "Hi " + getUsername() + "! You have successfully registered.",
                                    "Login",
                                    JOptionPane.INFORMATION_MESSAGE);
                            succeeded = true;
                            btnLogin.setText("Login");
                            try {
                                test = new MainScreen();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(LoginBox.this,
                                    "Exist username",
                                    "Login",
                                    JOptionPane.ERROR_MESSAGE);
                            // reset username and password
                            tfUsername.setText("");
                            pfPassword.setText("");
                            succeeded = false;
                        }
                    }else {
                        JOptionPane.showMessageDialog(LoginBox.this,
                                "Invalid username or password",
                                "Login",
                                JOptionPane.ERROR_MESSAGE);
                        // reset username and password
                        tfUsername.setText("");
                        pfPassword.setText("");
                        succeeded = false;
                    }

                }
                else if (Spendy.login(getUsername(), getPassword())) {
                    JOptionPane.showMessageDialog(LoginBox.this,
                            "Hi " + getUsername() + "! You have successfully logged in.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);

                    succeeded = true;
                    try {
                        test = new MainScreen();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginBox.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
                    succeeded = false;

                }
            }
        });
        JPanel bp = new JPanel();
        btnCancel = new JButton("Cancel");


        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnNewuser = new JButton("newUser");
        btnNewuser.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnLogin.setText("register");

            }
        });
        bp.add(btnLogin);
        bp.add(btnCancel);
        bp.add(btnNewuser);

        container.add(panel, BorderLayout.CENTER);
        container.add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    /**
     * getter get the username;
     * @return
     */
    public String getUsername() {
        return tfUsername.getText().trim();
    }


    /**
     * get the hashed password;
     * @return
     */
    public String getPassword() {
        return md5(new String(pfPassword.getPassword()));
    }

    /**
     * input is the plain text password; and transfer it to hashed code; ensure security
     * @param input
     * @return
     */
    public String md5(String input) {
        String md5 = null;
        if(null == input) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * check the process is succeed or not;
     * @return
     */
    public boolean isSucceeded() {
        return succeeded;
    }
}