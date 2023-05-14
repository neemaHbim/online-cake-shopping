package com.onlineCakeShopping.App.Domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
public class Cake {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank(message = "Name is Required!")
    private String name;
    @NotBlank(message = "Description is Required!")
    private String description;
    @NotBlank(message = "Price is Required!")
    private Integer price;


}
