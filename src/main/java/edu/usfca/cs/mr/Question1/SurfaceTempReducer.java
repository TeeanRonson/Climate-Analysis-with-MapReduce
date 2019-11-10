package edu.usfca.cs.mr.Question1;

import edu.usfca.cs.mr.Customs.DateLocation;
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
public class SurfaceTempReducer
        extends Reducer<DateLocation, FloatWritable, Text, Text> {

    private float highestTemp = Float.MIN_VALUE;
    private float lowestTemp = Float.MAX_VALUE;



    @Override
    protected void reduce(DateLocation key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {

        for (FloatWritable f: values) {
            if (f.get() != CONSTANTS.TWO9S) {
                highestTemp = Math.max(highestTemp, f.get());
                lowestTemp = Math.min(lowestTemp, f.get());
            }
        }

//        System.out.println(highestTemp);
//        System.out.println(lowestTemp);
//        first.set(key.toString());

        context.write(new Text(key.toString()), new Text("Highest: " + String.valueOf(highestTemp)));
        context.write(new Text(key.toString()), new Text("Lowest: " + String.valueOf(lowestTemp)));
    }

}
