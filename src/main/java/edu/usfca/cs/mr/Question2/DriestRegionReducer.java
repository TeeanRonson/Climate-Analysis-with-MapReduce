package edu.usfca.cs.mr.Question2;

import edu.usfca.cs.mr.Customs.DateLocation;
import edu.usfca.cs.mr.Customs.DryOrWetWeather;
import edu.usfca.cs.mr.util.CONSTANTS;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.util.Collections;

/**
 * Reducer: Input to the reducer is the output from the mapper. It receives
 * word, list<count> pairs.  Sums up individual counts per given word. Emits
 * <word, total count> pairs.
 */
public class DriestRegionReducer
        extends Reducer<DateLocation, DryOrWetWeather, Text, Text> {



    @Override
    protected void reduce(DateLocation key, Iterable<DryOrWetWeather> values, Context context) throws IOException, InterruptedException {

        int wetness = Integer.MIN_VALUE;
        int soilMois = Integer.MAX_VALUE;
        int relHumid = Integer.MAX_VALUE;
        int soilTemp = Integer.MIN_VALUE;
        float precip = Float.MAX_VALUE;

        for (DryOrWetWeather f: values) {
            if (f.getWetness().get() != CONSTANTS.MISSINGWETNESS) {
                wetness = Math.max(f.getWetness().get(), wetness);
            }
            if (f.getSoilMoisture().get() != CONSTANTS.FOUR9S) {
                soilMois = Math.min(f.getSoilMoisture().get(), soilMois);
            }

            if (f.getRelativeHumidity().get() != CONSTANTS.FOUR9S) {
                relHumid = Math.min(f.getRelativeHumidity().get(), relHumid);
            }
            if (f.getSoilTemperature().get() != CONSTANTS.FOUR9S) {
                soilTemp = Math.max(f.getSoilTemperature().get(), soilTemp);
            }
            if (f.getPrecipitation().get() != CONSTANTS.FOUR9S) {
                precip = Math.min(f.getPrecipitation().get(), precip);
            }
        }

        context.write(new Text(key.toString()), new Text(String.valueOf(wetness) + ", " + String.valueOf(soilMois) + ", " +
                    String.valueOf(relHumid) + ", " + String.valueOf(soilTemp) + ", " + String.valueOf(precip)));
    }

}
