package com.fractalis.greentoolswebservice.inventory.domain.model.aggregates;

import com.fractalis.greentoolswebservice.account.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventoryId")
    private Long id;

    @Column(name = "stationName", nullable = false)
    private String stationName;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "plant", nullable = false)
    private String plant;

    @Column(name = "temperature", nullable = false)
    private double temperature;

    @Column(name = "uv_solar", nullable = false)
    private double uvSolar;

    @Column(name = "humidity", nullable = false)
    private double humidity;


    public Inventory(Long user, String stationName, String plant, double temperature, double uvSolar, double humidity) {
        this.user_id = user;
        this.stationName = stationName;
        this.plant = plant;
        this.temperature = temperature;
        this.uvSolar = uvSolar;
        this.humidity = humidity;
    }

    public void updateInventory(String plant, String stationName, double temperature, double uvSolar, double humidity) {
        this.plant = plant;
        this.stationName = stationName;
        this.temperature = temperature;
        this.uvSolar = uvSolar;
        this.humidity = humidity;
    }
}