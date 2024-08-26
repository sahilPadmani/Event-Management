package com.example.eventx20.Database.Callback;

import com.example.eventx20.Database.DataModel.User;
import com.google.firebase.database.DatabaseError;
import java.util.List;

public interface ReadDataCallback {
    void onDataLoaded(List<User> users);
    void onError(DatabaseError error);
}

