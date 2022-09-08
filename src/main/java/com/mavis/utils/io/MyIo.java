package com.mavis.utils.io;

import java.io.*;

public class MyIo {
    //使用FileReader将input.txt文件中的内容复制到output.txt文件中
    public static void copyFile() {
        try {
            FileReader fr = new FileReader("E:\\Java_Project\\MyUtils\\resource\\input.txt");
            FileWriter fw = new FileWriter("E:\\Java_Project\\MyUtils\\resource\\output.txt");
            int ch = 0;
            while ((ch = fr.read()) != -1) {
                fw.write(ch);
            }
            fr.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("复制完成");
    }

    //使用FileInputStream将input.txt文件中的内容复制到output.txt文件中
    public static void copyFile2() {
        try {
            FileInputStream fis = new FileInputStream("E:\\Java_Project\\MyUtils\\resource\\input.txt");
            FileOutputStream fos = new FileOutputStream("E:\\Java_Project\\MyUtils\\resource\\output.txt");
            int ch = 0;
            while ((ch = fis.read()) != -1) {
                fos.write(ch);
            }
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("复制完成");
    }

    //使用BufferedInputStream将input.txt文件中的内容复制到output.txt文件中
    public static void copyFile3() {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\Java_Project\\MyUtils\\resource\\input.txt"));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\Java_Project\\MyUtils\\resource\\output.txt"));
            int ch = 0;
            while ((ch = bis.read()) != -1) {
                bos.write(ch);
            }
            bis.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("复制完成");
    }
    //读取文件中的内容
    public static void readFile() {
        try {
            FileReader fr = new FileReader("E:\\Java_Project\\MyUtils\\resource\\input.txt");
            int ch = 0;
            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取文件中的内容
    public static void readFile2() {
        try {
            FileInputStream fis = new FileInputStream("E:\\Java_Project\\MyUtils\\resource\\input.txt");
            int ch = 0;
            while ((ch = fis.read()) != -1) {
                System.out.print((char) ch);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        copyFile();
    }
}