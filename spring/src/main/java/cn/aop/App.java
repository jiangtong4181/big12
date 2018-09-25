package cn.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("aop.xml");
        welcominterface  ws = (welcominterface) ac.getBean("welcomeService");
        ws.sayhello("中");
    }
}
