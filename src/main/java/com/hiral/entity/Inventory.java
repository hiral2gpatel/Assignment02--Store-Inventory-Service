package com.hiral.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data //Lombok data annotation
@Builder
@AllArgsConstructor
@NoArgsConstructor // we need noargs constructor by default
@Entity

// Inventory class represent as table in databse,we need to add @Entity here
@NamedQuery(name = "Inventory.clearAll", query = "DELETE FROM Inventory")
@NamedQuery(name = "Inventory.findAll", query = "SELECT in FROM Inventory in")
@NamedQuery(name = "Inventory.getByName", query = "SELECT in from Inventory in where in.name = :name")
public class Inventory implements Comparable<Inventory>, Serializable {

    @Id
    @GeneratedValue
    private Long id;//All entity classes must define primary key ,
    private String name;
    private String sportt;
    private int numberOfQuantity;
    private double pricePerUnit;

    private Date inventoryDate; //inventory data is important because we have to sort our inventory by date

    @PrePersist
    void createdAt() {
        this.inventoryDate = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.inventoryDate = new Date();
    }


    @Override
    public int compareTo(Inventory o) {
        return inventoryDate.compareTo(o.inventoryDate);
    }
}
