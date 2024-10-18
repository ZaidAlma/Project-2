package src;
/**
 * This enum represents the different medical specialties offered at the clinic, each associated with a specific charge.
 * It provides a method to retrieve the charge for each specialty.
 *
 * @author Zaid Almadani
 */


public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350); //means nothing to set data for

    private final int charge;

    Specialty(int charge){
        this.charge = charge;
    }


    public int getCharge(){
        return charge;
    }


    public String getSpeciality() {
        if(charge == 250){
            return "FAMILY";
        }
        else if(charge == 300){
            return "PEDIATRICIAN";
        }
        else if(charge == 350){
            return "ALLERGIST";
        }
        return null;
    }
}