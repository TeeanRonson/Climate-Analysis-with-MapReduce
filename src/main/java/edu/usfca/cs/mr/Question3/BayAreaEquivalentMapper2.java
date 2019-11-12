package edu.usfca.cs.mr.Question3;

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

public class BayAreaEquivalentMapper2
        extends Mapper<LongWritable, Text, DateLocation, Weather> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //Set up Data
        InfoGrabber info = new InfoGrabber(value.toString());

        //Find the Weather of the Bay Area over time
        //Find ranges from Air Temperature, Humidity, Wind, Precipitation
        //Save ranges in Reducer
        //Pass whole dataset into MR, if we find a region with weather similar to BayArea, write to output
        //For all other regions, if the wind, airtemp, humidity and precipiration is within the bay area range. Then print out
        String location = Geohash.encode(info.get_Latitude(), info.get_Longitude(), 3);
        Weather weather = new Weather();
        DateLocation dl = new DateLocation();

        if (!location.equals(CONSTANTS.BAYAREA) && !location.equals(CONSTANTS.BAYAREA1) && !location.equals(CONSTANTS.BAYAREA2) && !location.equals(CONSTANTS.BAYAREA3) && !location.equals(CONSTANTS.BAYAREA4)) {

            dl.set(location, info.getUTCDateByMonth());

            weather.setWind(new FloatWritable(info.get_Wind_Speed_1_5()));
            weather.setAirTemp(new FloatWritable(info.get_Air_Temp()));
            weather.setRelativeHumidity(new IntWritable(info.get_Relative_Humidity()));
            weather.setPrecipitation(new FloatWritable(info.get_Precipitation()));

//            System.out.println(location + " " + weather.getPrecipitation() + " " + weather.getAirTemp() +
//                    " " + weather.getRelativeHumidity() +
//                    " " +
//                    weather.getWind());

            if (info.get_Wind_Speed_1_5() != CONSTANTS.INTTWO9 && info.get_Air_Temp() != CONSTANTS.FLOATFOUR9 && info.get_Relative_Humidity() != CONSTANTS.INTFOUR9
                    && info.get_Precipitation() != CONSTANTS.FLOATFOUR9) {
                context.write(dl, weather);
            }

        }
    }
}