package edu.usfca.cs.mr.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Stream;

/**
 * This is the main class. Hadoop will invoke the main method of this class.
 */
public class WordCountJob {
    public static void main(String[] args) {

        try {
            Configuration conf = new Configuration();

            /* Job Name. You'll see this in the YARN webapp */
            Job job = Job.getInstance(conf, "word count job");

            /* Current class */
            job.setJarByClass(WordCountJob.class);

            /* Mapper class */
            job.setMapperClass(WordCountMapper.class);

            /* Combiner class. Combiners are run between the Map and Reduce
             * phases to reduce the amount of output that must be transmitted.
             * In some situations, we can actually use the Reducer as a Combiner
             * but ONLY if its inputs and ouputs match up correctly. The
             * combiner is disabled here, but the following can be uncommented
             * for this particular job:
            //job.setCombinerClass(Question1Reducer.class);

            /* Reducer class */
            job.setReducerClass(WordCountReducer.class);

            /* Outputs from the Mapper. */
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            /* Outputs from the Reducer */
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            /* Reduce tasks */
            job.setNumReduceTasks(1);

            /* Job input path in HDFS */

//            Path test1 = new Path("test_file.txt");
//            FileInputFormat.addInputPath(job, test1);
            System.out.println("test");


//            Path wc = new Path(args[0]);
//            try (Stream<Path> paths = Files.walk(wc)) {
//
//                paths.forEach(p -> {
//                    try {
//
//
//                    } catch (RejectedExecutionException e) {
//                        System.out.println("Queue has been closed");
//                    } catch (IOException ioe) {
//                        System.out.println("IOException");
//                    }
//                });
//
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }

            FileInputFormat.setInputDirRecursive(job, true);
            FileInputFormat.addInputPath(job, new Path(args[0]));



            /* Job output path in HDFS. NOTE: if the output path already exists
             * and you try to create it, the job will fail. You may want to
             * automate the creation of new output directories here */
//            Path test1Output = new Path("output_test_file.txt");
//            FileOutputFormat.setOutputPath(job, test1Output);

            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            /* Wait (block) for the job to complete... */
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
