package edu.usfca.cs.mr.Question4;

import edu.usfca.cs.mr.Customs.DateLocation;
import edu.usfca.cs.mr.Customs.Weather;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TravelStartupReducer
        extends Reducer<DateLocation, Weather, Text, Text> {


    @Override
    protected void reduce(DateLocation key, Iterable<Weather> values, Context context) throws IOException, InterruptedException {

        float comfortIndex = 0;
        int count = 0;
        float fahrenheit = 0;
        float relativeHumid = 0;
        float airTempTotal = 0;
        float solarRadiation = 0;
        float precipTotal = 0;

        for (Weather w : values) {
            fahrenheit += 32 + (w.getAirTemp().get() * 9 / 5);
            relativeHumid += w.getRelativeHumidity().get();
            airTempTotal += w.getAirTemp().get();
            precipTotal += w.getPrecipitation().get();
            count = count + 1;
        }


        comfortIndex = (fahrenheit + relativeHumid)/(4*count);
        float airTempAve = airTempTotal / count;
        float solarRadiationAve = solarRadiation / count;
        float precipAve = precipTotal / count;
        float humidAve = relativeHumid / count;

        context.write(new Text(key.toString()), new Text(String.valueOf(comfortIndex) + ", " + String.valueOf(airTempAve) + ", " + String.valueOf(solarRadiationAve)
                    + ", " + String.valueOf(precipAve) + ", " + String.valueOf(humidAve)));


    }


    private boolean isComfortable(float comfortIndex) {

        if (comfortIndex >= 50) {
            return true;
        }

        return false;
    }




}




