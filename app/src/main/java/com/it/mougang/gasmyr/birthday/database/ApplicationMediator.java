package com.it.mougang.gasmyr.birthday.database;

import com.it.mougang.gasmyr.birthday.domain.Person;

import java.util.ArrayList;

/**
 * Created by gasmyr on 1/25/16.
 */
public interface ApplicationMediator {

    public void addPerson(Person person);

    public Person getPerson(Long personId);

    public ArrayList<Person> getAllPersons();

    public int getPersonCount();

    public int updatePerson(Person person);

    public void deletePerson(String personId);
}
