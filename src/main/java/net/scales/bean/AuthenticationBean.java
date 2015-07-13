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
public class AuthenticationBean extends AbstractBean{
     
	private static final long serialVersionUID = 4915047588553578520L;

	@ManagedProperty(value="#{authenticationManager}")
    private AuthenticationManager authenticationManager;

	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;
	
    private String userName;
    
    private String password;

	@Override
	public void init() {
	}

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
         
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(userName, password);
            Authentication result = authenticationManager.authenticate(request);
            userService.updateUserDetails((CustomUser) result.getPrincipal());
            SecurityContextHolder.getContext().setAuthentication(result);
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", userName);
        }catch (Exception e){
        	loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Loggin Error", e.getMessage());
            e.printStackTrace();
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
    }
    
	public String logout() {
		SecurityContextHolder.clearContext();
		return "login?faces-redirect=true";
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
    
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
