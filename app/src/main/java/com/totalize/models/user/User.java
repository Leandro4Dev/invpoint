package com.totalize.models.user;


import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.PropertyName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public static final String COLLECTION_NAME = "users";

    private String uid;
    private String display_name;
    private String email;
    private String registration;
    private Timestamp created_time;



}
