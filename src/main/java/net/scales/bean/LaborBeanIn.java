package net.scales.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.scales.model.CustomItem;
import net.scales.model.CustomUser;
import net.scales.model.Labor;
import net.scales.service.LaborService;

@ManagedBean(name = "laborBeanIn")
@ViewScoped
public class LaborBeanIn extends AbstractBean {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{laborServiceImpl}")
	private LaborService laborService;

	private List<Labor> laborList;

	private Labor selectedLabor;

	private CustomUser cUser;
	
	@Override
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			cUser = getLoggedUser();
			setStartDate(nvl(getStartDate()));
			dateChangeHandler();
		}
	}

	public void dateChangeHandler() {
		laborList = laborService.getLaborsByPeriod(getStartDate(), cUser);
	}
	
	public Labor getNewLabor(){
		return new Labor();
	}

	public List<CustomItem> completeTipulList(String query) {
        return laborService.getTipulList(10, query);
    }

	public void setLaborService(LaborService laborService) {
		this.laborService = laborService;
	}

	public List<Labor> getLaborList() {
		return laborList;
	}

	public void setLaborList(List<Labor> laborList) {
		this.laborList = laborList;
	}

	public Labor getSelectedLabor() {
		return selectedLabor;
	}

	public void setSelectedLabor(Labor selectedLabor) {
		this.selectedLabor = selectedLabor;
	}
}
