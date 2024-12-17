package com.totalize.infra;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.sun.tools.javac.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Firebase {

    public static void config(){
        try{

            try (InputStream serviceAccount = Main.class.getClassLoader()
                    .getResourceAsStream("serviceAccountKey.json")) {

                assert serviceAccount != null;
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
            }

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }



}
