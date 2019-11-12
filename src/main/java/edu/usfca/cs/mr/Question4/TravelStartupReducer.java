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

        for (Weather w : values) {
            fahrenheit += 32 + (w.getAirTemp().get() * 9 / 5);
            relativeHumid += w.getRelativeHumidity().get();
            count = count + 1;
        }


        comfortIndex = (fahrenheit + relativeHumid)/(4*count);
        System.out.println(comfortIndex);

        if (isComfortable(comfortIndex)) {

            System.out.println(comfortIndex);
            context.write(new Text(key.toString()), new Text(String.valueOf(comfortIndex)));
        }

    }


    private boolean isComfortable(float comfortIndex) {

        if (comfortIndex >= 50) {
            return true;
        }

        return false;
    }




}




