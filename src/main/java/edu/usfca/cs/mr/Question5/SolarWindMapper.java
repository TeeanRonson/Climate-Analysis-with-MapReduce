package edu.usfca.cs.mr.Question5;

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

public class SolarWindMapper
        extends Mapper<LongWritable, Text, DateLocation, Weather> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        try {

            //Set up Data
            InfoGrabber info = new InfoGrabber(value.toString());
            String location = Geohash.encode(info.get_Latitude(), info.get_Longitude(), 3);
            DateLocation dl = new DateLocation();
            dl.set(location, info.getUTCDateByMonth());
            Weather w = new Weather();
            w.setSolarRadiation(new IntWritable(info.get_Solar_Radiation()));
            w.setWind(new FloatWritable(info.get_Wind_Speed_1_5()));

            //clean data
            if (info.get_Solar_Radiation() >= 800) {
//            if (info.get_Wind_Speed_1_5() >= 4) {
                System.out.println(info.get_Solar_Radiation());
                System.out.println(info.get_Wind_Speed_1_5());
                context.write(dl, w);
            }


        } catch (Exception e) {
            e.fillInStackTrace();

        }

    }
}