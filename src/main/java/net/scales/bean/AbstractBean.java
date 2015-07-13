package net.scales.bean;

import java.io.Serializable;
import java.util.Date;

import net.scales.model.CustomUser;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = -7244932139281652821L;
	
	private Date startDate;
	
	private Date endDate;

	public abstract void init();
	
	public CustomUser getLoggedUser() {
		CustomUser loggedUser = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			loggedUser = (CustomUser) principal;
		}
		return loggedUser;
	}

	public Date nvl(Date d){
		 return d == null ? new Date() : d;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}