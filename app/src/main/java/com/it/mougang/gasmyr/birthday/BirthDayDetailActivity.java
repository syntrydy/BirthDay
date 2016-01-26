package com.it.mougang.gasmyr.birthday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.it.mougang.gasmyr.birthday.database.PersonDbHelper;
import com.it.mougang.gasmyr.birthday.domain.Constant;
import com.it.mougang.gasmyr.birthday.domain.Person;

public class BirthDayDetailActivity extends AppCompatActivity {
    private TextView fNameTv,phoneNumberTv,birthDateTv;
    private PersonDbHelper dbHelper;
    private static String currentBirthDayId,lName,fName,date,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_day_detail);
        dbHelper = new PersonDbHelper(getApplicationContext());

        Intent intent = getIntent();
        fName=intent.getStringExtra(Constant.FNAME);
        lName=intent.getStringExtra(Constant.LNAME);
        date=intent.getStringExtra(Constant.BDate);
        phone=intent.getStringExtra(Constant.PNUMBER);
        currentBirthDayId=intent.getStringExtra(Constant.P_ID);

        fNameTv=(TextView)findViewById(R.id.fullName);
        fNameTv.setText(fName+lName);
        phoneNumberTv=(TextView)findViewById(R.id.phoneNumber);
        phoneNumberTv.setText(phone);
        birthDateTv=(TextView)findViewById(R.id.BirthDate);
        birthDateTv.setText(date);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_delete_birthdate){
            dbHelper.deletePerson(currentBirthDayId);
            Intent intent=new Intent(getApplicationContext(),ListActivity.class);
            startActivity(intent);
        }
        if(id==R.id.action_update_birthdate){
            Person person = new Person(Integer.parseInt(currentBirthDayId.trim()),lName,fName, phone, date);
            Intent intent=new Intent(getApplicationContext(),UpdateBirthDayActivity.class);
            intent.putExtra(Constant.FNAME,person.getPersonName());
            intent.putExtra(Constant.LNAME,person.getPersonLastName());
            intent.putExtra(Constant.BDate,person.getPersonBirthDate());
            intent.putExtra(Constant.PNUMBER,person.getPersonPhoneNumber());
            intent.putExtra(Constant.P_ID,""+person.getPersonId());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
