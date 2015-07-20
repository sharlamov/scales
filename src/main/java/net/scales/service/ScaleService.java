package net.scales.service;

import java.util.Date;
import java.util.List;

import net.scales.model.CustomItem;
import net.scales.model.CustomUser;
import net.scales.model.Scales;

public interface ScaleService {

	public List<Scales> getScalesListByPeriod(Date date1, Date date2, Long elevatorId);

	public List<Scales> getScalesInByPeriod(Date date1, Date date2, Long elevatorId);

	public void updateScale(Scales data, CustomUser user) throws Exception;

	public void insertScale(Scales data, CustomUser user) throws Exception;

	public void updateScaleIn(Scales data, CustomUser user)
			throws Exception;

	public void insertScaleIn(Scales data, CustomUser user)
			throws Exception;

	public List<CustomItem> getTransportList(int i, String query);

	public List<CustomItem> getDestinatarList(int i, String query);

	public List<CustomItem> getPunctulList(int i, String query);

	public List<CustomItem> getTipulList(int i, String query);
	
	public int getDefSezon(Date fdate, long div);

	public int getLimit();
	
	public int getDefFurnizor();
}
