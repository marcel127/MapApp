package com.example.marcel.mapapp;

/**
 * Created by marcel on 12/26/16.
 */
public class Database {
    private static Database ourInstance = new Database();


    public static Database getInstance() {
        return ourInstance;
    }

    private Database() {
    }


}
