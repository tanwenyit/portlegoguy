/*
System by PortLegoGuy
 */
// Total 15 locations, all Malaysia's local flights
enum Location {
    
    KUL("KUL","Kuala Lumpur International Airport (KLIA)","Kuala Lumpur","Kuala Lumpur",319),
    JHB("JHB","Senai International Airport","Senai","Johor",0),
    AOR("AOR","Sultan Abdul Halim Airport","Alor Setar","Kedah",780),
    LGK("LGK","Langkawi International Airport","Langkawi","Kedah",780),
    KBR("KBR","Sultan Ismail Petra Airport","Kota Bharu","Kelantan",690),
    MKZ("MKZ","Melaka Airport","Melaka","Melaka",210),
    KUA("KUA","Kuantan Airport","Kuantan","Pahang",333),
    PEN("PEN","Penang International Airport","Bayan Lepas", "Pulau Pinang",685),
    IPH("IPH","Sultan Azlan Shah Airport","Ipoh","Perak",531),
    SZB("SZB","Sultan Abdul Aziz Shah International Airport","Subang","Selangor",338),
    TGG("TGG","Sultan Mahmud Airport","Kuala Terengganu","Terengganu",554),
    BKI("BKI","Kota Kinabalu International Airport (KKIA)","Kota Kinabalu","Sabah",-1454),
    KCH("KCH","Kuching International Airport","Kuching","Sarawak",-745),
    SBW("SBW","Sibu Airport","Sibu","Sarawak",-928),
    MYY("MYY","Miri Airport","Miri","Sarawak",-1182);
    
    private String iata;
    private String airport;
    private String location;
    private String state;
    private int distance;

    private Location(String i, String a, String l, String s, int d){
        iata = i;
        airport = a;
        location = l;
        state = s;
        distance = d;
    }
    public String getAirport(){
        return airport;
    }
    public String getLocation(){
        return location;
    }
    public String getState(){
        return state;
    }
    public int getDistance(){
        return distance;
    }
    public String getIATA(){
        return iata;
    }
}
