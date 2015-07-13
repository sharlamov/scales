package net.scales.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.scales.model.ScalesData;

import org.hibernate.HibernateException;
import org.primefaces.model.SortOrder;

public interface ScalesDataDAO {

	public List<Object> getScalesListByPeriod(Date date, Date date2);

	public List<Object> getScalesInByPeriod(Date date1, Date date2);

	public List<Object> getUniversList(String tip, String gr1);

	public List<Object> getSyssList(String tip, Integer gr1);

	public int updateScale(ScalesData data) throws HibernateException,
			SQLException;

	public int insertScale(ScalesData data) throws HibernateException,
			SQLException;

	public int updateScaleIn(ScalesData data) throws HibernateException,
			SQLException;

	public int insertScaleIn(ScalesData data) throws HibernateException,
			SQLException;

	public List<Object> getDirectory(int listType, int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters);

	public List<Object> getTransportList(int i, String query);

	public List<Object> getDestinatarList(int i, String query);

	public List<Object> getPunctulList(int i, String query);

	public List<Object> getTipulList(int i, String query);
	
	public int getDefSezon(Date fdate, long div);

	public Object getEnvParam(String string);
}