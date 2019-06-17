package com.tcg.radixworksheetgenerator;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<QuestionSet> worksheet = new ArrayList<>();

        for (Base target : Base.values()) {
            int lastNumber = worksheet.isEmpty() ? 0 : worksheet.get(worksheet.size() - 1).lastNumber;
            worksheet.add(new QuestionSet(lastNumber + 1, target, 2));
        }

        try {
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY_MM_dd"));
            PDFWorksheetWriter.write(worksheet, date + "_AP_CompSci_Number_System_Worksheet", true);
            PDFWorksheetWriter.write(worksheet, date + "_AP_CompSci_Number_System_Worksheet_key", false);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
