package edu.usfca.cs.mr.Question8;

import edu.usfca.cs.mr.Customs.DateLocation;
import edu.usfca.cs.mr.Customs.Weather;
import edu.usfca.cs.mr.util.CONSTANTS;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer: Input to the reducer is the output from the mapper. It receives
 * word, list<count> pairs.  Sums up individual counts per given word. Emits
 * <word, total count> pairs.
 */
public class SoybeanFuturesReducer
        extends Reducer<DateLocation, Weather, Text, Text> {


    //Average data across August for every year
    //Max
    //Min

    @Override
    protected void reduce(DateLocation key, Iterable<Weather> values, Context context) throws IOException, InterruptedException {

        float maxAirTemp = Float.MIN_VALUE;
        float maxSurfTemp = Float.MIN_VALUE;
        int precipitation = 0;
        float soilMoistureMax = Float.MIN_VALUE;
        float soilMoistureMin = Float.MAX_VALUE;
        float soilTempMax = Float.MIN_VALUE;
        float soilTempMin = Float.MAX_VALUE;
        int wetness = 0;
        float solarRadiation = 0;
        int count = 0;
        int solarError = 0;
        float airTemp = 0;
        float surfTemp = 0;

        for (Weather f: values) {
            maxAirTemp = Math.max(maxAirTemp, f.getAirTemp().get());
            maxSurfTemp = Math.max(maxSurfTemp, f.getSurfTemp().get());
            soilMoistureMax = Math.max(f.getSoilMoisture().get(), soilMoistureMax);
            soilMoistureMin = Math.min(f.getSoilMoisture().get(), soilMoistureMin);
            precipitation += f.getPrecipitation().get();
            soilTempMax = Math.max(f.getSoilTemp().get(), soilTempMax);
            soilTempMin = Math.min(f.getSoilTemp().get(), soilTempMin);
            wetness += f.getWetness().get();

            //Solar Radiation
            solarRadiation += f.getSolarRadiation().get();
            if (f.getSolarRadiation().get() == 0) solarError = solarError + 1;

            airTemp += f.getAirTemp().get();
            surfTemp += f.getSurfTemp().get();
            count = count + 1;
        }



        //TODO: Averages
        //Precipitation
        float precipAve = precipitation/count;


        //Wetness
        float wetnessAve = wetness/count;

        //Solar Radiation
        float solarRadAve = 0;
        if (solarRadiation > 0) {
            solarRadAve = solarRadiation/(count-solarError);
        }
        float airTempAve = airTemp/count;
        float surfTempAve = surfTemp/count;

        context.write(new Text(key.toString()), new Text(
                String.valueOf(maxAirTemp) + ", " +
                        String.valueOf(airTempAve) + ", " +
                        String.valueOf(maxSurfTemp) + ", " +
                        String.valueOf(surfTempAve) + ", " +
                String.valueOf(precipAve) + ", " +
                String.valueOf(soilMoistureMax) + ", " +
                String.valueOf(soilMoistureMin) + ", " +
                String.valueOf(soilTempMax) + ", " +
                String.valueOf(soilTempMin) + ", " +
                String.valueOf(wetnessAve) + ", " +
                String.valueOf(solarRadAve)));
    }
}