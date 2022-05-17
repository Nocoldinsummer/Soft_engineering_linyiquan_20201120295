package com.neusoft.product_manage.dao.impl;

import com.neusoft.product_manage.dao.AdminDao;
import com.neusoft.product_manage.po.Admin;
import com.neusoft.product_manage.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao{
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public Admin getAdminByNameByPass(String Name, String password) {
        Admin admin = null;
        String sql = "select * from admin where Name=? and password=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, Name);
            pst.setString(2, password);
            rs = pst.executeQuery();
            while(rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("Id"));
                admin.setName(rs.getString("Name"));
                admin.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return admin;
    }
}
