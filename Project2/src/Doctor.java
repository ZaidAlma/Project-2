package src;

public class Doctor extends Provider {
    private Specialty specialty;//encapsulate the rate per visit based on specialty
    private String npi; //National Provider Identification unique to the doctor


    public Doctor(Profile profile, Location location, Specialty specialty, String npi){
        super(profile, location);
        this.specialty = specialty;
        this.npi = npi;
    }

    @Override
    public int rate() {
        return specialty.getCharge();
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
        return "[" + profile.getFirstName() + " " + profile.getLastName() + " " + profile.getDob() + ", " + locationInfo + "] ["+ specialty.getSpeciality() + " #" + npi + "]";
    }

    public Specialty getSpecialty(){
        return specialty;
    }

    public String getNpi(){
        return npi;
    }
}
