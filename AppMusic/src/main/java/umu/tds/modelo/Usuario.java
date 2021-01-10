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
		return tipoDescuento;
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

	public void eliminarLista(ListaCanciones l) {
		this.listas.remove(l);
	}

	public boolean existePlaylist(String nombre) {
		for (ListaCanciones l : listas)
			if (l.getNombrePlaylist().equals(nombre))
				return true;
		return false;
	}

	public double calcularDescuento(double precio) {
		return descuento.calcDescuento(precio);
	}

	public int seleccionarDescuento() {
		int d = -1;
		LocalDate now = LocalDate.now();
		if ((now.getMonthValue() == 1 && now.getDayOfMonth() <= 6)
				|| (now.getMonthValue() == 12 && now.getDayOfMonth() >= 25)) {
			d = Descuento.NAVIDAD;
			this.descuento = new DescuentoNavidad();
			this.tipoDescuento = "Navidad";
		} else if (this.isEstudianteUMU()) {
			d = Descuento.ESTUDIANTE;
			this.descuento = new DescuentoEstudiante();
			this.tipoDescuento = "Estudiante";
		} else if (this.isMayor(now.getYear())) {
			d = Descuento.MAYORES;
			this.descuento = new DescuentoMayores();
			this.tipoDescuento = "Mayor de " + DescuentoMayores.EDAD + " años";
		}
		return d;
	}
}
