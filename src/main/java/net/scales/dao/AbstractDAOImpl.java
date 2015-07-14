package net.scales.dao;

import java.util.List;

import net.scales.model.CustomItem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractDAOImpl {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public String parseNumber(Number val) {
		return val != null ? val.toString() : "";
	}

	public String parseItem(CustomItem val) {
		return (val != null && val.getId() != null) ? val.getId().toString()
				: "";
	}

	public String parseItemText(CustomItem val) {
		if (val == null) {
			return "";
		}
		if (val.getId() == null && val.getLabel() != null)
			return val.getLabel();
		else
			return "";
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getLimitList(int size, String query, String sql) {
		sql = "select * from (" + sql + ") " + "where lower(denumirea) like '%"
				+ query.trim().toLowerCase()
				+ "%' and rownum < :size order by 2";
		return currentSession().createSQLQuery(sql).setInteger("size", size)
				.list();
	}
}
