package com.totalize.infra;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Firebase {

    public static void config(){
        try{
            FileInputStream serviceAccount = new FileInputStream("C:\\Dev\\invpoint_desktop\\serviceAccountKey.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);


        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }



}
