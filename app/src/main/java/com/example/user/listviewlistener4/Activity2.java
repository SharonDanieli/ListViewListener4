package com.example.user.listviewlistener4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity implements View.OnCreateContextMenuListener, AdapterView.OnItemClickListener {
    ListView lv;
    TextView tv; TextView tv2;
    String [] sidra = new String[20];
    Button toCredits;

    double x = 0;
    double a = 0;
    double number1;
    double meOrMa;
    int p = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        lv = findViewById(R.id.lv);
        tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);

        number1 = getIntent().getDoubleExtra("firstNumber", x);
        meOrMa = getIntent().getDoubleExtra("hefreshOrMachpil", a);

        lv.setOnItemClickListener(this);
        lv.setChoiceMode(lv.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sidra);
        lv.setAdapter(adp);

        String addOrMulti = "";
        addOrMulti = getIntent().getStringExtra("mehOrMach");
        sidra[0] = String.valueOf(number1);
        if(addOrMulti.equals("מחבר")){
            for(int i = 1; i<20; i++){
                sidra[i] = Double.toString(Double.parseDouble(sidra[i-1])+meOrMa);
            }
        }

        else if(addOrMulti.equals("מכפיל")){
            for(int i = 1; i<20; i++){
                sidra[i] = Double.toString(Double.parseDouble(sidra[i-1])*meOrMa);
            }
        }
        toCredits = findViewById(R.id.toCredits);
        lv.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        p = position+1;
        tv.setText("מקום האיבר בסדרה: " + p + "\n" + "האיבר הראשון בסדרה: " + number1 + "\n");

        String addOrMulti = getIntent().getStringExtra("mehOrMach");
        if(addOrMulti.equals("מחבר")){
            tv2.setText("ההפרש: " + meOrMa + "\n" + "סכום הסדרה מהאיבר הראשון עד לאיבר זה: " + ((p)*(2*number1+(position)*meOrMa))/2);
        }
        else if(addOrMulti.equals("מכפיל")){
            tv2.setText("המנה: " + meOrMa + "\n" + "סכום הסדרה מהאיבר הראשון עד לאיבר זה: " + ((number1*((Math.pow(meOrMa, p))-1))/(meOrMa-1)));
        }
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("פרטים נוספים");
        menu.add("מיקום האיבר");
        menu.add("סכום הסדרה עד לאיבר זה");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        p = info.position+1;
        String st=item.getTitle().toString();
        if (st.equals("מיקום האיבר"))
            Toast.makeText(this, "המיקום הוא: " + p, Toast.LENGTH_LONG).show();
        else if (st.equals("סכום הסדרה עד לאיבר זה")){
            String addOrMulti = getIntent().getStringExtra("mehOrMach");
            if(addOrMulti.equals("מחבר")){
                Toast.makeText(this, "סכום הסדרה מהאיבר הראשון עד לאיבר זה: " + (""+((p)*(2*number1+(p-1)*meOrMa))/2).replace("E", "10^"), Toast.LENGTH_LONG).show();
            }
            else if(addOrMulti.equals("מכפיל")){
                Toast.makeText(this, "סכום הסדרה מהאיבר הראשון עד לאיבר זה: " + ((number1*((Math.pow(meOrMa, p))-1))/(meOrMa-1)), Toast.LENGTH_LONG).show();
            }
        }
        return super.onContextItemSelected(item);
    }

    public void goToCredits(View view) {
        startActivity(new Intent(getApplicationContext(),Credits.class));
    }
}