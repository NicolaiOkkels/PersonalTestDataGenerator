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
        System.out.println("Input number of fake person to generate: ");
        int bulk = input.nextInt();

        CSVWriter csvWriter = new CSVWriter(new FileWriter("personal-test-data.csv"));

        List<String[]> rows = new LinkedList<String[]>();
        for (int i = 0; i < bulk; i++) {
            Person person = new Person();
            rows.add(new String[]{person.getCpr(), person.getFirstName() + " " + person.getLastName(), person.getGender(), String.valueOf(person.getBirthDate()), person.getAddress(), String.valueOf(person.getPhoneNumber())});
        }
        csvWriter.writeAll(rows);

        csvWriter.close();

        input.close();
    }
}