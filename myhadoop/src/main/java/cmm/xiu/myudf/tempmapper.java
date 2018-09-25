package cmm.xiu.myudf;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class tempmapper extends Mapper<LongWritable,Text,compkey,NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] arr = value.toString().split("\t");
        int year=Integer.parseInt(arr[0]);
        int temp=Integer.parseInt(arr[1]);
        compkey com = new compkey(year, temp);
        context.write(com,NullWritable.get());
    }
}
