package com.it.mougang.gasmyr.birthday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.it.mougang.gasmyr.birthday.domain.Person;

import java.util.ArrayList;

/**
 * Created by gasmyr on 1/25/16.
 */
public class PersonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Person> personArrayList;
    public PersonAdapter(Context context, ArrayList<Person> list){
        this.context = context;
        this.personArrayList = list;
    }
    @Override
    public int getCount() {
        return personArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return personArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return personArrayList.get(position).getPersonId();
    }
    static class ViewHolder{
        private TextView tvPersonneId,tvFullName,tvBirthDate,tvPhoneNumber;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.birthday_row, null);
            viewHolder = new ViewHolder();
            viewHolder.tvPersonneId = (TextView) convertView.findViewById(R.id.personId);
            viewHolder.tvFullName = (TextView) convertView.findViewById(R.id.personFullName);
            viewHolder.tvBirthDate = (TextView) convertView.findViewById(R.id.personBirthDate);
            viewHolder.tvPhoneNumber = (TextView) convertView.findViewById(R.id.personPhoneNumber);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Person person = personArrayList.get(position);
        long id = person.getPersonId();
        String name =person.getPersonName()+"  "+person.getPersonLastName();
        String birthDate =person.getPersonBirthDate();
        String phoneNumber =person.getPersonPhoneNumber();
        viewHolder.tvPersonneId.setText(""+id);
        viewHolder.tvFullName.setText(name);
        viewHolder.tvBirthDate.setText(birthDate);
        viewHolder.tvPhoneNumber.setText(phoneNumber);
        return convertView;
    }
    }

