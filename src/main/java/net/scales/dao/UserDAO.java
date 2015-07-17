package net.scales.dao;

import java.sql.SQLException;

import net.scales.model.CustomItem;
import net.scales.model.CustomUser;

import org.hibernate.HibernateException;

public interface UserDAO {

	public Object loadUserByUsername(String username);

	public void initUserParams(String userName, String password) throws HibernateException, SQLException;
	
	public CustomItem getUserDiv(long userId);
	
	public CustomItem getUserElevator(long userId);
	
	public int getUserScaleType(long userId);

	public String getUserRole(long userId);
	
	public void initContext(CustomUser user);
}