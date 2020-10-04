package ru.company;

import java.util.Date;

public class Human {
    private String lastName; // Фамилия
    private String firstName; // Имя
    private String patronymicName; // Отчество
    private Gender gender; // Пол
    private Date birthDate; // Дата рождения
    private String phoneNumber; // Номер телефона

    public Human(String lastName, String firstName, String patronymicName, Gender gender) {
        setLastName(lastName);
        setFirstName(firstName);
        setPatronymicName(patronymicName);
        setGender(gender);
    }

    public Human(String lastName, String firstName, String patronymicName, Gender gender, String phoneNumber) {
        setLastName(lastName);
        setFirstName(firstName);
        setPatronymicName(patronymicName);
        setGender(gender);
        setPhoneNumber(phoneNumber);
    }

    public Human(String lastName, String firstName, String patronymicName, Gender gender, Date birthDate, String phoneNumber) {
        setLastName(lastName);
        setFirstName(firstName);
        setPatronymicName(patronymicName);
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setBirthDate(birthDate);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.equals("") || lastName.length()<2)
            return;
        else
            this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.equals("") || firstName.length()<2)
            return;
        else
            this.firstName = firstName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        if (patronymicName.equals("") || patronymicName.length()<4)
            return;
        else
            this.patronymicName = patronymicName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length()<7)
            return;
        else
            this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
