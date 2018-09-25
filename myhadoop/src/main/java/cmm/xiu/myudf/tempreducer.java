package cmm.xiu.myudf;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class tempreducer extends Reducer<compkey,NullWritable,IntWritable,IntWritable> {
    @Override
    protected void reduce(compkey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

        for (NullWritable value : values) {
            int year=key.getYear();
            int temp=key.getTemp();
            context.write(new IntWritable(year),new IntWritable(temp));
        }
    }
}
