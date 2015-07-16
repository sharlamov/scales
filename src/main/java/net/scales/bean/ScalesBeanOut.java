package net.scales.bean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.scales.model.CustomItem;
import net.scales.model.Scales;
import net.scales.service.ScaleService;

@ManagedBean(name = "scalesBeanOut")
@ViewScoped
public class ScalesBeanOut extends AbstractBean {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{scaleServiceImpl}")
	private ScaleService scaleService;

	private List<Scales> scalesList;

	private Scales selectedScale;

	private CustomItem selectedItem;

	@Override
	public void init() {
		setStartDate(nvl(getStartDate()));
		setEndDate(nvl(getEndDate()));
		dateChangeHandler();
	}

	public void dateChangeHandler() {
		setScalesList(scaleService.getScalesListByPeriod(getStartDate(),
				getEndDate()));
	}

	public Scales getNewScale() {
		Scales sd = new Scales();
		Integer sezon = scaleService.getDefSezon(getStartDate(),
				getLoggedUser().getDiv().getId());
		sd.setSezon(sezon);
		return sd;
	}

	public void saveScale() {
		try {
			Date cDate = nvl(getStartDate());

			if (selectedScale.getId() == null) {
				selectedScale.setDateIn(cDate);
				scaleService.insertScale(selectedScale, getLoggedUser());
				dateChangeHandler();
				selectedScale = null;
			} else {
				scaleService.updateScale(selectedScale, getLoggedUser());
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

	public List<CustomItem> completeTransportList(String query) {
		return scaleService.getTransportList(10, query);
	}

	public List<CustomItem> completeDestinatarList(String query) {
		return scaleService.getDestinatarList(10, query);
	}

	public List<CustomItem> completePunctulList(String query) {
		return scaleService.getPunctulList(10, query);
	}

	public List<CustomItem> completeTipulList(String query) {
		return scaleService.getTipulList(10, query);
	}

	public void setScaleService(ScaleService scaleService) {
		this.scaleService = scaleService;
	}

	public List<Scales> getScalesList() {
		return scalesList;
	}

	public void setScalesList(List<Scales> scalesList) {
		this.scalesList = scalesList;
	}

	public Scales getSelectedScale() {
		return selectedScale == null ? new Scales() : selectedScale;
	}

	public void setSelectedScale(Scales selectedScale) {
		this.selectedScale = selectedScale;
	}

	public CustomItem getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(CustomItem selectedItem) {
		this.selectedItem = selectedItem;
	}
}
