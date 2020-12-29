package umu.tds.modelo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		this.descuento = null;
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

	public int getAnyoNacim() {
		return this.fechaNacim.getDayOfYear();
	}

	public boolean isEstudianteUMU() {
		Pattern pattern = Pattern.compile(".*@um.es");
		Matcher matcher = pattern.matcher(mail);
		return matcher.find();
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

	public String[] getNombreListas() {
		String[] nombreListas = new String[this.listas.size()];
		int i = 0;
		for (ListaCanciones l : this.listas) {
			nombreListas[i++] = l.getNombrePlaylist();
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
}
