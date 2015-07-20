package net.scales.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.scales.model.Scales;

import org.hibernate.HibernateException;

public interface ScaleDAO {

	public List<Object> getScalesListByPeriod(Date date, Date date2, Long elevatorId);

	public List<Object> getScalesInByPeriod(Date date1, Date date2, Long elevatorId);

	public List<Object> getUniversList(String tip, String gr1);

	public List<Object> getSyssList(String tip, Integer gr1);

	public int updateScale(Scales data) throws HibernateException,
			SQLException;

	public int insertScale(Scales data) throws HibernateException,
			SQLException;

	public int updateScaleIn(Scales data) throws HibernateException,
			SQLException;

	public int insertScaleIn(Scales data) throws HibernateException,
			SQLException;

	public List<Object> getTransportList(int i, String query);

	public List<Object> getDestinatarList(int i, String query);

	public List<Object> getPunctulList(int i, String query);

	public List<Object> getTipulList(int i, String query);
	
	public int getDefSezon(Date fdate, long div);

	public Object getEnvParam(String string);
}