package edu.usfca.cs.mr.Question8;

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

public class SoybeanFuturesMapper
        extends Mapper<LongWritable, Text, DateLocation, Weather> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //Set up Data
        InfoGrabber info = new InfoGrabber(value.toString());
        DateLocation dl = new DateLocation();

        String location = Geohash.encode(info.get_Latitude(), info.get_Longitude(), 3);

//        if (CONSTANTS.OHIO.contains(location)) {

//        if (CONSTANTS.Minessota.contains(location)) {

//        if (CONSTANTS.NDakota.contains(location)) {

//        if (CONSTANTS.Nebraska.contains(location)) {

//        if (CONSTANTS.Missouri.contains(location)) {

        if (CONSTANTS.INDIANA.contains(location)) {
            dl.set(location, info.getUTC_Date());

            //clean data
            Weather w = new Weather();
            System.out.println(location);

            w.setSolarRadiation(new IntWritable(info.get_Solar_Radiation()));
            w.setPrecipitation(new FloatWritable(info.get_Precipitation()));
            w.setSoilMoisture(new FloatWritable(info.get_Soil_Moisture_5()));
            w.setSoilTemp(new FloatWritable(info.get_Soil_Temperature_5()));
            w.setAirTemp(new FloatWritable(info.get_Air_Temp()));
            w.setSurfTemp(new FloatWritable(info.get_Surface_Temp()));

            if (info.getExactMonth() == 8) {
                System.out.println(info.getExactMonth());
                context.write(dl, w);
            }
        }
    }
}