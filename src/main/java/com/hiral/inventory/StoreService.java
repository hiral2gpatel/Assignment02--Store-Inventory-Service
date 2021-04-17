package com.hiral.inventory;

import com.hiral.entity.Inventory;
import com.hiral.entity.Store;

import java.util.List;

public interface StoreService {
    void clearList();

    List<Store> getStoreList();

    void addToList(Store store);

    Store getStoreyById(Long id);
}
