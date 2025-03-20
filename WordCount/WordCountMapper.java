import java.io.IOException;

import javax.naming.Context;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper
    extends Mapper<LongWritable, Text, Text, IntWritable> {

  private static final int MISSING = 9999;

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

   Text wordText = new Text();
   IntWritable one = new IntWritable(1);

for (String word : value.toString().toLowerCase().split("[^a-z]+")) {
    if (!word.isEmpty()) {
        wordText.set(word);
        context.write(wordText, one);
    }
}
  }
}
