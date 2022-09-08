package com.mavis.services;

import com.mavis.dao.AdminDAO;
import com.mavis.entity.Admin;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-08-26 09:45
 **/

public class AdminService {
    AdminDAO adminDAO = new AdminDAO();

    public boolean login(Admin admin) throws Exception {
        return adminDAO.login(admin);
    }
}