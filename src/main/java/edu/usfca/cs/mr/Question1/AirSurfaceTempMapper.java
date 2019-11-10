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
 * AirSurfaceTemp -> DateLocation
 */
public class AirSurfaceTempMapper
extends Mapper<LongWritable, Text, DateLocation, FloatWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //Initialise InfoGrabber
        InfoGrabber grab = new InfoGrabber(value.toString());
        DateLocation dl = new DateLocation();
        FloatWritable airTemp = new FloatWritable();
        FloatWritable surfTemp = new FloatWritable();


        //Get Location
        String location = Geohash.encode(grab.get_Latitude(), grab.get_Longitude(), 3);
        dl.set(location, grab.getUTCDateByMonth());

        //Get Air Temp
        airTemp.set(grab.get_Air_Temp());
        surfTemp.set(grab.get_Surface_Temp());

//        context.write(dl, surfTemp);
        context.write(dl, airTemp);
    }
}
