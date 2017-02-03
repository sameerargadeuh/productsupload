/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IGenericDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Clinton
 * @param <T>
 */
public class GenericDaoImpl<T> implements IGenericDao<T> {

    protected EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    private Class<T> type;

    public GenericDaoImpl() {
        // TODO Auto-generated constructor stub

        
    }

    public GenericDaoImpl(Class<T> type) {
        // TODO Auto-generated constructor stub
           this.type = type;
           entityManagerFactory = Persistence.createEntityManagerFactory("JPForProductsPU");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T create(T newInstance) {

        entityManager.persist(newInstance);
//        entityManager.flush();
        
        return newInstance;
    }

    @Override
    public T read(Long id) {
        return (T) entityManager.find(type, id);
    }

    @Override
    public void update(T transientObject) {
        try {
            entityManager.merge(transientObject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(T persistentObject) {
        try {
            entityManager.remove(persistentObject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    @Override
     public List<T> readByNameDQuery(String name,Map<String, String>params) {
         List<T> results = new ArrayList();

        try{
        TypedQuery<T> query
            = entityManager.createNamedQuery(name, type);
        for(String key:params.keySet()){
            query.setParameter(key, params.get(key));
        }
        
        results = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return results;
    }
     
       @Override
     public List<T> readByNameDQueryList(String name,Map<String, List<String>>params) {
         List<T> results = new ArrayList();

        try{
        TypedQuery<T> query
            = entityManager.createNamedQuery(name, type);
        for(String key:params.keySet()){
            query.setParameter(key, params.get(key));
        }
        
       results = query.getResultList();
        
        }catch(Exception e){
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void beginTransaction() {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
    }

    @Override
    public void commitTransaction() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
        }else{
            System.out.println("No Transaction is active at the moment");
        }

    }
    @Override
     public void releasePersistenceContext() {
        // TODO Auto-generated constructor stub
        if(entityManager.isOpen()){
             entityManager.close();
        }
       if(entityManagerFactory.isOpen()){
        entityManagerFactory.close();
     }
         
    }

    @Override
    public List<T> readByNameDQueryInt(String name, Map<String, Integer> params) {
        List<T> results = new ArrayList();

        try{
        TypedQuery<T> query
            = entityManager.createNamedQuery(name, type);
        for(String key:params.keySet()){
            query.setParameter(key, params.get(key));
        }
        
        results = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return results;
    }
    @Override
     public List<T> readByNameDQueryNoParam(String name) {
         List<T> results = new ArrayList();

        try{
        TypedQuery<T> query
            = entityManager.createNamedQuery(name, type);
      
        
        results = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void updateByNamedQueryInt(String name, Map<String, Integer> params) {
        try{
        TypedQuery<T> query
            = entityManager.createNamedQuery(name, type);
       for(String key:params.keySet()){
            query.setParameter(key, params.get(key));
        }
        
        query.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
