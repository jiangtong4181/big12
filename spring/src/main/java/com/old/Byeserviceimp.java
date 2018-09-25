package com.old;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("byeService")
@Scope("singleton")
public class Byeserviceimp implements Byeservice{
    public void saybye() {
        System.out.println(bye);
    }
    private String bye;

    public String getBye() {
        return bye;
    }

    public void setBye(String bye) {
        this.bye = bye;
    }
    public void Byeserviceimp(){
        System.out.println("new byeserviceimp");
    }
}
