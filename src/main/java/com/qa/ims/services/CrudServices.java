package com.qa.ims.services;

import java.util.List;

public interface CrudServices<T> {

    public List<T> readAll();
     
    public T create(T t);
     
    public T update(T t);
 
    void delete(Long id);

}
