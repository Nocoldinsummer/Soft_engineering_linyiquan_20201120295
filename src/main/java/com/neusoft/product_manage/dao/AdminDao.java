package com.neusoft.product_manage.dao;

import com.neusoft.product_manage.po.Admin;

public interface AdminDao {
    public Admin getAdminByNameByPass(String adminName, String password);
}
