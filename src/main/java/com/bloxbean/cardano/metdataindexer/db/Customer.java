package com.bloxbean.cardano.metdataindexer.db;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    private Long id;
    private String firstName;
    private String lastName;

}

