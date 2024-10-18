package src;

public class Technician extends Provider{
    private int ratePerVisit;

    public Technician(Profile profile, Location location, int ratePerVisit){
        super(profile, location);
        this.ratePerVisit = ratePerVisit;
    }
    @Override
    public int rate() {
        return ratePerVisit;
    }

    @Override
    public String toString(){
        Location location = super.getLocation();
        String locationInfo ="";

        if(location == Location.BRIDGEWATER){
            locationInfo = "BRIDGEWATER, " + super.getLocation().getCounty() + " " + super.getLocation().getZipCode();
        }
        else if(location == Location.EDISON){
            locationInfo = "EDISON, " + super.getLocation().getCounty() + " " + super.getLocation().getZipCode();
        }
        else if(location == Location.PISCATAWAY){
            locationInfo = "PISCATAWAY, " + super.getLocation().getCounty() + " " + super.getLocation().getZipCode();
        }
        else if(location == Location.PRINCETON){
            locationInfo = "PRINCETON, " + super.getLocation().getCounty() + " " + super.getLocation().getZipCode();
        }
        else if(location == Location.MORRISTOWN){
            locationInfo = "MORRISTOWN, " + super.getLocation().getCounty() + " " + super.getLocation().getZipCode();
        }
        else if(location == Location.CLARK){
            locationInfo = "CLARK, " + super.getLocation().getCounty() + " " + super.getLocation().getZipCode();
        }
        return "[" +profile.getFirstName() + " " + profile.getLastName() + " " + profile.getDob() + ", " + locationInfo + "] [rate: " + ratePerVisit + "]";
    }

//    public int getRatePerVisit(){
//        return ratePerVisit;
//    }
}
