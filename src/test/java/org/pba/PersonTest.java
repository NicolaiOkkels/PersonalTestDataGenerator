package org.pba;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    @DisplayName("birth date not before 1900")
    @RepeatedTest(value = 10)
    void birthDate_range1() {
        //Arrange ( -- done with setUp --)
        //Act - both actions should be executed together in a method

        boolean expected = person.getBirthDate().getYear() >= 1900;
        //Assert
        assertTrue(expected);

    }
    @Test
    @DisplayName("Fake birth date not in future")
    @RepeatedTest(value = 10)
    void birthDate_range2() {
        //Arrange ( -- done with setUp --)
        LocalDate bDate = person.birthDate();
        LocalDate now = LocalDate.now();
        Calendar date1 = new GregorianCalendar(bDate.getYear(),bDate.getMonthValue(),bDate.getDayOfMonth());
        Calendar date2 = new GregorianCalendar(now.getYear(),now.getMonthValue(),now.getDayOfMonth());
        //Act - both actions should be executed together in a method

        int expected = -1;
        int actual = date1.compareTo(date2);
        //Assert
        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Fake phone number lengt")
    @RepeatedTest(value = 10)
    void fakePhoneNumber() {
        //Arrange
        //Act - both actions should be executed together in a method
        int actual = String.valueOf(person.getPhoneNumber()).length();

        int expected = 8;

        //Assert
        assertEquals(expected, actual);
        System.out.println(person.getPhoneNumber());

    }
}