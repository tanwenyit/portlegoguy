//package portlegoguy;

/*
System by PortLegoGuy
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class mainPage extends JFrame{
    private JPanel panel;
    public mainPage(){
        setLayout(new BorderLayout(30,30));
                       
        JLabel title = new JLabel("Flight Ticket Booking System",SwingConstants.CENTER); // Center the title
        JLabel footer = new JLabel("System by PortLegoGuy",SwingConstants.CENTER);
        
        title.setFont(new Font("Calibri",Font.BOLD,40));
        title.setForeground(Color.BLACK);
        footer.setFont(new Font("Calibri",Font.BOLD,18));
        footer.setForeground(Color.RED);
        
        JButton cust = new JButton("CUSTOMER");
        JButton adm = new JButton("ADMIN");
        
        cust.addActionListener(new custBtnListener());
        adm.addActionListener(new admBtnListener());
        
        cust.setBackground(Color.BLUE);
        cust.setForeground(Color.WHITE);
        adm.setBackground(Color.YELLOW);
        adm.setForeground(Color.BLACK);
        cust.setPreferredSize(new Dimension(150,50));
        adm.setPreferredSize(new Dimension(150,50));
        
        panel = new JPanel();
        panel.add(cust);
        panel.add(adm);
        
        add(title,BorderLayout.NORTH);
        add(panel,BorderLayout.CENTER);
        add(footer,BorderLayout.SOUTH);
        
        setTitle("FTBS");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,300);
        setResizable(false);  // Prevent Resize window
        setVisible(true);
    }
    
    private class custBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            dispose();  // dispose the main menu window
            customerPage custPage = new customerPage();
        }
    }
    
    private class admBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            dispose();
            adminCheck admchk = new adminCheck();
        }
    }
}