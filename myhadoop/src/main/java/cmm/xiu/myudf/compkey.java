package cmm.xiu.myudf;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class compkey implements WritableComparable<compkey> {
    private int year;
    private int temp;

    public int compareTo(compkey o) {
        if(this.year==o.year){
            return this.temp-o.temp;
        }else{
            return o.year-this.year;
        }
    }



    public compkey(int year, int temp) {
        this.year = year;
        this.temp = temp;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(this.year);
        out.writeInt(this.temp);
    }

    public void readFields(DataInput in) throws IOException {
        year=in.readInt();
        temp=in.readInt();
    }
}
