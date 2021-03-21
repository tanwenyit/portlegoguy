/*
System by PortLegoGuy
 */
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

class Booking {
    ArrayList<Booking> bookList;
    ArrayList<Flight> fList;
    private int booking_ref_no; // cumulative variable and will be saved in text file independently.
    private Date booking_date;
    private int contact_ref_no; // references 'Contact' class
    private int flight_ref_num;
    private int total_person;
    private int adult_num;
    private int child_num;
    private String[] name;  // depends on total_person, will be initialized.
    private String[] family_name;
    private Date[] birthdate;
    private String[] passenger_type;
    private String[] sex;
    
    public Booking(){}
    
    public void newBooking(Flight f, int total, int a, int c){
        flight_ref_num = f.getFlightNum();
        total_person = total;
        adult_num = a;
        child_num = c;
        name = new String[total];
        family_name = new String[total];
        birthdate = new Date[total];
        passenger_type = new String[total];
        sex = new String[total];
    }
    
    public void saveBookingInfo(String[] b_name, String[] b_fname, Calendar[] b_bd, String[] b_sex, int crn) throws IOException{
        name = b_name;
        family_name = b_fname;
        sex = b_sex;
        
        // assign passenger_type
        int i;
        for(i=0 ; i < adult_num ; i++){
            passenger_type[i] = "ADULT";
        }
        
        while(i < total_person){
            passenger_type[i] = "CHILD";
            i++;
        }
        
        // locate birthdate
        for(int j=0 ; j < total_person ; j++){
            birthdate[j] = b_bd[j].getTime();
        }
        
        contact_ref_no = crn;
        
        // set new booking_ref_no and assign new booking dateTime
        setNewBookingRefNo();
        writeNewBooking();
    }
    
    public ArrayList retrieveBookingList() throws ParseException, IOException {
        // Read all existing booking list
        InputStream in = getClass().getResourceAsStream("allBookingDetails.txt");
        BufferedReader get = new BufferedReader(new InputStreamReader(in));
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd MMM yyyy HH:mm");	// dateTime format
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");	// date format
        
        String a,b,c,d,e,f,g;   // essential data
        String h,j,k,l,m;       // passengers' data
        String h1,j1,l1,m1;
        int a1,c1,d1,e1,f1,g1;
        Date b1,k1;
        
        bookList = new ArrayList<Booking>();
        Booking temp_book = new Booking();
        
        while((a = get.readLine()) != null){ // read booking_ref_no
            
            b = get.readLine(); // read booking_date
            c = get.readLine(); // read contact_ref_no
            d = get.readLine(); // read flight_ref_num
            e = get.readLine(); // read total_person
            f = get.readLine(); // read adult_num
            g = get.readLine(); // read child_num
            
            a1 = Integer.parseInt(a);
            b1 = (Date)dateTimeFormatter.parse(b);
            c1 = Integer.parseInt(c);
            d1 = Integer.parseInt(d);
            e1 = Integer.parseInt(e);
            f1 = Integer.parseInt(f);    
            g1 = Integer.parseInt(g);
                    
            temp_book.setBookingListItem(a1,b1,c1,d1,e1,f1,g1);
            
            // read all adults (at least 1) and childs (if any)
            int i;
            for(i=0 ; i < f1 ; i++){    // adult first
                h = get.readLine(); // name
                j = get.readLine(); // family name
                k = get.readLine(); // birthdate
                l = get.readLine(); // p_type
                m = get.readLine(); // sex
                
                
                k1 = (Date)dateFormatter.parse(k);
                
                temp_book.setBookingListPersonDetails(i,temp_book,h,j,k1,l,m);
                
            }
            while(i < e1){
                h = get.readLine(); // name
                j = get.readLine(); // family name
                k = get.readLine(); // birthdate
                l = get.readLine(); // p_type
                m = get.readLine(); // sex
                
                
                k1 = (Date)dateFormatter.parse(k);
                
                temp_book.setBookingListPersonDetails(i,temp_book,h,j,k1,l,m);
                
                i++;
            }
            
            bookList.add(temp_book);
        }
        
        return bookList;
    }
    
    public void setBookingListPersonDetails(int index, Booking b, String h, String j, Date k1, String l, String m){
        b.name[index] = h;
        b.family_name[index] = j;
        b.birthdate[index] = k1;
        b.passenger_type[index] = l;
        b.sex[index] = m;
    }
    
    public void setBookingListItem(int a, Date b, int c, int d, int total, int adult, int child){
        
        booking_ref_no = a;
        booking_date = b;
        contact_ref_no = c;
        flight_ref_num = d;
        total_person = total;
        adult_num = adult;
        child_num = child;
        name = new String[total];
        family_name = new String[total];
        birthdate = new Date[total];
        passenger_type = new String[total];
        sex = new String[total];
    }
    
    public void writeNewBooking() throws IOException {
        // Write the new booking details to the text file (will append at the end of the current file)
        PrintWriter save = new PrintWriter(new FileWriter("./src/allBookingDetails.txt", true));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");	// date format
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd MMM yyyy HH:mm");	// dateTime format (for booking date & time only)
        
        save.println(booking_ref_no);
        save.println(dateTimeFormatter.format(booking_date));
        save.println(contact_ref_no);
        save.println(flight_ref_num);
        save.println(total_person);
        save.println(adult_num);
        save.println(child_num);
        
        int i;
        
        // save all adult details (at least one)
        for(i = 0 ; i < adult_num ; i++){
            save.println(name[i]);
            save.println(family_name[i]);
            save.println(dateFormatter.format(birthdate[i]));
            save.println(passenger_type[i]);
            save.println(sex[i]);
        }
        
        // save all child details (if any)
        while(i < total_person){
            save.println(name[i]);
            save.println(family_name[i]);
            save.println(dateFormatter.format(birthdate[i]));
            save.println(passenger_type[i]);
            save.println(sex[i]);
            i++;
        }
        
        save.close();
    }
    
