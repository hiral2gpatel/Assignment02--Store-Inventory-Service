package com.hiral.inventory;

import com.hiral.entity.Inventory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless//ejb container is responsible for correct injection
@Remote(InventoryService.class)
public class InventoryServiceImpl implements InventoryService {

    private static final int MAX_CAPACITY_Inventory = 50;
    private static final int INITIAL_CAPACITY_Inventory = 2;

    @PersistenceContext
    private EntityManager iem;//entity manager corrects intiantiation of it,it handles all of the things

    @Override
    public void clearList() {
        Query deleteFromInventory = iem.createNamedQuery("Inventory.clearAll");
        deleteFromInventory.executeUpdate();

    }

    public List<Inventory> getAllByBuilder() { //this method is responsible getting inventory differently
        CriteriaBuilder builder = iem.getCriteriaBuilder();
        CriteriaQuery<Inventory> query = builder.createQuery(Inventory.class);//creating query using criteria builder
        Root<Inventory> from = query.from(Inventory.class);
        TypedQuery<Inventory> tquery = iem.createQuery(query.select(from));
        return tquery.getResultList();

    }

    @Override
    public List<Inventory> getInventoryList() {
        List<Inventory> inventoryList;
        return inventoryList = iem.createNamedQuery("Inventory.findAll", Inventory.class)
                .getResultList();//we are  getting all entries from the table and we get resultlist
    }

    @Override
    public void addToList(Inventory inventory) {
        iem.persist(inventory);//this transaction handle through persist
    }
    @Override
    public Inventory getInventoryById(Long id) {
        return iem.find(Inventory.class, id);
    }
}
