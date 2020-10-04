package ru.employee;

import ru.company.Gender;
import ru.company.Human;

import java.util.Date;

public class HospitalEmployee extends Human {
    private Date dateStartWorkExperience; // Дата начала стажа работы
    private long passId; // Пропуск


    public HospitalEmployee(String lastName, String firstName, String patronymicName, Gender gender) {
        super(lastName, firstName, patronymicName, gender);
    }

}
