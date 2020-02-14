package org.example.utilities;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.model.Invoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;

public class PDFCreator {

    private static String PDF_DOCUMENTS_PATH = System.getProperty("user.dir") + "\\target\\printedPDFDocuments\\";
    private static String PDF_DOCUMENTS_DIRECTORY_NAME = PDF_DOCUMENTS_PATH + "printedOn_";
    private static String PDF_DOCUMENT_FILE_NAME;

    public static void print(List<Invoice> invoices) {
        try {
            File directory = new File(PDF_DOCUMENTS_DIRECTORY_NAME
                    + getDateTimeNow().getDayOfMonth() + "_" + getDateTimeNow().getMonthValue() + "_" + getDateTimeNow().getYear()
                    + "_"
                    + getDateTimeNow().getHour() + "_" + getDateTimeNow().getMinute() + "_" + getDateTimeNow().getSecond());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            for (Invoice invoice : invoices) {
                PDF_DOCUMENT_FILE_NAME = invoice.getSignature().replace("/", "_") + ".pdf";
                File pdfFile = new File(directory.getAbsolutePath() + "\\" + PDF_DOCUMENT_FILE_NAME);
                Document document = new Document();
                PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
                pdfWriter.setPdfVersion(PdfWriter.VERSION_1_7);
                pdfWriter.setTagged();
                pdfWriter.setViewerPreferences(PdfWriter.DisplayDocTitle);
                document.addLanguage("en-US");
                document.addTitle("Faktura: " + invoice.getSignature());
                pdfWriter.createXmpMetadata();
                //write document open
                document.open();
                document.add(new Paragraph(invoice.getSignature()));
                document.close();
            }
            //write document close
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static LocalDateTime getDateTimeNow() {
        return LocalDateTime.now();
    }
}
