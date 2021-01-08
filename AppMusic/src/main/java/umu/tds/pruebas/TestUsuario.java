package umu.tds.pruebas;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.BeforeClass;
import org.junit.Test;

import umu.tds.modelo.Usuario;

public class TestUsuario {
	private static Usuario u;

	@BeforeClass
	public static void inicializar() {
		u = new Usuario("mlm", "1234", "maria.lopezm3@um.es", "Maria", "Lopez Martinez", LocalDate.parse("2000-03-24"));
	}

	@Test
	public void testIsEstudianteUMU() {
		assertTrue("Resultado testIsEstudianteUMU", u.isEstudianteUMU());
	}

	@Test
	public void testIsMayor() {
		assertFalse("Resultado testIsMayor", u.isMayor(LocalDate.now().getYear()));
	}

	@Test
	public void testLogin() {
		assertTrue("Resultado testLogin", u.login("1234"));
	}

}
