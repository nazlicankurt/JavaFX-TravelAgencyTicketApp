/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author HP
 */


public class Database {
    
    private SessionFactory factory;
    private Session session;
    
    //connection diye methodumuz oldu .
    
    public boolean Connection(){
        factory = NewHibernateUtil.getSessionFactory(); 
        session = factory.openSession();
        return true;
    }
    
    public void Disconnected(){
        factory.close();
    }
    
    public Session getSession(){
        return session;
    }
}
