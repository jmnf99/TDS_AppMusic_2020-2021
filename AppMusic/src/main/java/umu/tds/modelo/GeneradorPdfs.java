package umu.tds.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

public class GeneradorPdfs {
	private static final String DST = "misPlaylists.pdf";

	public static void generarPdf(List<ListaCanciones> listas) {
		PdfDocument pdf;
		try {
			String home = System.getProperty("user.home");
			String path = new File(home + "/Documents/" + DST).getCanonicalPath();
			pdf = new PdfDocument(new PdfWriter(path));
			Document documento = new Document(pdf);

			Paragraph titulo = new Paragraph("Mis playlists de AppMusic");
			titulo.setTextAlignment(TextAlignment.CENTER);
			titulo.setFontSize(16);
			documento.add(titulo);

			for (ListaCanciones lista : listas) {
				Paragraph nombrePlaylist = new Paragraph();
				nombrePlaylist.add(lista.getNombrePlaylist());
				nombrePlaylist.setBold();
				documento.add(nombrePlaylist);
				for (Cancion cancion : lista.getCanciones()) {
					documento.add(new Paragraph(cancion.getTitulo() + " - " + cancion.getInterpretes().toString()
							+ " - " + cancion.getEstilo().getNombre()));
				}
				documento.add(new Paragraph(""));
			}
			documento.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
