package com.neusoft.product_manage.dao.impl;

import com.neusoft.product_manage.dao.ProviderDao;
import com.neusoft.product_manage.po.Admin;
import com.neusoft.product_manage.po.Provider;
import com.neusoft.product_manage.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.product_manage.po.Provider;
import com.neusoft.product_manage.util.DBUtil;



public class ProviderDaoImpl implements ProviderDao{

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    @Override
    public List<Provider> listProvider(String name, String address) {
        List<Provider> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from provider where 1=1 ");
        if(name!=null&&!name.equals("")) {
            sql.append(" and name like '%"+name+"%' ");
        }
        if(address!=null&&!address.equals("")) {
            sql.append(" and providerAddress like '%"+address+"%' ");
        }
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            while(rs.next()) {
                Provider provider = new Provider();
                provider.setId(rs.getInt("Id"));
                provider.setPassword(rs.getString("password"));
                provider.setName(rs.getString("name"));
                provider.setAddress(rs.getString("address"));
                provider.setAddress(rs.getString("contact"));
                list.add(provider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return list;
    }

    @Override
    public List<Provider> listProvider2(int id, int opt) {
        List<Provider> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("");
        if(opt==1){
            sql.append("select * from provider where " +Integer.toString(id)+"<ID");
        }
        else{
            sql.append("select * from provider where "+Integer.toString(id)+">ID");
        }
//        System.out.println(sql);
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            while(rs.next()) {
                Provider provider = new Provider();
                provider.setId(rs.getInt("Id"));
                provider.setPassword(rs.getString("password"));
                provider.setName(rs.getString("name"));
                provider.setAddress(rs.getString("address"));
                provider.setAddress(rs.getString("contact"));
                list.add(provider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return list;
    }
    @Override
    public int saveProvider(String name) {
        int Id = 0;
        String sql = "insert into Provider(name,password) values(?,'123')";
        try {
            con = DBUtil.getConnection();
            //设置返回自增长列值
            pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, name);
            pst.executeUpdate();
            //获取自增长列值（一行一列）
            rs = pst.getGeneratedKeys();
            if(rs.next()) {
                Id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return Id;
    }

    @Override
    public int removeProvider(int Id) {
        int result = 0;
        String delFootSql = "delete from supplies where Code=?";
        String delProviderSql = "delete from Provider where Id=?";
        try {
            con = DBUtil.getConnection();
            //开启一个事务
            con.setAutoCommit(false);

            pst = con.prepareStatement(delFootSql);
            pst.setInt(1, Id);
            pst.executeUpdate();

            pst = con.prepareStatement(delProviderSql);
            pst.setInt(1, Id);
            result = pst.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            result = 0;
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public Provider getProviderByIdByPass(Integer Id, String password) {
        Provider provider = null;
        String sql = "select * from Provider where Id=? and password=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, Id);
            pst.setString(2, password);
            rs = pst.executeQuery();
            while(rs.next()) {
                provider = new Provider();
                provider.setId(rs.getInt("Id"));
                provider.setPassword(rs.getString("password"));
                provider.setName(rs.getString("name"));
                provider.setAddress(rs.getString("address"));
                provider.setContact(rs.getString("contact"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return provider;
    }

    @Override
    public Provider getProviderById(Integer Id) {
        Provider provider = null;
        String sql = "select * from Provider where Id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, Id);
            rs = pst.executeQuery();
            while(rs.next()) {
                provider = new Provider();
                provider.setId(rs.getInt("Id"));
                provider.setPassword(rs.getString("password"));
                provider.setName(rs.getString("name"));
                provider.setAddress(rs.getString("address"));
                provider.setAddress(rs.getString("contact"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return provider;
    }

    @Override
    public int updateProvider(Provider provider) {
        int result = 0;
        String sql = "update Provider set Name=?,Address=?,Contact=? where Id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, provider.getName());
            pst.setString(2, provider.getAddress());
            pst.setString(3, provider.getName());
            pst.setString(4, provider.getContact());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public int updateProviderByPassword(Integer Id,String password) {
        int result = 0;
        String sql = "update Provider set password=? where Id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, password);
            pst.setInt(2, Id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }
}
