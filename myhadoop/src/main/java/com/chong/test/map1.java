package com.chong.test;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class map1 extends Mapper<LongWritable,Text,compkey1,Text> {
    String name;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit split = (FileSplit)context.getInputSplit();
        name = split.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(name.contains("cust.txt")){
            int flag=1;
            String[] arr = value.toString().split(",");
            int id=Integer.parseInt(arr[0]);
            compkey1 com = new compkey1(flag, id);
            context.write(com,new Text(value.toString()));
        }else{
            int flag=2;
            String orderline=value.toString();
            String[] arr = orderline.split(",");
            int id=Integer.parseInt(arr[3]);
            compkey1 com = new compkey1(flag, id);
            context.write(com,new Text(orderline));
        }
    }
}
