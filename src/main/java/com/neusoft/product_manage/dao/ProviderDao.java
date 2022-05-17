package com.neusoft.product_manage.dao;

import com.neusoft.product_manage.po.Provider;

import java.util.List;

public interface ProviderDao {
    public List<Provider> listProvider(String Name, String Address);
    public List<Provider> listProvider2(int id, int opt);
    public int saveProvider(String Name);
    public int removeProvider(int Id);

    public Provider getProviderByIdByPass(Integer Id, String Password);
    public Provider getProviderById(Integer Id);
    public int updateProvider(Provider Provider);
    public int updateProviderByPassword(Integer Id,String password);
}
