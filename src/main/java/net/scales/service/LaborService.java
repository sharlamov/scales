package net.scales.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import net.scales.model.CustomItem;
import net.scales.model.CustomUser;
import net.scales.model.Labor;

public interface LaborService {

	public List<Labor> getLaborsByPeriod(Date date, CustomUser user);
	
	public List<Labor> getLaborOut(Date date, CustomUser user);

	public List<CustomItem> getTipulList(int i, String query);

	public void insertLabor(Labor labor, CustomUser user) throws HibernateException, SQLException;

	public void insertLaborOut(Labor labor, CustomUser user) throws HibernateException, SQLException;

	public void updateLabor(Labor labor, Labor oldlabor, CustomUser user) throws HibernateException, SQLException;

	public List<CustomItem> getDepList(int i, String query);
}
