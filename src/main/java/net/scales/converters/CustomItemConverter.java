package net.scales.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import net.scales.dao.ScalesDataDAO;
import net.scales.model.CustomItem;

import org.springframework.beans.factory.annotation.Autowired;

@FacesConverter("customItemConverter")
public class CustomItemConverter implements Converter {

	@Autowired
	private ScalesDataDAO scalesDataDAO;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		try{
			String[] data = value.split("#");
			switch (data.length) {
			case 1:
				return new CustomItem(null, null, data[0]);
			case 2:
				return data[0].isEmpty() ? new CustomItem(null, null, data[1])
						: new CustomItem(Long.parseLong(data[0]), null, data[1]);
			default:
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return ((CustomItem) object).toString();
		} else {
			return null;
		}
	}
}