package umu.tds.pruebas;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import umu.tds.controlador.AppMusic;
import umu.tds.modelo.Descuento;
import umu.tds.modelo.Usuario;

public class TestAppMusic {
	Usuario u;
	@Before
	public void inicializar() {
		u = new Usuario("mlm", "1234", "maria.lopezm3@um.es", "Maria", "Lopez Martinez", LocalDate.parse("2000-03-24"));
		AppMusic.getInstancia().setUsuarioActual(u);
		AppMusic.getInstancia().seleccionarDescuento();
	}

	@Test
	public void testseleccionarDescuento() {
		int descuentoEsperado = Descuento.ESTUDIANTE;
		assertEquals("Resultado testseleccionarDescuento", descuentoEsperado, AppMusic.getInstancia().seleccionarDescuento());
	}
	@Test
	public void testCalcularDescuento() {
		double precioEsperado = 6.0;
		assertEquals("Resultado testCalcularDescuento", precioEsperado, AppMusic.getInstancia().calcularDescuento(), 0.01);
	}

	@Test
	public void testLogin() {
		//Para que funcione el login este usuario debe estar creado en la BD
		assertTrue("Resultado testLogin", AppMusic.getInstancia().login(u.getNusuario(), u.getClave()));
	}
	
}
