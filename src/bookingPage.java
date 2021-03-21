/*
System by PortLegoGuy
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class bookingPage extends JFrame {
    JFrame frame;
    JFrame prev_frame;
    JLabel aduNum;
    JLabel chdNum;
    private int flight_num;
    private ArrayList<Flight> flightList;
    // will be used to store in text file
    private Flight temp_flight;
    private int total_num;
    private int adult;
    private int child;
    
    public bookingPage(int f, ArrayList list, JFrame prev){
        prev_frame = prev;
        
        flight_num = f;
        flightList = list;
        total_num = 1;
        adult = 1;
        child = 0;
        
        // get the related flight details
        for(Flight a : flightList){
            if(a.getFlightNum() == flight_num){
                temp_flight = a;
            }
        }
        
        frame = new JFrame("Booking Flight");
        
        // add components required to the frame
        addComponentsToPane(frame.getContentPane());
        // pack the panel with the frame before displaying
        frame.pack();
        
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700,500);
        frame.setResizable(false);  // Prevent Resize window
        frame.setVisible(true);
    }
    
    public final void addComponentsToPane(Container pane) {
        pane.setLayout(new GridBagLayout());     // new grid bag layout
        GridBagConstraints c = new GridBagConstraints();    // the constraints for each component
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");	// date format
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");	// time format
        
        //display the flight details for double confirmation
        JLabel current = new JLabel("You are currently booking:");
        current.setFont(new Font("calibri",Font.BOLD,18));
        //c.anchor = GridBagConstraints.BASELINE;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,5,10);
        c.ipady = 10;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;    // index of column = 0
        c.gridy = 0;    // index of row = 0
        c.gridwidth = GridBagConstraints.REMAINDER;    // column span till the end
        pane.add(current,c);
        
        JLabel start = new JLabel(temp_flight.getDep().getIATA(),SwingConstants.RIGHT);
        start.setFont(new Font("Calibri",Font.BOLD,24));
        start.setForeground(Color.BLACK);
        c.insets = new Insets(10,20,5,5);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 1.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 1.0;
        c.gridx = 0;    // index of column = 0
        c.gridy = 1;    // index of row = 1
        c.gridwidth = 1;    // column span
        pane.add(start,c);              
            
        JLabel to = new JLabel("->",SwingConstants.CENTER);
        to.setFont(new Font("Calibri",Font.BOLD,24));
        to.setForeground(Color.BLACK);
        c.insets = new Insets(10,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 1.0;    // Destance between row and column (in percentage max 1.0 min 0.0
        c.weighty = 1.0;
        c.gridx = 1;    // index of column = 1
        c.gridy = 1;    // index of row = 0
        pane.add(to,c);
                                        
        JLabel end = new JLabel(temp_flight.getDes().getIATA(),SwingConstants.LEFT);
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
            
        JLabel dep = new JLabel("Departure: " + temp_flight.getDep().getAirport() + " ( " + temp_flight.getDep().getLocation() + ", " + temp_flight.getDep().getState() + " ) ");
        c.insets = new Insets(5,20,2,20);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        pane.add(dep,c);
                 
        JLabel des = new JLabel("Destination: " + temp_flight.getDes().getAirport() + " ( " + temp_flight.getDes().getLocation() + ", " + temp_flight.getDes().getState() + " ) ");
        c.insets = new Insets(2,20,2,20);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = GridBagConstraints.REMAINDER;
        pane.add(des,c);
                    
        JLabel date = new JLabel("Depart Date: " + dateFormatter.format(temp_flight.getDateTimeDepart()));
        c.insets = new Insets(2,20,2,20);
        c.fill = GridBagConstraints.HORIZONTAL; // fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        pane.add(date,c);
                   
        // Calculate duration in hour and minute
        int hr = (int)temp_flight.getDuration() / 60;       // get hour
        int min = (int)temp_flight.getDuration() % 60;    // get minute
                   
        JLabel time = new JLabel("Duration: " + timeFormatter.format(temp_flight.getDateTimeDepart()) + " - " + timeFormatter.format(temp_flight.getETA()) + " ( " + hr + " hr " + min + " min ) ");
        c.insets = new Insets(2,20,20,20);
        c.fill = GridBagConstraints.HORIZONTAL;// fill up the row entirely
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        pane.add(time,c);
        
        // number of adult
        JLabel adu = new JLabel("Adult: ", SwingConstants.RIGHT);
        c.insets = new Insets(10,10,10,10); // insets(top, left, bottom, right)
        c.fill = GridBagConstraints.NONE; // fill up the row entirely
        c.ipady = 10;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;   
        c.gridy = 6;   
        c.gridwidth = 1;    
        pane.add(adu,c);
        
        JButton aduSub = new JButton("-");
        aduSub.setFont(new Font("Calibri",Font.BOLD,18));
        aduSub.setPreferredSize(new Dimension(50,50));
        c.insets = new Insets(10,10,10,0);
        c.fill = GridBagConstraints.NONE;
        c.ipadx = 5;
        c.ipady = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 1;    
        c.gridy = 6;    
        c.gridwidth = 1;    
        aduSub.addActionListener(new decreaseAdultListener());
        pane.add(aduSub,c);
        
        aduNum = new JLabel(Integer.toString(adult), SwingConstants.LEFT);
        aduNum.setFont(new Font("calibri", Font.BOLD,18));
        c.insets = new Insets(10,0,10,0);
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 2;    
        c.gridy = 6;    
        c.gridwidth = 1;    
        pane.add(aduNum,c);
        
        JButton aduAdd = new JButton("+");
        aduAdd.setFont(new Font("Calibri",Font.BOLD,18));
        aduAdd.setPreferredSize(new Dimension(50,50));
        c.insets = new Insets(10,0,10,10);
        c.ipadx = 5;
        c.ipady = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 3;    
        c.gridy = 6;    
        c.gridwidth = 1;    
        aduAdd.addActionListener(new increaseAdultListener());
        pane.add(aduAdd,c);
        
        // number of child
        JLabel chd = new JLabel("Child: ", SwingConstants.RIGHT);
        c.insets = new Insets(10,10,10,10); // insets(top, left, bottom, right)
        c.fill = GridBagConstraints.NONE; // fill up the row entirely
        c.ipady = 10;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;   
        c.gridy = 7;   
        c.gridwidth = 1;    
        pane.add(chd,c);
        
        JButton chdSub = new JButton("-");
        chdSub.setFont(new Font("Calibri",Font.BOLD,18));
        chdSub.setPreferredSize(new Dimension(50,50));
        c.insets = new Insets(10,10,10,0);
        c.fill = GridBagConstraints.NONE;
        c.ipadx = 5;
        c.ipady = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 1;    
        c.gridy = 7;    
        c.gridwidth = 1;    
        chdSub.addActionListener(new decreaseChildListener());
        pane.add(chdSub,c);
        
        chdNum = new JLabel(Integer.toString(child), SwingConstants.LEFT);
        chdNum.setFont(new Font("calibri", Font.BOLD,18));
        c.insets = new Insets(10,0,10,0);
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 2;    
        c.gridy = 7;    
        c.gridwidth = 1;    
        pane.add(chdNum,c);
        
        JButton chdAdd = new JButton("+");
        chdAdd.setFont(new Font("Calibri",Font.BOLD,18));
        aduAdd.setPreferredSize(new Dimension(50,50));
        c.insets = new Insets(10,0,10,10);
        c.ipadx = 5;
        c.ipady = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 3;    
        c.gridy = 7;    
        c.gridwidth = 1;    
        chdAdd.addActionListener(new increaseChildListener());
        pane.add(chdAdd,c);
        
        // next to info filling page
        JButton next = new JButton("Next");
        next.setForeground(Color.WHITE);
        next.setBackground(Color.BLUE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(15,10,20,10);
        c.ipadx = 5;
        c.ipady = 5;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = 0;    
        c.gridy = 8;    
        c.gridwidth = GridBagConstraints.REMAINDER;
        next.addActionListener(new nextBookingInfoListener());
        pane.add(next,c);
        
    }
    
    private class nextBookingInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Booking b = new Booking();  // this will be keep using until the booking info has been written in the text file
            b.newBooking(temp_flight, total_num, adult, child);
            
            // booking info form
            bookingInfoForm bForm = new bookingInfoForm(b, flightList, frame, prev_frame);
        }
    }
    
    private class increaseAdultListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(adult > 0 && total_num < (60 - temp_flight.getPassengers())){
                total_num++;
                adult++;
                aduNum.setText(Integer.toString(adult));    // update the number display
            }
            else{
                if(total_num == (60 - temp_flight.getPassengers()))
                    JOptionPane.showMessageDialog(null,"You have reached maximum remaining seat!");
            }
        }
    }
    
    private class decreaseAdultListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(adult > 1){
                total_num--;
                adult--;
                aduNum.setText(Integer.toString(adult));    // update the number display
            }
            else
                JOptionPane.showMessageDialog(null,"At least ONE adult to book the flight!");
        }
    }
    
    private class increaseChildListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(child >= 0 && total_num < (60 - temp_flight.getPassengers())){
                total_num++;
                child++;
                chdNum.setText(Integer.toString(child));    // update the number display
            }
            else{
                if(total_num == (60 - temp_flight.getPassengers()))
                    JOptionPane.showMessageDialog(null,"You have reached maximum remaining seat!");
            }
        }
    }
    
    private class decreaseChildListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(child > 0){
                total_num--;
                child--;
                chdNum.setText(Integer.toString(child));    // update the number display
            }
            else
                JOptionPane.showMessageDialog(null,"Minimum number reached");
        }
    }
}
