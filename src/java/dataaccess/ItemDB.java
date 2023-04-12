/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Item;
import models.User;

/**
 *
 * @author Xavier
 */
public class ItemDB {
    
    public List<Item> getAll(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            List<Item> items = user.getItemList();
            return items;
        } finally {
            em.close();
        }
    }

    public Item get(Integer id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Item item = em.find(Item.class, id);
            return item;
        } finally { 
            em.close();
        }
    }

    public void insert(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(item);
            em.merge(item);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(item);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.remove(em.merge(item));
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
