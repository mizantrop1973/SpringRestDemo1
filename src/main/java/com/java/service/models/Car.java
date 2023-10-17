package com.java.service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString(exclude = "owner")
@Entity
@Table(name = "fix_car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    private String licensePlate;

    private String model;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Override
    public String toString() {
        return model + "(ID=" + carId + ")";

    }


}
