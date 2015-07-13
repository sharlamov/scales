package net.scales.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.scales.model.CustomItem;
import net.scales.model.CustomUser;
import net.scales.model.ScalesData;

import org.primefaces.model.SortOrder;

public interface ScalesDataService {

	public List<ScalesData> getScalesListByPeriod(Date date1, Date date2);

	public List<ScalesData> getScalesInByPeriod(Date date1, Date date2);

	public void updateScale(ScalesData data, CustomUser user) throws Exception;

	public void insertScale(ScalesData data, CustomUser user) throws Exception;

	public void updateScaleIn(ScalesData data, CustomUser user)
			throws Exception;

	public void insertScaleIn(ScalesData data, CustomUser user)
			throws Exception;

	public List<Object> getDirectory(int listType, int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters);

	public List<CustomItem> getTransportList(int i, String query);

	public List<CustomItem> getDestinatarList(int i, String query);

	public List<CustomItem> getPunctulList(int i, String query);

	public List<CustomItem> getTipulList(int i, String query);
	
	public int getDefSezon(Date fdate, long div);

	public int getLimit();
	
	public int getDefFurnizor();
}
