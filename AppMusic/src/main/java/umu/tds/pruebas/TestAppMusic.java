package umu.tds.pruebas;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import umu.tds.controlador.AppMusic;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.EstiloMusical;
import umu.tds.modelo.Interprete;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.Usuario;

public class TestAppMusic {
	AppMusic app = AppMusic.getInstancia();
	Usuario u;
	ListaCanciones p;
	Cancion c1, c2;

	@Before
	public void inicializar() {
		u = new Usuario("mlm", "1234", "maria.lopezm3@um.es", "Maria", "Lopez Martinez", LocalDate.parse("2000-03-24"));
		p = new ListaCanciones("playlist");
		List<Interprete> i = new LinkedList<Interprete>();
		i.add(new Interprete("Nina Simone"));
		EstiloMusical e = new EstiloMusical("JAZZ");
		c1 = new Cancion("Summertime", e, "/AppMusic/src/main/java/umu.tds.catalogo.canciones/JAZZ/Nina Simone - Summertime.mp3", i);
		c2 = new Cancion("My Baby Just Cares For Me", e, "/AppMusic/src/main/java/umu.tds.catalogo.canciones/JAZZ/Nina Simone - My Baby Just Cares For Me.mp3", i);
		p.addCancion(c1);
		p.addCancion(c2);
		u.addListaCanciones(p);
		app.setUsuarioActual(u);
		app.setListaActual("playlist");
		app.seleccionarDescuento(LocalDate.now());
		
	}

	@Test
	public void testCalcularDescuento() {
		double precioEsperado = 6.0;
		assertEquals("Resultado testCalcularDescuento", precioEsperado, app.calcularDescuento(), 0.01);
	}
	@Test
	public void testGetNombreListaActual() {
		String nombreEsperado = "playlist";
		assertEquals("Resultado testGetNombreListaActual", nombreEsperado, app.getNombreListaActual());
	}
	
	@Test
	public void testGetCancionesListaActual() {
		List<Cancion> cancionesEsperadas = new LinkedList<Cancion>();
		cancionesEsperadas.add(c1);
		cancionesEsperadas.add(c2);
		assertEquals("Resultado testGetCancionesListaActual", cancionesEsperadas, app.getCancionesListaActual());
		
	}

}
