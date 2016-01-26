package com.it.mougang.gasmyr.birthday.domain;

import java.util.Date;

/**
 * Created by gasmyr on 1/24/16.
 */
public class Person {
    private int personId;
    private String personName;
    private String personLastName;
    private String personPhoneNumber;
    private String personBirthDate;

    public Person(){

    }

    public Person(String personName, String personLastName,String personPhoneNumber, String personBirthDate) {
        this.personName = personName;
        this.personLastName = personLastName;
        this.personPhoneNumber=personPhoneNumber;
        this.personBirthDate = personBirthDate;
    }

    public Person(int personId, String personName, String personLastName, String personPhoneNumber, String personBirthDate) {
        this.personId = personId;
        this.personName = personName;
        this.personLastName = personLastName;
        this.personPhoneNumber = personPhoneNumber;
        this.personBirthDate = personBirthDate;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getPersonBirthDate() {
        return personBirthDate;
    }

    public void setPersonBirthDate(String personBirthDate) {
        this.personBirthDate = personBirthDate;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
    }
}
