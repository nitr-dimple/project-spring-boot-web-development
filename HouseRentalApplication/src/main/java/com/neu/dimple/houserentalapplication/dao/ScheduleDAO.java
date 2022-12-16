package com.neu.dimple.houserentalapplication.dao;

import com.neu.dimple.houserentalapplication.exceptions.ScheduleException;
import com.neu.dimple.houserentalapplication.pojo.Schedule;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class ScheduleDAO extends  DAO{

    public ScheduleDAO() {
    }

    public Schedule create(Schedule schedule) throws ScheduleException {
        try{
            begin();
            getSession().save(schedule);
            commit();
            close();
            return schedule;
        } catch (HibernateException e) {
            rollback();
            throw new ScheduleException("Exception while creating schedule " + e.getMessage());
        }
    }

    public List<Schedule> get() throws ScheduleException{
        try{
            begin();
            Query q = getSession().createQuery("from Schedule");
            List<Schedule> scheduleList = q.list();
            commit();
            close();
            return scheduleList;
        }catch (HibernateException e){
            rollback();
            throw new ScheduleException("Exception while fetching schedule with visit id: " + e.getMessage());
        }
    }
}
