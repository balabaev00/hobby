package ru.employee;

import ru.company.Gender;

public class Doctor extends HospitalEmployee {
    private DoctorsSpeciality doctorsSpeciality;

    public Doctor(String lastName, String firstName, String patronymicName, Gender gender) {
        super(lastName, firstName, patronymicName, gender);
    }


}
