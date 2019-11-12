package edu.usfca.cs.mr.Question3;

import edu.usfca.cs.mr.Customs.DateLocation;
import edu.usfca.cs.mr.Customs.Weather;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class BayAreaEquivalentReducer2
        extends Reducer<DateLocation, Weather, Text, Text> {


    @Override
    protected void reduce(DateLocation
                                  key, Iterable<Weather> values, Context context) throws IOException, InterruptedException {


        int count = 0;
        float windtotal = 0;
        float airTempTotal = 0;
        int humidTotal = 0;
        float precipTotal = 0;

        for (Weather w: values) {
            windtotal = windtotal + w.getWind().get();
            airTempTotal = airTempTotal + w.getAirTemp().get();
            humidTotal = humidTotal + w.getRelativeHumidity().get();
            precipTotal = precipTotal + w.getPrecipitation().get();
            count = count + 1;
        }


        float windAve = windtotal/count;
        float airTempAve = airTempTotal/count;
        float humidAve = humidTotal/count;
        float precipAve = precipTotal/count;

//        System.out.println("------------------");
//        System.out.println(windAve);
//        System.out.println(airTempAve);
//        System.out.println(humidAve);
//        System.out.println(precipAve);
//        System.out.println(count);

        if (checkWindThreshold(windAve) && checkAirTempThreshold(airTempAve) && checkHumidThreshold(humidAve)) {
            context.write(new Text(key.toString()), new Text(String.valueOf(windAve) + "," +
                    String.valueOf(airTempAve) + ", " + String.valueOf(humidAve) + ", " + String.valueOf(precipAve)));
        }
    }


    private boolean checkWindThreshold(float wind) {

        if (wind >= 1.6 && wind <= 3.5) return true;
        return false;
    }

    private boolean checkAirTempThreshold(float airTemp) {

        if (airTemp >= 8 && airTemp <= 16) return true;
        return false;
    }

    private boolean checkHumidThreshold(float humid) {

        if (humid >= 80 && humid <= 90) return true;
        return false;
    }

    private boolean checklPrecipThreshold(float precip) {
        return true;
    }

}



//    float maxWind = Float.MIN_VALUE;
//        float minWind = Float.MAX_VALUE;
//        float maxAirTemp = Float.MIN_VALUE;
//        float minAirTemp = Float.MAX_VALUE;
//        int maxHumid = Integer.MIN_VALUE;
//        int minHumid = Integer.MAX_VALUE;
//        float maxPrecip = Integer.MIN_VALUE;
//        float minPrecip = Integer.MAX_VALUE;
//
//        for (Weather w: values) {
//            maxWind = Math.max(maxWind, w.getWind().get());
//            minWind = Math.min(minWind, w.getWind().get());
//            maxAirTemp = Math.max(maxAirTemp, w.getAirTemp().get());
//            minAirTemp = Math.min(minAirTemp, w.getAirTemp().get());
//            maxHumid = Math.max(maxHumid, w.getRelativeHumidity().get());
//            minHumid = Math.min(minHumid, w.getRelativeHumidity().get());
//            maxPrecip = Math.max(maxPrecip, w.getPrecipitation().get());
//            minPrecip = Math.min(minPrecip, w.getPrecipitation().get());
//        }
//
//        float maxWindThreshold = 15f;
//        float maxAirTempThreshold = 32.f;
//        float minAirTempThreshold = -1.0f;
//        float maxHumidThreshold = 100f;
//        float minHumidThreshold = 10.0f;
//        float maxPrecipThreshold = 1328;
//        float minPrecipThreshold = 0;
//
//        System.out.println("Reduce");
//
//        if (maxWind <= maxWindThreshold && maxAirTemp <= maxAirTempThreshold && minAirTemp >= minAirTempThreshold && maxHumid <= maxHumidThreshold && minHumid >= minHumidThreshold
//                && maxPrecip <= maxPrecipThreshold && minPrecip >= minPrecipThreshold) {
//
//            String windMax = String.valueOf(maxWind);
//            String windMin = String.valueOf(minWind);
//            String airTempMax = String.valueOf(maxAirTemp);
//            String airTempMin = String.valueOf(minAirTemp);
//            String humidMax = String.valueOf(maxHumid);
//            String humidMin = String.valueOf(minHumid);
//            String precipMax = String.valueOf(maxPrecip);
//            String precipMin = String.valueOf(minPrecip);
//
//
//            context.write(new Text(key.toString()),
//                    new Text(
//                            windMax + ", " +
//                                    windMin + ", " +
//                                    airTempMax + ", " +
//                                    airTempMin + ", " +
//                                    humidMax + ", " +
//                                    humidMin + ", " +
//                                    precipMax + ", " +
//                                    precipMin));
//
//        }