package com.onlineCakeShopping.App.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    private User client;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Date_of_order=new Date();
    @OneToOne
    private Cake cake;
    private Integer Quantity;
    private Integer TotalPrice;
    private String Status="Pending";
}
