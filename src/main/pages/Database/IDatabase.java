/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import javafx.collections.ObservableList;

/**
 *
 * @author HP
 * @param <T>
 */


public interface IDatabase<T> {
    
    public T get(int ID);
    public ObservableList<T> get();
    public void delete(T t);
    public void save(T t);
    public void update(T t);
    public void saveorUpdate(T t);
    
}
