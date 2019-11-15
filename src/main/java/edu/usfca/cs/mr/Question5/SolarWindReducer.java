package edu.usfca.cs.mr.Question5;

import edu.usfca.cs.mr.Customs.DateLocation;
import edu.usfca.cs.mr.Customs.Weather;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer: Input to the reducer is the output from the mapper. It receives
 * word, list<count> pairs.  Sums up individual counts per given word. Emits
 * <word, total count> pairs.
 */
public class SolarWindReducer
        extends Reducer<DateLocation, Weather, Text, Text> {

    @Override
    protected void reduce(DateLocation key, Iterable<Weather> values, Context context) throws IOException, InterruptedException {

        System.out.println("Reduce");
        int totalSolar = 0;
        float totalWind = 0;
        int count = 0;

        for (Weather f: values) {
            totalSolar += f.getSolarRadiation().get();
            totalWind += f.getWind().get();
            count = count + 1;
        }

        float aveSolar = totalSolar/count;
        float aveWind = totalWind/count;

        if (aveWind >= 6) {
            context.write(new Text(key.toString()), new Text(String.valueOf(aveSolar) + ", " +
                    String.valueOf(aveWind)));
        }

    }

}
