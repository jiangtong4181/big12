package com.old;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Appspring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        WelcomImp ws = (WelcomImp) ac.getBean("WelcomImp");
        ws.sayhello();
//        Byeservice bs = (Byeservice) ac.getBean("byeservice");
//        bs.saybye();
    }
}
