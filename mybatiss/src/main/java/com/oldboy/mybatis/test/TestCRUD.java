package com.oldboy.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


public class TestCRUD {
    @Test
    public void Testinsert() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        User u = new User();
        u.setName("tom");
        u.setAge(12);
        sess.insert("users.insert",u);
        sess.commit();
        sess.close();
        System.out.println("ok");
    }
    @Test
    public void Testupdata() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        User u = new User();
        u.setName("tomas");
        u.setAge(22);
        u.setId(1);
        sess.insert("users.updata",u);
        sess.commit();
        sess.close();
        System.out.println("ok");
    }
    @Test
    public void Testselect() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        User o = sess.selectOne("users.selectbyid", 1);
        System.out.println(o.getName());
        sess.commit();
        sess.close();
        System.out.println("ok");
    }
    @Test
    public void Testselectall() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        List<User> users = sess.selectList("users.selectall");
        System.out.println(users.size());
        sess.commit();
        sess.close();
        System.out.println("ok");
    }
    @Test
    public void Testdelete() throws Exception{
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        //开启会话
        SqlSession sess = sf.openSession();
        sess.delete("users.delete",1);
        sess.commit();
        sess.close();
        System.out.println("ok");
    }
}
