package edu.usfca.cs.mr.Question2;

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

public class DriestRegionMapper
        extends Mapper<LongWritable, Text, DateLocation, Weather> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //Set up Data
        InfoGrabber info = new InfoGrabber(value.toString());
        String selectedRegion = "9qd";
        DateLocation dl = new DateLocation();

        String location = Geohash.encode(info.get_Latitude(), info.get_Longitude(), 3);

//        System.out.println(location);

        if (location.equals(selectedRegion)) {
            dl.set(location, info.getUTCDateByMonth());
            System.out.println(dl.toString());

            //clean data
            Weather doww = new Weather();

            doww.setWetness(new IntWritable(info.get_Wetness()));
            doww.setPrecipitation(new FloatWritable(info.get_Precipitation()));
            doww.setRelativeHumidity(new IntWritable(info.get_Relative_Humidity()));
            doww.setAirTemp(new FloatWritable(info.get_Air_Temp()));
            doww.setSurfTemp(new FloatWritable(info.get_Surface_Temp()));
            doww.setWind(new FloatWritable(info.get_Wind_Speed_1_5()));

            if (info.get_Wetness() != CONSTANTS.INTFOUR9 &&
                    info.get_Relative_Humidity() != CONSTANTS.INTFOUR9 &&
                    info.get_Precipitation() != CONSTANTS.INTFOUR9) {

                System.out.println(info.get_Wetness());
                System.out.println(info.get_Relative_Humidity());
                System.out.println(info.get_Precipitation());


                context.write(dl, doww);
            }
        }
    }
}