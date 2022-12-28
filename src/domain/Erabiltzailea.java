package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Erabiltzailea implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String izena;
	public String abizena1;
	public String abizena2;
	@Id
	public String erabiltzaileIzena;
	public String pasahitza;
	public String telefonoZbkia;
	public String email;
	public Date jaiotzeData;
	
	public Erabiltzailea() {
		super();
	}
	
	public Erabiltzailea (String izena, String abizena1, String abizena2, String erabiltzaileIzena, String pasahitza, String telefonoZbkia, String email, Date jaiotzeData) {
		this.izena = izena;
		this.abizena1 = abizena1;
		this.abizena2 = abizena2;
		this.erabiltzaileIzena = erabiltzaileIzena;
		this.pasahitza = pasahitza;
		this.telefonoZbkia = telefonoZbkia;
		this.email=email;
		this.jaiotzeData=jaiotzeData;
	}
	
	public String getErabiltzaileIzena() {
		return erabiltzaileIzena;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
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

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
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

	public void setErabiltzaileIzena(String erabiltzaileIzena) {
		this.erabiltzaileIzena = erabiltzaileIzena;
	}
}
