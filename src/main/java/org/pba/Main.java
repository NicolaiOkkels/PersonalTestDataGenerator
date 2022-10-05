package org.pba;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        System.out.println(person.cprGenerator("female"));
        System.out.println(person.cprGenerator("male"));

        System.out.println(person.cprGenerator("female"));
        System.out.println(person.cprGenerator("male"));
        System.out.println(person.cprGenerator("female"));
        System.out.println(person.cprGenerator("male"));
    }

}