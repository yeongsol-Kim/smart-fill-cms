package com.smartf.comu.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reservoir")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservoir {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "reservoir_name")
    private String reservoirName;

    @Column(name = "fuel_level")
    private Double fuelLevel;

    @Column(name = "fuel_max")
    private Double fuelMax;

}