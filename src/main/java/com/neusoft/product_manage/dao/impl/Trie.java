package com.neusoft.product_manage.dao.impl;

public class Trie {
    int count;
    int predix;
    Trie[] nextNode = new Trie[26];
    public Trie(){
        count = 0;
        predix = 0;
    }
    //加入供货商
    public static void addWord(Trie root, String str){
        if (root == null || str == null){
            return;
        }
//
        //将字符串转换为字符数组
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (root.nextNode[chars[i]-'a'] == null){
                root.nextNode[chars[i]-'a'] = new Trie();
            }
            root = root.nextNode[chars[i]-'a'];
            root.predix++;
        }
        root.count++;
//        System.out.println(str+"供货商添加成功");
    }

    //查询供货商
    public static int searchWord(Trie root,String str){
        if (root == null || str == null){
            return  -1;
        }

        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (root.nextNode[chars[i]-'a'] == null){
                System.out.println("供货商不存在");
                return 0;
            }
            root = root.nextNode[chars[i]-'a'];
        }
        System.out.println("存在该供货商");
        return 1;
    }
}

