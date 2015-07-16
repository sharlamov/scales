package net.scales.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.scales.dao.LaborDAO;
import net.scales.dao.UserDAO;
import net.scales.model.CustomItem;
import net.scales.model.CustomUser;
import net.scales.model.Labor;
import net.scales.util.WebUtil;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LaborServiceImpl implements LaborService {

	@Autowired
	private LaborDAO laborDAO;

	@Autowired
	private UserDAO userDAO;

	public List<Labor> getLaborsByPeriod(Date date, CustomUser user) {
		userDAO.initContext(user);
		
		List<Object> list = laborDAO.getLaborsByPeriod(date);
		List<Labor> newlist = new ArrayList<Labor>(list.size());
		for (Object obj : list) {
			newlist.add(convertToLabor(obj));
		}
		return newlist;
	}
	
	public List<Labor> getLaborOut(Date date, CustomUser user) {
		userDAO.initContext(user);
		
		List<Object> list = laborDAO.getLaborsOutByDate(date);
		List<Labor> newlist = new ArrayList<Labor>(list.size());
		for (Object obj : list) {
			newlist.add(convertToLabor(obj));
		}
		return newlist;
	}
	
	private Labor convertToLabor(Object obj) {
		int i = 0;
		Labor labor = new Labor();
		
		Object[] data = (Object[]) obj;
		labor.setSezon(WebUtil.parse(data[i++], Long.class));
		labor.setNrAnaliz(WebUtil.parse(data[i++], Long.class));
		labor.setDateAnaliz(WebUtil.parse(data[i++], Date.class));
		labor.setAnlzVlajn(WebUtil.parse(data[i++], Double.class));
		labor.setAnlzSorn(WebUtil.parse(data[i++], Double.class));
		labor.setAnlzKislot(WebUtil.parse(data[i++], Double.class));
		labor.setAnlzZernprim(WebUtil.parse(data[i++], Double.class));
		labor.setAnlzZaraj(WebUtil.parse(data[i++], Double.class));
		labor.setAnlzMaslprim(WebUtil.parse(data[i++], Double.class));
		labor.setSanlzVlajn(WebUtil.parse(data[i++], Double.class));
		labor.setSanlzSorn(WebUtil.parse(data[i++], Double.class));
		labor.setSanlzKislot(WebUtil.parse(data[i++], Double.class));
		labor.setSanlzZernprim(WebUtil.parse(data[i++], Double.class));
		labor.setSanlzZaraj(WebUtil.parse(data[i++], Double.class));
		labor.setSanlzMaslprim(WebUtil.parse(data[i++], Double.class));
		labor.setComment(WebUtil.parse(data[i++], String.class));
		labor.setUserId(WebUtil.parse(data[i++], Long.class));
		labor.setSertificat(WebUtil.parse(data[i++], String.class));
		labor.setPriznakArm(WebUtil.parse(data[i++], Long.class));
		labor.setPartida(WebUtil.parse(data[i++], Long.class));
		labor.setTtnSeria(WebUtil.parse(data[i++], String.class));
		labor.setTtnNr(WebUtil.parse(data[i++], Long.class));
		labor.setAnlzNatura(WebUtil.parse(data[i++], Double.class));
		labor.setAnlzKleik(WebUtil.parse(data[i++], Double.class));
		labor.setDiv(WebUtil.parse(data[i++], Long.class));
		labor.setSc(new CustomItem(data[i++], null, data[i++]));
		labor.setAnlzVihod(WebUtil.parse(data[i++], Double.class));
		labor.setNrSert(WebUtil.parse(data[i++], String.class));
		labor.setAnlzBitoeZerno(WebUtil.parse(data[i++], Double.class));
		labor.setElevator(WebUtil.parse(data[i++], Long.class));
		labor.setPostav(WebUtil.parse(data[i++], String.class));
		labor.setOtpravit(WebUtil.parse(data[i++], String.class));
		labor.setDep(new CustomItem(data[i++], null, data[i++]));
		return labor;
	}
	
	public List<CustomItem> getTipulList(int i, String query) {
		List<Object> items = laborDAO.getTipulList(i, query);
		return WebUtil.toCustomItemList(items); 
	}

	public void insertLabor(Labor labor, CustomUser user) throws HibernateException, SQLException {
		labor.setDiv(user.getDiv().getId());
		labor.setElevator(user.getElevator().getId());
		labor.setUserId(user.getId());
		userDAO.initContext(user);
		laborDAO.insertLabor(labor);
	}

	public void insertLaborOut(Labor labor, CustomUser user) throws HibernateException, SQLException {
		labor.setDiv(user.getDiv().getId());
		labor.setElevator(user.getElevator().getId());
		labor.setUserId(user.getId());
		userDAO.initContext(user);
		laborDAO.insertLaborOut(labor);
	}

	public void updateLabor(Labor labor, Labor oldlabor, CustomUser user) throws HibernateException, SQLException {
		userDAO.initContext(user);
		laborDAO.updateLabor(labor, oldlabor);
	}

	public List<CustomItem> getDepList(int i, String query) {
		List<Object> items = laborDAO.getDepList(i, query);
		return WebUtil.toCustomItemList(items); 
	}
}
