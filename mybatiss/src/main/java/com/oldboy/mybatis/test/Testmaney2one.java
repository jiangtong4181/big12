package com.oldboy.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class Testmaney2one {
    @Test
    public void Testinsert() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        User u = new User();
        Order order = new Order();
        order.setOrderno("004");
        order.setPrice(2600);
        order.setUser(u);
        sess.insert("users.insert",u);
        sess.insert("orders.insert",order);
        sess.commit();
        sess.close();
        System.out.println("ok");
    }
    @Test
    public void Testselectbyone() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        Order o = sess.selectOne("orders.selectbyone", 2);
        System.out.println(o.getUser().getName());
        sess.close();
    }
    @Test
    public void Testone2maney() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        User o = sess.selectOne("users.selectbyid", 2);
        sess.close();
    }
    @Test
    public void Testiteminsert() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        Order o = new Order();
        o.setId(2);
        Item i = new Item();
        i.setIname("i1");
        i.setOrder(o);
        sess.insert("orderitems.insert",i);
        sess.close();
    }
    @Test
    public void Testselectorder() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        Order o = sess.selectOne("orders.selectbyone", 1);
        sess.close();
    }
    @Test
    public void Testselectitem() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        Item o = sess.selectOne("orderitems.select", 1);
        sess.close();
    }
    @Test
    public void Testselectuser() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        User o = sess.selectOne("users.selectbyid", 2);
        sess.close();
    }

    @Test
    public void Testself() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        Area a1 = new Area("quanguo");
        Area a2 = new Area("henan");
        Area a3 = new Area("hebei");
        Area a4 = new Area("baoding");
        Area a5 = new Area("shijiazhuang");
        Area a6 = new Area("kaifeng");
        Area a7 = new Area("luoyang");
        a1.addchildren(a2,a3);
        a2.addchildren(a6,a7);
        a3.addchildren(a4,a5);
        sess.insert("areas.insert",a1);
        sess.insert("areas.insert",a2);
        sess.insert("areas.insert",a3);
        sess.insert("areas.insert",a4);
        sess.insert("areas.insert",a5);
        sess.insert("areas.insert",a6);
        sess.insert("areas.insert",a7);
        sess.commit();
        sess.close();
    }
}
