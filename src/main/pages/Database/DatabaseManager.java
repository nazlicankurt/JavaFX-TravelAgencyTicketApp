/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class DatabaseManager<T> implements IDatabase<T> {

    private Class s; //referans class

    public DatabaseManager(Class s) {
        this.s = s;
    }

    @Override
    public T get(int ID) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();  // Fabrika Oluşturuldu
        Session session = sessionFactory.openSession(); // Çalışacak işçi oluşturuldu..
        Transaction transaction = session.beginTransaction(); // işlemleri database e işliyor.
        T t = (T) session.get(s, ID); // generic type türünde obje oluşturuluyor...
        transaction.commit(); // Hibernate işlemlerini onayla
        session.flush(); // sesssionı boşalt
        session.close(); // sessionı kapat
        return t;
    }

    @Override
    public ObservableList<T> get() {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ObservableList<T> list = FXCollections.observableArrayList(session.createCriteria(s).list());
        transaction.commit();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public void delete(T t) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
       
        session.delete(t);
        transaction.commit();
        session.flush();
        session.close();
    }

    @Override
    public void save(T t) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        session.save(t);
        transaction.commit();
        session.flush();
        session.close();
     
    }

    @Override
    public void update(T t) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        session.update(t);
        transaction.commit();
       session.flush();
        session.close();
    }

    @Override
    public void saveorUpdate(T t) {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        session.saveOrUpdate(t);
        transaction.commit();
        session.flush();
        session.close();
 
    }

}
