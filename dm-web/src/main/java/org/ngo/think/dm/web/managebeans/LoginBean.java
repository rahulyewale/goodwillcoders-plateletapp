package org.ngo.think.dm.web.managebeans;
import java.io.Serializable;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
 
 
/**
 * Simple login bean.
 *
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
 
    private static final long serialVersionUID = 7765876811740798583L;
 
    // Simple user database :)
    private static final String[] USERS = {"admin:th!nk123","guest:th!nk123","officestaff:th!nk123"};
     
    private String username;
    private String password;
     
    private boolean loggedIn;
    
    //to show Hide uploadDonors to Admin and others resp.
    private String upload;
     
    @ManagedProperty(value="#{navigationBean}")
    private NavigationBean navigationBean;
     
    /**
     * Login operation.
     * @return
     */
    public String doLogin() {
        // Get every user from our sample database :)
        for (String user: USERS) {
            String dbUsername = user.split(":")[0];
            String dbPassword = user.split(":")[1];
             
            // Successful login
            if (dbUsername.equals(username) && dbPassword.equals(password)) {
                loggedIn = true;
                if(dbUsername.equals("admin"))
                {
                	setUpload("block");
                }
                else
                {
                	setUpload("none");
                }
                return navigationBean.redirectToWelcome();
            }
        }
         
        // Set login ERROR
        FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
         
        // To to login page
        return navigationBean.toLogin();
         
    }
     
    /**
     * Logout operation.
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;
        username ="";
        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return navigationBean.toLogin();
    }
 
    // ------------------------------
    // Getters & Setters
     
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public boolean isLoggedIn() {
        return loggedIn;
    }
 
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
 
    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}
     
}
