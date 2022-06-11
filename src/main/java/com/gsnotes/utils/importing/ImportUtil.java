package com.gsnotes.utils.importing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsnotes.exceptionhandlers.exception.IntegrityException;
import com.gsnotes.utils.importing.model.ImportModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class ImportUtil {
    private final static String[] labels={"id","cne","nom","prenom","idNiveau","type"};

    public static List<Map<String,Object>> readFromExcel(InputStream excelFile, int pSheet,int header) throws IntegrityException {

        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();

        try {
            Workbook workbook = null;
            try {
                workbook = new XSSFWorkbook(excelFile);
                Sheet datatypeSheet = workbook.getSheetAt(pSheet);
                Iterator<Row> iterator = datatypeSheet.iterator();

                while (iterator.hasNext()) {
                    if(0<header) {
                        header--;
                        iterator.next();
                    }
                    Map<String,Object> rowValues = new HashMap<>();

                    Row currentRow = iterator.next();
                    if(currentRow.getCell(labels.length+1)!=null)
                        throw new IntegrityException("Error while importing an excel file", new Exception());
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    int i=0;

                    while (cellIterator.hasNext()) {

                        Cell currentCell = cellIterator.next();

                        if (currentCell.getCellType() == CellType.STRING) {

                            rowValues.put(labels[i],currentCell.getStringCellValue());

                        } else if (currentCell.getCellType() == CellType.NUMERIC) {
                            rowValues.put(labels[i],currentCell.getNumericCellValue());
                        }
                        i++;
                    }

                    data.add(rowValues);

                }
            } finally {
                if (workbook != null) {
                    workbook.close();
                }
            }
        } catch (Exception ex) {
            throw new IntegrityException("Error while importing an excel file", ex);
        }

        return data;

    }



    public static List<ImportModel> getFromCsvUsingModel(InputStream inputStream,int sheet) throws IntegrityException {
        List<Map<String,Object>> data = readFromExcel(inputStream,0,sheet);

        List<ImportModel> out = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        for(Map<String,Object> map : data)
        {
            String json="";
            try {
                 json = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                throw new IntegrityException("Unable to parse map",e);
            }
            try {
                ImportModel model = mapper.readValue(json,ImportModel.class);
                out.add(model);
            } catch (Exception e) {
                throw new IntegrityException("Excel format is not correct",e);
            }
        }
        return out;
    }
}
