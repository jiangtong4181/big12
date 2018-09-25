package cmm.xiu.myudf;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class grouper extends WritableComparator {
    public grouper() {
        super(compkey.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        compkey a1 = (compkey) a;
        compkey b1 = (compkey) b;
        return a1.compareTo(b1);
    }
}
