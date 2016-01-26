package com.it.mougang.gasmyr.birthday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.it.mougang.gasmyr.birthday.database.PersonDbHelper;
import com.it.mougang.gasmyr.birthday.domain.Person;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthDayAddActivity extends AppCompatActivity {
    private Button addBirthDayButton;
    private EditText fNameEditView, lNameEditView, pNumberEditView, bdateEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_day_add);
        addBirthDayButton = (Button) findViewById(R.id.addBirthDayButton);
        init();
        addBirthDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String personFirstName = fNameEditView.getText().toString().trim();
                String personLastName = lNameEditView.getText().toString().trim();
                String personPhoneNumber = pNumberEditView.getText().toString().trim();
                String personBirthDate = bdateEditView.getText().toString().trim();
                if(!personBirthDate.isEmpty() && !personFirstName.isEmpty()
                        && !personLastName.isEmpty() && !personPhoneNumber.isEmpty()){
                    Person person = new Person(personFirstName, personLastName, personPhoneNumber, personBirthDate);
                    PersonDbHelper dbHelper=new PersonDbHelper(getApplicationContext());
                    dbHelper.addPerson(person);
                    Intent intent=new Intent(getApplicationContext(),ListActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields need to be filled.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void init() {
        fNameEditView = (EditText) findViewById(R.id.nameLabel);
        lNameEditView = (EditText) findViewById(R.id.lastNameLabel);
        pNumberEditView = (EditText) findViewById(R.id.phoneNumberLabel);
        bdateEditView = (EditText) findViewById(R.id.birthDateLabel);
    }
}
