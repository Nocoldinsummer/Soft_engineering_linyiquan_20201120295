package com.neusoft.product_manage.view.impl;

import com.neusoft.product_manage.dao.AdminDao;
import com.neusoft.product_manage.dao.impl.AdminDaoImpl;
import com.neusoft.product_manage.po.Admin;
import com.neusoft.product_manage.view.AdminView;
import java.util.Scanner;


public class AdminViewImpl implements AdminView{
    private Scanner input = new Scanner(System.in);

    @Override
    public Admin login() {
        System.out.println("请输入管理员名称：");
        String Name = input.next();
        System.out.println("请输入密码：");
        String password = input.next();
        String checkStr=Admin.getRandomString(5);
        System.out.println("请输入验证码：");
        System.out.println(checkStr);
        String userStr=input.next();
        if(!checkStr.equals(userStr))return null;
        AdminDao dao = (AdminDao) new AdminDaoImpl();
        return dao.getAdminByNameByPass(Name, password);
//        return dao.getAdminByNameByPass(Name, password);
    }


}
