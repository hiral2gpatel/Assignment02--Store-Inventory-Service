package com.hiral.inventory;

import com.hiral.entity.Inventory;

import java.util.List;

public interface InventoryService {
    void clearList();

    List<Inventory> getInventoryList();

    void addToList(Inventory inventory);

    Inventory getInventoryById(Long id);
}
