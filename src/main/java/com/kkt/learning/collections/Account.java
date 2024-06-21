package com.kkt.learning.collections;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
@AllArgsConstructor
@NoArgsConstructor
public class Account {
 
    @Id
    private String id;
    private String owner;
    private Double value;
 
}