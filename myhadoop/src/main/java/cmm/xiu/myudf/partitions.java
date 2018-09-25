package cmm.xiu.myudf;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class partitions extends Partitioner<compkey,NullWritable> {
    @Override
    public int getPartition(compkey compkey, NullWritable nullWritable, int numPartitions) {
        Integer year=compkey.getYear();
        return (year.hashCode()&Integer.MAX_VALUE)%numPartitions;
    }
}
