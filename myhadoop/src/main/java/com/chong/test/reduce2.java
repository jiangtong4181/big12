package com.chong.test;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class reduce2 extends Reducer<compkey1,Text,Text,NullWritable> {
    @Override
    protected void reduce(compkey1 key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> it = values.iterator();
        String[] arr = it.next().toString().split(",");
        String name=arr[1];
        while(it.hasNext()){
            String[] arr1 = it.next().toString().split(",");
            String orderno=arr1[1];
            String price=arr1[2];
            String newline=name+","+orderno+","+price;
            context.write(new Text(newline),NullWritable.get());
        }
    }
}
