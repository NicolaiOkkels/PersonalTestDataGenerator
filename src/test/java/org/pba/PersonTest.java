package org.pba;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person person;

    @BeforeEach
    void setUp() throws SQLException, IOException, ParseException, ClassNotFoundException {
        person = new Person();
    }

    @Test
    void fakeAddress_randomAssortmentOfChars() throws SQLException, ClassNotFoundException {
        //Arrange ( -- done with setUp --)
        //Act - both actions should be executed together in a method
        boolean success = person.fakeAddress().split(" ")[0].length() > 0;
        //Assert
        assertTrue(success);
    }

    @Test
    void fakeCPR_gender1() throws SQLException, IOException, ParseException, ClassNotFoundException {
        //Arrange ( -- done with setUp --)
        //Act - both actions should be executed together in a method
        String s = person.fakeCPR("male");

        int actual = Integer.parseInt(s.substring(s.length() - 1)) % 2;
        int expected = 1;
        //Assert
        assertEquals(expected,actual);
    }
    @Test
    void fakeCPR_gender2() {
        //Arrange ( -- done with setUp --)
        //Act - both actions should be executed together in a method
        String s = person.fakeCPR("female");

        int actual = Integer.parseInt(s.substring(s.length() - 1)) % 2;
        int expected = 0;
        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void fakeCPR_gender3() {
        //Arrange ( -- done with setUp --)
        //Act - both actions should be executed together in a method
        String actual = person.fakeCPR("random string");
        //Assert
        assertNull(actual);
    }

    @Test
    void birthDate_notNull() {
        //Arrange ( -- done with setUp --)
        //Act - both actions should be executed together in a method
        LocalDate actual = person.getBirthDate();
        //Assert
        //assert
    }

    @Test
    void fakePhoneNumber() {
        //Arrange
        //Act - both actions should be executed together in a method
        //Assert
    }
}