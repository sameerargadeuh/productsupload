/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Sameer
 */
public interface IGenericDao <T> {

    /** Persist the newInstance object into database */
    T create(T newInstance);

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T read(Long id);

    /** Save changes made to a persistent object.  */
    void update(T transientObject);

    /** Remove an object from persistent storage in the database */
    void delete(T persistentObject);
    public List<T> readByNameDQuery(String name,Map<String, String>params) ;
    public List<T> readByNameDQueryInt(String name,Map<String, Integer>params) ;
    public List<T> readByNameDQueryList(String name,Map<String, List<String>>params);
    public List<T> readByNameDQueryNoParam(String name);
    public void updateByNamedQueryInt(String name, Map<String, Integer> params);
    void beginTransaction();
    void commitTransaction();
    void releasePersistenceContext();
}
