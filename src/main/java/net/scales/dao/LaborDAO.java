package net.scales.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.scales.model.Labor;

import org.hibernate.HibernateException;

public interface LaborDAO {

	public List<Object> getLaborsByPeriod(Date date);

	public int updateLabor(Labor data) throws HibernateException, SQLException;

	public int insertScale(Labor data) throws HibernateException, SQLException;

	public List<Object> getTipulList(int i, String query);
}