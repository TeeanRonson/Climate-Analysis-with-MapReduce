package edu.usfca.cs.mr.Question3;

import edu.usfca.cs.mr.Customs.DateLocation;
import edu.usfca.cs.mr.Customs.Weather;
import edu.usfca.cs.mr.util.CONSTANTS;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class BayAreaEquivalentReducer
        extends Reducer<DateLocation, Weather, Text, Text> {


    @Override
    protected void reduce(DateLocation key, Iterable<Weather> values, Context context) throws IOException, InterruptedException {

        int count = 0;
        float windtotal = 0;
        float airTempTotal = 0;
        int humidTotal = 0;
        float precipTotal = 0;

        for (Weather w: values) {
            windtotal += w.getWind().get();
            airTempTotal += w.getAirTemp().get();
            humidTotal += w.getRelativeHumidity().get();
            precipTotal += w.getPrecipitation().get();
            count = count + 1;
        }


        System.out.println(windtotal/count);
        System.out.println(airTempTotal/count);
        System.out.println(airTempTotal/count);
        System.out.println(precipTotal/count);

        context.write(new Text(key.toString()), new Text(String.valueOf(windtotal/count) + "," +
            String.valueOf(airTempTotal/count) + ", " + String.valueOf(humidTotal/count) + ", " + String.valueOf(precipTotal/count)));
    }
}






//    float maxWind = Float.MIN_VALUE;
////        float minWind = Float.MAX_VALUE;
////        float maxAirTemp = Float.MIN_VALUE;
////        float minAirTemp = Float.MAX_VALUE;
////        int maxHumid = Integer.MIN_VALUE;
////        int minHumid = Integer.MAX_VALUE;
////        float maxPrecip = Integer.MIN_VALUE;
////        float minPrecip = Integer.MAX_VALUE;
//
////        for (Weather w: values) {
////            maxWind = Math.max(maxWind, w.getWind().get());
////            minWind = Math.min(minWind, w.getWind().get());
////            maxAirTemp = Math.max(maxAirTemp, w.getAirTemp().get());
////            minAirTemp = Math.min(minAirTemp, w.getAirTemp().get());
////            maxHumid = Math.max(maxHumid, w.getRelativeHumidity().get());
////            minHumid = Math.min(minHumid, w.getRelativeHumidity().get());
////            maxPrecip = Math.max(maxPrecip, w.getPrecipitation().get());
////            minPrecip = Math.min(minPrecip, w.getPrecipitation().get());
////        }
//
////        String windMax = String.valueOf(maxWind);
////        String windMin = String.valueOf(minWind);
////        String airTempMax = String.valueOf(maxAirTemp);
////        String airTempMin = String.valueOf(minAirTemp);
////        String humidMax = String.valueOf(maxHumid);
////        String humidMin = String.valueOf(minHumid);
////        String precipMax = String.valueOf(maxPrecip);
////        String precipMin = String.valueOf(minPrecip);
//
//
//        context.write(new Text(key.toString()),
//                new Text(
//                windMax + ", " +
//                windMin + ", " +
//                airTempMax + ", " +
//                airTempMin + ", " +
//                humidMax + ", " +
//                humidMin + ", " +
//                precipMax + ", " +
//                precipMin));