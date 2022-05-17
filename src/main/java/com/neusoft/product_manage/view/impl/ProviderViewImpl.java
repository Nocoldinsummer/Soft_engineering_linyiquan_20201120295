package com.neusoft.product_manage.view.impl;

import com.neusoft.product_manage.dao.ProviderDao;
import com.neusoft.product_manage.dao.impl.ProviderDaoImpl;
import com.neusoft.product_manage.po.Provider;
import com.neusoft.product_manage.view.ProviderView;
import com.neusoft.product_manage.dao.impl.Trie;
import java.util.List;
import java.util.Scanner;

public class ProviderViewImpl implements ProviderView{
    private Scanner input = new Scanner(System.in);

    @Override
    public void listProviderAll() {
        ProviderDao dao = new ProviderDaoImpl();
        List<Provider> list = dao.listProvider(null,null);
        System.out.println("供货商编号\t供货商名称\t供货商地址\t层级编号");
        for(Provider b : list) {
            System.out.println(b.getId()+"\t"+b.getName()+"\t"+b.getAddress()+"\t"+b.getContact());
        }
    }

    @Override
    public void listProvider(Trie trie) {
        String ProviderName = "";
        String ProviderAddress = "";
        String ProviderContect = "";
        int opt = 0;
        String inputStr = "";
        System.out.println("是否需要全字匹配，快速查询：(y/n)");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入供货商名称：");
            ProviderName = input.next();
            int t = Trie.searchWord(trie, ProviderName);
            if (t != 1) {
                return;
            }
            ProviderDao dao = new ProviderDaoImpl();
            List<Provider> list = dao.listProvider(ProviderName, "");
            System.out.println("供货商编号\t供货商名称\t供货商地址\t供货商层级编号");
            for (Provider b : list) {
                System.out.println(b.getId() + "\t" + b.getName() + "\t" + b.getAddress() + "\t" + b.getContact());
            }
            return;
        }
        System.out.println("是否需要进行多结果查询：(y/n)");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输出大于或小于（1/2）");
            opt = input.nextInt();
            System.out.println("请输入供货商ID：");
            int ID = input.nextInt();
            ProviderDao dao = new ProviderDaoImpl();
            List<Provider> list = dao.listProvider2(ID, opt);
            System.out.println("供货商编号\t供货商名称\t供货商地址\t供货商层级编号");
            for (Provider b : list) {
                System.out.println(b.getId() + "\t" + b.getName() + "\t" + b.getAddress() + "\t" + b.getContact());
            }
            return;
        }
        System.out.println("是否需要输入供货商名称关键词(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入供货商名称关键词：");
            ProviderName = input.next();
        }
        System.out.println("是否需要输入供货商地址关键词(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入供货商地址关键词：");
            ProviderAddress = input.next();
        }
        ProviderDao dao = new ProviderDaoImpl();
        List<Provider> list = dao.listProvider(ProviderName, ProviderAddress);
        System.out.println("供货商编号\t供货商名称\t供货商地址\t供货商层级编号");
        for (Provider b : list) {
            System.out.println(b.getId() + "\t" + b.getName() + "\t" + b.getAddress() + "\t" + b.getContact());

        }
    }

    @Override
    public void listProvider2(int id, int opt) {

    }

    @Override
    public String saveProvider() {
        System.out.println("请输入供货商名称：");
        String ProviderName = input.next();
        ProviderDao dao = new ProviderDaoImpl();
        int ProviderId = dao.saveProvider(ProviderName);
        if(ProviderId>0) {
            System.out.println("新建供货商成功！供货商编号为："+ProviderId);
        }else {
            System.out.println("新建供货商失败！");
        }
        return ProviderName;
    }

    @Override
    public void removeProvider() {
        System.out.println("请输入供货商编号：");
        int ProviderId = input.nextInt();

        ProviderDao dao = new ProviderDaoImpl();
        System.out.println("确认要删除吗(y/n)：");
        if(input.next().equals("y")) {
            int result = dao.removeProvider(ProviderId);
            if(result==1) {
                System.out.println("删除供货商成功！");
            }else {
                System.out.println("删除供货商失败！");
            }
        }
    }

    @Override
    public Provider login() {
        System.out.println("请输入供货商编号：");
        int ProviderId = input.nextInt();
        System.out.println("请输入密码：");
        String password = input.next();

        ProviderDao dao = new ProviderDaoImpl();
        return dao.getProviderByIdByPass(ProviderId, password);
    }

    @Override
    public void showProvider(Integer ProviderId) {
        ProviderDao dao = new ProviderDaoImpl();
        Provider Provider = dao.getProviderById(ProviderId);
        System.out.println(Provider);
    }

    @Override
    public void editProvider(Integer ProviderId) {
        //先将供货商信息查询出来显示，然后用户才能更新
        ProviderDao dao = new ProviderDaoImpl();
        Provider Provider = dao.getProviderById(ProviderId);
        System.out.println(Provider);

        String inputStr = "";
        System.out.println("是否修改供货商名称(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的供货商名称：");
            Provider.setName(input.next());
        }

        System.out.println("是否修改供货商地址(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的供货商地址：");
            Provider.setAddress(input.next());
        }

        System.out.println("是否修改供货商介绍(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的供货商介绍：");
            Provider.setContact(input.next());
        }
        int result = dao.updateProvider(Provider);
        if(result>0) {
            System.out.println("\n修改供货商信息成功！\n");
        }else {
            System.out.println("\n修改供货商信息失败！\n");
        }
    }

    @Override
    public void updateProviderByPassword(Integer ProviderId) {
        ProviderDao dao = new ProviderDaoImpl();
        Provider Provider = dao.getProviderById(ProviderId);

        System.out.println("\n请输入旧密码：");
        String oldPass = input.next();
        System.out.println("\n请输入新密码：");
        String password = input.next();
        System.out.println("\n请再次输入新密码：");
        String beginPassword = input.next();

        if(!Provider.getPassword().equals(oldPass)) {
            System.out.println("\n旧密码输入错误！");
        }else if(!password.equals(beginPassword)) {
            System.out.println("\n两次输入密码不一致！");
        }else {
            int result = dao.updateProviderByPassword(ProviderId, password);
            if(result>0) {
                System.out.println("\n修改密码成功！");
            }else {
                System.out.println("\n修改密码失败！");
            }
        }
    }
}
