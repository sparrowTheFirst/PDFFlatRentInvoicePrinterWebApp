package org.example.utilities;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDiv;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.model.Invoice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PDFCreator {

    private static final String PDF_DOCUMENTS_PATH = System.getProperty("user.dir") + "\\target\\printedPDFDocuments\\";
    private static String PDF_DOCUMENTS_DIRECTORY_NAME = PDF_DOCUMENTS_PATH + "printedOn_";
    private static String PDF_DOCUMENT_FILE_NAME;

    public static void print(List<Invoice> invoices) {
        try {
            File directory = new File(PDF_DOCUMENTS_DIRECTORY_NAME
                    + SystemDateTime.getDateTimeNow().getDayOfMonth() + "_" + SystemDateTime.getDateTimeNow().getMonthValue() + "_" + SystemDateTime.getDateTimeNow().getYear()
                    + "_"
                    + SystemDateTime.getDateTimeNow().getHour() + "_" + SystemDateTime.getDateTimeNow().getMinute() + "_" + SystemDateTime.getDateTimeNow().getSecond());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            for (Invoice invoice : invoices) {
                PDF_DOCUMENT_FILE_NAME = invoice.getSignature().replace("/", "_") + ".pdf";
                File pdfFile = new File(directory.getAbsolutePath() + "\\" + PDF_DOCUMENT_FILE_NAME);

                final String SALESMAN_DATA = "Sprzedawca: \n" + invoice.getSalesman().getCompanyName().toUpperCase() + "\n" + invoice.getSalesman().getPostcode() + " " + invoice.getSalesman().getCity().toUpperCase() + "\n" + invoice.getSalesman().getAddress().toUpperCase();
                final String BUYER_FIELD_NAME = "nabywca:".toUpperCase();
                final String BUYER_DATA = invoice.getContractor().getFirstName().toUpperCase() + " " + invoice.getContractor().getLastName().toUpperCase() + "\n" + invoice.getContractor().getPostcode() + " " + invoice.getContractor().getCity().toUpperCase() + "\n" + invoice.getContractor().getAddress().toUpperCase() + "\nmieszkanie nr ".toUpperCase() + invoice.getContractor().getApartmentNumber();
                final String ORIGINAL_COPY_TEXT = "oryginał / kopia";
                final String SERVICE_CONTENT_TEXT = "CZYNSZ ZA LOKAL\nMIESZKALNY ZA MIESIAC\n" + invoice.getPeriod() + "-" + invoice.getCreatedAt().getYear();

                Document document = new Document(PageSize.A4);
                PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
                pdfWriter.setPdfVersion(PdfWriter.VERSION_1_7);
                pdfWriter.setTagged();
                pdfWriter.setLanguage("pl-PL");
                pdfWriter.setViewerPreferences(PdfWriter.DisplayDocTitle);
//                document.addLanguage("en-US");
                document.addLanguage("pl-PL");
                document.addTitle("Faktura: " + invoice.getSignature());
                pdfWriter.createXmpMetadata();
                Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
                Font normalSize8Font = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
                Font normalSize6Font = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.NORMAL);
                Font italicFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.ITALIC);

                //write document open
                document.open();

                //table
                PdfPTable table = new PdfPTable(15);
                table.setWidthPercentage(100);

                //first row
                int firstRowHeight = 55;
                Paragraph phrase = new Paragraph(SALESMAN_DATA, boldFont);
                PdfPCell cell = new PdfPCell();
                cell.setMinimumHeight(firstRowHeight);
                cell.setColspan(8);
                cell.addElement(phrase);
                table.addCell(cell);
                phrase = new Paragraph("Rachunek:\n" + "NR " + invoice.getSignature() + "\nData wystawienia: " + getFormattedData(invoice.getCreatedAt()) + "\nData sprzedazy: " + getFormattedData(invoice.getSoldAt()), boldFont);
                cell = new PdfPCell();
                cell.setMinimumHeight(firstRowHeight);
                cell.setColspan(7);
                cell.addElement(phrase);
                table.addCell(cell);

                //second row
                int secondRowHeight = 20;
                phrase = new Paragraph(ORIGINAL_COPY_TEXT.toUpperCase(), new Font(Font.FontFamily.HELVETICA, 6, Font.ITALIC));
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                phrase.setAlignment(Element.ALIGN_CENTER);
                cell = new PdfPCell();
                cell.setMinimumHeight(secondRowHeight);
                cell.setColspan(15);
                cell.addElement(phrase);
                table.addCell(cell);

                //third row
                int thirdRowHeight = 60;
                phrase = new Paragraph(BUYER_FIELD_NAME, boldFont);
                cell = new PdfPCell();
                cell.setMinimumHeight(thirdRowHeight);
                cell.setColspan(3);
                cell.addElement(phrase);
                table.addCell(cell);
                phrase = new Paragraph(BUYER_DATA, boldFont);
                cell = new PdfPCell();
                cell.setMinimumHeight(thirdRowHeight);
                cell.setColspan(12);
                cell.addElement(phrase);
                table.addCell(cell);

                //fourth row
                phrase = new Paragraph("L.P.", boldFont);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                cell = new PdfPCell();
                cell.addElement(phrase);
                table.addCell(cell);
                phrase = new Paragraph("NAZWA TOWARU/USLUGi", boldFont);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                cell = new PdfPCell();
                cell.addElement(phrase);
                cell.setColspan(5);
                table.addCell(cell);
                phrase = new Paragraph("ILOSC", boldFont);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                cell = new PdfPCell();
                cell.setColspan(2);
                cell.addElement(phrase);
                table.addCell(cell);
                phrase = new Paragraph("J.M.", boldFont);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                cell = new PdfPCell();
                cell.addElement(phrase);
                table.addCell(cell);
                phrase = new Paragraph("CENA JEDNOSTKOWA", boldFont);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                cell = new PdfPCell();
                cell.setColspan(3);
                cell.addElement(phrase);
                table.addCell(cell);
                phrase = new Paragraph("WARTOSC", boldFont);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                phrase.setAlignment(Element.ALIGN_CENTER);
                cell = new PdfPCell();
                cell.setColspan(3);
                cell.addElement(phrase);
                table.addCell(cell);

                //fifth row
                int fifthRowHeight = 50;
                phrase = new Paragraph("1", normalSize8Font);
                phrase.setAlignment(Element.ALIGN_RIGHT);
                cell = new PdfPCell();
                cell.setMinimumHeight(fifthRowHeight);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph(SERVICE_CONTENT_TEXT, normalSize8Font);
                cell = new PdfPCell();
                cell.setMinimumHeight(fifthRowHeight);
                cell.addElement(phrase);
                cell.setColspan(5);
                table.addCell(cell);

                phrase = new Paragraph("1", normalSize8Font);
                phrase.setAlignment(Element.ALIGN_RIGHT);
                cell = new PdfPCell();
                cell.setMinimumHeight(fifthRowHeight);
                cell.setColspan(2);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph("ZEST", normalSize8Font);
                cell = new PdfPCell();
                cell.setMinimumHeight(fifthRowHeight);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph(invoice.getContractor().getAmount(), normalSize8Font);
                phrase.setAlignment(Element.ALIGN_RIGHT);
                cell = new PdfPCell();
                cell.setMinimumHeight(fifthRowHeight);
                cell.setColspan(3);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph(invoice.getContractor().getAmount(), normalSize8Font);
                phrase.setAlignment(Element.ALIGN_RIGHT);
                cell = new PdfPCell();
                cell.setMinimumHeight(fifthRowHeight);
                cell.setColspan(3);
                cell.addElement(phrase);
                table.addCell(cell);

                //sixth row
                int sixthRowHeight = 25;
                cell = new PdfPCell();
                cell.setMinimumHeight(sixthRowHeight);
                cell.setColspan(8);
                table.addCell(cell);

                phrase = new Paragraph("RAZEM:", boldFont);
                phrase.setAlignment(Element.ALIGN_CENTER);
                cell = new PdfPCell();
                cell.setColspan(4);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph(invoice.getContractor().getAmount(), normalSize8Font);
                phrase.setAlignment(Element.ALIGN_RIGHT);
                cell = new PdfPCell();
                cell.setColspan(3);
                cell.addElement(phrase);
                table.addCell(cell);

                //seventh row
                int seventhRowHeight = 40;
                phrase = new Paragraph("forma platnosci:".toUpperCase(), boldFont);
                cell = new PdfPCell();
                cell.setMinimumHeight(seventhRowHeight);
                cell.setColspan(8);
                cell.addElement(phrase);
                phrase = new Paragraph("gotowka".toUpperCase(), italicFont);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph("termin:".toUpperCase(), boldFont);
                cell = new PdfPCell();
                cell.setMinimumHeight(seventhRowHeight);
                cell.setColspan(7);
                cell.addElement(phrase);
                phrase = new Paragraph(getFormattedData(invoice.getSoldAt()), italicFont);
                cell.addElement(phrase);
                table.addCell(cell);

                //eighth row
                int eighthRowHeight = 40;
                phrase = new Paragraph("razem do zaplaty:".toUpperCase(), boldFont);
                cell = new PdfPCell();
                cell.setMinimumHeight(eighthRowHeight);
                cell.setColspan(11);
                cell.addElement(phrase);
                phrase = new Paragraph("slownie: ".toUpperCase() + invoice.getAmountInWords(), italicFont);
                phrase.setIndentationLeft(30);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph(invoice.getContractor().getAmount() + " PLN", boldFont);
                phrase.setAlignment(Element.ALIGN_CENTER);
                cell = new PdfPCell();
                cell.setMinimumHeight(eighthRowHeight);
                cell.setColspan(4);
                cell.addElement(phrase);
                table.addCell(cell);

                //ninth row
                int ninthRowHeight = 45;
                phrase = new Paragraph("(podpis sprzdawcy)".toUpperCase(), normalSize6Font);
                phrase.setAlignment(Element.ALIGN_TOP);
                phrase.setAlignment(Element.ALIGN_CENTER);
                cell = new PdfPCell();
                cell.setMinimumHeight(ninthRowHeight);
                cell.setColspan(5);
                cell.addElement(phrase);
                table.addCell(cell);

                cell = new PdfPCell();
                cell.setMinimumHeight(ninthRowHeight);
                cell.setColspan(5);
                table.addCell(cell);

                phrase = new Paragraph("(podpis kupca)".toUpperCase(), normalSize6Font);
                phrase.setAlignment(Element.ALIGN_TOP);
                phrase.setAlignment(Element.ALIGN_CENTER);
                cell = new PdfPCell();
                cell.setMinimumHeight(ninthRowHeight);
                cell.setColspan(5);
                cell.addElement(phrase);
                table.addCell(cell);

                //big divs
                PdfDiv divTop = new PdfDiv();
                PdfDiv divBottom = new PdfDiv();

                divTop.setPaddingLeft(20);
                divTop.setPaddingTop(10);
                divTop.setPaddingRight(30);
                divTop.setPaddingBottom(10);

                divBottom.setPaddingLeft(20);
                divBottom.setPaddingTop(10);
                divBottom.setPaddingRight(30);
                divBottom.setPaddingBottom(10);

                divTop.addElement(table);
                divBottom.addElement(table);

                //document
                document.add(divTop);
                document.add(divBottom);
                document.close();
                //write document close
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFormattedData(LocalDate date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateTimeFormatter.format(date);
    }
}