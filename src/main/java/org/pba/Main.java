package org.pba;

import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, SQLException, ClassNotFoundException {

        Scanner input = new Scanner(System.in);
        System.out.println("Input number of fake person to generate between 2 - 100: ");
        int i = input.nextInt();
        while(i < 2 || i > 100){
            System.out.println("Input number of fake persons to generate between 2 - 100:");
            i = input.nextInt();
        }
        System.out.println("Test jenkins");
        CSVWriter csvWriter = new CSVWriter(new FileWriter("personal-test-data.csv"));

        Person person = new Person();
        List<String[]> rows = new LinkedList<>();
        for (Person p: person.fakePersonInBulk(i)) {
            rows.add(new String[]{p.getCpr(), p.getFirstName() + " " + p.getLastName(),
                    p.getGender(), String.valueOf(p.getBirthDate()), p.getAddress(), String.valueOf(p.getPhoneNumber())});
        }

        csvWriter.writeAll(rows);

        csvWriter.close();

        input.close();
    }
}