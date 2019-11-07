package edu.usfca.cs.mr.Question1;

import edu.usfca.cs.mr.Customs.DateLocation;
import edu.usfca.cs.mr.util.CONSTANTS;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer: Input to the reducer is the output from the mapper. It receives
 * word, list<count> pairs.  Sums up individual counts per given word. Emits
 * <word, total count> pairs.
 */
public class AirTempReducer
extends Reducer<DateLocation, FloatWritable, Text, Text> {

    @Override
    protected void reduce(DateLocation key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {

        //for each air temp we have
        //find the highest and lowest air temp
        float highestTemp = Float.MIN_VALUE;
        float lowestTemp = Float.MAX_VALUE;

        for (FloatWritable f: values) {
            if (f.get() != CONSTANTS.MISSING1DEC) {
                highestTemp = Math.max(highestTemp, f.get());
                lowestTemp = Math.min(lowestTemp, f.get());
            }
        }

        System.out.println(highestTemp);
        System.out.println(lowestTemp);
//        Text outputKey = new Text(String.valueOf(key.getTime()) + " " + key.getLocation());

        context.write(new Text(key.getTime() + " " + key.getLocation()), new Text("Highest: " + String.valueOf(highestTemp)));
        context.write(new Text(key.getTime() + " " + key.getLocation()), new Text("Lowest: " + String.valueOf(lowestTemp)));
    }

}
















