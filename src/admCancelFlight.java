/*
System by PortLegoGuy
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class admCancelFlight extends JFrame{
    JFrame frame, prev_frame;
    ArrayList<Flight> flightList;
    Flight flight_cancelled;
    int flight_num;
    
    public admCancelFlight(int f, ArrayList list, JFrame prev_frame){
        flightList = list;
        flight_num = f;
        this.prev_frame = prev_frame;
        
        frame = new JFrame("Cancel Flight Confirmation");
        
        // add components required to the frame
        addComponentsToPane(frame.getContentPane());
        // pack the panel with the frame before displaying
        frame.pack();
        
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,300);
        frame.setResizable(false);  // Prevent Resize window
        frame.setVisible(true);
    }
    
    public final void addComponentsToPane(Container pane) {
        pane.setLayout(new GridBagLayout());     // new grid bag layout
        GridBagConstraints c = new GridBagConstraints();    // the constraints for each component
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");	// date format
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");	// time format
        
        // confirmation message
        JLabel warning = new JLabel("Are you sure to cancel the flight below?", SwingConstants.LEFT);
        warning.setFont(new Font("Calibri", Font.BOLD, 16));
        warning.setForeground(Color.BLACK);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(10,10,10,10); // insets(top, left, bottom, right)
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 10;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;    // index of column = 0
        c.gridy = 0;    // index of row = 0
        c.gridwidth = GridBagConstraints.REMAINDER;    // column span till the end
        pane.add(warning,c);
        
        // the flight details
        for(Flight f : flightList){ // get the flight
            if(f.getFlightNum() == flight_num){
                flight_cancelled = f;
            }
        }
        
        JLabel start = new JLabel(flight_cancelled.getDep().getIATA(),SwingConstants.LEFT);
        start.setFont(new Font("Calibri",Font.BOLD,24));
        start.setForeground(Color.BLACK);
        c.insets = new Insets(10,20,5,5);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 1.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 1.0;
        c.gridx = 0;    // index of column
        c.gridy = 1;    // index of row
        c.gridwidth = 1;    // column span
        pane.add(start,c);
        
        JLabel to = new JLabel("->",SwingConstants.LEFT);
        to.setFont(new Font("Calibri",Font.BOLD,24));
        to.setForeground(Color.BLACK);
        c.insets = new Insets(10,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 1.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 1.0;
        c.gridx = 1;    // index of column = 1
        c.gridy = 1;    // index of row = 0
        pane.add(to,c);
        
        JLabel end = new JLabel(flight_cancelled.getDes().getIATA(),SwingConstants.LEFT);
        end.setFont(new Font("Calibri",Font.BOLD,24));
        end.setForeground(Color.BLACK);
        c.insets = new Insets(10,5,5,20);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 1.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 1.0;
        c.gridx = 2;    // index of column = 2
        c.gridy = 1;    // index of row = 0
        pane.add(end,c);
        
        JLabel date = new JLabel("Depart Date: " + dateFormatter.format(flight_cancelled.getDateTimeDepart()));
        c.insets = new Insets(2,20,2,20);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        pane.add(date,c);
        
        // Calculate duration in hour and minute
        int hr = (int)flight_cancelled.getDuration() / 60;       // get hour
        int min = (int)flight_cancelled.getDuration() % 60;    // get minute
                    
        JLabel time = new JLabel("Duration: " + timeFormatter.format(flight_cancelled.getDateTimeDepart()) + " - " + timeFormatter.format(flight_cancelled.getETA()) + " ( " + hr + " hr " + min + " min ) ");
        c.insets = new Insets(2,20,20,20);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        pane.add(time,c);
        
        // yes and no button
        JButton yes = new JButton("Yes");
        yes.setForeground(Color.WHITE);
        yes.setBackground(Color.BLUE);
        yes.setPreferredSize(new Dimension(40,20));
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        yes.addActionListener(new confirmCancelListener());
        pane.add(yes,c);
        
        JButton no = new JButton("No");
        no.setForeground(Color.WHITE);
        no.setBackground(Color.RED);
        no.setPreferredSize(new Dimension(40,20));
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = GridBagConstraints.REMAINDER;
        no.addActionListener(new denyCancelListener());
        pane.add(no,c);
    }
    
    public void removeFlight() throws IOException{         // remove the flight, booking list, and the contact details
                
        ArrayList<Flight> toRemove = new ArrayList<Flight>();
        
        ArrayList<Booking> bookList = new ArrayList<Booking>();
        ArrayList<Booking> removeBL = new ArrayList<Booking>();
        Booking temp_b = new Booking();
        
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        ArrayList<Contact> removeCL = new ArrayList<Contact>();
        Contact temp_c = new Contact();
        
        try {   // retrieve all booking and related contact lists
            bookList = temp_b.retrieveBookingList();
            contactList = temp_c.retrieveContactList();
        } catch (ParseException ex) {
            Logger.getLogger(showPassengersPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // remove the related flight
        for(Flight f : flightList){
            if(f.getFlightNum() == flight_num){ // if matched with the cancelled flight
                toRemove.add(f);
            }
        }
        
        // remove the booking related to the flight
        for(Booking bl : bookList){
            if(bl.getFlightRefNum() == flight_num) {
                
                removeBL.add(bl);
                
                // remove the contact details related to the booking
                for(Contact cl : contactList){
                    if(bl.getContactRefNum() == cl.getContactRefNo()){
                        removeCL.add(cl);
                    }
                }
            }
        }
        
        
        // remove the flight, booking, contact if the toRemove element is existed in the flightList, booking list and contact list
        flightList.removeAll(toRemove);
        bookList.removeAll(removeBL);
        contactList.removeAll(removeCL);
        
        rewriteFlightList(flightList);      // remove from flight list
        rewriteContactList(contactList);    // remove from booking list
        rewriteBookingList(bookList);       // remove from contact list
    }
    
    public void rewriteContactList(ArrayList<Contact> List) throws IOException {
        PrintWriter save = new PrintWriter(new FileWriter("./src/contactDetails.txt"));
        if(List != null){
            for(Contact c : List){
                save.println(c.getContactRefNo());
                save.println(c.getName());
                save.println(c.getFName());
                save.println(c.getEmail());
                save.println(c.getPhone());
                save.println(c.getSex());
            }
        }
        else{
            save.println("");
        }
        
        save.close();
    }
    
    public void rewriteBookingList(ArrayList<Booking> List) throws IOException {
        PrintWriter save = new PrintWriter(new FileWriter("./src/allBookingDetails.txt"));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");	// date format
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd MMM yyyy HH:mm");	// dateTime format (for booking date & time only)
        
        if(List != null){
            for(Booking b : List){
                save.println(b.getBookRefNum());
                save.println(dateTimeFormatter.format(b.getBookDate()));
                save.println(b.getContactRefNum());
                save.println(b.getFlightRefNum());
                save.println(b.getTotalPerson());
                save.println(b.getAdultNum());
                save.println(b.getChildNum());

                int i;

                // save all adult details (at least one)
                for(i = 0 ; i < b.getAdultNum() ; i++){
                    save.println(b.getName(i));
                    save.println(b.getFName(i));
                    save.println(dateFormatter.format(b.getBirthdate(i)));
                    save.println(b.getPassType(i));
                    save.println(b.getSex(i));
                }

                // save all child details (if any)
                while(i < b.getTotalPerson()){
                    save.println(b.getName(i));
                    save.println(b.getFName(i));
                    save.println(dateFormatter.format(b.getBirthdate(i)));
                    save.println(b.getPassType(i));
                    save.println(b.getSex(i));
                    i++;
                }
            }
        }
        else{
            save.println("");
        }
        
        save.close();
    }
    
    public void rewriteFlightList(ArrayList List) throws IOException {
        
        PrintWriter save = new PrintWriter(new FileWriter("./src/allFlightDetails.txt"));
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd MMM yyyy HH:mm");	// dateTime format
        
        if(List != null){
            for(Flight f : flightList){     // rewrite into the text file for saving
                
                save.println(f.getFlightNum());
                save.println(dateTimeFormatter.format(f.getDateTimeDepart()));
                save.println(dateTimeFormatter.format(f.getETA()));
                save.println(f.getDuration());
                save.println(f.getDep());
                save.println(f.getDes());
                save.println(f.getPassengers());
            }
        }
        else{
            save.println("");
        }
        
        save.close();        
    }
    
    private class confirmCancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                removeFlight();
                frame.dispose();
                prev_frame.dispose();
            } catch (IOException ex) {
                Logger.getLogger(admCancelFlight.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class denyCancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }
}
