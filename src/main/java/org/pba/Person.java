package org.pba;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
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

    public Person() throws IOException, ParseException{
        JSONObject jsonObject = parseJsonPerson();
        String name = (String) jsonObject.get("name");
        String surname = (String) jsonObject.get("surname");
        String gender = (String) jsonObject.get("gender");

        this.firstName = name;
        this.lastName = surname;
        this.gender = gender;
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

        DatabaseConnection con = new DatabaseConnection("jdbc:mysql://localhost:3307/addresses");

        //Database query
        Statement st = con.getConnection().createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM postal_code ORDER BY Rand() LIMIT 1");
        result.next();
        String cPostalCode = result.getString(1);
        String cTownName = result.getString(2);
        con.getConnection().close();


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
            this.address = sb + " " + number + upperLetter + " - " + cPostalCode + " " + cTownName;
        } else {
            int floorNumber = new Random().nextInt(99) + 1;
            String[] s = {"mv", "tv", "th"};
            String randomString = s[new Random().nextInt(s.length)];
            this.address = sb + " " + number + ". " + floorNumber + " " + randomString + "." + " - " + cPostalCode + " " + cTownName;
        }
        return address;
    }

    public String fakeCPR(String gender){
        LocalDate birthDate = fakeBirthDate();
        int i = 0;
        Random r = new Random();

        if(gender.equals("female")){
            Set<Integer> evenNumbers = IntStream.rangeClosed(1000, 9999)
                    .filter(n -> n % 2 == 0).boxed().collect(Collectors.toSet());
            i = (int) evenNumbers.toArray()[r.nextInt(evenNumbers.size())];
        }else if(gender.equals("male")) {
            Set<Integer> evenNumbers = IntStream.rangeClosed(1000, 9999)
                    .filter(n -> n % 2 == 1).boxed().collect(Collectors.toSet());
            i = (int) evenNumbers.toArray()[r.nextInt(evenNumbers.size())];
        } else {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "ddMMuu" , Locale.UK );
        this.cpr = dateTimeFormatter.format(birthDate) + "-" + i;

        return cpr;
    }

    public LocalDate fakeBirthDate(){
        LocalDate startDate = LocalDate.of(1900, 1, 1);

        int days = (int) ChronoUnit.DAYS.between(startDate, LocalDate.now());
        Random random = new Random();
        int rDays = random.nextInt(days);
        this.birthDate = startDate.plusDays(ThreadLocalRandom.current().nextInt(rDays));

        return birthDate;
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
        String endDigit;


        if(startDigit == 2){
            endDigit = String.format("%07d", new Random().nextInt(9999999));
        } else if(startDigit <= 93){
            endDigit = String.format("%06d", new Random().nextInt(999999));
        } else{
            endDigit = String.format("%05d", new Random().nextInt(99999));
        }

        this.phoneNumber = Integer.parseInt(String.valueOf(startDigit) +endDigit);
        return phoneNumber;
    }

    public String fakeNameAndGender(){
        return "Full name: " + this.firstName + " " + this.lastName + "Gender: " + this.gender;
    }

    public String fakeNameGenderAndBirth(){
        return fakeNameAndGender() + " Birth: " + fakeBirthDate();
    }

    public String fakeCprNameAndGender(){
        return "CPR: " + fakeCPR(this.gender) + fakeNameAndGender();
    }

    public String fakeCprNameGenderAndBirth(){
        return fakeCprNameAndGender() + " Birth: " + fakeBirthDate();
    }

    public String fakePersonAllInformation() throws SQLException, ClassNotFoundException {
        return fakeCprNameGenderAndBirth() + "Address: " + fakeAddress() + " Phone number: " + fakePhoneNumber();
    }

    public List<Person> fakePersonInBulk(int numberOfPersons) throws IOException, ParseException, SQLException, ClassNotFoundException {

        List<Person> fakePersonList = new ArrayList<>();

        for (int i = 0; i < numberOfPersons; i++) {
            Person person = new Person();
            person.fakeBirthDate();
            person.fakeAddress();
            person.fakePhoneNumber();
            person.fakeCPR(person.getGender());

            fakePersonList.add(person);
        }

        return fakePersonList;
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

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
