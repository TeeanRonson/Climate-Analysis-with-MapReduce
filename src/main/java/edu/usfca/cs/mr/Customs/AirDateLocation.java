package edu.usfca.cs.mr.Customs;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

class AirDateLocation implements WritableComparable<AirDateLocation> {

    private FloatWritable air;
    private FloatWritable surface;

    public AirDateLocation(FloatWritable air, FloatWritable surface) {
        this.air = air;
        this.surface = surface;

    }

    @Override
    public void write(DataOutput out) throws IOException {
        air.write(out);
        surface.write(out);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        air.readFields(in);
        surface.readFields(in);

    }

    @Override
    public int compareTo(AirDateLocation o) {


        return -1;
    }
}
