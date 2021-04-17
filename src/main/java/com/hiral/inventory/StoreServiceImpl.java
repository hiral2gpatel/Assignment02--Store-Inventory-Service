package com.hiral.inventory;

import com.hiral.entity.Inventory;
import com.hiral.entity.Store;

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

@Stateless
@Remote(StoreService.class)
public class StoreServiceImpl implements StoreService {

    @PersistenceContext
    private EntityManager ssem;

    @Override
    public void clearList() {
        Query deleteFromSportInventory = ssem.createNamedQuery("Store.clearAll");
        deleteFromSportInventory.executeUpdate();
    }
    public List<Store> getAllByBuilder() {
        CriteriaBuilder criteriabuilder = ssem.getCriteriaBuilder();
        CriteriaQuery<Store> query = criteriabuilder.createQuery(Store.class);
        Root<Store> from  = query.from(Store.class);
        TypedQuery<Store> typedQuery = ssem.createQuery(query.select(from));
        return typedQuery.getResultList();
    }

    @Override
    public List<Store> getStoreList() {

        List<Store> storeList;
       return storeList =  ssem.createNamedQuery("Store.findAll", Store.class)
                .getResultList();
    }


    @Override
    public void addToList(Store store) {
        ssem.persist(store);

    }

    @Override
    public Store getStoreyById(Long id) {
        return ssem.find(Store.class, id);
    }

}
