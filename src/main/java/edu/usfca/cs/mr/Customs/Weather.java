package edu.usfca.cs.mr.Customs;

import com.sun.org.apache.bcel.internal.generic.FLOAD;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


/**
 * Wetness - High values (>= 1000) indicate an absence of moisture. Error value -9999
 * Soil Moisture [18] - Consider the lowest values since lowest value indicate low soil moisture and greater dryness. Error value with -99.000.
 * RELATIVE_HUMIDITY[16]  - Consider lowest values since lowest value indicate low relative humidity and greater dryness. Error value with -9999.
 * Soil Temperature [19] - Consider high values since higher values indicate dryness. Ignore values with -9999.0.
 * Precipitation [10] - Consider lower values since lower values indicate dryness. Ignore values with  -9999.0.
 */
public class Weather implements Writable {

    private IntWritable wetness;
    private IntWritable relativeHumidity;
    private FloatWritable precipitation;
    private FloatWritable soilMoisture;
    private FloatWritable soilTemp;
    private FloatWritable airTemp;
    private FloatWritable surfTemp;
    private FloatWritable wind;
    private IntWritable solarRadiation;

    public Weather() {
        this.wetness = new IntWritable();
        this.soilMoisture = new FloatWritable();
        this.relativeHumidity = new IntWritable();
        this.soilTemp = new FloatWritable();
        this.precipitation = new FloatWritable();
        this.airTemp = new FloatWritable();
        this.surfTemp = new FloatWritable();
        this.wind = new FloatWritable();
        this.airTemp = new FloatWritable();
        this.surfTemp = new FloatWritable();
        this.wind = new FloatWritable();
        this.solarRadiation = new IntWritable();
    }


    public void setPrecipitation(FloatWritable precipitation) {
        this.precipitation = precipitation;
    }

    public void setRelativeHumidity(IntWritable relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public void setSoilMoisture(FloatWritable soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public void setSoilTemp(FloatWritable soilTemp) {
        this.soilTemp = soilTemp;
    }

    public void setWetness(IntWritable wetness) {
        this.wetness = wetness;
    }

    public void setAirTemp(FloatWritable airTemp) {
        this.airTemp = airTemp;
    }

    public void setSurfTemp(FloatWritable surfTemp) {
        this.surfTemp = surfTemp;
    }

    public void setWind(FloatWritable wind) {
        this.wind = wind;
    }

    public void setSolarRadiation(IntWritable solarRadiation) {
        this.solarRadiation = solarRadiation;
    }

    public FloatWritable getSurfTemp() {
        return surfTemp;
    }

    public FloatWritable getSoilMoisture() {
        return soilMoisture;
    }

    public FloatWritable getSoilTemp() {
        return soilTemp;
    }

    public FloatWritable getPrecipitation() {
        return precipitation;
    }

    public IntWritable getRelativeHumidity() {
        return relativeHumidity;
    }

    public IntWritable getWetness() {
        return wetness;
    }

    public FloatWritable getAirTemp() {
        return airTemp;
    }

    public FloatWritable getWind() {
        return wind;
    }

    public IntWritable getSolarRadiation() {
        return solarRadiation;
    }

    @Override
    public String toString() {
        String wetness = String.valueOf(this.wetness.get());
        String precipitation = String.valueOf(this.precipitation.get());


        return wetness + ", " + ", " + precipitation;
    }


    @Override
    public void write(DataOutput out) throws IOException {
        wetness.write(out);
        soilTemp.write(out);
        relativeHumidity.write(out);
        soilMoisture.write(out);
        precipitation.write(out);
        airTemp.write(out);
        surfTemp.write(out);
        wind.write(out);
        solarRadiation.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        wetness.readFields(in);
        soilMoisture.readFields(in);
        relativeHumidity.readFields(in);
        soilTemp.readFields(in);
        precipitation.readFields(in);
        airTemp.readFields(in);
        surfTemp.readFields(in);
        wind.readFields(in);
        solarRadiation.readFields(in);
    }
}
