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

        for (int i = 0; i < bulk; i++) {
            Person person = new Person();
            System.out.println("CPR: " + person.getCpr() + ", Name: " + person.getFirstName() + " " + person.getLastName() + ", " + person.getGender() + ", " + person.getBirthDate() + ", " + person.getAddress() + ", " + person.getPhoneNumber());
        }

        input.close();
/*
        CSVWriter csvWriter = new CSVWriter(new FileWriter("example.csv"));

        List<String[]> rows = new LinkedList<String[]>();
        rows.add(new String[]{"1", "jan", "Male", "20"});
        rows.add(new String[]{"2", "con", "Male", "24"});
        rows.add(new String[]{"3", "jane", "Female", "18"});
        rows.add(new String[]{"4", "ryo", "Male", "28"});
        csvWriter.writeAll(rows);

        csvWriter.close();

 */
    }
}