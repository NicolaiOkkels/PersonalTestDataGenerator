package org.pba;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.ls.LSOutput;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Person {

    private String firstName;
    private String lastName;
    private String gender;
    private String cpr;
    private int phoneNumber;
    private String address;
    private LocalDate birthDate;

    public Person() throws IOException, ParseException, SQLException, ClassNotFoundException {
        JSONObject jsonObject = parseJsonPerson();
        String name = (String) jsonObject.get("name");
        String surname = (String) jsonObject.get("surname");
        String gender = (String) jsonObject.get("gender");

        this.firstName = name;
        this.lastName = surname;
        this.gender = gender;
        this.cpr = fakeCPR(gender);
        this.phoneNumber = fakePhoneNumber();
        this.address = fakeAddress();
        this.birthDate = birthDate();
    }

    public JSONObject parseJsonPerson() throws IOException, ParseException {
        Random random = new Random();
        // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader("src/main/resources/jSONFiles/person-names.json"));

        // typecasting obj to JSONObject
        JSONObject jsonObject = (JSONObject) obj;

        JSONArray persons = (JSONArray) jsonObject.get("persons");
        int i = random.nextInt(persons.size())+1;
        JSONObject person = (JSONObject) persons.get(i);

        return person;
    }

    public String fakeAddress() throws SQLException, ClassNotFoundException {
        //Database connection
        /*
        DatabaseConnection con = new DatabaseConnection("jdbc:mysql://localhost:3307/addresses");

        //Database query
        Statement st = con.getConnection().createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM postal_code ORDER BY Rand() LIMIT 1");
        result.next();
        String cPostalCode = result.getString(1);
        String cTownName = result.getString(2);
        con.getConnection().close();
         */

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder();
        int streetLength = new Random().nextInt(27) + 1;
        for (int i = 0; i < streetLength; i++) {
            sb.append(alphabet.charAt(new Random().nextInt(alphabet.length())));
        }

        int number = new Random().nextInt(999) + 1;
        int bool = new Random().nextInt(2) + 1;

        if(bool == 1){
            String upperLetter = String.valueOf(Character.toUpperCase(alphabet.charAt(new Random().nextInt(alphabet.length()))));
            return sb + " " + number + upperLetter + " - "; //+cPostalCode + " " + cTownName;
        } else {
            int floorNumber = new Random().nextInt(99) + 1;
            String[] s = {"mv", "tv", "th"};
            String randomString = s[new Random().nextInt(s.length)];
            return sb + " " + number + ". " + floorNumber + " " + randomString + "." + " - "; //+cPostalCode + " " + cTownName;
        }
    }

    public String fakeCPR(String gender){
        LocalDate birthDate = birthDate();
        int endRange = 9999;
        int startRange = 1000;
        int lastDigit = 0;

        if(gender.equals("female")){
            Random evenRandom = new Random();
            lastDigit = startRange + evenRandom.nextInt((endRange - startRange) / 2) * 2;
        }else if(gender.equals("male")) {
            Random oddRandom = new Random();
            lastDigit = startRange + oddRandom.nextInt((endRange - startRange) / 2) * 2 + 1;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "ddMMuu" , Locale.UK );

        return dateTimeFormatter.format(birthDate) + "-" + lastDigit;
    }

    public LocalDate birthDate(){
        LocalDate startDate = LocalDate.of(1900, 1, 1);

        int days = (int) ChronoUnit.DAYS.between(startDate, LocalDate.now());
        int randomDays = new Random().nextInt(days);

        LocalDate randomDate = startDate.plusDays(
                ThreadLocalRandom.current().nextInt(randomDays));

        return randomDate;
    }

    public int fakePhoneNumber(){

        int[] startDigitsCombinations = {2,30,31,40,41,42,50,51,52,53,
                60,61,71,81,91,92,93,342,344,349,356,357,
                359,362,365,366,389,398,431,441,462,466,
                468,472,474,476,478,485,486,488,489,493,494,
                495,496,498,499,542,543,545,551,552,556,571,
                572,573,574,577,579,584,586,587,589,597,598,
                627,629,641,649,658,662,663,664,665,667,692,
                693,694,697,771,772,782,783,785,786,788,789,
                826,827,829
        };

        int i = new Random().nextInt(startDigitsCombinations.length);
        int startDigit = startDigitsCombinations[i];
        int endDigit;

        if(startDigit == 2){
            endDigit = new Random().nextInt(9999999);
        } else if(startDigit <= 93){
            endDigit = new Random().nextInt(999999);
        } else{
            endDigit = new Random().nextInt(99999);
        }

        return Integer.parseInt(String.valueOf(startDigit) + String.valueOf(endDigit));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getCpr() {
        return cpr;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
