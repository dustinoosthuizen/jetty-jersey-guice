package com.webward.dao;

import com.google.inject.Inject;
import com.webward.entities.Item;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by dustinosthzn on 2014/11/09.
 */
public class ItemDao {

    @Inject
    private EntityManager entityManager;

    public Item findByName(String name)
    {
        return (Item)entityManager.createNamedQuery("Item.findByName").setParameter("name", name).getSingleResult();

    }

    public Item findById(int id)
    {
        return (Item)entityManager.createNamedQuery("Item.findById").setParameter("id", id).getResultList();

    }

    public void save(Item anItem)
    {
        entityManager.persist(anItem);

    }

    public void deleteById(int id)
    {
        entityManager.createNamedQuery("Item.deleteById").setParameter("id",new Integer(id)).executeUpdate();

    }



}
