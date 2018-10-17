package com.example.user.listviewlistener4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spin;
    EditText et1;
    EditText et2;
    Button button;
    String mehaberOrMachpil = "מחבר";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin = findViewById(R.id.spin);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getApplicationContext(), Activity2.class);

                if(position==0){
                    mehaberOrMachpil = "מחבר";
                }
                if(position==1){
                    mehaberOrMachpil = "מכפיל";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please choose between the two options", Toast.LENGTH_SHORT).show();
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.heshbonitOrHandasit, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        button = findViewById(R.id.button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Credits");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent t=new Intent(this, Credits.class);
        startActivity(t);
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {

        Intent in = new Intent(this, Activity2.class);
        if (et1.getText().toString().equals("") || et1.getText().toString().equals("-") || et1.getText().toString().equals(".") || et2.getText().toString().equals("") || et2.getText().toString().equals("-") || et2.getText().toString().equals(".") || (spin.getSelectedItemPosition()<0)) {
            Toast.makeText(this, "please enter valid values", Toast.LENGTH_LONG).show();
        }
        else {
            String et11 = et1.getText().toString();
            String et22 = et2.getText().toString();
            in.putExtra("firstNumber", Double.parseDouble(et11));
            in.putExtra("hefreshOrMachpil", Double.parseDouble(et22));
            in.putExtra("mehOrMach", mehaberOrMachpil);
            startActivity(in);
        }
    }
}