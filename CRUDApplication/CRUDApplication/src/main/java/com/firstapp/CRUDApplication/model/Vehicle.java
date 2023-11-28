package com.firstapp.CRUDApplication.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Vehicles")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;

    @Column
    private String make;

    @Column
    private String model;

//    @Column
//    private String capacity;
//
//    @Column
//    private String year;

}
