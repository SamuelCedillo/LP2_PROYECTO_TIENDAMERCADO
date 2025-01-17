package cibertec.com.pe.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.itextpdf.html2pdf.HtmlConverter;

@Service
public class PdfService {
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public ByteArrayInputStream generarPdf(String templateNombre, Map<String, Object> datos) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		// setear el html con información
		Context context = new Context();
		context.setVariables(datos);
		// construye el html con los datos
		String html = templateEngine.process(templateNombre, context);
		//convierte el html en pdf
		HtmlConverter.convertToPdf(new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8)), 
				outputStream);
		
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
}
