package umu.tds.modelo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Reproductor {

	private static Reproductor reproductor = null;
	private MediaPlayer mediaPlayer;
	private String cancionActual = null;
	private boolean reproduciendo = false;
	private String binPath;
	private String tempPath;

	public static Reproductor getUnicaInstancia() {
		if (reproductor == null)
			return new Reproductor();
		return reproductor;
	}

	private Reproductor() {
		com.sun.javafx.application.PlatformImpl.startup(() -> {
		});
		binPath = Reproductor.class.getClassLoader().getResource(".").getPath();
		binPath = binPath.replaceFirst("/", "");
		// quitar "/" añadida al inicio del path en plataforma Windows
		tempPath = binPath.replace("/bin", "/temp");
		tempPath += "tmp";
		File f = new File(tempPath);
		f.mkdir();
	}

	public void reproducirCancion(String ruta) {
		// Si todavia no se ha reproducido ninguna cancion o
		// la cancion que queremos reproducir es distinta a la que estaba sonando
		// generamos un nuevo media player, si no reproducimos el anterior
		if (cancionActual == null || !cancionActual.equals(ruta)) {
			if (ruta.toLowerCase().contains("http")) {
				URL uri;
				try {
					uri = new URL(ruta);
					System.setProperty("java.io.tmpdir", tempPath);
					Path mp3 = Files.createTempFile("now-playing", ".mp3");

					try (InputStream stream = uri.openStream()) {
						Files.copy(stream, mp3, StandardCopyOption.REPLACE_EXISTING);
					}

					Media media = new Media(mp3.toFile().toURI().toString());
					mediaPlayer = new MediaPlayer(media);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}

			} else {
				File f = new File(ruta);
				Media hit = new Media(f.toURI().toString());
				if (mediaPlayer != null)
					mediaPlayer.stop();
				mediaPlayer = new MediaPlayer(hit);
			}
			cancionActual = ruta;
		}
		mediaPlayer.play();
		reproduciendo = true;
	}

	public void eliminarCache() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.dispose();
		}
		File directorio = new File(tempPath);
		String[] files = directorio.list();
		for (String archivo : files) {
			File fichero = new File(tempPath + File.separator + archivo);
			fichero.delete();
		}
		directorio.delete();
	}

	public void pausarCancion() {
		if (reproduciendo) {
			mediaPlayer.pause();
			reproduciendo = false;
		}
	}
}
