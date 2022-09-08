package com.mavis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mavis.entity.Medicine;
import com.mavis.services.MedicineService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: Pharmacy
 * @description: 药品控制层
 * @author: Mavis
 * @create: 2022-09-01 18:41
 **/
@WebServlet("/medicine")
public class MedicineController extends BaseServlet{

    MedicineService medicineService = new MedicineService();

    /**
     * 更新药品信息
     * @param req
     * @param resp
     */
    public void updateMedicine(HttpServletRequest req, HttpServletResponse resp){
        Integer mid = getIntegerParams(req, "mid");
        String mname = getStringParams(req, "mname");
        String type = getStringParams(req, "type");
        String facturer = getStringParams(req, "facturer");
        String note = getStringParams(req, "note");
        String symptom = getStringParams(req, "symptom");
        String used = getStringParams(req, "used");
        String taboo = getStringParams(req, "taboo");
        Double cost = getDoubleParams(req, "cost");
        Double price = getDoubleParams(req, "price");
        String mimg = getStringParams(req, "ming");
        Medicine medicine = new Medicine();
        medicine.setMname(mname);
        medicine.setType(type);
        medicine.setFacturer(facturer);
        medicine.setNote(note);
        medicine.setSymptom(symptom);
        medicine.setUsed(used);
        medicine.setTaboo(taboo);
        medicine.setCost(cost);
        medicine.setPrice(price);
        medicine.setProfit(price-cost);
        medicine.setMimg(mimg);
        boolean b = medicineService.updateMedicine(medicine, mid);
        System.out.println(b);
        writeToPage(resp, b+"");
    }

    /**
     * 根据mid获取药品json对象
     * @param req
     * @param resp
     */
    public void getMedicineByMid(HttpServletRequest req,HttpServletResponse resp){
        Integer mid = getIntegerParams(req, "mid");
        if (mid != null){
            List<Medicine> medicines = medicineService.getMedicineByMid(mid);
            if (medicines != null && medicines.size() > 0){
                Medicine medicine = medicines.get(0);
                writeToPage(resp,JSON.toJSONString(medicine));
            }
        }
    }

    /**
     * 根据mid删除药品
     * @param req
     * @param resp
     */
    public void deleteMedicineByMid(HttpServletRequest req,HttpServletResponse resp){
        Integer mid = getIntegerParams(req, "mid");
        boolean b = medicineService.deleteMedicineByMid(mid);
        if (b == true){
            writeToPage(resp,"删除成功");
        }else {
            writeToPage(resp,"删除失败");
        }
    }

    /**
     * 添加药品
     * @param req
     * @param resp
     */
    public void addMedicine(HttpServletRequest req,HttpServletResponse resp){
        String mname = getStringParams(req, "mname");
        String type = getStringParams(req, "type");
        String note = getStringParams(req, "note");
        String symptom = getStringParams(req, "symptom");
        String used = getStringParams(req, "used");
        String taboo = getStringParams(req, "taboo");
        Double cost = getDoubleParams(req, "cost");
        Double price = getDoubleParams(req, "price");
        String mimg = getStringParams(req, "ming");
        String facturer = getStringParams(req, "facturer");
        Medicine medicine = new Medicine();
        medicine.setMname(mname);
        medicine.setType(type);
        medicine.setNote(note);
        medicine.setSymptom(symptom);
        medicine.setUsed(used);
        medicine.setTaboo(taboo);
        medicine.setCost(cost);
        medicine.setPrice(price);
        medicine.setProfit(price-cost);
        medicine.setMimg(mimg);
        medicine.setFacturer(facturer);
        boolean b = medicineService.addMedicine(medicine);
        if (b == true){
            writeToPage(resp,"添加成功");
        }else {
            writeToPage(resp,"添加失败");
        }
    }

    /**
     * 获取所有药品Json对象
     * @param req
     * @param resp
     */
    public void getAllMedicine(HttpServletRequest req, HttpServletResponse resp){
        List<Medicine> allMedicine = medicineService.getAllMedicine();
        int count = allMedicine.size();
        //将对象进行json格式化
        JSONObject res = new JSONObject();
        res.put("code",0);
        res.put("msg","");
        res.put("count",count);
        res.put("data",allMedicine);
        writeToPage(resp,res.toJSONString());
    }

}