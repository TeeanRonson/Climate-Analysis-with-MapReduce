package edu.usfca.cs.mr.Customs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateLocation implements WritableComparable<DateLocation> {

    private String location;
    private int time;

    public DateLocation(String location, int time) {
        this.location = location;
        this.time = time;

    }

    public int getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeChars(location);
        out.writeInt(time);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        location = in.readLine();
        time = in.readInt();
    }

    @Override
    public int compareTo(DateLocation o) {

        return this.time - o.time;

    }
}
