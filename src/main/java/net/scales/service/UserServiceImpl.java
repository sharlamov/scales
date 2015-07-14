package net.scales.service;

import java.util.HashSet;
import java.util.Set;

import net.scales.dao.ScaleDAO;
import net.scales.dao.UserDAO;
import net.scales.enums.UserRoleEnum;
import net.scales.model.CustomItem;
import net.scales.model.CustomUser;
import net.scales.util.WebUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
	private UserDAO userDAO;
    
    @Autowired
	private ScaleDAO scalesDataDAO;
    
    
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.ROLE_USER.name()));
        
        CustomUser userDetails = new CustomUser();
        
        if(!arg0.isEmpty()){
        	Object userCredentials = userDAO.loadUserByUsername(arg0);
        	if(userCredentials != null){
            	int i = 0;
            	Object[] data = (Object[]) userCredentials;
            	userDetails.setId(WebUtil.parse(data[i++], Long.class));
                userDetails.setUsername(WebUtil.parse(data[i++], String.class));
                userDetails.setAdminLevel(Integer.parseInt(data[i++].toString()));
                userDetails.setPassword(WebUtil.parse(data[i++], String.class));
                userDetails.setCustomerAuthorities(roles);
        	}
        }
		return userDetails;
	}


	public void updateUserDetails(CustomUser cu) throws UsernameNotFoundException  {
		 CustomItem elevator = userDAO.getUserElevator(cu.getId());
         if(elevator == null){
         	throw new UsernameNotFoundException("Elevator not found");
         }

         CustomItem div = userDAO.getUserDiv(cu.getId());
         if(div == null || div.getLabel() == null){
         	throw new UsernameNotFoundException("DIV not found");
         }
         
         Integer type = userDAO.getUserScaleType(cu.getId());
         
         cu.setScaleType(type);
         cu.setElevator(elevator);
         cu.setDiv(div);
	}
}
