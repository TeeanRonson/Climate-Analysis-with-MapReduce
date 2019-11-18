package edu.usfca.cs.mr.Question7;

import edu.usfca.cs.mr.Customs.DateLocation;
import edu.usfca.cs.mr.Customs.Weather;
import edu.usfca.cs.mr.util.Geohash;
import edu.usfca.cs.mr.util.InfoGrabber;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Question7Mapper
        extends Mapper<LongWritable, Text, DateLocation, Weather> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //Set up Data
        InfoGrabber info = new InfoGrabber(value.toString());
        String selectedRegion = "9q";
//        String selectedRegion1 = "9r";
//        String selectedRegion2 = "9v";

        DateLocation dl = new DateLocation();

        String location = Geohash.encode(info.get_Latitude(), info.get_Longitude(), 2);

        if (location.equals(selectedRegion)) {
            dl.set(location, info.getUTCDateByMonth());

            Weather w = new Weather();

            w.setAirTemp(new FloatWritable(info.get_Air_Temp()));
            w.setPrecipitation(new FloatWritable(info.get_Precipitation()));
            w.setSolarRadiation(new IntWritable(info.get_Solar_Radiation()));
            w.setSurfTemp(new FloatWritable(info.get_Surface_Temp()));
            w.setRelativeHumidity(new IntWritable(info.get_Relative_Humidity()));
            w.setSoilMoisture(new FloatWritable(info.get_Soil_Moisture_5()));
            w.setSoilTemp(new FloatWritable(info.get_Soil_Temperature_5()));
            w.setWetness(new IntWritable(info.get_Wetness()));
            w.setWind(new FloatWritable(info.get_Wind_Speed_1_5()));

            context.write(dl, w);


         }
    }
}