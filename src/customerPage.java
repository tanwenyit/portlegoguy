/*
System by PortLegoGuy
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Calendar;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;

class customerPage extends JFrame {
    JFrame frame;
    JComboBox<Location> destination;    // destination
    JComboBox<Location> departure;      // departure
    JDatePickerImpl datePicker;         // date of depart
    Location[] lot;
        
    public customerPage(){
        frame = new JFrame("[Customer] - Flight Schedule");
        
        // add components required to the frame
        addComponentsToPane(frame.getContentPane());
        // pack the panel with the frame before displaying
        frame.pack();
        
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850,450);
        frame.setResizable(false);  // Prevent Resize window
        frame.setVisible(true);
    }
    
    public final void addComponentsToPane(Container pane){    // add anything on the display here
        pane.setLayout(new GridBagLayout());     // new grid bag layout
        GridBagConstraints c = new GridBagConstraints();    // the constraints for each component
        
        // Title Banner
        JLabel title = new JLabel("Welcome to Flight Booking System");
        title.setFont(new Font("Calibri",Font.BOLD,30));
        title.setForeground(Color.BLACK);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(0,20,0,0); // insets(top, left, bottom, right)
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 10;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;    // index of row
        c.gridy = 0;    // index of column
        c.gridwidth = 3;    // column span
        pane.add(title,c);
        
        // Back to main menu page button
        JButton back = new JButton("Main Menu");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.YELLOW);
        back.setPreferredSize(new Dimension(100,30));
        c.fill = GridBagConstraints.NONE; 
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 1;
        c.ipady = 10;   
        c.ipadx = 10;
        c.gridx = 3;
        c.gridy = 0;
        back.addActionListener(new mainPageListener());
        pane.add(back,c);
            
        // Departure input
        JLabel dep = new JLabel("Select Departure:");
        c.gridwidth = 1;
        c.insets = new Insets(40,20,20,20); // insets(top, left, bottom, right)
        c.ipady = 10;   
        c.ipadx = 10;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(dep,c);
        
        lot = Location.values(); // Get all enum values
        String[] loca = new String[lot.length];
        int i = 0;
        for(Location l:lot){
            loca[i] = l.getLocation() + ", " + l.getState();
            i++;
        }
        // Combo list box for selection
        departure = new JComboBox(loca);
        departure.setSelectedIndex(-1); // By default is selected 1st item, this will nullify the box initially.
        c.gridwidth = 1;
        c.ipady = 10;   
        c.ipadx = 10;
        c.gridx = 1;
        c.gridy = 1;
        pane.add(departure,c);
        
        // Destination input
        JLabel des = new JLabel("Select Destination:");
        c.gridwidth = 1;
        c.ipady = 10;   
        c.ipadx = 10;
        c.gridx = 2;
        c.gridy = 1;
        pane.add(des,c);
        
        // Again, add the combo list box
        destination = new JComboBox(loca);
        destination.setSelectedIndex(-1); // By default is selected 1st item, this will nullify the box initially.
        c.gridwidth = 1;
        c.ipady = 10;   
        c.ipadx = 10;
        c.gridx = 3;
        c.gridy = 1;
        pane.add(destination,c);
        
        // Pick Depart Date
        JLabel dateTitle = new JLabel("Pick Depart Date:");
        c.gridwidth = 1;
        c.ipady = 10;   
        c.ipadx = 10;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(dateTitle,c);
        
        // Pick Depart Date
        UtilCalendarModel model = new UtilCalendarModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.gridwidth = 1;
        c.ipady = 10;   
        c.ipadx = 10;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(datePicker,c);
        
        // Add "Check Flight" button
        JButton chk = new JButton("Check Flight");
        chk.setForeground(Color.WHITE);
        chk.setBackground(Color.BLUE);
        chk.setPreferredSize(new Dimension(100,50));
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.insets = new Insets(30,30,5,30); // insets(top, left, bottom, right)
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 3;
        chk.addActionListener(new checkFlightListener());
        pane.add(chk,c);
        
        // Add today's date
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        Calendar now = Calendar.getInstance();
        Date nowD = now.getTime();
        
        JLabel today = new JLabel("Today: " + dateFormatter.format(nowD),SwingConstants.CENTER);
        today.setFont(new Font("Calibri",Font.BOLD,14));
        today.setForeground(Color.BLACK);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.insets = new Insets(0,20,10,30); // insets(top, left, bottom, right)
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 4;
        pane.add(today,c);
    }
    
    private class checkFlightListener implements ActionListener{    // to check the existance of the flight and validation of inputs
        @Override
        public void actionPerformed(ActionEvent e){
            String dep = (String)departure.getSelectedItem();
            String des = (String)destination.getSelectedItem();
            
            if(!dep.equals(des)){   // if departure != destination
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
                Calendar selectedValue = (Calendar) datePicker.getModel().getValue();
                Calendar now = Calendar.getInstance();
                Date selectedDate = selectedValue.getTime();
                
                if(!now.before(selectedValue)){ // if selected date is before the current date, reject the date!
                    JOptionPane.showMessageDialog(null, "Please select the date after today.");
                }
                else{   // retrieve the flight details and all schedule of the flight
                    String temp;
                    Location _dep = null, _des = null;

                    for(Location l:lot){
                        temp = l.getLocation() + ", " + l.getState();
                        if(temp.equals(dep)){
                            _dep = l;
                        }
                        if(temp.equals(des)){
                            _des = l;
                        }
                    }
                    try {
                        // continue here
                        Flight search = new Flight();
                        search.searchFlight(_dep, _des, selectedDate);
                        custCheckFlight chkFlight = new custCheckFlight(search);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(adminPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Please select different departure and destination!");
            }
        }
    }
    
    private class mainPageListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            frame.dispose();  // dispose the main menu window
            mainPage start = new mainPage();
        }
    }
}
