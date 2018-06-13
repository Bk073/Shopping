package com.shopping.dao.daoImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.RoleDao;
import com.shopping.dao.UserDao;
import com.shopping.entity.Cart;

import com.shopping.entity.User;
import com.shopping.entity.UserRole;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleDao roleDao;

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
		        .createQuery( "from User where username = :username" )
		        .setString( "username", username )
		        .list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
	/*private Session openSession() {
        return sessionFactory.getCurrentSession();
    }
	public User getUser(String login) {
        List<User> userList = new ArrayList<User>();
        Query query = openSession().createQuery("from User u where u.login = :login");
        query.setParameter("login", login);
        userList = query.list();
        if (userList.size() > 0)
            return userList.get(0);
        else
            return null;    
    }
*/


	public void saveUser(User user) {
		Set<UserRole> roles = new HashSet<UserRole>();
		 roles.add((UserRole) roleDao.findRoleByRoleNAme("USER"));
		 System.out.println("ROLES --------------"+roles);
		 user.setUserRole(roles);
		/* user.setRoles(Arrays.asList("ROLE_USER"));*/
		 Session session = sessionFactory.getCurrentSession();
		 user.setEnabled(true);
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		 Cart cart = new Cart();
			//it is to set CartId for customer table
			user.setCart(cart);//set the cart to the customer
			//if we omit this statement, hen it will insert null for customerid in cart
			//to set the customerid in cart table
			cart.setUser(user);
		 session.save(user);
		 
		
	}


	public List<User> getAllUsers() {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM User" , User.class)
						.getResultList();
	
	}

}