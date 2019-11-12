package edu.usfca.cs.mr.Question4;

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

public class TravelStartupMapper
        extends Mapper<LongWritable, Text, DateLocation, Weather> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //Set up Data
        InfoGrabber info = new InfoGrabber(value.toString());

        String location = Geohash.encode(info.get_Latitude(), info.get_Longitude(), 2);
        Weather weather = new Weather();
        DateLocation dl = new DateLocation();


        //New York
//        if (location.equals(CONSTANTS.NEWYORK) || location.equals(CONSTANTS.NEWYORK1) || location.equals(CONSTANTS.NEWYORK2) || location.equals(CONSTANTS.NEWYORK3)
//                || location.equals(CONSTANTS.NEWYORK4) || location.equals(CONSTANTS.NEWYORK5) || location.equals(CONSTANTS.NEWYORK6) || location.equals(CONSTANTS.NEWYORK7) || location.equals(CONSTANTS.NEWYORK8)) {
//
//            dl.set(location, info.getUTCDateByMonth());
//
//            weather.setAirTemp(new FloatWritable(info.get_Air_Temp()));
//            weather.setRelativeHumidity(new IntWritable(info.get_Relative_Humidity()));
//
//            if (info.get_Air_Temp() != CONSTANTS.FLOATFOUR9 && info.get_Relative_Humidity() != CONSTANTS.INTFOUR9) {
//                context.write(dl, weather);
//            }
//
//        }

//        if (CONSTANTS.ORLANDO.contains(location)) {
//
//            dl.set(location, info.getUTCDateByMonth());
//
//            weather.setAirTemp(new FloatWritable(info.get_Air_Temp()));
//            weather.setRelativeHumidity(new IntWritable(info.get_Relative_Humidity()));
//
//            if (info.get_Air_Temp() != CONSTANTS.FLOATFOUR9 && info.get_Relative_Humidity() != CONSTANTS.INTFOUR9) {
//                context.write(dl, weather);
//            }
//        }

        if (CONSTANTS.ALASKA.contains(location)) {

            System.out.println(location);

            dl.set(location, info.getUTCDateByMonth());

            weather.setAirTemp(new FloatWritable(info.get_Air_Temp()));
            weather.setRelativeHumidity(new IntWritable(info.get_Relative_Humidity()));

            if (info.get_Air_Temp() != CONSTANTS.FLOATFOUR9 && info.get_Relative_Humidity() != CONSTANTS.INTFOUR9) {
                context.write(dl, weather);
            }
        }





    }
}