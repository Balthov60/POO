package dataHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Data {

    private ArrayList<Passenger> passengers = new ArrayList<>();

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

    public void display() {
        for(Passenger passenger : passengers) {
            System.out.println(passenger.getId() + " " +  passenger.getGender() + " " + passenger.getTravelingClass()
                                + " " + passenger.isAdult() +  " " +passenger.isSurvivor());
        }
    }
}
