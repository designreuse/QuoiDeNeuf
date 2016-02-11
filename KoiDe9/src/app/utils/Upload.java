package app.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class Upload extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		rep.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		System.out.println("TENTITAIVE D UPLOAD");
		final String realPath = getServletContext().getRealPath("/");
		final String cheminComplet = realPath + "/img/avatars/";

		final Part p = req.getPart("fichier");

		String nomFichier = p.getHeader("content-disposition");
		String type = p.getHeader("Content-Type");

		nomFichier = nomFichier.replaceAll(".*filename=\"", "").replaceAll("\"", "").replaceAll(".*\\\\", "");
		System.out.println(type + " : " + nomFichier);
		System.out.println(cheminComplet);

		String nom = ecrireFichier(p, nomFichier, cheminComplet);
		//rep.sendRedirect("index.jsp");
		//req.getRequestDispatcher("index.jsp").forward(req, rep);
		
		PrintWriter out = rep.getWriter();
		rep.setHeader("Content-Type", "text/plain");
		out.println(nom);
		
	}

	private String ecrireFichier(Part part, String nomFichier, String chemin) throws FileNotFoundException {
		BufferedInputStream src = null;
		BufferedOutputStream dest = null;
		final int BUFFER = 4096;
		try {
			src = new BufferedInputStream(part.getInputStream(), BUFFER);
			File f = new File(chemin + nomFichier);
			if (f.exists()) {
				String ext = "." + nomFichier.replaceAll(".*\\.", "");
				String nom = nomFichier.replaceAll("\\.[^.]*$", "");

				nomFichier = nom + "_" + (new File(chemin)).list().length + ext;
				System.out.println(ext + " : " + nom + " : " + nomFichier);
			}

			dest = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), BUFFER);

			final byte[] tampon = new byte[BUFFER];
			int longueur;
			while ((longueur = src.read(tampon)) > 0) {
				dest.write(tampon, 0, longueur);
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				dest.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			try {
				src.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return nomFichier;
	}
}
