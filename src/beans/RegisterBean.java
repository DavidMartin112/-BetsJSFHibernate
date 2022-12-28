package beans;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;
import domain.Erabiltzailea;
import exceptions.UserAlreadyExist;

public class RegisterBean {
	BLFacade bl = FacadeBean.getBusinessLogic();
	private String izena;
	private String abizena1;
	private String abizena2;
	private String erabiltzaileIzena;
	private String pasahitza;
	private String errepass;
	private String telefonoZbkia;
	private String email;
	private Date jaiotzeData;

	public RegisterBean() {}
	
	
	
	public String getErrepass() {
		return errepass;
	}



	public void setErrepass(String errepass) {
		this.errepass = errepass;
	}



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
	public String getAbizena1() {
		return abizena1;
	}

	public void setAbizena1(String abizena1) {
		this.abizena1 = abizena1;
	}

	public String getAbizena2() {
		return abizena2;
	}

	public void setAbizena2(String abizena2) {
		this.abizena2 = abizena2;
	}

	public String getErabiltzaileIzena() {
		return erabiltzaileIzena;
	}

	public void setErabiltzaileIzena(String erabiltzaileIzena) {
		this.erabiltzaileIzena = erabiltzaileIzena;
	}

	public String getTelefonoZbkia() {
		return telefonoZbkia;
	}

	public void setTelefonoZbkia(String telefonoZbkia) {
		this.telefonoZbkia = telefonoZbkia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJaiotzeData() {
		return jaiotzeData;
	}

	public void setJaiotzeData(Date jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public String erregistratu() {
		if(izena.isEmpty() || abizena1.isEmpty() || abizena2.isEmpty() || erabiltzaileIzena.isEmpty() || pasahitza.isEmpty() ||
				telefonoZbkia.isEmpty() || email.isEmpty() || jaiotzeData == null || errepass.isEmpty()) {
			return("error");
		}else {
			if(!pasahitza.equals(errepass)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Pasahitzak berdinak izan behar dira."));
				return("error");
			}
			try {	
				Erabiltzailea erab = bl.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, email, jaiotzeData);
				if (erab instanceof Erabiltzailea) {
					return("login");
				}else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Errorea erregistratzeko garaian."));
					return("error");
				}
			}catch(UserAlreadyExist e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Erabiltzailea existitzen da."));
				return("error");
			}
		}
	}
}