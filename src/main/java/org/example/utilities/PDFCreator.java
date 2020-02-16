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

                final String SALESMAN_DATA = "sprzedawca: \n".toUpperCase() + invoice.getSalesman().getCompanyName().toUpperCase() + "\n" + invoice.getSalesman().getPostcode() + " " + invoice.getSalesman().getCity().toUpperCase() + "\n" + invoice.getSalesman().getAddress().toUpperCase();
                final String BUYER_FIELD_NAME = "nabywca:".toUpperCase();
                final String BUYER_DATA = invoice.getContractor().getFirstName().toUpperCase() + " " + invoice.getContractor().getLastName().toUpperCase() + "\n" + invoice.getContractor().getPostcode() + " " + invoice.getContractor().getCity().toUpperCase() + "\n" + invoice.getContractor().getAddress().toUpperCase() + "\nmieszkanie nr ".toUpperCase() + invoice.getContractor().getApartmentNumber();
                final String ORIGINAL_COPY_TEXT = "oryginał / kopia";
                final String SERVICE_CONTENT_TEXT = "czynsz za lokal\nmieszkalny za miesiąc\n".toUpperCase() + invoice.getPeriod() + "-" + invoice.getCreatedAt().getYear();

                Document document = new Document(PageSize.A4);
                PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
                pdfWriter.setPdfVersion(PdfWriter.VERSION_1_7);
                pdfWriter.setTagged();
                pdfWriter.setViewerPreferences(PdfWriter.DisplayDocTitle);
                document.addLanguage("pl-PL");
                document.addTitle("Faktura: " + invoice.getSignature());
                pdfWriter.createXmpMetadata();
                BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/times.ttf", BaseFont.CP1257, BaseFont.EMBEDDED);
                Font timesNewRomanNormalSize8Font = new Font(baseFont, 8, Font.NORMAL);
                Font timesNewRomanBoldSize8Font = new Font(baseFont, 8, Font.BOLD);
                Font timesNewRomanItalicSize8Font = new Font(baseFont, 8, Font.ITALIC);
                Font timesNewRomanNormalSize6Font = new Font(baseFont, 6, Font.NORMAL);
                baseFont = BaseFont.createFont("c:/windows/fonts/verdana.ttf", BaseFont.CP1257, BaseFont.EMBEDDED);
                Font verdanaItalicSize8Font = new Font(baseFont, 8, Font.ITALIC);

                //write document open
                document.open();

                //table
                PdfPTable table = new PdfPTable(15);
                table.setWidthPercentage(100);

                //first row
                int firstRowHeight = 55;
                Paragraph phrase = new Paragraph(SALESMAN_DATA, timesNewRomanBoldSize8Font);
                PdfPCell cell = new PdfPCell();
                cell.setMinimumHeight(firstRowHeight);
                cell.setColspan(8);
                cell.addElement(phrase);
                table.addCell(cell);
                phrase = new Paragraph("Rachunek:\n" + "NR " + invoice.getSignature() + "\nData wystawienia: " + getFormattedData(invoice.getCreatedAt()) + "\nData sprzedaży: " + getFormattedData(invoice.getSoldAt()), timesNewRomanBoldSize8Font);
                cell = new PdfPCell();
                cell.setMinimumHeight(firstRowHeight);
                cell.setColspan(7);
                cell.addElement(phrase);
                table.addCell(cell);

                //second row
                int secondRowHeight = 20;
                phrase = new Paragraph(ORIGINAL_COPY_TEXT.toUpperCase(), verdanaItalicSize8Font);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                phrase.setAlignment(Element.ALIGN_CENTER);
                cell = new PdfPCell();
                cell.setMinimumHeight(secondRowHeight);
                cell.setColspan(15);
                cell.addElement(phrase);
                table.addCell(cell);

                //third row
                int thirdRowHeight = 60;
                phrase = new Paragraph(BUYER_FIELD_NAME, timesNewRomanBoldSize8Font);
                cell = new PdfPCell();
                cell.setMinimumHeight(thirdRowHeight);
                cell.setColspan(3);
                cell.addElement(phrase);
                table.addCell(cell);
                phrase = new Paragraph(BUYER_DATA, timesNewRomanBoldSize8Font);
                cell = new PdfPCell();
                cell.setMinimumHeight(thirdRowHeight);
                cell.setColspan(12);
                cell.addElement(phrase);
                table.addCell(cell);

                //fourth row
                phrase = new Paragraph("l.p.".toUpperCase(), timesNewRomanBoldSize8Font);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                cell = new PdfPCell();
                cell.addElement(phrase);
                table.addCell(cell);
                phrase = new Paragraph("nazwa towaru / usługi".toUpperCase(), timesNewRomanBoldSize8Font);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                cell = new PdfPCell();
                cell.addElement(phrase);
                cell.setColspan(5);
                table.addCell(cell);
                phrase = new Paragraph("ilość".toUpperCase(), timesNewRomanBoldSize8Font);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                cell = new PdfPCell();
                cell.setColspan(2);
                cell.addElement(phrase);
                table.addCell(cell);
                phrase = new Paragraph("j.m.".toUpperCase(), timesNewRomanBoldSize8Font);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                cell = new PdfPCell();
                cell.addElement(phrase);
                table.addCell(cell);
                phrase = new Paragraph("cena jednostkowa".toUpperCase(), timesNewRomanBoldSize8Font);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                cell = new PdfPCell();
                cell.setColspan(3);
                cell.addElement(phrase);
                table.addCell(cell);
                phrase = new Paragraph("wartość".toUpperCase(), timesNewRomanBoldSize8Font);
                phrase.setAlignment(Element.ALIGN_MIDDLE);
                phrase.setAlignment(Element.ALIGN_CENTER);
                cell = new PdfPCell();
                cell.setColspan(3);
                cell.addElement(phrase);
                table.addCell(cell);

                //fifth row
                int fifthRowHeight = 50;
                phrase = new Paragraph("1", timesNewRomanNormalSize8Font);
                phrase.setAlignment(Element.ALIGN_RIGHT);
                cell = new PdfPCell();
                cell.setMinimumHeight(fifthRowHeight);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph(SERVICE_CONTENT_TEXT, timesNewRomanNormalSize8Font);
                cell = new PdfPCell();
                cell.setMinimumHeight(fifthRowHeight);
                cell.addElement(phrase);
                cell.setColspan(5);
                table.addCell(cell);

                phrase = new Paragraph("1", timesNewRomanNormalSize8Font);
                phrase.setAlignment(Element.ALIGN_RIGHT);
                cell = new PdfPCell();
                cell.setMinimumHeight(fifthRowHeight);
                cell.setColspan(2);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph("zest".toUpperCase(), timesNewRomanNormalSize8Font);
                cell = new PdfPCell();
                cell.setMinimumHeight(fifthRowHeight);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph(invoice.getContractor().getAmount(), timesNewRomanNormalSize8Font);
                phrase.setAlignment(Element.ALIGN_RIGHT);
                cell = new PdfPCell();
                cell.setMinimumHeight(fifthRowHeight);
                cell.setColspan(3);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph(invoice.getContractor().getAmount(), timesNewRomanNormalSize8Font);
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

                phrase = new Paragraph("razem:".toUpperCase(), timesNewRomanBoldSize8Font);
                phrase.setAlignment(Element.ALIGN_CENTER);
                cell = new PdfPCell();
                cell.setColspan(4);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph(invoice.getContractor().getAmount(), timesNewRomanNormalSize8Font);
                phrase.setAlignment(Element.ALIGN_RIGHT);
                cell = new PdfPCell();
                cell.setColspan(3);
                cell.addElement(phrase);
                table.addCell(cell);

                //seventh row
                int seventhRowHeight = 40;
                phrase = new Paragraph("forma płatności:".toUpperCase(), timesNewRomanBoldSize8Font);
                cell = new PdfPCell();
                cell.setMinimumHeight(seventhRowHeight);
                cell.setColspan(8);
                cell.addElement(phrase);
                phrase = new Paragraph("gotówka".toUpperCase(), timesNewRomanItalicSize8Font);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph("termin:".toUpperCase(), timesNewRomanBoldSize8Font);
                cell = new PdfPCell();
                cell.setMinimumHeight(seventhRowHeight);
                cell.setColspan(7);
                cell.addElement(phrase);
                phrase = new Paragraph(getFormattedData(invoice.getSoldAt()), timesNewRomanItalicSize8Font);
                cell.addElement(phrase);
                table.addCell(cell);

                //eighth row
                int eighthRowHeight = 40;
                phrase = new Paragraph("razem do zapłaty:".toUpperCase(), timesNewRomanBoldSize8Font);
                cell = new PdfPCell();
                cell.setMinimumHeight(eighthRowHeight);
                cell.setColspan(11);
                cell.addElement(phrase);
                phrase = new Paragraph("słownie: ".toUpperCase() + NumberConverter.getAmountInWords(invoice.getContractor().getAmount()), timesNewRomanItalicSize8Font);
                phrase.setIndentationLeft(30);
                cell.addElement(phrase);
                table.addCell(cell);

                phrase = new Paragraph(invoice.getContractor().getAmount() + " PLN", timesNewRomanBoldSize8Font);
                phrase.setAlignment(Element.ALIGN_CENTER);
                cell = new PdfPCell();
                cell.setMinimumHeight(eighthRowHeight);
                cell.setColspan(4);
                cell.addElement(phrase);
                table.addCell(cell);

                //ninth row
                int ninthRowHeight = 45;
                phrase = new Paragraph("(podpis sprzedającego)".toUpperCase(), timesNewRomanNormalSize6Font);
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

                phrase = new Paragraph("(podpis kupującego)".toUpperCase(), timesNewRomanNormalSize6Font);
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