package edu.usfca.cs.mr.Question1;

import edu.usfca.cs.mr.Customs.DateLocation;
import edu.usfca.cs.mr.util.Geohash;
import edu.usfca.cs.mr.util.InfoGrabber;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * AirTemp -> DateLocation
 */
public class AirTempMapper
extends Mapper<LongWritable, Text, DateLocation, FloatWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        InfoGrabber grab = new InfoGrabber(value.toString());

        String location = Geohash.encode(grab.getLat(), grab.getLong(), 5);

        DateLocation dl = new DateLocation(location, grab.getUTCDate());
        FloatWritable airTemp = new FloatWritable(grab.getAirTemp());

        System.out.println(location + " " + airTemp);
        context.write(dl, airTemp);

    }
}
