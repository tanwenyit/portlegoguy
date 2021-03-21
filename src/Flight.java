/*
System by PortLegoGuy
 */
import java.io.*;
import java.text.SimpleDateFormat;  
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

class Flight {
    ArrayList<Flight> flightList;
    private int flight_no;
    private Date date_time_depart;  // input
    private Date ETA;
    private double duration;
    private Location departure; // from input
    private Location destination;   // from input
    private int passengers;
    
    public Flight(){}
    
    public void newFlight(Location dep, Location des, Date date_f) {    // Add new flight
        // initialize new flight details
        date_time_depart = date_f;
        departure = dep;
        destination = des;
        passengers = 0;
    }
    
    public void searchFlight(Location dep, Location des, Date d){
        departure = dep;
        destination = des;
        date_time_depart = d;
    }
    
    public void storeTempFlightDetails(int a, Date b, Date c, double d, Location e, Location f, int g){
        flight_no = a;
        date_time_depart = b;
        ETA = c;
        duration = d;
        departure = e;
        destination = f;
        passengers = g;
    }
    
    public void setFlightNum() throws IOException {
        // Read flight no accumulator from text file
        InputStream in = getClass().getResourceAsStream("flight_no_accumulate.txt");
        Scanner get = new Scanner(in);
        //Scanner get = new Scanner(new FileReader("flight_no_accumulate.txt"));
        PrintWriter update = new PrintWriter(new FileWriter("./src/flight_no_accumulate.txt"));
        
        // Assign new flight number to the new flight
        flight_no = get.nextInt();
        flight_no++;
        
        // write back to the text file
        update.print(flight_no);
        
        // Close file
        get.close();
        update.close();
        
        writeNewFlight();
    }
    
    public void writeNewFlight() throws IOException {
        // Write the new flight details to the text file (will append at the end of the current file)
        PrintWriter save = new PrintWriter(new FileWriter("./src/allFlightDetails.txt", true));
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd MMM yyyy HH:mm");	// dateTime format
        
        save.println(flight_no);
        save.println(dateTimeFormatter.format(date_time_depart));
        save.println(dateTimeFormatter.format(ETA));
        save.println(duration);
        save.println(departure);
        save.println(destination);
        save.println(passengers);
        
        save.close();
    }
    
    public void rewriteAllFlights(ArrayList fList) throws IOException{
        // Rewrite the nall existing flights' details (with updated passengers number) to the text file
        flightList = fList;
        PrintWriter save = new PrintWriter(new FileWriter("./src/allFlightDetails.txt"));
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd MMM yyyy HH:mm");	// dateTime format
        
        for(Flight fl : flightList){
            save.println(fl.flight_no);
            save.println(dateTimeFormatter.format(fl.date_time_depart));
            save.println(dateTimeFormatter.format(fl.ETA));
            save.println(fl.duration);
            save.println(fl.departure);
            save.println(fl.destination);
            save.println(fl.passengers);
        }
        
        save.close();
    }
    
    public void setTimeDepart(Date t){
        date_time_depart = t;
        duration = calcETA();
    }
    
    public double calcETA(){
        double start = departure.getDistance();
        double end = destination.getDistance();
        double min, max, max_val, min_val;
        double dur = 0;
        
        if(start > end){
            max = start;
            min = end;
        }
        else{
            max = end;
            min = start;
        }
        
        if((min >= 0 && max >= 0) || (min < 0 && max < 0)){ // take max_val - min_val if both are >= 0 or < 0
            max_val = max + min;
            min_val = max - min;
            double temp = 0;
            
            if( min == 0 || max == 0 ){
                temp = max_val;
            }
            else{
                temp = max_val - min_val;
            }
            dur = (temp/600.0)*60;  // get total duration in minute
        }
        else if((min >= 0 && max < 0) || (min < 0 && max >= 0)){   // take max_val
            max_val = max - min;
            dur = (max_val/600.0)*60;
        }
        
        if(dur < 0)
            dur *= -1;
        
        System.out.println(dur);
        
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date_time_depart);
        cal.add(Calendar.MINUTE, (int)dur);
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");	// date format
	SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");			// time format
        ETA = cal.getTime();
        System.out.println("Start: " + dateFormatter.format(date_time_depart) + " " + timeFormatter.format(date_time_depart));
        System.out.println("End: " + dateFormatter.format(ETA) + " " + timeFormatter.format(ETA));
        
        return dur;
    }
    
    public int getFlightNum(){
        return flight_no;
    }
    
    public Location getDep(){
        return departure;
    }
    
    public Location getDes(){
        return destination;
    }
    
    public Date getDateTimeDepart(){
        return date_time_depart;
    }
    
    public Date getETA(){
        return ETA;
    }
    
    public double getDuration(){
        return duration;
    }
    
    public int getPassengers(){
        return passengers;
    }
    
    public void setPassengersLeft(int p){
        passengers += p;
    }
}
