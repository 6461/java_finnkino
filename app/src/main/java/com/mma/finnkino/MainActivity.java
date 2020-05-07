package com.mma.finnkino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner theatre = null;
    EditText name = null;
    EditText date = null;
    EditText start = null;
    EditText end = null;
    ListView list = null;
    private TheatreHandler tHandler;
    private ShowHandler sHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        theatre = findViewById(R.id.spinnerTheatre);
        name = findViewById(R.id.editName);
        date = findViewById(R.id.editDate);
        start = findViewById(R.id.editStart);
        end = findViewById(R.id.editEnd);
        list = findViewById(R.id.list);

        XMLParser xml = new XMLParser("https://www.finnkino.fi/xml/TheatreAreas/");
        tHandler = new TheatreHandler(xml);

        ArrayAdapter<Theatre> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tHandler.getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        theatre.setAdapter(adapter);
    }

    public void searchAction(View view) {
        String name = this.name.getText().toString().trim();
        String date = this.date.getText().toString().trim();
        String start = this.start.getText().toString().trim();
        String end = this.end.getText().toString().trim();

        Theatre theatre = (Theatre) this.theatre.getSelectedItem();
        String url = "https://www.finnkino.fi/xml/Schedule/?area=" + theatre.getID();

        if (date != null && !date.isEmpty()) {
            url = url + "&dt=" + date;
        }

        XMLParser xml = new XMLParser(url);
        sHandler = new ShowHandler(xml);
        System.out.println("URL: " + url);

        ArrayAdapter<Show> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sHandler.getFilteredList(date, start, end, name));
        list.setAdapter(adapter);
    }
}
