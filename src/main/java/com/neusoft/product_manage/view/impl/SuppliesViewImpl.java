package com.neusoft.product_manage.view.impl;

import java.util.List;
import java.util.Scanner;

import com.neusoft.product_manage.dao.SuppliesDao;
import com.neusoft.product_manage.dao.impl.SuppliesDaoImpl;
import com.neusoft.product_manage.po.Supplies;
import com.neusoft.product_manage.view.SuppliesView;

public class SuppliesViewImpl implements SuppliesView{
    private Scanner input = new Scanner(System.in);

    @Override
    public List<Supplies> showSuppliesList(Integer businessId) {
        SuppliesDao dao = (SuppliesDao) new SuppliesDaoImpl();
        List<Supplies> list = dao.listSuppliesByBusinessId(businessId);
        System.out.println("货物编号\t货物名称\t货物介绍\t货物价格");
        for(Supplies Supplies : list) {
            System.out.println(Supplies.getCode()+"\t"+Supplies.getName()+"\t"+Supplies.getProduction_quality()+"\t"+Supplies.getManufacturer()+"\t"+Supplies.getSourse());
        }
        return list;
    }

    @Override
    public void saveSupplies(Integer Id) {
        Supplies Supplies = new Supplies();
        System.out.println("请输入货物名称：");
        Supplies.setName(input.next());
        System.out.println("请输入货物介绍：");
        Supplies.setCode(input.nextInt());
        System.out.println("请输入货物价格：");
        Supplies.setSourse(input.next());

        SuppliesDao dao = new SuppliesDaoImpl();
        int result = dao.saveSupplies(Supplies);
        if(result>0) {
            System.out.println("\n新增货物成功！\n");
        }else {
            System.out.println("\n新增货物失败！\n");
        }
    }

    @Override
    public void updateSupplies(Integer businessId) {
        SuppliesDao dao = new SuppliesDaoImpl();
        List<Supplies> list = showSuppliesList(businessId);

        if(list.size()==0) {
            System.out.println("没有任何货物！");
        }else {
            System.out.println("请选择要更新的货物编号：");
            int SuppliesId = input.nextInt();
            Supplies Supplies = dao.getSuppliesById(SuppliesId);
            System.out.println(Supplies);

            String inputStr = "";
            System.out.println("是否更新货物名称(y/n)：");
            inputStr = input.next();
            if(inputStr.equals("y")) {
                System.out.println("请输入新的货物名称：");
                Supplies.setName(input.next());
            }

            System.out.println("是否更新货物介绍(y/n)：");
            inputStr = input.next();
            if(inputStr.equals("y")) {
                System.out.println("请输入新的货物介绍：");
                Supplies.setSourse(input.next());
            }
            int result = dao.updateSupplies(Supplies);
            if(result>0) {
                System.out.println("\n修改货物成功！\n");
            }else {
                System.out.println("\n修改货物失败！\n");
            }
        }
    }

    @Override
    public void removeSupplies(Integer businessId) {
        SuppliesDao dao = new SuppliesDaoImpl();
        List<Supplies> list = showSuppliesList(businessId);

        if(list.size()==0) {
            System.out.println("没有任何货物！");
        }else {
            System.out.println("请选择要删除的货物编号：");
            int SuppliesId = input.nextInt();

            System.out.println("确认要删除吗(y/n)：");
            if(input.next().equals("y")) {
                int result = dao.removeSupplies(SuppliesId);
                if(result>0) {
                    System.out.println("\n删除货物成功！\n");
                }else {
                    System.out.println("\n删除货物失败！\n");
                }
            }
        }
    }
}
