package com.old;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("welcomservice")
@Scope(value="singleton")
public class WelcomImp implements WelcomService {
    String name ;
    private Byeservice bs;

    public Byeservice getBs() {
        return bs;
    }
    public WelcomImp(String str1,Integer intt){
        System.out.println("4444");
    }
    public void setBs(Byeservice bs) {
        this.bs = bs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayhello() {
        bs.saybye();
    }
}
