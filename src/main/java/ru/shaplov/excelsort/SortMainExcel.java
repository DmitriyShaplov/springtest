package ru.shaplov.excelsort;

import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SortMainExcel {

    public static void main(String[] args) {
        String path = "D:\\Download\\Universitety_RF.xlsx";
        XSSFSheet sheet;
        XSSFWorkbook workbook = readWorkbook(path);
        try {
            if (workbook != null) {
                sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIter = sheet.rowIterator();
                List<XSSFRow> rows = new ArrayList<>();
                int index = 0;
                while (rowIter.hasNext()) {
                    XSSFRow row = (XSSFRow) rowIter.next();
                    if (index != 0) {
                        rows.add(row);
                    }
                    index++;
                }
                rows.sort((row1, row2) -> {
                    boolean ex1 = false;
                    boolean ex2 = false;
                    Date dateCellValue1 = null;
                    try {
                        dateCellValue1 = row1.getCell(4).getDateCellValue();
                    } catch (Exception e) {
                        ex1 = true;
                    }
                    Date dateCellValue2 = null;
                    try {
                        dateCellValue2 = row2.getCell(4).getDateCellValue();
                    } catch (Exception e) {
                        ex2 = true;
                    }
                    if (dateCellValue1 == null) {
                        ex1 = true;
                    }
                    if (dateCellValue2 == null) {
                        ex2 = true;
                    }
                    if (ex1 || ex2) {
                        return ex1 && ex2 ? 0 : ex1 ? 1 : -1;
                    }
                    LocalDate date1 = Instant.ofEpochMilli(dateCellValue1.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate date2 = Instant.ofEpochMilli(dateCellValue2.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                    return date1.getMonth().compareTo(date2.getMonth()) != 0 ? date1.getMonth().compareTo(date2.getMonth()) :
                            date1.getDayOfMonth() - (date2.getDayOfMonth());
                });

                for (int i = 0; i < rows.size(); i++) {
                    rows.get(i).setRowNum(i + 1);
                }

                XSSFSheet sheet1 = workbook.createSheet();
                sheet1.copyRows(rows, 0, new CellCopyPolicy());
                workbook.removeSheetAt(0);

                writeWorkbook(workbook, "D:\\Download\\Universitety_R1F.xlsx");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static XSSFWorkbook readWorkbook(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            return new XSSFWorkbook(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeWorkbook(XSSFWorkbook wb, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
