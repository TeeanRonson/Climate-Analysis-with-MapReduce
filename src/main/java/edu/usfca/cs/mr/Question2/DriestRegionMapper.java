package edu.usfca.cs.mr.Question2;

import edu.usfca.cs.mr.Customs.DateLocation;
import edu.usfca.cs.mr.Customs.DryOrWetWeather;
import edu.usfca.cs.mr.util.Geohash;
import edu.usfca.cs.mr.util.InfoGrabber;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DriestRegionMapper
        extends Mapper<LongWritable, Text, DateLocation, DryOrWetWeather> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //Set up Data
        InfoGrabber info = new InfoGrabber(value.toString());

        DateLocation dl = new DateLocation();

        String location = Geohash.encode(info.get_Latitude(), info.get_Longitude(), 3);
        dl.set(location, info.getUTCDateByMonth());

        System.out.println(dl.toString());

        //clean data
        DryOrWetWeather doww = new DryOrWetWeather();

        doww.set(info.get_Wetness(),
                info.get_Soil_Moisture_5(),
                info.get_Relative_Humidity(),
                info.get_Relative_Humidity(),
                info.get_Precipitation());

        System.out.println(doww.toString());

        context.write(dl, doww);
    }
}