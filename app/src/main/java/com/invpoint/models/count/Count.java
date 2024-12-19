package com.invpoint.models.count;

import com.google.cloud.Timestamp;
import com.invpoint.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Count {
    public static final String COLLECTION_NAME = "count";

    private String seq;
    private String count;
    private Timestamp createdTime;
    private Long amount;
    private User userData;

}
