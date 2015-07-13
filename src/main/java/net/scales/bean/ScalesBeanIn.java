package net.scales.bean;

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

@ManagedBean(name = "scalesBeanIn")
@ViewScoped
public class ScalesBeanIn extends AbstractBean {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{scalesDataServiceImpl}")
	private ScalesDataService scalesDataService;

	private List<ScalesData> scalesList;

	private ScalesData selectedScale;

	private CustomItem selectedItem;
	
	private CustomUser cUser;
	
	private int limit;
	
	@Override
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			cUser = getLoggedUser();
			setStartDate(nvl(getStartDate()));
			setEndDate(nvl(getEndDate()));
			limit = scalesDataService.getLimit();
			dateChangeHandler();
		}
	}

	public void dateChangeHandler() {
		setScalesList(scalesDataService.getScalesInByPeriod(getStartDate(), getEndDate()));
	}
	
	public ScalesData getNewScale(){
		ScalesData sd = new ScalesData();
		Integer sezon = scalesDataService.getDefSezon(getStartDate(), cUser.getDiv().getId());
		sd.setSezon(sezon);
		return sd;
	}

	public String bgcolor(ScalesData sd){
		double mNetto = sd.getMasaNeto();
		double mTtn = sd.getTtnMasa() == null ? 0 : sd.getTtnMasa();
		
		if(mNetto - mTtn < limit)
			return "rowstrongred";
		if(mNetto - mTtn < 0)
			return "rowred";
		if(mNetto == mTtn)
			return "rowgreen";
		if(mNetto > mTtn)
			return "rowstronggreen";
		
		return "";
	}
	
	public void saveScale(){
		try {
			Date cDate = nvl(getStartDate());
			
			if(selectedScale.getId() == null){
				selectedScale.setDateIn(cDate);
				scalesDataService.insertScaleIn(selectedScale, cUser);
				dateChangeHandler();
				selectedScale = null;
			}else{
				scalesDataService.updateScaleIn(selectedScale, cUser);
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

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
