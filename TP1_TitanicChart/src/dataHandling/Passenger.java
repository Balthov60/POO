package dataHandling;

import java.util.Objects;

public class Passenger {

    private String id;
    private TravelingClass travelingClass;
    private Ages age;
    private Gender gender;
    private boolean isSurvivor;

    public String getId() {
        return id;
    }
    public TravelingClass getTravelingClass() {
        return travelingClass;
    }
    public Ages getAge() {
        return age;
    }
    public Gender getGender() {
        return gender;
    }
    public boolean isSurvivor() {
        return isSurvivor;
    }

    public void createFromString(String passengerData) {
        String[] passengerInfos = passengerData.split("(\\s+)");
        id = passengerInfos[0];
        travelingClass = TravelingClass.values()[Integer.parseInt(passengerInfos[1])];
        age = Ages.values()[Integer.parseInt(passengerInfos[2])];
        gender = Gender.values()[Integer.parseInt(passengerInfos[3])];
        isSurvivor = Objects.equals(passengerInfos[4], "1");
    }
}
