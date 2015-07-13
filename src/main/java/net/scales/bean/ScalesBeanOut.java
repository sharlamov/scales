package net.scales.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.scales.model.CustomItem;
import net.scales.model.CustomUser;
import net.scales.model.ScalesData;
import net.scales.service.ScalesDataService;

@ManagedBean(name = "scalesBeanOut")
@ViewScoped
public class ScalesBeanOut extends AbstractBean {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{scalesDataServiceImpl}")
	private ScalesDataService scalesDataService;

	private List<ScalesData> scalesList;

	private ScalesData selectedScale;

	private CustomItem selectedItem;
	
	private List<CustomItem> dropList = new ArrayList<CustomItem>();
	
	private CustomUser cUser;
	
	@Override
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			cUser = getLoggedUser();
			setStartDate(nvl(getStartDate()));
			setEndDate(nvl(getEndDate()));
			dateChangeHandler();
		}
	}

	public void dateChangeHandler() {
		setScalesList(scalesDataService.getScalesListByPeriod(getStartDate(), getEndDate()));
	}
	
	public ScalesData getNewScale(){
		ScalesData sd = new ScalesData();
		Integer sezon = scalesDataService.getDefSezon(getStartDate(), cUser.getDiv().getId());
		sd.setSezon(sezon);
		return sd;
	}
	
	public void saveScale(){
		try {
			Date cDate = nvl(getStartDate());
			
			if(selectedScale.getId() == null){
				selectedScale.setDateIn(cDate);
				scalesDataService.insertScale(selectedScale, cUser);
				dateChangeHandler();
				selectedScale = null;
			}else{
				scalesDataService.updateScale(selectedScale, cUser);
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Save error", e.getMessage());
			context.addMessage(null, message);
			context.validationFailed();
			e.printStackTrace();
		}
	} 

	public List<CustomItem> completeTransportList(String query) {
        return scalesDataService.getTransportList(10, query);
    }
	
	public List<CustomItem> completeDestinatarList(String query) {
        return scalesDataService.getDestinatarList(10, query);
    }
	
	public List<CustomItem> completePunctulList(String query) {
        return scalesDataService.getPunctulList(10, query);
    }
	
	public List<CustomItem> completeTipulList(String query) {
        return scalesDataService.getTipulList(10, query);
    }
	
	public void setScalesDataService(ScalesDataService scalesDataService) {
		this.scalesDataService = scalesDataService;
	}

	public List<ScalesData> getScalesList() {
		return scalesList;
	}

	public void setScalesList(List<ScalesData> scalesList) {
		this.scalesList = scalesList;
	}

	public ScalesData getSelectedScale() {
		return selectedScale == null ? new ScalesData() : selectedScale;
	}

	public void setSelectedScale(ScalesData selectedScale) {
		this.selectedScale = selectedScale;
	}

	public CustomItem getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(CustomItem selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<CustomItem> getDropList() {
		return dropList;
	}

	public void setDropList(List<CustomItem> dropList) {
		this.dropList = dropList;
	}
}
