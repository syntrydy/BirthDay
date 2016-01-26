package com.it.mougang.gasmyr.birthday;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.it.mougang.gasmyr.birthday.database.PersonDbHelper;
import com.it.mougang.gasmyr.birthday.domain.Constant;
import com.it.mougang.gasmyr.birthday.domain.Person;

public class ListActivity extends AppCompatActivity {
    private ListView personListView;
    private PersonAdapter personAdapter;
    private PersonDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_reorder_black_36dp);
        personListView = (ListView) findViewById(R.id.birthdaysList);
        dbHelper = new PersonDbHelper(getApplicationContext());
        personAdapter = new PersonAdapter(getApplicationContext(), dbHelper.getAllPersons());
        personListView.setAdapter(personAdapter);
        selectItem();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent birthDayAddActivity = new Intent(getApplicationContext(), BirthDayAddActivity.class);
                startActivity(birthDayAddActivity);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_birthday, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectItem() {
        personListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person person = (Person) parent.getItemAtPosition(position);
                Intent intent=new Intent(getApplicationContext(),BirthDayDetailActivity.class);
                intent.putExtra(Constant.FNAME,person.getPersonName());
                intent.putExtra(Constant.LNAME,person.getPersonLastName());
                intent.putExtra(Constant.BDate,person.getPersonBirthDate());
                intent.putExtra(Constant.PNUMBER,person.getPersonPhoneNumber());
                intent.putExtra(Constant.P_ID,""+person.getPersonId());
                startActivity(intent);
            }
        });
    }
}
