package edu.usfca.cs.mr.Question6;

import edu.usfca.cs.mr.Customs.DateLocation;
import edu.usfca.cs.mr.Customs.Weather;
import edu.usfca.cs.mr.util.CONSTANTS;
import edu.usfca.cs.mr.util.Geohash;
import edu.usfca.cs.mr.util.InfoGrabber;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class GeoHashPrefixMapper
        extends Mapper<LongWritable, Text, DateLocation, Weather> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //Set up Data
        InfoGrabber info = new InfoGrabber(value.toString());
        String selectedRegion = "9qd";
        DateLocation dl = new DateLocation();

        String location = Geohash.encode(info.get_Latitude(), info.get_Longitude(), 3);

        if (location.equals(selectedRegion)) {
            dl.set(location, info.getUTCDateByMonth());

            Weather w = new Weather();

            w.setAirTemp(new FloatWritable(info.get_Air_Temp()));
            w.setPrecipitation(new FloatWritable(info.get_Precipitation()));
            context.write(dl, w);

         }
    }
}