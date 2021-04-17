package com.hiral.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

@NamedQuery(name = "Store.clearAll", query = "DELETE FROM Store")
@NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s")
@NamedQuery(name = "Store.getByName", query = "SELECT s from Store s where s.name = :name")
public class Store implements Comparable<Store>, Serializable {
@Id
@GeneratedValue
private Long id;
private String name;
private String location;

@OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
private List<Inventory> listOfInventoryy;

private Date storeInventoryDate;
 @PrePersist
    void createdAt() {
        this.storeInventoryDate = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.storeInventoryDate = new Date();
    }

    @Override
    public int compareTo(Store o) {
        return storeInventoryDate.compareTo(o.storeInventoryDate);
    }
}
