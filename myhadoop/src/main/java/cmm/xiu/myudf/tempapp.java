package cmm.xiu.myudf;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class tempapp {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        Job job = Job.getInstance(conf);
        job.setJobName("taggen1");
        job.setJarByClass(tempapp.class);
        job.setMapperClass(tempmapper.class);
        job.setReducerClass(tempreducer.class);
        job.setMapOutputKeyClass(compkey.class);
        job.setMapOutputValueClass(NullWritable.class);
        FileInputFormat.setMaxInputSplitSize(job,102400);
        FileInputFormat.setMinInputSplitSize(job,102400);
        job.setGroupingComparatorClass(grouper.class);
        job.setPartitionerClass(partitions.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path("d:\\test\\kv.txt"));
        FileOutputFormat.setOutputPath(job, new Path("d:\\test\\out\\"));
        job.setNumReduceTasks(3);
        Boolean b = job.waitForCompletion(true);
    }
}
