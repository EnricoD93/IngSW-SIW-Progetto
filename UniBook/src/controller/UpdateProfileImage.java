package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.json.Test;

import model.Utente;
import persistence.DatabaseManager;
import persistence.UtilDao;
import persistence.dao.UtenteDao;

public class UpdateProfileImage extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			final List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
			String request = null;
			Long ID = null;
			for (final FileItem fileItem : items) {
				if (fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("request")) {
						request = fileItem.getString();
					} else
						ID = Long.parseLong(fileItem.getString());
				} else {
					final String fileName = FilenameUtils.getName(fileItem.getName());
					final Utente utente = ((Utente) req.getSession().getAttribute("currentUser"));
					utente.setProfileImagePath("images/profileImages/" + fileName);
					UtenteDao ud = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
					ud.updateImage(utente, "images/profileImages/"+fileName);
					writeImage(fileItem, "profileImages");
					return;
				}

			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void writeImage(final FileItem fileItem, final String folder) throws IOException {

		final String fileName = FilenameUtils.getName(fileItem.getName());
		final InputStream fileContent = fileItem.getInputStream();
		final BufferedImage image = ImageIO.read(fileContent);
		final URL location = Test.class.getProtectionDomain().getCodeSource().getLocation();
		final String[] split = location.getFile().split("WEB-INF");
		final String newPath = System.getProperty("user.home") + "/git/IngSW-SIW-Progetto/UniBook/WebContent/images/"
				+ folder + "/" + fileName;
		final File outputfile = new File(newPath);
		final String newPath2 = split[0] + "images/" + folder + "/" + fileName;
		final File outputfile2 = new File(newPath2);
		ImageIO.write(image, "png", outputfile2);
		ImageIO.write(image, "png", outputfile);
	}
	
	
}
