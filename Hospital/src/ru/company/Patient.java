package ru.company;

import java.util.Date;

public class Patient extends Human {

    public Patient(String lastName, String firstName, String patronymicName, Gender gender) {
        super(lastName, firstName, patronymicName, gender);
    }

    public Patient(String lastName, String firstName, String patronymicName, Gender gender, String phoneNumber) {
        super(lastName, firstName, patronymicName, gender, phoneNumber);
    }

    public Patient(String lastName, String firstName, String patronymicName, Gender gender, Date birthDate, String phoneNumber) {
        super(lastName, firstName, patronymicName, gender, birthDate, phoneNumber);
    }
}
