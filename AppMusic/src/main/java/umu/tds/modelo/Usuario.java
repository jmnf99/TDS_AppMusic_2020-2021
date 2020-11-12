package umu.tds.modelo;

import java.time.LocalDate;

public class Usuario {
	private String nUsuario, clave, mail, nombre, apellidos;
	private LocalDate fechaNacim;
	private boolean premium;
	
	public Usuario(String nUsuario, String clave, String mail, String nombre, String apellidos, LocalDate fechaNacim) {
		this.nUsuario = nUsuario;
		this.clave = clave;
		this.mail = mail;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacim = fechaNacim;
		this.premium = false;
	}
	
	//Getters
	public String getUsuario() {
		return nUsuario;
	}

	public String getClave() {
		return clave;
	}
	
	public String getMail() {
		return mail;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public LocalDate getFechaNacim() {
		return fechaNacim;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public boolean login(String clave) {
		return this.clave.equals(clave);
	}
}
