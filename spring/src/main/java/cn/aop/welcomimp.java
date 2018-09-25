package cn.aop;

public class welcomimp implements welcominterface {
    public void sayhello(String name) {
        System.out.println("本体" + name);
    }
}
