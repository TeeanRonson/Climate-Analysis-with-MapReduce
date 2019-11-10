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

    private Text location;
    private IntWritable date;

    public DateLocation(String location, int time) {
        this.location = new Text(location);
        this.date = new IntWritable(time);

    }

    public DateLocation() {
        this.location = new Text();
        this.date = new IntWritable();
    }

    public void set(String location, int time) {
        this.location = new Text(location);
        this.date = new IntWritable(time);
    }

    public IntWritable getDate() {
        return date;
    }

    public Text getLocation() {
        return location;
    }

    @Override
    public String toString() {

        if (location == null || date.get() == 0) {
            return null;
        }
        return location + ", " + String.valueOf(date.get()) + ", ";
    }

    @Override
    public void write(DataOutput out) throws IOException {
        location.write(out);
        date.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        location.readFields(in);
        date.readFields(in);
    }

    @Override
    public int compareTo(DateLocation o) {

        if (this.date.get() == o.date.get()) {
            return this.location.compareTo(o.getLocation());
        }
        return this.date.get() - o.date.get();

    }
}
