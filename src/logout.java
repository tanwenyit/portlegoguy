/*
System by PortLegoGuy
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class logout extends JFrame{
    private JFrame admPage;
    private JPanel panel;
    private JTextField username;
    private JPasswordField password;
    
    public logout(JFrame admPage){
        this.admPage = admPage;
        
        setTitle("Logout");
        setSize(250,100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        logoutForm();
        add(panel);
        
        setResizable(false);  // Prevent Resize window
        setVisible(true);
    }
    
    public void logoutForm(){
        JLabel msg = new JLabel("Are you sure to logout the system?");
        
        JButton yesBtn = new JButton("Yes");
        yesBtn.setBackground(Color.BLUE);
        yesBtn.setForeground(Color.WHITE);        
        yesBtn.addActionListener(new yesListener());
        
        JButton noBtn = new JButton("Cancel");
        noBtn.setBackground(Color.RED);
        noBtn.setForeground(Color.WHITE);
        noBtn.addActionListener(new noListener());
        
        panel = new JPanel();
        
        panel.add(msg);
        panel.add(yesBtn);
        panel.add(noBtn);
    }
    
    private class yesListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            dispose();
            admPage.dispose();
            mainPage start = new mainPage();
        }
    }
    
    private class noListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }
    
}
