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
    public static void main(String[] args) throws IOException, ParseException {

        Random random = new Random();
        // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader("src/main/resources/jSONFiles/person-names.json"));

        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;

        // getting firstName and lastName
        //String firstName = (String) jo.get("name");

        JSONArray ja = (JSONArray) jo.get("persons");
        int pos = random.nextInt(ja.size())+1;
        JSONObject newJO = (JSONObject) ja.get(pos);
        String name = (String) newJO.get("name");
        String surname = (String) newJO.get("surname");
        String gender = (String) newJO.get("gender");
        System.out.println(newJO);
        System.out.println(name);
        System.out.println(surname);
        System.out.println(gender);
        String persons = ja.toJSONString();
        //System.out.println(persons);




    }
}