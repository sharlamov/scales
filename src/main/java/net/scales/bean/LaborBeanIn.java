package net.scales.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.scales.model.CustomItem;
import net.scales.model.Labor;
import net.scales.service.LaborService;
import net.scales.service.ScaleService;

@ManagedBean(name = "laborBeanIn")
@ViewScoped
public class LaborBeanIn extends AbstractBean {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{laborServiceImpl}")
	private LaborService laborService;

	@ManagedProperty(value = "#{scaleServiceImpl}")
	private ScaleService scaleService;

	private List<Labor> laborList;

	private Labor selectedLabor;

	private Labor oldLabor;

	private String type;

	@Override
	public void init() {
		type = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("type");
		setStartDate(nvl(getStartDate()));
		dateChangeHandler();
	}

	public void dateChangeHandler() {
		laborList = type.equalsIgnoreCase("IN") ? 
				laborService.getLaborsByPeriod(getStartDate(), getLoggedUser())
				: laborService.getLaborOut(getStartDate(), getLoggedUser());
	}

	public Labor getNewLabor() {
		setOldLabor(null);
		Labor l = new Labor();
		Long sezon = (long) scaleService.getDefSezon(getStartDate(),
				getLoggedUser().getDiv().getId());
		l.setSezon(sezon);
		l.setDateAnaliz(getStartDate());
		return l;
	}

	public void openEditForm(Labor labor) {
		try {
			setOldLabor((Labor) labor.clone());
			setSelectedLabor(labor);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public void saveLabor() {
		try {
			if (oldLabor == null) {
				if(type.equalsIgnoreCase("in"))
					laborService.insertLabor(selectedLabor, getLoggedUser());
				else
					laborService.insertLaborOut(selectedLabor, getLoggedUser());
				dateChangeHandler();
				selectedLabor = null;
			} else {
				laborService.updateLabor(selectedLabor, oldLabor,
						getLoggedUser());
				setOldLabor(null);
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Save error", e.getMessage());
			context.addMessage(null, message);
			context.validationFailed();
			e.printStackTrace();
		}
	}

	public List<CustomItem> completeTipulList(String query) {
		return laborService.getTipulList(10, query);
	}
	
	public List<CustomItem> completeDepList(String query) {
		return laborService.getDepList(10, query);
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

	public Labor getOldLabor() {
		return oldLabor;
	}

	public void setOldLabor(Labor oldLabor) {
		this.oldLabor = oldLabor;
	}

	public void setScaleService(ScaleService scaleService) {
		this.scaleService = scaleService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
