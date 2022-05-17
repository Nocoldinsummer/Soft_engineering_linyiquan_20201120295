package com.neusoft.product_manage.view;

import com.neusoft.product_manage.po.Provider;
import com.neusoft.product_manage.dao.impl.Trie;
public interface ProviderView {
    public void listProviderAll();
    public void listProvider(Trie trie);
    public void listProvider2(int id,int opt);
    public String saveProvider();
    public void removeProvider();

    public Provider login();
    public void showProvider(Integer Id);
    public void editProvider(Integer Id);
    public void updateProviderByPassword(Integer Id);
}
