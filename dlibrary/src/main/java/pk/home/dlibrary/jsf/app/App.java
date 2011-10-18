/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.home.dlibrary.jsf.app;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author traveler
 */
@ManagedBean(name = "app")
@RequestScoped
public class App implements Serializable {
    
    
    private static final long serialVersionUID = 2167179189163857373L;

    public Date getDate() {
        return new Date();
    }

    public void setDate(Date date) {
        
    }
}
