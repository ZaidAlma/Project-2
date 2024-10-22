package src;

/**
 *
 * @author Zaid Almadani
 */

public abstract class Provider extends Person{
    private Location location;
    public abstract int rate();

    public Provider(Profile profile, Location location){
        super(profile);
        this.location = location;
    }

    public Location getLocation(){
        return location;
    }

}


