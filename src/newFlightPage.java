/*
System by PortLegoGuy
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;  
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

class newFlightPage extends JFrame {
    JFrame frame;
    Flight flight;
    JComboBox<String> meridien;
    JComboBox hour;
    JComboBox minute;
    
    public newFlightPage(Flight flight){
        frame = new JFrame("[New Flight] Details");
        this.flight = flight;
        
        // add components required to the frame
        addComponentsToPane(frame.getContentPane());
        // pack the panel with the frame before displaying
        frame.pack();
        
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450,500);
        frame.setResizable(false);  // Prevent Resize window
        frame.setVisible(true);
    }
    
    public final void addComponentsToPane(Container pane){  // add anything on the display here
        pane.setLayout(new GridBagLayout());     // new grid bag layout
        GridBagConstraints c = new GridBagConstraints();    // the constraints for each component
        
        // Title Banner
        JLabel title = new JLabel("New Flight Summary");
        title.setFont(new Font("Calibri",Font.BOLD,24));
        title.setForeground(Color.BLACK);
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,0,10,20); // insets(top, left, bottom, right)
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 10;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;    // index of row
        c.gridy = 0;    // index of column
        c.gridwidth = 2;    // column span
        pane.add(title,c);
        
        // Departure
        JLabel dep = new JLabel("Departure: ");
        dep.setFont(new Font("Calibri",Font.CENTER_BASELINE,14));
        dep.setForeground(Color.BLACK);
        c.gridwidth = 1;    // column span
        c.insets = new Insets(10,10,10,5);
        c.ipady = 5;
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(dep,c);
        
        Location temp_dep = flight.getDep();
        JLabel departure = new JLabel(temp_dep.getLocation() + ", " + temp_dep.getState());
        departure.setFont(new Font("Calibri",Font.BOLD,14));
        departure.setForeground(Color.BLACK);
        c.insets = new Insets(10,5,10,10);
        c.ipady = 5;
        c.ipadx = 0;
        c.gridx = 1;
        c.gridy = 1;
        pane.add(departure,c);
        
        // Destination
        JLabel des = new JLabel("Destination: ");
        des.setFont(new Font("Calibri",Font.CENTER_BASELINE,14));
        des.setForeground(Color.BLACK);
        c.insets = new Insets(10,10,10,5);
        c.ipady = 5;
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(des,c);
        
        Location temp_des = flight.getDes();
        JLabel destination = new JLabel(temp_des.getLocation() + ", " + temp_des.getState());
        destination.setFont(new Font("Calibri",Font.BOLD,14));
        destination.setForeground(Color.BLACK);
        c.insets = new Insets(10,5,10,10);
        c.ipady = 5;
        c.ipadx = 0;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(destination,c);
        
        // Flight Date
        JLabel date = new JLabel("Date Depart:");
        date.setFont(new Font("Calibri",Font.CENTER_BASELINE,14));
        date.setForeground(Color.BLACK);
        c.insets = new Insets(10,10,10,5);
        c.ipady = 5;
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 3;
        pane.add(date,c);
        
        Date temp_date = flight.getDateTimeDepart();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        JLabel dateF = new JLabel(dateFormatter.format(temp_date));
        dateF.setFont(new Font("Calibri",Font.BOLD,14));
        dateF.setForeground(Color.BLACK);
        c.insets = new Insets(10,5,10,10);
        c.ipady = 5;
        c.ipadx = 0;
        c.gridx = 1;
        c.gridy = 3;
        pane.add(dateF,c);
        
        // Time flight
        JLabel time = new JLabel("Select Time Depart: ");
        time.setFont(new Font("Calibri",Font.CENTER_BASELINE,14));
        time.setForeground(Color.BLACK);
        c.insets = new Insets(10,10,10,5);
        c.ipady = 5;
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 4;
        pane.add(time,c);
        
        // Set time
        ArrayList<Integer> hr = new ArrayList<Integer>();
        for(int i=0 ; i<12 ; i++){  // hour
            hr.add(i+1);
        }
        
        ArrayList<Integer> min = new ArrayList<Integer>();
        for(int i=0 ; i<60 ; i++){  // minute
            min.add(i);
        }
        String[] M = {"AM","PM"};   // Meridien
        
        // hour selection
        hour = new JComboBox(hr.toArray());
        hour.setSelectedIndex(-1);
        c.insets = new Insets(10,5,10,10);
        c.ipady = 5;
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 5;
        pane.add(hour,c);
        
        minute = new JComboBox(min.toArray());
        minute.setSelectedIndex(-1);
        c.gridx = 1;
        c.gridy = 5;
        pane.add(minute,c);
        
        meridien = new JComboBox(M);
        c.gridx = 2;
        c.gridy = 5;
        pane.add(meridien,c);
        
        // "Confirm new flight" button
        JButton confirm = new JButton("Confirm New Flight");
        confirm.setForeground(Color.WHITE);
        confirm.setBackground(Color.BLUE);
        confirm.setPreferredSize(new Dimension(100,50));
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.insets = new Insets(30,30,5,30); // insets(top, left, bottom, right)
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 3;
        confirm.addActionListener(new confirmNewFlightListener());
        pane.add(confirm,c);
    }
    
    private class confirmNewFlightListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int hr = (int)hour.getSelectedItem();
            int min = (int)minute.getSelectedItem();
            String ap = (String)meridien.getSelectedItem();
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(flight.getDateTimeDepart());
            if(ap.equals("AM")){
                cal.set(Calendar.HOUR_OF_DAY,hr);
            }
            else{
                cal.set(Calendar.HOUR_OF_DAY,hr + 12);
            }
            cal.set(Calendar.MINUTE,min);
            
            SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm"); // time format
            Date time = cal.getTime();
            
            flight.setTimeDepart(time);
            try{    // attempt to assign new flight number to the new flight
                flight.setFlightNum();
            }
            catch(IOException a){
                JOptionPane.showMessageDialog(null,"[ERROR] - Cannot retrieve flight_no_accumulator!");
            }
            
            frame.dispose();
            JOptionPane.showMessageDialog(null,"New Flight Created Successfully!");
        }
    }
    
}
