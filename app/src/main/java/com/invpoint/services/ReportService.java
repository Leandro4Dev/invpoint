package com.invpoint.services;

import com.invpoint.models.count.CountDAO;
import com.invpoint.models.count.Count;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportService {

    public static void generate(String fileName){

        List<Count> counts = CountDAO.getAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("contagens");
        String[] columns = { "Serial", "Seq.", "Data/Hora", "Usuário", "Matricula", "Quantidade" };

        Row headerRow = sheet.createRow(0);
        for(int i = 0; i <= columns.length - 1; i++){
            headerRow.createCell(i).setCellValue(columns[i]);
            sheet.setColumnWidth(i, 22 * 256);
        }

        CellStyle dateTimeCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateTimeCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy HH:mm:ss"));

        int rowIndex = 1;
        for(Count count: counts){
            Row row = sheet.createRow(rowIndex);
            rowIndex++;

            row.createCell(0).setCellValue((int) Integer.parseInt(count.getSeq()));
            row.createCell(1).setCellValue((int) Integer.parseInt(count.getCount()));

            row.createCell(2).setCellValue(count.getCreatedTime().toDate());
            row.getCell(2).setCellStyle(dateTimeCellStyle);

            row.createCell(3).setCellValue(count.getUserData().getDisplay_name());
            row.createCell(4).setCellValue((int) Integer.parseInt(count.getUserData().getRegistration()));
            row.createCell(5).setCellValue((long) count.getAmount());


            for(int i = 0; i<= 5; i++){
                row.getCell(i).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
                row.getCell(i).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
            }


        }

        String downloadsPath = System.getProperty("user.home") + File.separator + "Downloads";

        File targetDirectory = new File(downloadsPath, "Contagem Inventário");
        if (!targetDirectory.exists()) {
            boolean created = targetDirectory.mkdirs();
            if (created) {
                System.out.println("Diretório criado: " + targetDirectory.getAbsolutePath());
            } else {
                System.out.println("Não foi possível criar o diretório.");
                return;
            }
        }

        File file = new File(targetDirectory, fileName + ".xlsx");

        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
            System.out.println(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
