package edu.usfca.cs.mr.Question7;

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
public class Question7Reducer
        extends Reducer<DateLocation, Weather, Text, Text> {



    @Override
    protected void reduce(DateLocation key, Iterable<Weather> values, Context context) throws IOException, InterruptedException {

        //monthly
        float totalTemp = 0;
        float totalPrecip = 0;
        int totalSolarRad = 0;
        float totalSurfTemp = 0;
        int totalHumid = 0;
        float totalSoilMois = 0;
        float totalSoilTemp = 0;
        int totalWetness = 0;
        float totalWindSpeed = 0;

        int tempCount = 0;
        int precipCount = 0;
        int solarRadCount = 0;
        int surfTempCount = 0;
        int humidCount = 0;
        int soilMoisCount = 0;
        int soilTempCount = 0;
        int wetnessCount = 0;
        int windCount = 0;

        for (Weather f: values) {

            if (f.getAirTemp().get() != 0) {
                totalTemp += f.getAirTemp().get();
                tempCount += 1;
            }

            if (f.getPrecipitation().get() != 0) {
                totalPrecip += f.getPrecipitation().get();
                precipCount += 1;
            }

            if (f.getSolarRadiation().get() != 0) {
                totalSolarRad += f.getSolarRadiation().get();
                solarRadCount += 1;
            }

            if (f.getSurfTemp().get() != 0) {
                totalSurfTemp += f.getSurfTemp().get();
                surfTempCount += 1;
            }

            if (f.getRelativeHumidity().get() != 0) {
                totalHumid += f.getRelativeHumidity().get();
                humidCount += 1;
            }

            if (f.getSoilMoisture().get() != 0) {
                totalSoilMois += f.getSoilMoisture().get();
                soilMoisCount += 1;
            }

            if (f.getWetness().get() != 0) {
                totalSoilTemp += f.getWetness().get();
                wetnessCount += 1;
            }

            if (f.getWind().get() != 0) {
                totalWindSpeed += f.getWind().get();
                windCount += 1;
            }

        }

        float aveTemp = totalTemp / tempCount;
        float avePrecip = totalPrecip / precipCount;
        int aveSolarRad = totalSolarRad / solarRadCount;
        float aveSurfTemp = totalSurfTemp / surfTempCount;
        int aveHumid = totalHumid / humidCount;
        float aveSoilMois = totalSoilMois / soilMoisCount;
        float aveSoilTemp = totalSoilTemp / soilTempCount;
        int aveWetness = totalWetness / wetnessCount;
        float aveWindSpeed = totalWindSpeed / windCount;


        context.write(new Text(key.toString()), new Text(String.valueOf(aveTemp) + ", " +
                    String.valueOf(avePrecip) + ", " +
                    String.valueOf(aveSolarRad) + ", " +
                    String.valueOf(aveSurfTemp) + ", " +
                    String.valueOf(aveHumid) + ", " +
                    String.valueOf(aveSoilMois) + ", " +
                    String.valueOf(aveSoilTemp) + ", " +
                    String.valueOf(aveWetness) + ", " +
                    String.valueOf(aveWindSpeed)));

    }

}
