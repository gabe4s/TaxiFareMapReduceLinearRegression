package Hadoop;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// HadoopFinal Assignment
// Author: Brad Smith
// Date: Apr 22, 2016
// Class: CS200
// Email: brad.smith.1324@gmail.com

public class Hadoop {
	public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		conf.set("textinputformat.record.delimiter", "\n");
		
		Job job = Job.getInstance(conf, "linreg");
		job.setJarByClass(Hadoop.class);
		job.setMapperClass(MR1.Mapper1.class);
		job.setCombinerClass(MR1.Reducer1.class);
		job.setReducerClass(MR1.Reducer2.class);
//		job.setNumReduceTasks(1);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(TripWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.waitForCompletion(true);
	}
}
