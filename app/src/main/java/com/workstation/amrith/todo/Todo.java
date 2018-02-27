package com.workstation.amrith.todo;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Todo extends AppCompatActivity {

    FloatingActionButton fab;
    ArrayList<Entry> todoList;
    ListView listView;
    ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        fab = (FloatingActionButton)findViewById(R.id.floatingActionButton);

        todoList = new ArrayList<>();
        todoList.add(new Entry("amrith","21 Jan"));
        todoList.add(new Entry("anand","25 Jan"));

        listView = (ListView)findViewById(R.id.lv);
        listViewAdapter = new ListViewAdapter(todoList,getApplicationContext());
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Log.d("Macha","Click Macha");
                Entry ent = todoList.get(i);
                Snackbar.make(view,ent.date,Snackbar.LENGTH_SHORT).setAction("OK",null).show();

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPopup();
            }
        });
    }

    void callPopup()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Todo.this);
        builder.setTitle("Enter Task");
        View viewInflated = getLayoutInflater().inflate(R.layout.event,null);

        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
        //Step 2
        final EditText time = (EditText) viewInflated.findViewById(R.id.time);
        builder.setView(viewInflated);

        //Step 2
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Todo.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String m_Text = input.getText().toString();
                String timeText = time.getText().toString();
                todoList.add(new Entry(m_Text, timeText));
                Toast.makeText(getApplicationContext(),m_Text,Toast.LENGTH_SHORT).show();
                listViewAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        builder.show();
    }
}
