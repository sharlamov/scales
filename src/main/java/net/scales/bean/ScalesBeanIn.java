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

@ManagedBean(name = "scalesBeanIn")
@ViewScoped
public class ScalesBeanIn extends AbstractBean {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{scaleServiceImpl}")
	private ScaleService scaleService;

	private List<Scales> scalesList;

	private Scales selectedScale;

	private CustomItem selectedItem;

	private int limit;

	@Override
	public void init() {
		setStartDate(nvl(getStartDate()));
		setEndDate(nvl(getEndDate()));
		limit = scaleService.getLimit();
		dateChangeHandler();
	}

	public void dateChangeHandler() {
		setScalesList(scaleService.getScalesInByPeriod(getStartDate(),
				getEndDate()));
	}

	public Scales getNewScale() {
		Scales sd = new Scales();
		Integer sezon = scaleService.getDefSezon(getStartDate(),
				getLoggedUser().getDiv().getId());
		sd.setSezon(sezon);
		return sd;
	}

	public String bgcolor(Scales sd) {
		double mNetto = sd.getMasaNeto();
		double mTtn = sd.getTtnMasa() == null ? 0 : sd.getTtnMasa();

		if (mNetto - mTtn < limit)
			return "rowstrongred";
		if (mNetto - mTtn < 0)
			return "rowred";
		if (mNetto == mTtn)
			return "rowgreen";
		if (mNetto > mTtn)
			return "rowstronggreen";

		return "";
	}

	public void saveScale() {
		try {
			Date cDate = nvl(getStartDate());

			if (selectedScale.getId() == null) {
				selectedScale.setDateIn(cDate);
				scaleService.insertScaleIn(selectedScale, getLoggedUser());
				dateChangeHandler();
				selectedScale = null;
			} else {
				scaleService.updateScaleIn(selectedScale, getLoggedUser());
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

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
