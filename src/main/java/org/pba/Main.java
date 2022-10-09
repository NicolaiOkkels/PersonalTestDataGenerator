package org.pba;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, SQLException, ClassNotFoundException {

        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            System.out.println(person.getCpr() + " ," + person.getFirstName() + " " + person.getLastName() + ", " + person.getGender() + ", " + person.getBirthDate() + ", " + person.getAddress() + ", " + person.getPhoneNumber());
        }
    }
}