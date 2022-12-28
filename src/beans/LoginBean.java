package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Erabiltzailea;

public class LoginBean {
	BLFacade bl = FacadeBean.getBusinessLogic();
	private String izena;
	private String pasahitza;

	public LoginBean() {}
	
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getPasahitza() {
		return pasahitza;
	}
	public void setPasahitza() {
		this. pasahitza = pasahitza;
	}
	
	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public String egiaztatu() {
		Erabiltzailea erab = bl.isLogin(izena, pasahitza);
		if (erab instanceof Erabiltzailea)
			return "ok";
		else
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Izena edo pasahitza ez dira zuzenak."));
		System.out.println("Error in login");
		return "error";
	}
}