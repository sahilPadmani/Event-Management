package com.example.eventx20;
// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;
// MainActivity.java
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class currentevents extends AppCompatActivity {
    String eventname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentevents);

        // Sample data
        List<Event> eventList = new ArrayList<>();
        eventList.add(new Event("Hackathon 2024", "12 Sep 2024", "10:00 AM", "Auditorium"));
        eventList.add(new Event("Tech Talk", "14 Sep 2024", "02:00 PM", "Room 101"));
        eventList.add(new Event("Coding Workshop", "15 Sep 2024", "11:00 AM", "Lab 3"));
        eventList.add(new Event("Networking Session", "16 Sep 2024", "05:00 PM", "Cafeteria"));

        // Find RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set adapter with click listener
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, eventList);
        recyclerView.setAdapter(adapter);

    }

}
