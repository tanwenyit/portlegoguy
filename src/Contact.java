/*
System by PortLegoGuy
 */
import java.io.*;
import java.text.ParseException;  
import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    ArrayList<Contact> contactList;
    private int contact_ref_no; // cumulative variable and will be saved in text file independently.
    private String name;
    private String family_name;
    private String email;
    private String phone;
    private String sex;
    
    public Contact(){}
    
    public void newContact(String c_name, String c_fname, String c_gender, String c_email, String c_phone) throws IOException{
        name = c_name;
        family_name = c_fname;
        email = c_email;
        phone = c_phone;
        sex = c_gender;
        
        setNewContactRefNo();   // assign new contact_ref_no
        writeNewContact();
    }
    
    public void writeNewContact() throws IOException {
        // Write the new contact details to the text file (will append at the end of the current file)
        PrintWriter save = new PrintWriter(new FileWriter("./src/contactDetails.txt", true));
        
        save.println(contact_ref_no);
        save.println(name);
        save.println(family_name);
        save.println(email);
        save.println(phone);
        save.println(sex);
        
        save.close();
    }
    
    public void setContactListItem(int a, String b, String c, String d, String e, String f){
        contact_ref_no = a;
        name = b;
        family_name = c;
        email = d;
        phone = e;
        sex = f;
    }
    
    public ArrayList retrieveContactList() throws ParseException, IOException {
        // Read all existing contact list
        InputStream in = getClass().getResourceAsStream("contactDetails.txt");
        BufferedReader get = new BufferedReader(new InputStreamReader(in));
        
        String a,b,c,d,e,f;
        contactList = new ArrayList<Contact>();
        Contact temp = new Contact();
        
        while((a = get.readLine()) != null){ // read contact_ref_no
            
            b = get.readLine(); // read name
            c = get.readLine(); // read family_name
            d = get.readLine(); // read email
            e = get.readLine(); // read phone
            f = get.readLine(); // read sex
            
            temp.setContactListItem(Integer.parseInt(a), b, c, d, e, f);
            contactList.add(temp);
        }
        
        return contactList;
    }
    
    public void setNewContactRefNo() throws IOException{
        // Read flight no accumulator from text file
        InputStream in = getClass().getResourceAsStream("contact_no_accumulate.txt");
        Scanner get = new Scanner(in);
        //Scanner get = new Scanner(new FileReader("flight_no_accumulate.txt"));
        PrintWriter update = new PrintWriter(new FileWriter("./src/contact_no_accumulate.txt"));
        
        // Assign new flight number to the new flight
        contact_ref_no = get.nextInt();
        contact_ref_no++;
        
        // write back to the text file
        update.print(contact_ref_no);
        
        // Close file
        get.close();
        update.close();
    }
    
    public int getContactRefNo(){
        return contact_ref_no;
    }
    
    public String getName(){
        return name;
    }
    
    public String getFName(){
        return family_name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public String getSex(){
        return sex;
    }
    
}
