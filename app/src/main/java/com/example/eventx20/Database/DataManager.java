package com.example.eventx20.Database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.eventx20.Database.Callback.FindByModel;
import com.example.eventx20.Database.Callback.InsertModel;
import com.example.eventx20.Database.Callback.ReadDataCallback;
import com.example.eventx20.Database.DataModel.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DataManager {
    private static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private static final DatabaseReference databaseReference = firebaseDatabase.getReference("User");

    public static void InsertData(@NonNull User user, @NonNull InsertModel callback) {
        HashMap<String, Object> dataset = new HashMap<>();

        dataset.put("Name", user.getName());
        dataset.put("Email", user.getEmail());
        dataset.put("Password", user.getPassword());

        String key = databaseReference.push().getKey();
        dataset.put("Key", key);

        if (key != null) {
            databaseReference.child(key).setValue(dataset).addOnCompleteListener(task -> {
                if(task.isComplete()){
                    callback.onComplete();
                } else if (task.isCanceled()) {
                    callback.onError();
                }
            });
        } else {
            callback.onError();
        }
    }



    // ASYNC CODE
    /** @noinspection MismatchedQueryAndUpdateOfCollection*/
    public static void ReadData(@NonNull ReadDataCallback callback) {
        List<User> allUser = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    // Convert the snapshot into a user object
                    User userData = snapshot.getValue(User.class);
                    allUser.add(userData);
                }
                callback.onDataLoaded(allUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error);
            }
        });
    }

    // ASYNC CODE
    public static void FindByGmail(@NonNull String email, @NonNull String password, @NonNull FindByModel callback) {
        Query query = databaseReference.orderByChild("Email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean successful = false;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User userData = snapshot.getValue(User.class);
                        if (userData != null && email.equals(userData.getEmail()) && password.equals(userData.getPassword())) {
                            successful = true;
                            callback.onSuccess(userData);  // Notify the caller with the user data
                            break; // We found a match, no need to check further
                        }
                    }
                }
                if (!successful) {
                    callback.onFailure();  // Notify the caller that no matching user was found
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onFailure();  // Notify the caller that the operation was cancelled or failed
                Log.w("Firebase", "Failed to fetch data.", databaseError.toException());
            }
        });
    }

    public static void FindByName(@NonNull String name, @NonNull String password, @NonNull FindByModel callback) {
        Query query = databaseReference.orderByChild("Name").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean successful = false;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User userData = snapshot.getValue(User.class);
                        if (userData != null && name.equals(userData.getName()) && password.equals(userData.getPassword())) {
                            successful = true;
                            callback.onSuccess(userData);  // Notify the caller with the user data
                            break; // We found a match, no need to check further
                        }
                    }
                }
                if (!successful) {
                    callback.onFailure();  // Notify the caller that no matching user was found
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onFailure();  // Notify the caller that the operation was cancelled or failed
                Log.w("Firebase", "Failed to fetch data.", databaseError.toException());
            }
        });
    }
}
