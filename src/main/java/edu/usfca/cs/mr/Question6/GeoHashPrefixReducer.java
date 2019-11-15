package edu.usfca.cs.mr.Question6;

import edu.usfca.cs.mr.Customs.DateLocation;
import edu.usfca.cs.mr.Customs.Weather;
import edu.usfca.cs.mr.util.CONSTANTS;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer: Input to the reducer is the output from the mapper. It receives
 * word, list<count> pairs.  Sums up individual counts per given word. Emits
 * <word, total count> pairs.
 */
public class GeoHashPrefixReducer
        extends Reducer<DateLocation, Weather, Text, Text> {



    @Override
    protected void reduce(DateLocation key, Iterable<Weather> values, Context context) throws IOException, InterruptedException {

        //monthly
        float maxTemp = Float.MIN_VALUE;
        float minTemp = Float.MAX_VALUE;
        float totalTemp = 0;
        float totalPrecip = 0;
        float averageTemp = 0;
        float averagePrecip = 0;
        int count = 0;

        for (Weather f: values) {
            maxTemp = Math.max(maxTemp, f.getAirTemp().get());
            minTemp = Math.min(minTemp, f.getAirTemp().get());
            totalTemp += f.getAirTemp().get();
            totalPrecip += f.getPrecipitation().get();
            count = count + 1;
        }

        averageTemp = totalTemp / count;
        averagePrecip = totalPrecip / count;


        context.write(new Text(key.toString()), new Text(String.valueOf(maxTemp) + ", " + String.valueOf(minTemp)
        + ", " + String.valueOf(averageTemp) + ", " + String.valueOf(averagePrecip)));
    }

}
