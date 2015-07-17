package net.scales.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import net.scales.model.CustomUser;
import net.scales.service.UserService;

import org.primefaces.context.RequestContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean(name = "authenticationBean")
@SessionScoped
public class AuthenticationBean extends AbstractBean {

	private static final long serialVersionUID = 4915047588553578520L;

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;

	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;

	private String userName;

	private String password;

	private CustomUser currentUser;

	@Override
	public void init() {
	}

	public void login(ActionEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean loggedIn = false;
		String page = "";

		try {
			Authentication request = new UsernamePasswordAuthenticationToken(
					userName, password);
			Authentication result = authenticationManager.authenticate(request);
			currentUser = (CustomUser) result.getPrincipal();
			userService.updateUserDetails(currentUser);
			SecurityContextHolder.getContext().setAuthentication(result);
			loggedIn = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome",
					userName);
			page = getStartPage();
		} catch (Exception e) {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					"Loggin Error", e.getMessage());
			e.printStackTrace();
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("loggedIn", loggedIn);
		context.addCallbackParam("loggedPage", page);
	}

	public String logout() {
		SecurityContextHolder.clearContext();
		return "login?faces-redirect=true";
	}

	public String getStartPage() {
		switch (currentUser.getLevel()) {
		case LABORANT:
			return "analiseIn.xhtml?type=in";
		case CANTAR:
			return "iesiri.xhtml";
		default:
			return "iesiri.xhtml";
		}
	}

	public String isVisible(String page) {
		switch (currentUser.getLevel()) {
		case LABORANT:
			return (page.equals("analiseIn.xhtml") || page.equals("analiseOut.xhtml")) ? "display: block;" : "display: none;";
		case CANTAR:
			return (page.equals("intrari.xhtml") || page.equals("iesiri.xhtml")) ?  "display: block;" : "display: none;";
		case ADMIN:
			return "";
		default:
			return  "display: none;";
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CustomUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(CustomUser currentUser) {
		this.currentUser = currentUser;
	}
}
