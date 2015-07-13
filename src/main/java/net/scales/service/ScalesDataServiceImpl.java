package net.scales.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.scales.dao.ScalesDataDAO;
import net.scales.dao.UserDAO;
import net.scales.model.CustomItem;
import net.scales.model.CustomUser;
import net.scales.model.ScalesData;
import net.scales.util.WebUtil;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScalesDataServiceImpl implements ScalesDataService {

	@Autowired
	private ScalesDataDAO scalesDataDAO;

	@Autowired
	private UserDAO userDAO;

	public List<ScalesData> getScalesListByPeriod(Date date1, Date date2) {
		List<Object> list = scalesDataDAO.getScalesListByPeriod(date1, date2);
		List<ScalesData> newlist = new ArrayList<ScalesData>(list.size());
		for (Object obj : list) {
			newlist.add(convertToScalesDataOut(obj));
		}
		return newlist;
	}

	public List<ScalesData> getScalesInByPeriod(Date date1, Date date2) {
		List<Object> list = scalesDataDAO.getScalesInByPeriod(date1, date2);
		List<ScalesData> newlist = new ArrayList<ScalesData>(list.size());
		for (Object obj : list) {
			newlist.add(convertToScalesDataIn(obj));
		}
		return newlist;
	}

	public void updateScale(ScalesData data, CustomUser user) throws Exception {
		if (user.getScaleType() == 0)
			throw new Exception("The current user doesn't have rights");

		userDAO.initUserParams(user.getUsername(), user.getPassword());
		scalesDataDAO.updateScale(data);
	}

	public void insertScale(ScalesData data, CustomUser user) throws Exception {
		if (user.getScaleType() == 0)
			throw new Exception("The current user doesn't have rights");

		userDAO.initUserParams(user.getUsername(), user.getPassword());

		if (user.getScaleType() == 5) {
			data.setDiv(user.getDiv().getId());
			data.setElevator(user.getElevator());
			data.setUserid(user.getId());
			scalesDataDAO.insertScale(data);
		} else
			throw new Exception("The current user doesn't have rights");
	}

	public void updateScaleIn(ScalesData data, CustomUser user) throws Exception {
		if (user.getScaleType() == 0)
			throw new Exception("The current user doesn't have rights");

		userDAO.initUserParams(user.getUsername(), user.getPassword());
		scalesDataDAO.updateScaleIn(data);
	}

	public void insertScaleIn(ScalesData data, CustomUser user) throws Exception {
		if (user.getScaleType() == 0)
			throw new Exception("The current user doesn't have rights");

		userDAO.initUserParams(user.getUsername(), user.getPassword());

		if (user.getScaleType() == 5) {
			data.setDiv(user.getDiv().getId());
			data.setElevator(user.getElevator());
			data.setUserid(user.getId());
			scalesDataDAO.insertScaleIn(data);
		} else
			throw new Exception("The current user doesn't have rights");
	}

	public List<Object> getDirectory(int listType, int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		return scalesDataDAO.getDirectory(listType, first, pageSize, sortField,
				sortOrder, filters);
	}

	public List<Object> getPuctSosireList() {
		return scalesDataDAO.getSyssList("S", 12);
	}

	public List<Object> getCerealeList() {
		return scalesDataDAO.getUniversList("M", "P");
	}

	private ScalesData convertToScalesDataOut(Object obj) {
		int i = 0;
		ScalesData scalesData = new ScalesData();
		
		Object[] data = (Object[]) obj;
		scalesData.setId(WebUtil.parse(data[i++], Long.class));
		scalesData.setConducator(WebUtil.parse(data[i++], String.class));
		scalesData.setAuto(WebUtil.parse(data[i++], String.class));
		scalesData.setRemorca(WebUtil.parse(data[i++], String.class));
		scalesData.setTransportator(new CustomItem(data[i++], null, data[i++]));
		scalesData.setDestinatar(new CustomItem(data[i++], null, data[i++]));
		scalesData.setPunctulSosire(new CustomItem(data[i++], null, data[i++]));
		scalesData
				.setStatiaDestinatie(new CustomItem(data[i++], null, data[i++]));
		scalesData.setTipulCereale(new CustomItem(data[i++], null, data[i++]));
		scalesData.setSezon(WebUtil.parse(data[i++], Integer.class));
		scalesData.setTtnNumber(WebUtil.parse(data[i++], String.class));
		scalesData.setTtnData(WebUtil.parse(data[i++], Date.class));
		scalesData.setNrAnaliz(WebUtil.parse(data[i++], Integer.class));
		scalesData.setMasaBruto(WebUtil.parse(data[i++], Double.class));
		scalesData.setMasaTara(WebUtil.parse(data[i++], Double.class));
		scalesData.setDateIn(WebUtil.parse(data[i++], Date.class));
		scalesData.setDateOut(WebUtil.parse(data[i++], Date.class));
		scalesData.setElevator(new CustomItem(data[i++], null, data[i++]));
		scalesData.setUserid(WebUtil.parse(data[i++], Long.class));
		scalesData.setDiv(WebUtil.parse(data[i++], Long.class));

		return scalesData;
	}
	
	private ScalesData convertToScalesDataIn(Object obj) {
		int i = 0;
		ScalesData scalesData = new ScalesData();
		
		Object[] data = (Object[]) obj;
		scalesData.setId(WebUtil.parse(data[i++], Long.class));
		scalesData.setConducator(WebUtil.parse(data[i++], String.class));
		scalesData.setAuto(WebUtil.parse(data[i++], String.class));
		scalesData.setRemorca(WebUtil.parse(data[i++], String.class));
		scalesData.setDestinatar(new CustomItem(data[i++], null, data[i++]));
		scalesData.setPunctulSosire(new CustomItem(data[i++], null, data[i++]));
		scalesData.setTipulCereale(new CustomItem(data[i++], null, data[i++]));
		scalesData.setSezon(WebUtil.parse(data[i++], Integer.class));
		scalesData.setTtnNumber(WebUtil.parse(data[i++], String.class));
		scalesData.setTtnData(WebUtil.parse(data[i++], Date.class));
		scalesData.setTtnMasa(WebUtil.parse(data[i++], Double.class));
		scalesData.setNrAnaliz(WebUtil.parse(data[i++], Integer.class));
		scalesData.setTransportator(new CustomItem(data[i++], null, data[i++]));
		scalesData.setMasaBruto(WebUtil.parse(data[i++], Double.class));
		scalesData.setMasaTara(WebUtil.parse(data[i++], Double.class));
		scalesData.setDateIn(WebUtil.parse(data[i++], Date.class));
		scalesData.setDateOut(WebUtil.parse(data[i++], Date.class));
		scalesData.setDiv(WebUtil.parse(data[i++], Long.class));
		scalesData.setElevator(new CustomItem(data[i++], null, data[i++]));
		scalesData.setUserid(WebUtil.parse(data[i++], Long.class));

		return scalesData;
	}
	
	public List<CustomItem> getTransportList(int i, String query) {
		List<Object> items = scalesDataDAO.getTransportList(i, query);
		return WebUtil.toCustomItemList(items); 
	}

	public List<CustomItem> getDestinatarList(int i, String query) {
		List<Object> items = scalesDataDAO.getDestinatarList(i, query);
		return WebUtil.toCustomItemList(items); 
	}

	public List<CustomItem> getPunctulList(int i, String query) {
		List<Object> items = scalesDataDAO.getPunctulList(i, query);
		return WebUtil.toCustomItemList(items); 
	}

	public List<CustomItem> getTipulList(int i, String query) {
		List<Object> items = scalesDataDAO.getTipulList(i, query);
		return WebUtil.toCustomItemList(items); 
	}
	
	public int getDefSezon(Date fdate, long div){
		return scalesDataDAO.getDefSezon(fdate, div);
	}

	public int getLimit() {
		Object obj = scalesDataDAO.getEnvParam("YFSR_LIMIT_DIFF_MPFS");
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	public int getDefFurnizor() {
		Object obj = scalesDataDAO.getEnvParam("DEF_FURNIZOR");
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}
}
