/*
System by PortLegoGuy
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class adminCheck extends JFrame{
    private JPanel panel;
    private JTextField username;
    private JPasswordField password;
    
    public adminCheck(){
        setTitle("[Admin] - Log In to System");
        setSize(350,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        logInForm();
        add(panel);
        
        setResizable(false);  // Prevent Resize window
        setVisible(true);
    }
    
    public void logInForm(){
        JLabel uname = new JLabel("Username: ");
        JLabel pwd = new JLabel("Password: ");
        username = new JTextField(20);
        password = new JPasswordField(20);
        
        JButton LogInBtn = new JButton("Log In");
        LogInBtn.setBackground(Color.BLUE);
        LogInBtn.setForeground(Color.WHITE);        
        LogInBtn.addActionListener(new logInListener());
        
        JButton backBtn = new JButton("Main Menu");
        backBtn.setBackground(Color.YELLOW);
        backBtn.setForeground(Color.BLACK);
        backBtn.addActionListener(new mainMenuListener());
        
        panel = new JPanel();
        
        panel.add(uname);
        panel.add(username);
        panel.add(pwd);
        panel.add(password);
        panel.add(LogInBtn);
        panel.add(backBtn);
    }
    
    private class logInListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String un, pd;
             un = username.getText();
             pd = password.getText();
             
             if(un.equals("admin") && pd.equals("admin")){
                 dispose();
                 adminPage admPg = new adminPage();
             }
             else{
                dispose();
                JOptionPane.showMessageDialog(null,"Invalid Username or Password!\nPlease Try Again!");
                adminCheck admchk = new adminCheck();
             }
        }
    }
    
    private class mainMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            dispose();
            mainPage start = new mainPage();
        }
    }
    
}
