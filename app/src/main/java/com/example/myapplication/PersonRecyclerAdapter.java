package com.example.myapplication;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.PersonHolder> {

    private ArrayList<Person> persons;

    public PersonRecyclerAdapter(ArrayList<Person> persons) {
        this.persons = persons;

    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_layout, parent, false);
        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
        holder.txtView_name.setText(persons.get(position).getName());
        holder.txtView_phone.setText(persons.get(position).getTelNumber());
        holder.imgView_photo.setImageBitmap(persons.get(position).getPhoto());
        holder.checkBox_person.setChecked(persons.get(position).isChecked());
        Log.d("IsChecked", String.valueOf(persons.get(position).isChecked()));
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class PersonHolder extends RecyclerView.ViewHolder{
        TextView txtView_name;
        TextView txtView_phone;
        ImageView imgView_photo;
        private CheckBox checkBox_person;

        public PersonHolder(@NonNull View personLayout){
            super(personLayout);
            txtView_name = personLayout.findViewById(R.id.txtView_name);
            txtView_phone = personLayout.findViewById(R.id.txtView_phone);
            imgView_photo = personLayout.findViewById(R.id.imgView_photo);
            checkBox_person = personLayout.findViewById(R.id.checkBox_person);
        }

        public CheckBox getCheckBox_person() {
            return checkBox_person;
        }

        public void setCheckBox_person(CheckBox checkBox_person) {
            this.checkBox_person = checkBox_person;
        }
    }
}
