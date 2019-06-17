package com.tcg.radixworksheetgenerator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class PDFWorksheetWriter {

    public static void write(List<QuestionSet> worksheet, String fileName, boolean onlyOddAnswers) throws DocumentException, FileNotFoundException {
        Document document  = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));

        document.open();

        document.add(new Phrase("Name:\n\n"));


        for (QuestionSet questionSet : worksheet) {
            document.add(new Phrase(questionSet.direction));
            PdfPTable table = new PdfPTable(2);
            questionSet.forEach(question -> table.addCell(questionCell(question)));
            document.add(table);
        }

        document.newPage();

        Paragraph answersPageHeader = new Paragraph("Answers");
        answersPageHeader.setAlignment(Element.ALIGN_CENTER);

        document.add(answersPageHeader);

        PdfPTable answers = new PdfPTable(3);

        for (QuestionSet questionSet : worksheet) {
            for (Question question : questionSet) {
                if(!onlyOddAnswers || question.questionNumber % 2 != 0) {
                    PdfPCell cell = borderlessCell();
                    cell.addElement(new Phrase(question.answer()));
                    answers.addCell(cell);
                }
            }
        }

        document.add(answers);

        document.close();
    }

    private static PdfPCell questionCell(Question question) {
        PdfPCell cell = borderlessCell();
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Phrase(question.question()));
        Chunk radixSub = new Chunk(question.givenRadix.subScript);
        radixSub.setTextRise(-2);
        radixSub.setFont(FontFactory.getFont(FontFactory.HELVETICA, 8));
        paragraph.add(new Phrase(radixSub));
        cell.addElement(paragraph);
        return cell;
    }

    private static PdfPCell borderlessCell() {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingBottom(15);
        return cell;
    }

}
