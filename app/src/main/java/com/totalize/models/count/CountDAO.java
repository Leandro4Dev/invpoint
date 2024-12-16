package com.totalize.models.count;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.totalize.models.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CountDAO {

    public static List<Count> getAll() {
        List<Count> counts = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();

        try {
            // Referenciar a coleção e buscar documentos
            CollectionReference collection = db.collection(Count.COLLECTION_NAME);
            ApiFuture<QuerySnapshot> querySnapshotApiFuture = collection
                    .orderBy("created_time", Query.Direction.DESCENDING)
                    .get();

            for (DocumentSnapshot document : querySnapshotApiFuture.get().getDocuments()) {
                // Converter o documento para o modelo Count
                String seq = document.getString("seq");;
                String countNun = document.getString("count");
                Timestamp createdTime = (Timestamp) document.get("created_time");
                Long amount = document.getLong("amount");

                DocumentReference userRef = (DocumentReference) document.get("user_id");

                Count count = new Count(seq, countNun, createdTime, amount, null);

                if (countNun != null && userRef != null) {
                    DocumentSnapshot userDoc = userRef.get().get();
                    if (userDoc.exists()) {
                        User user = userDoc.toObject(User.class);
                        count.setUserData(user);
                    }
                }

//                System.out.println(count);
                counts.add(count);
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }

        return counts;
    }

}
