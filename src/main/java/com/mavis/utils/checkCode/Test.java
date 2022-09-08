package com.mavis.utils.checkCode;

import java.io.FileOutputStream;

/**
 * @program: Utils
 * @description:
 * @author: Mavis
 * @create: 2022-07-04 14:19
 * aaa
 **/

public class Test {
    public static void main(String[] args) throws Exception {
//        定义输出路径
        String path = "src/main/resources";
//        定义输出验证码的图片路径
        FileOutputStream fos = new FileOutputStream(path +"\\checkcode.png");
//        输出验证码图片文件并且获取验证码内容
        String s = CheckCodeUtil.outputVerifyImage(150, 50, fos, 6);
        fos.close();
        System.out.println("输出验证码的内容："+s);
        //111
    }
}