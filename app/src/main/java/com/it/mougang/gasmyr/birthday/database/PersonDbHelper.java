package com.it.mougang.gasmyr.birthday.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.it.mougang.gasmyr.birthday.domain.Person;

import java.util.ArrayList;

/**
 * Created by gasmyr on 1/25/16.
 */
public class PersonDbHelper extends SQLiteOpenHelper implements ApplicationMediator{
    public static final String DB_NAME = "birthday";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "birthdays";
    public static final String COLUMN_PERSON_ID = "id";
    public static final String COLUMN_PERSON_FIRST_NAME = "fName";
    public static final String COLUMN_PERSON_LAST_NAME = "lName";
    public static final String COLUMN_PERSON_PHONE_NUMBER = "pNumber";
    public static final String COLUMN_PERSON_BIRTH_DATE = "bDate";

    public PersonDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_PERSON_ID + " " +
                "INTEGER PRIMARY KEY," + COLUMN_PERSON_FIRST_NAME + " " +
                "TEXT," + COLUMN_PERSON_LAST_NAME + " TEXT," + COLUMN_PERSON_PHONE_NUMBER +
                " TEXT," + COLUMN_PERSON_BIRTH_DATE + " TEXT);";
        db.execSQL(QUERY_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String QUERY_DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(QUERY_DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PERSON_FIRST_NAME, person.getPersonName());
        values.put(COLUMN_PERSON_LAST_NAME, person.getPersonLastName());
        values.put(COLUMN_PERSON_PHONE_NUMBER, person.getPersonPhoneNumber());
        values.put(COLUMN_PERSON_BIRTH_DATE, person.getPersonBirthDate().toString());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    @Override
    public Person getPerson(Long personId) {
        Person person = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE "+ COLUMN_PERSON_ID + "=?";
        Cursor cursor = db.rawQuery(QUERY, new String[] { String.valueOf(personId) });
        if (cursor != null) {
            cursor.moveToFirst();
            person = new Person(cursor.getInt(0),cursor.getString(1),
                    cursor.getString(2),cursor.getString(3),cursor.getString(4));
        }
        db.close();
        return person;
    }

    @Override
    public ArrayList<Person> getAllPersons() {
        ArrayList<Person> personsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if(!cursor.isLast()){
            while (cursor.moveToNext()) {
                Person person = new Person();
                person.setPersonId(cursor.getInt(0));
                person.setPersonName(cursor.getString(1));
                person.setPersonLastName(cursor.getString(2));
                person.setPersonPhoneNumber(cursor.getString(3));
                person.setPersonBirthDate(cursor.getString(4));
                personsList.add(person);
            }
        }
        db.close();
        return personsList;
    }

    @Override
    public int getPersonCount() {
        int total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String QUERY = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(QUERY, null);
        db.close();
        total = cursor.getCount();
        return total;
    }

    @Override
    public int updatePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PERSON_FIRST_NAME, person.getPersonName());
        values.put(COLUMN_PERSON_LAST_NAME, person.getPersonLastName());
        values.put(COLUMN_PERSON_PHONE_NUMBER, person.getPersonPhoneNumber());
        values.put(COLUMN_PERSON_BIRTH_DATE, person.getPersonBirthDate().toString());
        int status = db.update(TABLE_NAME, values, COLUMN_PERSON_ID+" =?", new String[]{String.valueOf(person.getPersonId())});
        db.close();
        return status;
    }


    @Override
    public void deletePerson(String personId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_PERSON_ID+" =?", new String[]{String.valueOf(personId)});
        db.close();
    }
}
