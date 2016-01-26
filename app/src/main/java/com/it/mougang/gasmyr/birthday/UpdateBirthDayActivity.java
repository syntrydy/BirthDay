package com.it.mougang.gasmyr.birthday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.it.mougang.gasmyr.birthday.database.PersonDbHelper;
import com.it.mougang.gasmyr.birthday.domain.Constant;
import com.it.mougang.gasmyr.birthday.domain.Person;

public class UpdateBirthDayActivity extends AppCompatActivity {
    private Button updateBirthDayButton;
    private EditText fNameEditView, lNameEditView, pNumberEditView, bdateEditView;
    private String name,lname,date,phone,currentBirthDayId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_birth_day);
        updateBirthDayButton = (Button) findViewById(R.id.updateBirthDayButton);
        Intent intent = getIntent();
        name=intent.getStringExtra(Constant.FNAME);
        lname=intent.getStringExtra(Constant.LNAME);
        date=intent.getStringExtra(Constant.BDate);
        phone=intent.getStringExtra(Constant.PNUMBER);
        currentBirthDayId=intent.getStringExtra(Constant.P_ID);
        init();
        fNameEditView.setText(name);
        lNameEditView.setText(lname);
        pNumberEditView.setText(phone);
        bdateEditView.setText(date);


        updateBirthDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String personFirstName = fNameEditView.getText().toString().trim();
                String personLastName = lNameEditView.getText().toString().trim();
                String personPhoneNumber = pNumberEditView.getText().toString().trim();
                String personBirthDate = bdateEditView.getText().toString().trim();
                if (!personBirthDate.isEmpty() && !personFirstName.isEmpty()
                        && !personLastName.isEmpty() && !personPhoneNumber.isEmpty()) {
                    Person person = new Person(Integer.parseInt(currentBirthDayId),
                            personFirstName, personLastName, personPhoneNumber, personBirthDate);
                    PersonDbHelper dbHelper = new PersonDbHelper(getApplicationContext());
                    dbHelper.updatePerson(person);
                    Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "All fields need to be filled.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void init() {
        fNameEditView = (EditText) findViewById(R.id.nameLabelU);
        lNameEditView = (EditText) findViewById(R.id.lastNameLabelU);
        pNumberEditView = (EditText) findViewById(R.id.phoneNumberLabelU);
        bdateEditView = (EditText) findViewById(R.id.birthDateLabelU);
    }
}
