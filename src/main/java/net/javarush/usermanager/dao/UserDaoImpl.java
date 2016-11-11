package net.javarush.usermanager.dao;
import net.javarush.usermanager.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    private static int count=1;
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session=this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void updateUser(User user) {
        Session session=this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void removeUser(int id) {
        Session session=this.sessionFactory.getCurrentSession();
        User user=(User)session.load(User.class,new Integer(id));
        if(user!=null)session.delete(user);
    }

    @Override
    public User getUserById(int id) {
        Session session=this.sessionFactory.getCurrentSession();
        User user=(User)session.get(User.class,new Integer(id));
        return user;
    }

    public List<User>getUsersByName(String name){
        List<User> allUsers=listUsers();
        ArrayList<User> searchedUsers=new ArrayList<User>();
        for(User user:allUsers){
            if(name.equals(user.getName()))searchedUsers.add(user);
        }
        return searchedUsers;
    }


    public List<User>nextPage(){
        List<User>fullList=listUsers();
        if(fullList.size()>5*count)count++;
        List<User>userPage=new ArrayList<User>();
        if(fullList.size()>=5*count){
            for(int i=5*(count-1);i<count*5;i++){
                userPage.add(fullList.get(i));
            }
        }else{
            for(int i=5*(count-1)+1;i<fullList.size();i++){
                userPage.add(fullList.get(i));
            }
        }
        return userPage;
    }
    public List<User>prevPage(){
        List<User>fullList=listUsers();
        if(count>1)count--;
        List<User>userPage=new ArrayList<User>();
      for(int i=(count-1)*5;i<count*5;i++)userPage.add(fullList.get(i));

        return userPage;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session=this.sessionFactory.getCurrentSession();
        List<User>userList=session.createQuery("from User").list();
        return userList;
    }
}