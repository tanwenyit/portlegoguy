/*
System by PortLegoGuy
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class showPassengersPage extends JFrame {
    JFrame frame;
    JScrollPane scrollboard;    // enable the result to be scrollable
    int flight_num;
    
    public showPassengersPage(int f) throws IOException{
        flight_num = f;
        
        JPanel panel = new JPanel(new GridBagLayout());
        ArrayList<Booking> bookList = new ArrayList<Booking>();
        Booking temp = new Booking();
        
        try {   // retrieve all booking lists
            bookList = temp.retrieveBookingList();
        } catch (ParseException ex) {
            Logger.getLogger(showPassengersPage.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        // add components required to the frame
        addComponentsToPane(panel, flight_num, bookList);
        scrollboard = new JScrollPane(panel);
        
        // pack the panel with the frame before displaying
        frame = new JFrame("Show Passenger - Flight No.: " + flight_num);
        frame.getContentPane().add(scrollboard);
        
        frame.pack();
        frame.setSize(700, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);  // Prevent Resize window
    }
    
    public final void addComponentsToPane(Container pane, int search_num, ArrayList<Booking> bookList) throws IOException{
        GridBagConstraints c = new GridBagConstraints();    // the constraints for each component
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");	// date format
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");	// time format
        int row = 0, column = 0;
        int i=0;
        boolean found = false;
        
        JLabel banner = new JLabel("Passenger List");
        banner.setFont(new Font("Calibri",Font.BOLD,22));
        c.insets = new Insets(10,10,10,10); // insets(top, left, bottom, right)
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 10;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    // index of column = 0
        c.gridy = row;    // index of row = 0
        c.gridwidth = GridBagConstraints.REMAINDER;    // column span till the end
        pane.add(banner,c);
        row++;
        
        for(Booking bl : bookList){
            if(bl.getFlightRefNum() == search_num){  // if the same flight num, display all the passengers details
                found = true;
                for(int j=0 ; j<bl.getTotalPerson() ; j++){
                    JLabel pass = new JLabel("Passenger " + (i+1), SwingConstants.CENTER);
                    pass.setFont(new Font("Calibri", Font.BOLD,18));
                    c.insets = new Insets(10,10,5,10); // insets(top, left, bottom, right)
                    c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 0;
                    c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 0.0;
                    c.gridx = column;    
                    c.gridy = row;
                    c.gridwidth = 1;
                    pane.add(pass,c);
                    row++; i++;
                    
                    JLabel name = new JLabel("Name     : " + bl.getFullName(j));
                    c.insets = new Insets(5,10,5,10); // insets(top, left, bottom, right)
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 0;
                    c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 0.0;
                    c.gridx = column;    
                    c.gridy = row;
                    c.gridwidth = 1;
                    pane.add(name,c);
                    row++;
                    
                    JLabel birthdate = new JLabel("Birthdate: " + dateFormatter.format(bl.getBirthdate(j)));
                    c.insets = new Insets(5,10,5,10); // insets(top, left, bottom, right)
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 0;
                    c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 0.0;
                    c.gridx = column;    
                    c.gridy = row;
                    c.gridwidth = 1;
                    pane.add(birthdate,c);
                    row++;
                    
                    JLabel type = new JLabel("Type     : " + bl.getPassType(j));
                    c.insets = new Insets(5,10,5,10); // insets(top, left, bottom, right)
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 0;
                    c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 0.0;
                    c.gridx = column;    
                    c.gridy = row;
                    c.gridwidth = 1;
                    pane.add(type,c);
                    row++;
                    
                    JLabel gender = new JLabel("Gender   : " + bl.getSex(j));
                    c.insets = new Insets(5,10,10,10); // insets(top, left, bottom, right)
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 0;
                    c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 0.0;
                    c.gridx = column;    
                    c.gridy = row;
                    c.gridwidth = 1;
                    pane.add(gender,c);
                    row++;
                }
            }
        }   // end displaying
        
        if(found == false){
            JLabel noresult = new JLabel("No Passengers Yet.",SwingConstants.CENTER);
            c.insets = new Insets(15,10,10,10); // insets(top, left, bottom, right)
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            c.gridwidth = 1;
            pane.add(noresult,c);
            row++;
        }
    }
}
