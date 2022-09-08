package com.mavis.utils.poi;


import javafx.scene.control.TablePositionBase;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PoiUtils {

    /**
     * PATH:路径（末尾记得加\\）
     * FileName：文件名
     */
    public static final String PATH = "D:\\Doc\\";
    public static final String FileName = "1024总表.xlsx";

    /**
     * 03版本的Excel文件读取自动包装成实体类并且返回
     * @param path 文件路径
     * @param fileName 文件名
     * @param T 类
     * @param <T> 泛型
     * @return  实体类集合
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static <T>  List<T> readExcel034Class(String path, String fileName,Class T) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        FileInputStream fis = new FileInputStream(path+fileName);
        Workbook workbook = new HSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
//        System.out.println(rowNum);
        Row row1 = sheet.getRow(0);
        int columnCount = row1.getLastCellNum();
        List<String> columuCountName = new ArrayList<>();
        List<String> ValueList = new ArrayList<>();
        int cellNum = 0;
        for (int i = 0; i < columnCount; i++) {
            Cell cell = row1.getCell(i);
            cell.setCellType(CellType.STRING);
            String columnName = cell.getStringCellValue();
            columuCountName.add(i,columnName);
        }
//        System.out.println(columuCountName);
        List<HashMap> obj = new ArrayList<>();
        for (int i = 1; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            cellNum = row.getLastCellNum();
            for (int j = 0; j < cellNum; j++) {
                Cell cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue();
                ValueList.add(j,cellValue);
            }
            HashMap hashMap = new HashMap<>();
            for (int j = 0; j < cellNum; j++) {
                hashMap.put(columuCountName.get(j),ValueList.get(j));
            }
            obj.add(hashMap);
        }
        List<T> rsT = new ArrayList<>();
        for (HashMap hashMap : obj) {
            Constructor declaredConstructor = T.getDeclaredConstructor();
            T o =(T) declaredConstructor.newInstance();
            Set key = hashMap.keySet();
            for (Object keys : key) {
                String name = (String) keys;
                Field field = T.getDeclaredField(name);
                field.setAccessible(true);
                field.set(o,hashMap.get(keys));
            }
            rsT.add(o);
        }
        fis.close();
        return rsT;
    }

    /**
     * 07版本的Excel文件读取自动包装成实体类并且返回
     * @param path 路径
     * @param fileName 文件名
     * @param T 类
     * @param <T> 泛型
     * @return 实体类集合
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static <T>  List<T> readExcel074Class(String path, String fileName,Class T) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        FileInputStream fis = new FileInputStream(path+fileName);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
//        System.out.println(rowNum);
        Row row1 = sheet.getRow(0);
        int columnCount = row1.getLastCellNum();
        List<String> columuCountName = new ArrayList<>();
        List<String> ValueList = new ArrayList<>();
        int cellNum = 0;
        for (int i = 0; i < columnCount; i++) {
            Cell cell = row1.getCell(i);
            cell.setCellType(CellType.STRING);
            String columnName = cell.getStringCellValue();
            columuCountName.add(i,columnName);
        }
//        System.out.println(columuCountName);
        List<HashMap> obj = new ArrayList<>();
        for (int i = 1; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            cellNum = row.getLastCellNum();
            for (int j = 0; j < cellNum; j++) {
                Cell cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue();
                ValueList.add(j,cellValue);
            }
            HashMap hashMap = new HashMap<>();
            for (int j = 0; j < cellNum; j++) {
                hashMap.put(columuCountName.get(j),ValueList.get(j));
            }
            obj.add(hashMap);
        }
        List<T> rsT = new ArrayList<>();
        for (HashMap hashMap : obj) {
            Constructor declaredConstructor = T.getDeclaredConstructor();
            T o =(T) declaredConstructor.newInstance();
            Set key = hashMap.keySet();
            for (Object keys : key) {
                String name = (String) keys;
                Field field = T.getDeclaredField(name);
                field.setAccessible(true);
                field.set(o,hashMap.get(keys));
            }
            rsT.add(o);
        }
        fis.close();
        return rsT;
    }

    /**
     * 07版本Excel表格读取包装成list集合返回
     * @param path 路径
     * @param fileName 文件名
     * @return 包含键值对的List集合
     * @throws IOException
     */
    public static List<HashMap> readExcel07(String path, String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(path+fileName);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
//        System.out.println(rowNum);
        Row row1 = sheet.getRow(0);
        int columnCount = row1.getLastCellNum();
        List<String> columuCountName = new ArrayList<>();
        List<String> ValueList = new ArrayList<>();
        int cellNum = 0;
        for (int i = 0; i < columnCount; i++) {
            Cell cell = row1.getCell(i);
            cell.setCellType(CellType.STRING);
            String columnName = cell.getStringCellValue();
            columuCountName.add(i,columnName);
        }
//        System.out.println(columuCountName);
        List<HashMap> obj = new ArrayList<>();
        for (int i = 1; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            cellNum = row.getLastCellNum();
            for (int j = 0; j < cellNum; j++) {
                Cell cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue();
                ValueList.add(j,cellValue);
            }
            HashMap hashMap = new HashMap<>();
            for (int j = 0; j < cellNum; j++) {
                hashMap.put(columuCountName.get(j),ValueList.get(j));
            }
            obj.add(hashMap);
        }
        fis.close();
        return obj;
    }

    /**
     * 03版本Excel表格读取包装成list集合返回
     * @param path 路径
     * @param fileName 文件名
     * @return 包含键值对的List集合
     * @throws IOException
     */
    public static List<HashMap> readExcel03(String path, String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(path+fileName);
        Workbook workbook = new HSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
//        System.out.println(rowNum);
        Row row1 = sheet.getRow(0);
        int columnCount = row1.getLastCellNum();
        List<String> columuCountName = new ArrayList<>();
        List<String> ValueList = new ArrayList<>();
        int cellNum = 0;
        for (int i = 0; i < columnCount; i++) {
            Cell cell = row1.getCell(i);
            cell.setCellType(CellType.STRING);
            String columnName = cell.getStringCellValue();
            columuCountName.add(i,columnName);
        }
//        System.out.println(columuCountName);
        List<HashMap> obj = new ArrayList<>();
        for (int i = 1; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            cellNum = row.getLastCellNum();
            for (int j = 0; j < cellNum; j++) {
                Cell cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue();
                ValueList.add(j,cellValue);
            }
            HashMap hashMap = new HashMap<>();
            for (int j = 0; j < cellNum; j++) {
                hashMap.put(columuCountName.get(j),ValueList.get(j));
            }
            obj.add(hashMap);
        }
        fis.close();
        return obj;
    }

    public static void main(String[] args) throws IOException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<HashMap> hashMaps = readExcel07(PATH, FileName);
        for (int i = 0; i < hashMaps.size(); i++) {
            System.out.println(hashMaps.get(i));
        }
    }

}
