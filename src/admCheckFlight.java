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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class admCheckFlight extends JFrame{
    JFrame frame;
    ArrayList<Flight> flightList;
    JScrollPane scrollboard;    // enable the result to be scrollable
    
    public admCheckFlight(Flight search) throws IOException {
        
        JPanel panel = new JPanel(new GridBagLayout());
                    
        // add components required to the frame
        addComponentsToPane(panel, search);
        scrollboard = new JScrollPane(panel);
        
        // pack the panel with the frame before displaying
        frame = new JFrame("Flight Schedule Details");
        frame.getContentPane().add(scrollboard);
        
        frame.pack();
        frame.setSize(700, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);  // Prevent Resize window
    }
    
    public final void addComponentsToPane(Container pane, Flight search) throws IOException{
        // set gridbaglayout for the frame
        GridBagConstraints c = new GridBagConstraints();    // the constraints for each component
            
        try {    
            // display all related flight one by one
            flightList = new ArrayList<Flight>();   // initialize ArrayList
            retrieveAllFlight();
        } catch (ParseException ex) {
            Logger.getLogger(admCheckFlight.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Display the flight schedule if dep, des, and date are matched.
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");	// date format
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");	// time format
        int row = 0, column = 0;
        int hr, min;
        boolean result_found = false;
        
        JLabel title = new JLabel("Search Flight Results");
        title.setFont(new Font("Calibri",Font.BOLD,30));
        title.setForeground(Color.BLACK);
        //c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(20,40,10,10); // insets(top, left, bottom, right)
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 10;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    // index of column = 0
        c.gridy = row;    // index of row = 0
        c.gridwidth = GridBagConstraints.REMAINDER;    // column span till the end
        pane.add(title,c);
        row++;
        
        for(int i=0 ; i < flightList.size() ; i++){ // for search result
            if(flightList.get(i).getDep().equals(search.getDep()) && flightList.get(i).getDes().equals(search.getDes())){
                if(dateFormatter.format(flightList.get(i).getDateTimeDepart()).equals(dateFormatter.format(search.getDateTimeDepart()))){
                    //
                    result_found = true;
                    
                    JLabel start = new JLabel(flightList.get(i).getDep().getIATA(),SwingConstants.RIGHT);
                    start.setFont(new Font("Calibri",Font.BOLD,24));
                    start.setForeground(Color.BLACK);
                    c.insets = new Insets(10,20,5,5);
                    c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 5;
                    c.weightx = 1.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 1.0;
                    c.gridx = column;    // index of column = 0
                    c.gridy = row;    // index of row = 
                    c.gridwidth = 1;    // column span
                    pane.add(start,c);
                    column++;                    
                    
                    JLabel to = new JLabel("->",SwingConstants.CENTER);
                    to.setFont(new Font("Calibri",Font.BOLD,24));
                    to.setForeground(Color.BLACK);
                    c.insets = new Insets(10,5,5,5);
                    c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 5;
                    c.weightx = 1.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 1.0;
                    c.gridx = column;    // index of column = 1
                    c.gridy = row;    // index of row = 0
                    pane.add(to,c);
                    column++; 
                                        
                    JLabel end = new JLabel(flightList.get(i).getDes().getIATA(),SwingConstants.LEFT);
                    end.setFont(new Font("Calibri",Font.BOLD,24));
                    end.setForeground(Color.BLACK);
                    c.insets = new Insets(10,5,5,20);
                    c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 5;
                    c.weightx = 1.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 1.0;
                    c.gridx = column;    // index of column = 2
                    c.gridy = row;    // index of row = 0
                    pane.add(end,c);
                    column = 0;
                                        
                    row++;  // next row
                    
                    // Flight Details
                    JLabel dep = new JLabel("Departure: " + flightList.get(i).getDep().getAirport() + " ( " + flightList.get(i).getDep().getLocation() + ", " + flightList.get(i).getDep().getState() + " ) ");
                    c.insets = new Insets(5,20,2,20);
                    c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 5;
                    c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 0.0;
                    c.gridx = column;
                    c.gridy = row;
                    c.gridwidth = GridBagConstraints.REMAINDER;
                    pane.add(dep,c);
                    row++;  // next row
                    
                    JLabel des = new JLabel("Destination: " + flightList.get(i).getDes().getAirport() + " ( " + flightList.get(i).getDes().getLocation() + ", " + flightList.get(i).getDes().getState() + " ) ");
                    c.insets = new Insets(2,20,2,20);
                    c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 5;
                    c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 0.0;
                    c.gridx = column;
                    c.gridy = row;
                    c.gridwidth = GridBagConstraints.REMAINDER;
                    pane.add(des,c);
                    row++;  // next row
                    
                    JLabel date = new JLabel("Depart Date: " + dateFormatter.format(flightList.get(i).getDateTimeDepart()));
                    c.insets = new Insets(2,20,2,20);
                    c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 5;
                    c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 0.0;
                    c.gridx = column;
                    c.gridy = row;
                    c.gridwidth = 2;
                    pane.add(date,c);
                    
                    // show passengers button
                    JButton showP = new JButton("Show Passengers");
                    showP.setForeground(Color.BLACK);
                    showP.setBackground(Color.YELLOW);
                    showP.setPreferredSize(new Dimension(40,20));
                    c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 5;
                    c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 0.0;
                    c.gridx = column + 2;
                    c.gridy = row;
                    c.gridwidth = GridBagConstraints.REMAINDER;
                    int f = flightList.get(i).getFlightNum();
                    showP.addActionListener(new showPassengersListener(f));
                    pane.add(showP,c);
                    row++;  // next row
                    
                    // Calculate duration in hour and minute
                    hr = (int)flightList.get(i).getDuration() / 60;       // get hour
                    min = (int)flightList.get(i).getDuration() % 60;    // get minute
                    
                    JLabel time = new JLabel("Duration: " + timeFormatter.format(flightList.get(i).getDateTimeDepart()) + " - " + timeFormatter.format(flightList.get(i).getETA()) + " ( " + hr + " hr " + min + " min ) ");
                    c.insets = new Insets(2,20,20,20);
                    c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 5;
                    c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 0.0;
                    c.gridx = column;
                    c.gridy = row;
                    c.gridwidth = 2;
                    pane.add(time,c);
                    
                    // cancel flight button
                    JButton cancelF = new JButton("Cancel Flight");
                    cancelF.setForeground(Color.WHITE);
                    cancelF.setBackground(Color.RED);
                    cancelF.setPreferredSize(new Dimension(40,20));
                    c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                    c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                    c.ipadx = 5;
                    c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                    c.weighty = 0.0;
                    c.gridx = column + 2;
                    c.gridy = row;
                    c.gridwidth = GridBagConstraints.REMAINDER;
                    cancelF.addActionListener(new cancelFlightListener(f));
                    pane.add(cancelF,c);
                    row++;  // next row
                }
            }
        }
        
        if(!result_found){  // if no result found
            JLabel noresult = new JLabel("No flight found.",SwingConstants.CENTER);
            noresult.setFont(new Font("Calibri",Font.CENTER_BASELINE,20));
            c.insets = new Insets(10,20,10,20);
            c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 5;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;
            c.gridy = row;
            c.gridwidth = GridBagConstraints.REMAINDER;
            pane.add(noresult,c);
            row++;
        }
        
        // display other schedule of the same settings (dep and des)
        JButton otherFlight = new JButton("Other Schedule of the flight");
        otherFlight.setFont(new Font("Calibri",Font.ITALIC,20));
        otherFlight.setForeground(Color.BLACK);
        otherFlight.setBackground(Color.YELLOW);
        c.insets = new Insets(30,20,10,20);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;
        c.gridy = row;
        c.gridwidth = GridBagConstraints.REMAINDER;
        pane.add(otherFlight,c);
        row++;
        
        for(int i=0 ; i < flightList.size() ; i++){ // for other schedule
            if(flightList.get(i).getDep().equals(search.getDep()) && flightList.get(i).getDes().equals(search.getDes())){
                
                JLabel start = new JLabel(flightList.get(i).getDep().getIATA(),SwingConstants.RIGHT);
                start.setFont(new Font("Calibri",Font.BOLD,24));
                start.setForeground(Color.BLACK);
                c.insets = new Insets(10,20,5,5);
                c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                c.ipadx = 5;
                c.weightx = 1.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                c.weighty = 1.0;
                c.gridx = column;    // index of column = 0
                c.gridy = row;    // index of row = 
                c.gridwidth = 1;    // column span
                pane.add(start,c);
                column++;                    
                    
                JLabel to = new JLabel("->",SwingConstants.CENTER);
                to.setFont(new Font("Calibri",Font.BOLD,24));
                to.setForeground(Color.BLACK);
                c.insets = new Insets(10,5,5,5);
                c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                c.ipadx = 5;
                c.weightx = 1.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                c.weighty = 1.0;
                c.gridx = column;    // index of column = 1
                c.gridy = row;    // index of row = 0
                pane.add(to,c);
                column++; 
                                        
                JLabel end = new JLabel(flightList.get(i).getDes().getIATA(),SwingConstants.LEFT);
                end.setFont(new Font("Calibri",Font.BOLD,24));
                end.setForeground(Color.BLACK);
                c.insets = new Insets(10,5,5,20);
                c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                c.ipadx = 5;
                c.weightx = 1.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                c.weighty = 1.0;
                c.gridx = column;    // index of column = 2
                c.gridy = row;    // index of row = 0
                pane.add(end,c);
                column = 0;
                                        
                row++;  // next row
                    
                    // Flight Details
                JLabel dep = new JLabel("Departure: " + flightList.get(i).getDep().getAirport() + " ( " + flightList.get(i).getDep().getLocation() + ", " + flightList.get(i).getDep().getState() + " ) ");
                c.insets = new Insets(5,20,2,20);
                c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                c.ipadx = 5;
                c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                c.weighty = 0.0;
                c.gridx = column;
                c.gridy = row;
                c.gridwidth = GridBagConstraints.REMAINDER;
                pane.add(dep,c);
                row++;  // next row
                 
                JLabel des = new JLabel("Destination: " + flightList.get(i).getDes().getAirport() + " ( " + flightList.get(i).getDes().getLocation() + ", " + flightList.get(i).getDes().getState() + " ) ");
                c.insets = new Insets(2,20,2,20);
                c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                c.ipadx = 5;
                c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                c.weighty = 0.0;
                c.gridx = column;
                c.gridy = row;
                c.gridwidth = GridBagConstraints.REMAINDER;
                pane.add(des,c);
                row++;  // next row
                    
                JLabel date = new JLabel("Depart Date: " + dateFormatter.format(flightList.get(i).getDateTimeDepart()));
                c.insets = new Insets(2,20,2,20);
                c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                c.ipadx = 5;
                c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                c.weighty = 0.0;
                c.gridx = column;
                c.gridy = row;
                c.gridwidth = 2;
                pane.add(date,c);
                
                // show passengers button
                JButton showP = new JButton("Show Passengers");
                showP.setForeground(Color.BLACK);
                showP.setBackground(Color.YELLOW);
                showP.setPreferredSize(new Dimension(40,20));
                c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                c.ipadx = 5;
                c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                c.weighty = 0.0;
                c.gridx = column + 2;
                c.gridy = row;
                c.gridwidth = GridBagConstraints.REMAINDER;
                int f = flightList.get(i).getFlightNum();
                showP.addActionListener(new showPassengersListener(f));
                pane.add(showP,c);
                row++;  // next row
                    
                    // Calculate duration in hour and minute
                hr = (int)flightList.get(i).getDuration() / 60;       // get hour
                min = (int)flightList.get(i).getDuration() % 60;    // get minute
                    
                JLabel time = new JLabel("Duration: " + timeFormatter.format(flightList.get(i).getDateTimeDepart()) + " - " + timeFormatter.format(flightList.get(i).getETA()) + " ( " + hr + " hr " + min + " min ) ");
                c.insets = new Insets(2,20,20,20);
                c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                c.ipadx = 5;
                c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                c.weighty = 0.0;
                c.gridx = column;
                c.gridy = row;
                c.gridwidth = 2;
                pane.add(time,c);
                
                // cancel flight button
                JButton cancelF = new JButton("Cancel Flight");
                cancelF.setForeground(Color.WHITE);
                cancelF.setBackground(Color.RED);
                cancelF.setPreferredSize(new Dimension(40,20));
                c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
                c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
                c.ipadx = 5;
                c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
                c.weighty = 0.0;
                c.gridx = column + 2;
                c.gridy = row;
                c.gridwidth = GridBagConstraints.REMAINDER;
                cancelF.addActionListener(new cancelFlightListener(f));
                pane.add(cancelF,c);
                row++;  // next row
            }
        }
    }
    
    public final void retrieveAllFlight() throws ParseException, IOException {  // retrieve all flights
        // Read all existing flight
        InputStream in = getClass().getResourceAsStream("allFlightDetails.txt");
        BufferedReader get = new BufferedReader(new InputStreamReader(in));
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd MMM yyyy HH:mm");	// dateTime format
        
        Flight temp_details;
        
        String a,b,c,d,e,f,g;
        int flight_no;
        Date date_time_depart;  // input
        Date ETA;
        double duration;
        Location departure; // from input
        Location destination;   // from input
        int passengers;
        
        while((a = get.readLine()) != null){    // read flight_no
            b = get.readLine(); // read date_time_depart
            c = get.readLine(); // read ETA
            d = get.readLine(); // read duration
            e = get.readLine(); // read departure
            f = get.readLine(); // read destination
            g = get.readLine(); // read passengers
            
            flight_no = Integer.parseInt(a);    // 1
            date_time_depart = (Date)dateTimeFormatter.parse(b);  // 2
            ETA = (Date)dateTimeFormatter.parse(c);   // 3
            duration = Double.parseDouble(d);   // 4
            departure = Enum.valueOf(Location.class, e); // 5
            destination = Enum.valueOf(Location.class, f);   // 6
            passengers = Integer.parseInt(g);   // 7
            
            // Store in the arraylist
            Flight temp_flight = new Flight();  // always fresh instance!
            temp_flight.storeTempFlightDetails(flight_no, date_time_depart, ETA, duration, departure, destination, passengers);
            flightList.add(temp_flight);
        }
    }
    
    private class cancelFlightListener implements ActionListener {
        private int flight_no_cancel;
        public cancelFlightListener(int f){
            flight_no_cancel = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            admCancelFlight cf = new admCancelFlight(flight_no_cancel, flightList, frame);
        }
    }
    
    private class showPassengersListener implements ActionListener {    // get all booking details
        private int flight_no_ref;
        public showPassengersListener(int f){
            flight_no_ref = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                showPassengersPage showP = new showPassengersPage(flight_no_ref);
            } catch (IOException ex) {
                Logger.getLogger(admCheckFlight.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
