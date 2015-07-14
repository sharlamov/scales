package net.scales.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ComponentSystemEvent;

import net.scales.model.CustomUser;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = -7244932139281652821L;

	private Date startDate;
	
	private Date endDate;
	
	private CustomUser loggedUser;
	
	boolean isInitialized = false;
	
	public void initController(ComponentSystemEvent componentSystemEvent) throws Exception {
        if (!isInitialized) {
        	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		if (principal instanceof UserDetails) {
    			setLoggedUser((CustomUser) principal);
    			init();
                isInitialized = true;
    		}
        }
    }

    public abstract void init() throws Exception;
    
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

	public CustomUser getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(CustomUser loggedUser) {
		this.loggedUser = loggedUser;
	}
}