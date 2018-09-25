package com.chong.test;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class compkey1 implements WritableComparable<compkey1> {

    private int flag;
    private int id;
    public int compareTo(compkey1 o) {
        if(this.id==o.id){
            return this.flag-o.flag;
        }else{
            return this.id-o.id;
        }
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(flag);
        out.writeInt(id);
    }

    public void readFields(DataInput in) throws IOException {
        this.flag=in.readInt();
        this.id=in.readInt();
    }

    public compkey1(int flag, int id) {
        this.flag = flag;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
