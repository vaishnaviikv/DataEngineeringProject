import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PageRankIter {

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("Usage: PageRankIter <input_directory> <output_directory> <iterations>");
            System.exit(-1);
        }

        String inputDir = args[0];
        String outputDir = args[1];
        int totalIterations = Integer.parseInt(args[2]);

        for (int iteration = 1; iteration <= totalIterations; iteration++) {
            Configuration conf = new Configuration();
            Job job = Job.getInstance(conf, "PageRank Computation - Iteration " + iteration);
            job.setJarByClass(PageRankIter.class);

            FileInputFormat.addInputPath(job, new Path(inputDir));
            FileOutputFormat.setOutputPath(job, new Path(outputDir + "/iteration_" + iteration));

            job.setMapperClass(PageRankMapper.class);
            job.setReducerClass(PageRankReducer.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            job.setNumReduceTasks(1);
            job.waitForCompletion(true);

            inputDir = outputDir + "/iteration_" + iteration + "/part-r-00000";
        }
    }
}
