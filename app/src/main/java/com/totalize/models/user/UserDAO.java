package com.totalize.models.user;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.totalize.models.count.Count;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserDAO {

    public static List<User> getAll() {
        Firestore db = FirestoreClient.getFirestore();
        List<User> userList = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection(User.COLLECTION_NAME).get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                User user = document.toObject(User.class);
                System.out.println(user);
                userList.add(user);
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public static User getUserByUid(String uid) {
        Firestore db = FirestoreClient.getFirestore();
        try {
            DocumentReference documentReference = db.collection(User.COLLECTION_NAME).document(uid);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(User.class);
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
