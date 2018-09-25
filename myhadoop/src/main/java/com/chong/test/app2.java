package com.chong.test;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class app2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        Job job = Job.getInstance(conf);
        job.setJobName("taggen1");
        job.setJarByClass(app2.class);
        job.setMapperClass(map1.class);
        job.setReducerClass(reduce2.class);
        job.setMapOutputKeyClass(compkey1.class);
        job.setMapOutputValueClass(Text.class);
        job.setGroupingComparatorClass(group2.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job, new Path("d:\\test\\cust.txt"));
        FileInputFormat.addInputPath(job, new Path("d:\\test\\orders.txt"));
        FileOutputFormat.setOutputPath(job, new Path("d:\\test\\out"));
        job.setNumReduceTasks(1);
        Boolean b = job.waitForCompletion(true);
    }
}
