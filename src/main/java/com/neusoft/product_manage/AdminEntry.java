package com.neusoft.product_manage;

import com.neusoft.product_manage.po.Admin;
import com.neusoft.product_manage.po.Provider;
import com.neusoft.product_manage.view.AdminView;
import com.neusoft.product_manage.view.ProviderView;
import com.neusoft.product_manage.view.impl.AdminViewImpl;
import com.neusoft.product_manage.view.impl.ProviderViewImpl;
import com.neusoft.product_manage.dao.impl.Trie;
import java.util.Scanner;

public class AdminEntry {
    public void work() {
        Scanner input = new Scanner(System.in);

        System.out.println("---------------------------------------------------------");
        System.out.println("|\t\t\t 货物管理系统  \t\t\t|");
        System.out.println("---------------------------------------------------------");

        AdminView adminView = (AdminView) new AdminViewImpl();
        ProviderView providerView = (ProviderView) new ProviderViewImpl();
        Admin admin = adminView.login();

        //登录
        if(admin!=null) {
            int menu = 0;
            Trie trie = new Trie();
            while(menu!=5) {
                //输出主菜单
                System.out.println("\n1.所有供貨商列表\t2.搜索供貨商\t3.新建供貨商\t4.删除供貨商\t5.退出系统 =========");
                System.out.println("请输入你的选择：");
                menu = input.nextInt();
                switch(menu) {
                    case 1:
                        providerView.listProviderAll();
                        break;
                    case 2:
                        providerView.listProvider(trie);
                        break;
                    case 3:
                        String S=providerView.saveProvider();
                        Trie.addWord(trie,S);
                        break;
                    case 4:
                        providerView.removeProvider();
                        break;
                    case 5:
                        System.out.println("------------------------感謝使用-----------------------");
                        break;
                    default:
                        System.out.println("没有这个选项！\n");
                        break;
                }
            }

        }else {
            System.out.println("\n请检查是否正确输入管理员，密码，验证码!\n");
        }
    }

    public static void main(String[] args) {
        new AdminEntry().work();
    }

}