    public void printInvoice(Contact contact, ArrayList<Flight> flightList) throws IOException{
        // print invoice to the customer
        String filename = "BK_" + Integer.toString(booking_ref_no) + ".txt";
        String userHomeFolder = System.getProperty("user.home") + "/Desktop";
        File textfile = new File(userHomeFolder,filename);  // print the file to user desktop
        
        PrintWriter save = new PrintWriter(new FileWriter(textfile));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");	// date format
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd MMM yyyy HH:mm");	// dateTime format (for booking date & time only)
        
        save.println("Flight Ticket Booking Invoice");
        save.println("-------------------------------------------------------------------");
        save.println("Booking References No.: " + booking_ref_no);
        save.println("Invoice Date          : " + dateTimeFormatter.format(booking_date));
        save.println("Total seat booked     : " + total_person);
        save.println("Adult: " + adult_num);
        save.println("Child: " + child_num);
        save.println("-------------------------------------------------------------------");
        save.println("Contact details");
        save.println("---------------");
        save.println("Name                  : " + contact.getName());
        save.println("Family name           : " + contact.getFName());
        save.println("Email Address         : " + contact.getEmail());
        save.println("Mobile Phone          : " + contact.getPhone());
        save.println("Gender                : " + contact.getSex());
        save.println("-------------------------------------------------------------------\n");
        save.println("Flight Details");
        save.println("--------------");
        
        for(Flight fl : flightList){
            if(fl.getFlightNum() == flight_ref_num){
                save.println("     " + fl.getDep() + " -> " + fl.getDes());
                save.println("Flight Number   : " + fl.getFlightNum());
                save.println("Date Time Depart: " + dateTimeFormatter.format(fl.getDateTimeDepart()));
                save.println("ETA             : " + dateTimeFormatter.format(fl.getETA()));
                save.println("Departure       : " + fl.getDep().getAirport() + " ( " + fl.getDep().getLocation() + ", " + fl.getDep().getState());
                save.println("Destination     : " + fl.getDes().getAirport() + " ( " + fl.getDes().getLocation() + ", " + fl.getDes().getState());
            }
        }
        
        save.println("-------------------------------------------------------------------\n");
        save.println("Seat(s) Details");
        save.println("---------------");
        
        // print all seats details
        int i;
        for(i = 0 ; i < adult_num ; i++){
            save.println("Adult " + (i+1));
            save.println("Name       : " + name[i]);
            save.println("Family Name: " + family_name[i]);
            save.println("Birthdate  : " + dateFormatter.format(birthdate[i]));
            save.println("Gender     : " + sex[i]);
            save.println("");
        }
        while(i < total_person){
            save.println("Child " + (i+1));
            save.println("Name       : " + name[i]);
            save.println("Family Name: " + family_name[i]);
            save.println("Birthdate  : " + dateFormatter.format(birthdate[i]));
            save.println("Gender     : " + sex[i]);
            save.println("");
            i++;
        }
        save.println("-------------------------------------------------------------------\n");
        save.println("DISCLAIMER: This document is not an actual flight ticket. Please use this document as check-in evidence for converting your booking to valid flight ticket(s)");
        save.println("FTBS developed by PortLegoGuy. All Rights Reserved.");
    }
    
    public void setNewBookingRefNo() throws IOException{
        // Read flight no accumulator from text file
        InputStream in = getClass().getResourceAsStream("booking_no_accumulate.txt");
        Scanner get = new Scanner(in);
        //Scanner get = new Scanner(new FileReader("flight_no_accumulate.txt"));
        PrintWriter update = new PrintWriter(new FileWriter("./src/booking_no_accumulate.txt"));
        
        // Assign new flight number to the new flight
        booking_ref_no = get.nextInt();
        booking_ref_no++;
        
        // write back to the text file
        update.print(booking_ref_no);
        
        // Close file
        get.close();
        update.close();
        
        // set new booking dateTime
        Calendar now = Calendar.getInstance();
        Date today = now.getTime();
        booking_date = today;
    }
    
    public int getTotalPerson(){
        return total_person;
    }
    
    public int getAdultNum(){
        return adult_num;
    }
    
    public int getChildNum(){
        return child_num;
    }
    
    public int getFlightRefNum(){
        return flight_ref_num;
    }
    
    public int getBookRefNum(){
        return booking_ref_no;
    }
    
    public int getContactRefNum(){
        return contact_ref_no;
    }
    
    public Date getBookDate(){
        return booking_date;
    }
    
    public String getName(int index){
        return name[index];
    }
    
    public String getFName(int index){
        return family_name[index];
    }
    
    public String getFullName(int index){
        return name[index] + " " + family_name[index];
    }
    
    public Date getBirthdate(int index){
        return birthdate[index];
    }
    
    public String getPassType(int index){
        return passenger_type[index];
    }
    
    public String getSex(int index){
        return sex[index];
    }
}
