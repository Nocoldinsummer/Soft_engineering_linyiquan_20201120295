package com.neusoft.product_manage.view;

import com.neusoft.product_manage.po.Supplies;

import java.util.List;

public interface SuppliesView {
    public List<Supplies> showSuppliesList(Integer Id);
    public void saveSupplies(Integer Id);
    public void updateSupplies(Integer Id);
    public void removeSupplies(Integer Id);
}
