package umu.tds.modelo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	private int codigo;
	private String nUsuario, clave, mail, nombre, apellidos;
	private LocalDate fechaNacim;
	private List<ListaCanciones> listas;
	private Descuento descuento;
	private String tipoDescuento;
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
		this.tipoDescuento = "Ninguno";
		this.descuento = new DescuentoNormal();
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

	public Descuento getDescuento() {
		return descuento;
	}

	public ListaCanciones getLista(String nombre) {
		for (ListaCanciones l : listas) {
			if (l.getNombrePlaylist().equals(nombre)) {
				return l;
			}
		}
		return null;
	}

	public boolean isEstudianteUMU() {
		int index = this.mail.indexOf('@');
		String dominio = this.mail.substring(index);
		return dominio.equals("@um.es");
	}

	public boolean isMayor(int anyoActual) {
		return (anyoActual - this.fechaNacim.getYear()) >= DescuentoMayores.EDAD;
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

	public String getTipoDescuento() {
		return this.tipoDescuento;
	}

	public boolean login(String clave) {
		return this.clave.equals(clave);
	}

	public List<String> getNombreListas() {
		List<String> nombreListas = new LinkedList<String>();
		for (ListaCanciones l : this.listas) {
			nombreListas.add(l.getNombrePlaylist());
		}
		return nombreListas;
	}

	public void setDescuento(int codigoDescuento) {
		switch (codigoDescuento) {
		case Descuento.NAVIDAD:
			this.descuento = new DescuentoNavidad();
			this.tipoDescuento = "Navidad";
			break;
		case Descuento.ESTUDIANTE:
			this.descuento = new DescuentoEstudiante();
			this.tipoDescuento = "Estudiante";
			break;
		case Descuento.MAYORES:
			this.descuento = new DescuentoMayores();
			this.tipoDescuento = "Mayor de " + DescuentoMayores.EDAD + " años";

			break;
		default:
			throw new IllegalArgumentException("Codigo de Descuento incorrecto");
		}
	}

	public void eliminarLista(ListaCanciones l) {
		this.listas.remove(l);
	}

	public boolean existePlaylist(String nombre) {
		for (ListaCanciones l : listas)
			if (l.getNombrePlaylist().equals(nombre))
				return true;
		return false;
	}
}
