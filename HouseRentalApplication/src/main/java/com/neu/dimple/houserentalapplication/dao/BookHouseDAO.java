package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.BookHouseException;
import com.neu.dimple.houserentalapplication.pojo.BookHouse;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class BookHouseDAO extends  DAO{

    public BookHouse create(BookHouse bookHouse) throws BookHouseException {
        try{
            begin();
            getSession().save(bookHouse);
            commit();
            close();
            return bookHouse;
        }catch (HibernateException e){
            rollback();
            throw new BookHouseException("Error while creating housebooking: "+ e.getMessage());
        }
    }

    public List<BookHouse> getBookHouseWithHouseId(UUID id) throws BookHouseException {
        try{
            begin();
            Query q = getSession().createQuery(" from BookHouse where houseId = : id");
            q.setParameter("id", id);
            List<BookHouse> bookHouseList = q.list();
            commit();
            close();
            return bookHouseList;
        }catch (HibernateException e){
            rollback();
            throw new BookHouseException("Error while fetching booked house detail using houseId: " + id);
        }
    }

    public List<BookHouse> getBookHouseWithUserId(UUID id) throws BookHouseException {
        try{
            begin();
            Query q = getSession().createQuery(" from BookHouse where userId = : id");
            q.setParameter("id", id);
            List<BookHouse> bookHouseList = q.list();
            commit();
            close();
            return bookHouseList;
        }catch (HibernateException e){
            rollback();
            throw new BookHouseException("Error while fetching booked house detail using userId: " + id);
        }
    }

    public BookHouse get(UUID id) throws BookHouseException{
        try{
            begin();
            BookHouse bookHouse = getSession().get(BookHouse.class, id);
            commit();
            close();
            return bookHouse;
        }catch (HibernateException e){
            rollback();
            throw new BookHouseException("Error while fetching bookhouse with id " + id);
        }
    }

    public void update(BookHouse bookHouse) throws BookHouseException {
        try{
            begin();
            getSession().update(bookHouse);
            commit();
            close();
        }catch (HibernateException e){
            rollback();
            throw new BookHouseException("Error while updating bookhouse with id : " + bookHouse.getId());
        }
    }
}
