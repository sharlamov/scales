package net.scales.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.scales.model.Labor;

import org.hibernate.HibernateException;

public interface LaborDAO {

	public List<Object> getLaborsByPeriod(Date date, Long elevatorId);
	
	public List<Object> getLaborsOutByDate(Date date, Long elevatorId);

	public void updateLabor(Labor data, Labor oldData) throws HibernateException, SQLException;

	public void insertLabor(Labor data) throws HibernateException, SQLException;

	public void insertLaborOut(Labor data) throws HibernateException, SQLException; 
	
	public List<Object> getTipulList(int i, String query);

	public List<Object> getDepList(int i, String query);
}