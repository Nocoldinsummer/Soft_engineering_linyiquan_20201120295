package com.neusoft.product_manage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.product_manage.po.Supplies;
import com.neusoft.product_manage.util.DBUtil;
import com.neusoft.product_manage.dao.SuppliesDao;

public class SuppliesDaoImpl implements SuppliesDao{

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public List<Supplies> listSuppliesByBusinessId(Integer Id) {
        List<Supplies> list = new ArrayList<>();
        String sql = "select * from Supplies where Id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, Id);
            rs = pst.executeQuery();
            while(rs.next()) {
                Supplies supplies = new Supplies();
                supplies.setCode(rs.getInt("code"));
                supplies.setName(rs.getString("name"));
                supplies.setProduction_quality(rs.getString("production_quality"));
                supplies.setSourse(rs.getString("Source"));
                list.add(supplies);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return list;
    }

    @Override
    public int saveSupplies(Supplies supplies) {
        int result = 0;
        String sql = "insert into Supplies values(null,?,?,?,?)";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, supplies.getName());
            pst.setInt(2, supplies.getCode());
            pst.setString(3, supplies.getProduction_quality());
            pst.setString(4, supplies.getSourse());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public List<Supplies> listProvider(String Name, String Address) {
        return null;
    }

    @Override
    public int saveSupplies(String Name) {
        return 0;
    }

    @Override
    public int removeSupplies(int Id) {
        return 0;
    }

    @Override
    public Supplies getSuppliesByIdByPass(Integer Id, String password) {
        return null;
    }

    @Override
    public Supplies getSuppliesById(Integer Code) {
        Supplies supplies = null;
        String sql = "select * from supplies where Code=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, Code);
            rs = pst.executeQuery();
            while(rs.next()) {
                supplies=new Supplies();
                supplies.setCode(rs.getInt("code"));
                supplies.setName(rs.getString("name"));
                supplies.setProduction_quality(rs.getString("production_quality"));
                supplies.setSourse(rs.getString("Source"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return supplies;
    }

    @Override
    public int updateSupplies(Supplies supplies) {
        int result = 0;
        String sql = "update Supplies set Name=?,Source=?,Production_quality=? where Code=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, supplies.getName());
            pst.setString(2, supplies.getSourse());
            pst.setString(3, supplies.getProduction_quality());
            pst.setInt(4, supplies.getCode());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public int updateSuppliesByPassword(Integer Id, String password) {
        return 0;
    }

    @Override
    public int removeSupplies(Integer Id) {
        int result = 0;
        String sql = "delete from Supplies where Id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, Id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }
}
