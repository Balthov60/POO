package dataHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Data {

    private final ArrayList<Passenger> passengers = new ArrayList<>();

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void importPassengersFromFile(String path) {
        String data = getDataFromFile(path);

        if (Objects.equals(data, "")) {
            System.out.println("Error : No dataHandling.Data Found");
            return;
        }
        String[] passengersData = data.split("(?=\\b[P])");

        for(String passengerData : passengersData) {
            addNewPassengerFromString(passengerData);
        }
    }
    private String getDataFromFile(String path) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(path));
            String lastLine = "";
            String line;

            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
                lastLine = line;
            }

            br.close();
            return lastLine;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "";
        }
    }
    private void addNewPassengerFromString(String passengerData) {
        Passenger passenger = new Passenger();
        passenger.createFromString(passengerData);

        passengers.add(passenger);
    }

    public Map<Gender, Integer> getQuantityForEachGender() {
        Map<Gender, Integer> quantityForEachGender = new HashMap<>();
        Gender gender;

        for (Passenger passenger : getPassengers()) {
            gender = passenger.getGender();

            quantityForEachGender.put(gender, quantityForEachGender.getOrDefault(gender, 0) + 1);
        }

        return quantityForEachGender;
    }
    public Map<TravelingClass, Integer> getQuantityForEachTravelingClasses() {
        Map<TravelingClass, Integer> quantityForEachTravelingClasses = new HashMap<>();
        TravelingClass travelingClass;

        for (Passenger passenger : getPassengers()) {
            travelingClass = passenger.getTravelingClass();

            int count = quantityForEachTravelingClasses.getOrDefault(travelingClass, 0);
            quantityForEachTravelingClasses.put(travelingClass, count + 1);
        }

        return quantityForEachTravelingClasses;
    }
    public Map<TravelingClass, Integer> getSurvivorQuantityForEachTravelingClasses() {
        Map<TravelingClass, Integer> quantityForEachTravelingClasses = new HashMap<>();
        TravelingClass travelingClass;

        for (Passenger passenger : getPassengers()) {
            if (passenger.isSurvivor()) {
                travelingClass = passenger.getTravelingClass();

                int count = quantityForEachTravelingClasses.getOrDefault(travelingClass, 0);
                quantityForEachTravelingClasses.put(travelingClass, count + 1);
            }
        }

        return quantityForEachTravelingClasses;
    }
    public Map<Ages, Integer> getQuantityForEachAges() {
        Map<Ages, Integer> quantityForEachTravelingClasses = new HashMap<>();
        Ages age;

        for (Passenger passenger : getPassengers()) {
            age = passenger.getAge();

            int count = quantityForEachTravelingClasses.getOrDefault(age, 0);
            quantityForEachTravelingClasses.put(age, count + 1);
        }

        return quantityForEachTravelingClasses;
    }
    public Map<Ages, Integer> getSurvivorQuantityForEachAges() {
        Map<Ages, Integer> quantityForEachTravelingClasses = new HashMap<>();

        Ages age;
        for (Passenger passenger : getPassengers()) {
            if (passenger.isSurvivor()) {
                age = passenger.getAge();

                int count = quantityForEachTravelingClasses.getOrDefault(age, 0);
                quantityForEachTravelingClasses.put(age, count + 1);
            }
        }

        return quantityForEachTravelingClasses;
    }
}
