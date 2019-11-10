package edu.usfca.cs.mr.Customs;

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
public class DryOrWetWeather implements Writable {

    private IntWritable wetness;
    private IntWritable soilMoisture;
    private IntWritable relativeHumidity;
    private IntWritable soilTemperature;
    private FloatWritable precipitation;

    public DryOrWetWeather() {
        this.wetness = new IntWritable();
        this.soilMoisture = new IntWritable();
        this.relativeHumidity = new IntWritable();
        this.soilMoisture = new IntWritable();
        this.precipitation = new FloatWritable();
    }

    public void set(Integer wetness, Integer soilMoisture, Integer relativeHumidity, Integer soilTemperature, Float precipitation) {

        this.wetness = new IntWritable(wetness);
        this.soilMoisture = new IntWritable(soilMoisture);
        this.relativeHumidity = new IntWritable(relativeHumidity);
        this.soilTemperature = new IntWritable(soilTemperature);
        this.precipitation = new FloatWritable(precipitation);
    }

    public FloatWritable getPrecipitation() {
        return precipitation;
    }

    public IntWritable getRelativeHumidity() {
        return relativeHumidity;
    }

    public IntWritable getSoilMoisture() {
        return soilMoisture;
    }

    public IntWritable getSoilTemperature() {
        return soilTemperature;
    }

    public IntWritable getWetness() {
        return wetness;
    }

    @Override
    public String toString() {
        String wetness = String.valueOf(this.wetness.get());
        String soilMoisture = String.valueOf(this.soilMoisture.get());
        String relativeHumidity = String.valueOf(this.relativeHumidity.get());
        String soilTemp = String.valueOf(this.soilTemperature.get());
        String precipitation = String.valueOf(this.precipitation.get());

        return wetness + ", " + soilMoisture + ", " + relativeHumidity + ", " + soilTemp + ", " + precipitation;
    }


    @Override
    public void write(DataOutput out) throws IOException {
        wetness.write(out);
        soilTemperature.write(out);
        relativeHumidity.write(out);
        soilMoisture.write(out);
        precipitation.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        wetness.readFields(in);
        soilMoisture.readFields(in);
        relativeHumidity.readFields(in);
        soilMoisture.readFields(in);
        precipitation.readFields(in);
    }
}
