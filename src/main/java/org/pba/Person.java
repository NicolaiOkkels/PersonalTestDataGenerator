package org.pba;

import org.w3c.dom.ls.LSOutput;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Person {

    private String firstName;
    private String lastName;
    private String gender;

    private int cpr;

    private int phoneNumber;

    public Person(String firstName, String lastName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Person() {
    }

    public String cprGenerator(String gender){
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

    public int phoneNumberGenerator(){

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
        Random random = new Random();

        int i = random.nextInt(startDigitsCombinations.length);
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
}
