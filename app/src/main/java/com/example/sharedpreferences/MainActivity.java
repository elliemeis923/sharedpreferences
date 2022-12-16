package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create gson object
        Gson gson = new Gson();

        Address address = new Address("Germany", "Berlin");

        ArrayList<FamilyMember> family = new ArrayList<>();
        family.add(new FamilyMember("wife", 30));
        family.add(new FamilyMember("daughter", 5));

        //turn object into string
        Employee employee = new Employee("John", 30, "john@gmail.com", address, family);
        String json = gson.toJson(employee);
        String json2 = gson.toJson(family);

        //turn string back to object
        Employee employee2 = gson.fromJson(json, Employee.class);
        Type familyType = new TypeToken<ArrayList<FamilyMember>>(){}.getType();
        ArrayList<FamilyMember> family2 = gson.fromJson(json, familyType);

        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("sharedpreferences", MODE_PRIVATE);

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        String s1 = sh.getString("name", "");
        int a = sh.getInt("age", 0);
        boolean e = sh.getBoolean("enrolled", false);

        // We can then use the data
        EditText name = (EditText) findViewById(R.id.name_edit);
        name.setText(s1);
        EditText age = (EditText) findViewById(R.id.age_edit);
        age.setText(String.valueOf(a));
        CheckBox enrolled = (CheckBox) findViewById(R.id.enrolled_checkbox);
        enrolled.setChecked(e);

    }

    public void save(View view) {
        EditText name = (EditText) findViewById(R.id.name_edit);
        EditText age = (EditText) findViewById(R.id.age_edit);
        CheckBox enrolled = (CheckBox) findViewById(R.id.enrolled_checkbox);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedpreferences", MODE_PRIVATE);

        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // Storing the key and its value as the data fetched from edittext
        myEdit.putString("name", name.getText().toString());
        myEdit.putInt("age", Integer.parseInt(age.getText().toString()));
        myEdit.putBoolean("enrolled", enrolled.isChecked());

        // Once the changes have been made,
        // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        myEdit.apply();
    }
}
