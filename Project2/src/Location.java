package src;
/**
 * This enum represents the locations where the clinic operates, each associated with a county and ZIP code.
 * It provides access to the county and ZIP code for each location.
 *
 * @author Zaid Almadani
 */


public enum Location {
    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mercer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");
    private final String county;
    private final String zip;

    Location(String county, String zip){
        this.county = county;
        this.zip = zip;
    }

    public String getCounty(){
        return county;
    }

    public String getZipCode(){
        return zip;
    }
}