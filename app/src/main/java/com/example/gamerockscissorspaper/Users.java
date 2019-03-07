package com.example.gamerockscissorspaper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class Users implements Serializable {
    private transient Context context;

    public static final String APP_PREFERENCES_NAME = "Names";

    SharedPreferences sPref;

    private final List<User> list;

    public Users(Context context) throws Exception {
        this.context = context;
        sPref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
        Set<String> set = sPref.getStringSet(APP_PREFERENCES_NAME, new HashSet<String>());
        list = new ArrayList<>();
        for (String s: set) {
            list.add(new User(s)  );
        }

    }

    public User getUser(int n){
        return list.get(n);
    }

    public List<User> getUsers(){
        return list;
    }

    public void addUser(User user) {
        list.add(user);
    }

    public void save() {
        SharedPreferences.Editor ed = sPref.edit();
        Set<String> set = new HashSet<>();
        for (User user: list){
            set.add(user.getName() + ":" + user.getScore());

        }
        ed.putStringSet(APP_PREFERENCES_NAME, set);

        ed.apply();
    }
    public User getUser(String name){

        Log.e("getUser", "list.size:" + list.size());

        for (User user : list){

            if (user.getName().equals(name)) return user;

            Log.e("User", "user.getName()" + user.getName());
        }
        return null;
    }
}
