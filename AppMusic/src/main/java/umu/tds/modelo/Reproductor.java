package umu.tds.modelo;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Reproductor {

	private static Reproductor reproductor = null;
	private MediaPlayer mediaPlayer;
	private String cancionActual = null;

	public static Reproductor getUnicaInstancia() {
		if (reproductor == null)
			return new Reproductor();
		return reproductor;
	}

	private Reproductor() {
		com.sun.javafx.application.PlatformImpl.startup(() -> {
		});

	}

	public void reproducirCancion(String ruta) {
		// Si todavia no se ha reproducido ninguna cancion o
		// la cancion que queremos reproducir es distinta a la que estaba sonando
		// generamos un nuevo media player, si no reproducimos el anterior
		if (cancionActual == null || !cancionActual.equals(ruta)) {
			File f = new File(ruta);
			Media hit = new Media(f.toURI().toString());
			if (mediaPlayer != null)
				mediaPlayer.stop();
			mediaPlayer = new MediaPlayer(hit);
			cancionActual = ruta;
		}
		mediaPlayer.play();
	}

	public void pausarCancion() {
		mediaPlayer.pause();
	}
}
