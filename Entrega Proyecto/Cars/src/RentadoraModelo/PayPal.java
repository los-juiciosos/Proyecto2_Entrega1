package RentadoraModelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

public class PayPal extends MetodoTransaccion{

	public PayPal(){
		
	}
	
	@Override
	public boolean cobrar(String numeroCuenta) {
		
		if (numeroCuenta.contains("8")) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	@Override
	public void generarFactura(String numeroCuenta,double monto, String pasarela) {
		
		try {
			File folder = new File("./facturas/");
			folder.mkdirs();
            PdfWriter writer = new PdfWriter("./facturas/facturasPaypal-"+numeroCuenta + ".pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("========================FACTURA " + pasarela +" =========================="));
            document.add(new Paragraph("numero de Cuenta: " + numeroCuenta ));
            document.add(new Paragraph("monto descontado: " + monto ));
            document.add(new Paragraph("========================VALIDADO " + pasarela +" =========================="));

            document.close();
            
            guardarTxt(numeroCuenta,monto,pasarela);
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
}
