/*
System by PortLegoGuy
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;

class bookingInfoForm extends JFrame {
    JFrame frame, prev_frame, prev_frame_2;
    Booking bookingInfo;
    JDatePickerImpl datePicker;
    JComboBox<String> sex;
    JScrollPane scrollboard;    // enable the result to be scrollable
    ArrayList<Flight> flightList;
    
    HashMap<Integer,JTextField> nameList;
    HashMap<Integer,JTextField> fnameList;
    HashMap<Integer,JComboBox> genderList;
    HashMap<Integer,JDatePickerImpl> bdList;
    
    JTextField cnameB, cfnameB, emailB, phoneB;
    JComboBox cgender;
    int total;
    int adult, child;
    
    public bookingInfoForm(Booking b, ArrayList flight, JFrame f1, JFrame f2){
        bookingInfo = b;
        flightList = flight;
        prev_frame = f1;
        prev_frame_2 = f2;
        
        JPanel panel = new JPanel(new GridBagLayout());
        
        addComponentsToPane(panel);
        scrollboard = new JScrollPane(panel);
        
        // pack the panel with the frame before displaying
        frame = new JFrame("Booking Information");
        frame.getContentPane().add(scrollboard);
        frame.pack();
        
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,500);
        frame.setResizable(false);  // Prevent Resize window
        frame.setVisible(true);
    }
    
    public final void addComponentsToPane(Container pane) {
        GridBagConstraints c = new GridBagConstraints();    // the constraints for each component
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");	// date format
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");	// time format
        
        nameList = new HashMap<Integer,JTextField>();
        fnameList = new HashMap<Integer,JTextField>();
        genderList = new HashMap<Integer,JComboBox>();
        bdList = new HashMap<Integer,JDatePickerImpl>();
        
        String[] gender = {"MALE","FEMALE"};
        
        total = bookingInfo.getTotalPerson();
        adult = bookingInfo.getAdultNum();
        child = bookingInfo.getChildNum();
        int i, row = 0, column = 0;
        
        // adult info form
        for(i=0 ; i < adult ; i++){
            
            JLabel heading = new JLabel("Adult " + (i+1));
            heading.setFont(new Font("Calibri",Font.BOLD,22));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(20,10,5,10);
            c.ipady = 10;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;    
            c.gridwidth = GridBagConstraints.REMAINDER;    // column span till the end
            pane.add(heading,c);
            row++;  // next row
            
            JLabel name = new JLabel("Name: ",SwingConstants.LEFT);
            name.setFont(new Font("Calibri", Font.ITALIC,16));
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            c.gridwidth = 1;    // column span till the end
            pane.add(name,c);
            column++;
            
            JTextField nameB = new JTextField(20);
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(nameB,c);
            nameList.put(i, nameB);
            column++;
            
            JLabel fname = new JLabel("Family Name/ Surname: ",SwingConstants.LEFT);
            fname.setFont(new Font("Calibri", Font.ITALIC,16));
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(fname,c);
            column++;
            
            JTextField fnameB = new JTextField(20);
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(fnameB,c);
            fnameList.put(i,fnameB);
            row++; column = 0;
            
            JLabel bdate = new JLabel("Birth Date",SwingConstants.LEFT);
            bdate.setFont(new Font("Calibri", Font.ITALIC,16));
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(bdate,c);
            column++;
            
            // pick birth date
            UtilCalendarModel model = new UtilCalendarModel();
            JDatePanelImpl datePanel = new JDatePanelImpl(model);
            datePicker = new JDatePickerImpl(datePanel);
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(datePicker,c);
            bdList.put(i,datePicker);
            column++;
            
            // pick gender
            JLabel s = new JLabel("Gender: ");
            s.setFont(new Font("Calibri", Font.ITALIC,16));
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(s,c);
            column++;
            
            sex = new JComboBox(gender);
            sex.setSelectedIndex(-1);
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(sex,c);
            genderList.put(i,sex);
            row++; column = 0;
        }
        
        // child info form (the rest of the counter will be the child number)
        int cd = 1;
        while(i < total){
            
            JLabel heading = new JLabel("Child " + cd);
            heading.setFont(new Font("Calibri",Font.BOLD,22));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(20,10,5,10);
            c.ipady = 10;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;    
            c.gridwidth = GridBagConstraints.REMAINDER;    // column span till the end
            pane.add(heading,c);
            row++;  // next row
            
            JLabel name = new JLabel("Name: ",SwingConstants.LEFT);
            name.setFont(new Font("Calibri", Font.ITALIC,16));
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            c.gridwidth = 1;    // column span till the end
            pane.add(name,c);
            column++;
            
            JTextField nameB = new JTextField(20);
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(nameB,c);
            nameList.put(i, nameB);
            column++;
            
            JLabel fname = new JLabel("Family Name/ Surname: ",SwingConstants.LEFT);
            fname.setFont(new Font("Calibri", Font.ITALIC,16));
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(fname,c);
            column++;
            
            JTextField fnameB = new JTextField(20);
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(fnameB,c);
            fnameList.put(i,fnameB);
            row++; column = 0;
            
            JLabel bdate = new JLabel("Birth Date",SwingConstants.LEFT);
            bdate.setFont(new Font("Calibri", Font.ITALIC,16));
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(bdate,c);
            column++;
            
            // pick birth date
            UtilCalendarModel model = new UtilCalendarModel();
            JDatePanelImpl datePanel = new JDatePanelImpl(model);
            datePicker = new JDatePickerImpl(datePanel);
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(datePicker,c);
            bdList.put(i,datePicker);
            column++;
            
            // pick gender
            JLabel s = new JLabel("Gender: ");
            s.setFont(new Font("Calibri", Font.ITALIC,16));
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(s,c);
            column++;
            
            sex = new JComboBox(gender);
            sex.setSelectedIndex(-1);
            c.insets = new Insets(0,10,0,10);
            c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
            c.ipadx = 0;
            c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
            c.weighty = 0.0;
            c.gridx = column;    
            c.gridy = row;
            pane.add(sex,c);
            genderList.put(i,sex);
            row++; column = 0;
            i++; cd++;
        }
        // Provide contact details
        JButton cBanner = new JButton("Contact Details");
        cBanner.setForeground(Color.BLACK);
        cBanner.setBackground(Color.YELLOW);
        c.insets = new Insets(0,10,0,10);
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    
        c.gridy = row;
        c.gridwidth = GridBagConstraints.REMAINDER;
        pane.add(cBanner,c);
        row++;
        
        JLabel cname = new JLabel("Name: ");
        cname.setFont(new Font("Calibri", Font.ITALIC,16));
        c.insets = new Insets(0,10,0,10);
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    
        c.gridy = row;
        c.gridwidth = 1;    // column span till the end
        pane.add(cname,c);
        column++;
        
        cnameB = new JTextField(20);
        c.insets = new Insets(0,10,0,10);
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    
        c.gridy = row;
        c.gridwidth = 1;    // column span till the end
        pane.add(cnameB,c);
        column++;
        
        JLabel cfname = new JLabel("Family Name/ Surname: ");
        cfname.setFont(new Font("Calibri", Font.ITALIC,16));
        c.insets = new Insets(0,10,0,10);
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    
        c.gridy = row;
        c.gridwidth = 1;    // column span till the end
        pane.add(cfname,c);
        column++;
        
        cfnameB = new JTextField(20);
        c.insets = new Insets(0,10,0,10);
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    
        c.gridy = row;
        c.gridwidth = 1;    // column span till the end
        pane.add(cfnameB,c);
        row++; column = 0;
        
        JLabel email = new JLabel("Email Address: ");
        email.setFont(new Font("Calibri", Font.ITALIC,16));
        c.insets = new Insets(0,10,0,10);
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    
        c.gridy = row;
        c.gridwidth = 1;    // column span till the end
        pane.add(email,c);
        column++;
        
        emailB = new JTextField(20);
        c.insets = new Insets(0,10,0,10);
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    
        c.gridy = row;
        c.gridwidth = 1;    // column span till the end
        pane.add(emailB,c);
        column++;
        
        JLabel phone = new JLabel("Mobile Phone: ");
        phone.setFont(new Font("Calibri", Font.ITALIC,16));
        c.insets = new Insets(0,10,0,10);
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    
        c.gridy = row;
        c.gridwidth = 1;    // column span till the end
        pane.add(phone,c);
        column++;
        
        phoneB = new JTextField(20);
        c.insets = new Insets(0,10,0,10);
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    
        c.gridy = row;
        c.gridwidth = 1;    // column span till the end
        pane.add(phoneB,c);
        row++; column = 0;
        
        JLabel csex = new JLabel("Gender: ");
        phone.setFont(new Font("Calibri", Font.ITALIC,16));
        c.insets = new Insets(0,10,0,10);
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    
        c.gridy = row;
        c.gridwidth = 1;    // column span till the end
        pane.add(csex,c);
        column++;
        
        cgender = new JComboBox(gender);
        cgender.setSelectedIndex(-1);
        c.insets = new Insets(0,10,0,10);
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    
        c.gridy = row;
        c.gridwidth = 1;    // column span till the end
        pane.add(cgender,c);
        row++; column = 0;
        
        // Add button for saving the details
        JButton done = new JButton("Done");
        done.setBackground(Color.GREEN);
        done.setForeground(Color.BLACK);
        c.insets = new Insets(0,10,0,10);
        c.ipady = 5;   // Make the title tall; ipad: Inner padding (x or y)
        c.ipadx = 0;
        c.weightx = 0.0;    // Destance between row and column (in percentage max 1.0 min 0.0)
        c.weighty = 0.0;
        c.gridx = column;    
        c.gridy = row;
        c.gridwidth = GridBagConstraints.REMAINDER;    // column span till the end
        done.addActionListener(new doneInfoListener());
        pane.add(done,c);
    }
    
    private class doneInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // retrieve all info from the adult and child list
            String[] temp_name = new String[total];
            String[] temp_fname = new String[total];
            Calendar[] temp_bd = new Calendar[total];
            String[] temp_sex = new String[total];
            
            String c_name, c_fname, c_gender, c_email, c_phone;
            
            for(int j=0 ; j<total ; j++){
                temp_name[j] = nameList.get(j).getText();
                temp_fname[j] = fnameList.get(j).getText();
                temp_sex[j] = (String)genderList.get(j).getSelectedItem();
                temp_bd[j] = (Calendar)bdList.get(j).getModel().getValue();
            }
            
            c_name = cnameB.getText();
            c_fname = cfnameB.getText();
            c_gender = (String)cgender.getSelectedItem();
            c_email = emailB.getText();
            c_phone = phoneB.getText();
            
            try {   // save the booking details and update the flight's leftovers
                Contact cont = new Contact();
                cont.newContact(c_name, c_fname, c_gender, c_email, c_phone);
                bookingInfo.saveBookingInfo(temp_name, temp_fname, temp_bd, temp_sex, cont.getContactRefNo());
                
                // print invoice to the customer
                bookingInfo.printInvoice(cont, flightList);
                
                for(Flight fl : flightList){
                    if(fl.getFlightNum() == bookingInfo.getFlightRefNum()){
                        fl.setPassengersLeft(bookingInfo.getTotalPerson());
                    }
                }
                
                Flight temp_flight = new Flight();
                temp_flight.rewriteAllFlights(flightList);  // should update the text file with new passengers number
                
                // once everything has been updated, close the last 3 frames!
                frame.dispose();
                prev_frame.dispose();
                prev_frame_2.dispose();
            } 
            catch (IOException ex) {
                Logger.getLogger(bookingInfoForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
