package umu.tds.modelo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	private int codigo;
	private String nUsuario, clave, mail, nombre, apellidos;
	private LocalDate fechaNacim;
	private List<ListaCanciones> listas;
	private boolean premium;

	public Usuario(String nUsuario, String clave, String mail, String nombre, String apellidos, LocalDate fechaNacim) {
		this.codigo = 0;
		this.nUsuario = nUsuario;
		this.clave = clave;
		this.mail = mail;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacim = fechaNacim;
		this.listas = new LinkedList<ListaCanciones>();
		this.premium = false;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	// Getters
	public int getCodigo() {
		return codigo;
	}

	public String getNusuario() {
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

	public void addListaCanciones(ListaCanciones l) {
		listas.add(l);
	}

	public List<ListaCanciones> getListas() {
		return new LinkedList<ListaCanciones>(listas);
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public boolean isPremium() {
		return premium;
	}

	public boolean login(String clave) {
		return this.clave.equals(clave);
	}
}
