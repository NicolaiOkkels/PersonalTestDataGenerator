package org.pba;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person person;

    @BeforeEach
    void setUp() {
        person = new Person();
    }

    @Test
    void fakeAddress_street() throws SQLException, ClassNotFoundException {
        //Arrange ( -- done with setUp --)
        //Act - both actions should be executed together in a method
        boolean success = person.fakeAddress().split(" ")[0].length() > 0;
        //Assert
        assertTrue(success);
    }

    @Test
    void fakeAddress_number() throws SQLException, ClassNotFoundException {
        //Arrange ( -- done with setUp --)
        //Act - both actions should be executed together in a method
        String result = person.fakeAddress().split(". ")[1];
        //Assert
        //assertTrue(success);
    }



    @Test
    void fakeCPR() {
        //Arrange
        //Act - both actions should be executed together in a method
        //Assert
    }

    @Test
    void birthDate() {
        //Arrange
        //Act - both actions should be executed together in a method
        //Assert
    }

    @Test
    void fakePhoneNumber() {
        //Arrange
        //Act - both actions should be executed together in a method
        //Assert
    }
}