package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
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

   public User getUserByCar(String model, int series){
      Query query1 = sessionFactory.getCurrentSession().createQuery("from User where id = :id");
      Query query2 = sessionFactory.getCurrentSession().createQuery(
              "from Car where model = :model and series = :series");

      query2.setParameter("model", model);
      query2.setParameter("series", series);
      Car car = (Car) query2.uniqueResult();
      query1.setParameter("id", car.getId());

      return (User) query1.uniqueResult() ;
   }

}