package src;

public class Doctor extends Provider {
    private Specialty specialty;//encapsulate the rate per visit based on specialty
    private String npi; //National Provider Identification unique to the doctor


    public Doctor(Profile profile, Specialty specialty, String npi, Location location){
        super(profile, location);
        this.specialty = specialty;
        this.npi = npi;
    }

    @Override
    public int rate() {
        return specialty.getCharge();
    }

    public Specialty getSpecialty(){
        return specialty;
    }

    public String getNpi(){
        return npi;
    }
}
