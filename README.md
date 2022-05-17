# Soft engineering training assignment项目描述：
这个项目是在软工实训上学习elm项目之后，为了锻练和巩固JDBC所做的项目
项目的名称是货物管理系统，项目在整体逻辑方面与elm项目有相似之处，具体的相似是指
同样是管理员登录，以及涉及到基本的增删改查。例如供货商信息的查找，供货商信息的增加，供货商信息的删除等操作。
但是这个项目的代码实现，以及增删改查的具体实现功能与原来的项目不同，下面会一一指出。
下面讲主要不同之处
首先，在管理员登录使用了验证码，这个涉及到随机字符串的生成，以及校验，对比和反馈
在po/Admin中加入了下面的静态方法
```
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
```
这个可以实现随机字符串的生成
在用户登录时，会自动生成验证码，并且会打印在屏幕上，让用户输入
如果，用户输入的结果与生成的不一致，那么就会拒绝登录

在查找方面，新增了快速全字查找的方法
在impl里新加入了Trie类
这个是算法竞赛的知识，字典树。
```
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
```
因为传统的匹配查找需要将每个用户的信息的每一个字段和提供的字段进行一一对比，找到结果，
在小数据的范围下看不出差别，但是在海量数据中，字典树可以通过O（n）的时间复杂度查询结果，
获得较大的效率提升。

在用户查询中可以得到多结果查询，这个查询主要针对Int型的字段，
```
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
                provider.setContact(rs.getString("contact"));
                list.add(provider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return list;
    }
```
用户可以通过输入相应的选项opt，以及tmp。
如果opt=1，那么就会得到对应字段大于tmp的数据结果；如果opt=2，那么就会得到对应字段小于tmp的数据结果。
这是微课中所没有的查询方式。


最后其他很多细节方面与微课，和上课所交的elm项目有很多不同之处，这里不再一一赘述，但是实现了最后的增删改查。
运行结果还是能成功连接数据库，并且得到了期望的结果。