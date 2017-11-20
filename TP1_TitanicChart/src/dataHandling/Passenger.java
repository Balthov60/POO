package dataHandling;

public class Passenger {

    private String id;
    private TravelingClass travelingClass;
    private boolean isAdult;
    private Gender gender;
    private boolean isSurvivor;

    public String getId() {
        return id;
    }
    public TravelingClass getTravelingClass() {
        return travelingClass;
    }
    public boolean isAdult() {
        return isAdult;
    }
    public Gender getGender() {
        return gender;
    }
    public boolean isSurvivor() {
        return isSurvivor;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setTravelingClass(TravelingClass travelingClass) {
        this.travelingClass = travelingClass;
    }
    public void setAdult(boolean adult) {
        isAdult = adult;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void setSurvivor(boolean survivor) {
        isSurvivor = survivor;
    }

    public void createFromString(String passengerData) {
        String[] passengerInfos = passengerData.split("(\\s+)");
        id = passengerInfos[0];
        travelingClass = TravelingClass.values()[Integer.parseInt(passengerInfos[1])];
        isAdult = Boolean.parseBoolean(passengerInfos[2]);
        gender = Gender.values()[Integer.parseInt(passengerInfos[3])];
        isSurvivor = Boolean.parseBoolean(passengerInfos[4]);
    }
}
