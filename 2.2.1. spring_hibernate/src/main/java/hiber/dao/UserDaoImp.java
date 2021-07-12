package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getUserByModelAndSeriesCar(String model, int series){
      String hql = "from User as user where user.car.model = :model and  user.car.series = :series";
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery(hql)
              .setParameter("model", model)
              .setParameter("series", series);

      return query.getResultList();
   }

}
