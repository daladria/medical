package com.magis.utilities.api;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.stream.Stream;

//import org.apache.tomcat.jni.OS;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfUtilities {
	
	public static void main(String[] args) throws Exception {
	//	HashMap<String,String> params = new HashMap<String, String>();
	//	produceInvoice(params);
		test();
	}
	
	public static void produceInvoice(HashMap<String,String> params) throws Exception {
	    Document document = new Document();
	    //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(params.get("outputFile")));
	    ByteArrayOutputStream  os =new ByteArrayOutputStream();
	    PdfWriter writer = PdfWriter.getInstance(document, os);
	    document.open();
	}
	public static void test() throws Exception {	
	    Document document = new Document();
//	    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:/html.pdf"));
	    ByteArrayOutputStream  os =new ByteArrayOutputStream();
	    PdfWriter writer = PdfWriter.getInstance(document, os);

	    document.open();
	    Font font = FontFactory.getFont(FontFactory.HELVETICA, "Cp1254", false, 16,Font.NORMAL, BaseColor.BLACK);
	    Chunk chunk = new Chunk("Hello World Çç Ğğ İi Iı Öö Şş Üü", font);
//	    document.add(chunk);

	     font = FontFactory.getFont("resources/fonts/Montserrat-Bold.otf", "Cp1254", true, 16,Font.NORMAL, BaseColor.BLACK);
	    chunk = new Chunk("Hello MONTSERRAT Çç Ğğ İi Iı Öö Şş Üü", font);
	    document.add(chunk);

	    
	    PdfPTable table = new PdfPTable(3);
	    table.setTotalWidth(PageSize.A4.getWidth());
	    table.setLockedWidth(true);
	    table.setWidths(new float[] { 1, 2,4 });
		
	    chunk = new Chunk("TITLE", font);
	    PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.GREEN);
		cell.setBorderWidth(0);
        cell.setPhrase(new Phrase(chunk));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);


	    chunk = new Chunk("ROW TITLE", font);
	    cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setBorderWidth(0);
        cell.setPhrase(new Phrase(chunk));
        cell.setRowspan(2);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.CYAN);
	    chunk = new Chunk("YATAY ROW1", font);
	    cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.RED);
		cell.setBorderWidth(0);
        cell.setPhrase(new Phrase(chunk));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		font = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.WHITE);
	    chunk = new Chunk("YATAY ROW2", font);
	    cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.PINK);
		cell.setBorderWidth(0);
        cell.setPhrase(new Phrase(chunk));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
	    
	    addTableHeader(table);
	    addRows(table);
	    addCustomRows(table);
	    
	    font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLDITALIC, BaseColor.DARK_GRAY);
	    chunk = new Chunk("Tablo", font);
	     cell = new PdfPCell();
	     cell.setPhrase(new Phrase(chunk));
	     table.addCell(cell);

	    PdfPTable innerTable2 = new PdfPTable(3);
	    //innerTable2.setWidthPercentage(30);
	     innerTable2.addCell("Cell 1");
	     innerTable2.addCell("Cell 2");
	     innerTable2.addCell("Cell 3");
	     cell = new PdfPCell(innerTable2);
	     cell.setBorder(2);
	     //cell.setColspan(2);
	     table.addCell(cell);
	    
	     innerTable2 = new PdfPTable(3);
		    //innerTable2.setWidthPercentage(30);
		     innerTable2.addCell("Cell 1");
		     innerTable2.addCell("Cell 2");
		     innerTable2.addCell("Cell 3");
		     cell = new PdfPCell(innerTable2);
		     cell.setBorder(2);
		     //cell.setColspan(2);
		     table.addCell(cell);
	    document.add(table);
	    
	    document.close();
	    
	    System.out.println("PDF Created...");
	    String b64 = java.util.Base64.getEncoder().encodeToString(os.toByteArray());
	    System.out.println(b64);
	    FileOutputStream fl = new FileOutputStream("d:/b64.pdf");
	    fl.write(Base64.getDecoder().decode(b64));
	    fl.close();
	    
	}
	
	private static void addTableHeader(PdfPTable table) {
	    Stream.of("column header 1", "column header 2", "column header 3")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
	private static void addRows(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.YELLOW);
		cell.setBorderWidth(2);
        cell.setPhrase(new Phrase("row 1, col 1"));
		table.addCell(cell);
	    table.addCell("row 1, col 2");
	    table.addCell("row 1, col 3");
	}
	
	private static void addCustomRows(PdfPTable table)  throws Exception {
			    Path path = Paths.get(ClassLoader.getSystemResource("pdfImages/deneme.png").toURI());
			    Image img = Image.getInstance(path.toAbsolutePath().toString());
			    img.scalePercent(5);
			    
			    PdfPCell imageCell = new PdfPCell(img);
			    imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    
			    table.addCell(imageCell);
			 
			    PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
			    horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    table.addCell(horizontalAlignCell);
			 
			    PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
			    verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			    table.addCell(verticalAlignCell);
			}
}
