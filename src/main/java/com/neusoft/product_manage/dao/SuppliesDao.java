package com.neusoft.product_manage.dao;

import com.neusoft.product_manage.po.Supplies;

import java.util.List;



public interface SuppliesDao {
    List<Supplies> listSuppliesByBusinessId(Integer Id);

    int saveSupplies(Supplies supplies);

    public List<Supplies> listProvider(String Name, String Address);
    public int saveSupplies(String Name);
    public int removeSupplies(int Id);

    public Supplies getSuppliesByIdByPass(Integer Id,String password);
    public Supplies getSuppliesById(Integer Id);
    public int updateSupplies(Supplies supplies);
    public int updateSuppliesByPassword(Integer Id,String password);

    int removeSupplies(Integer suppliesId);
}
