import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PageRankReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text page, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        double accumulatedRank = 0.0;
        String linkedPages = "";

        for (Text value : values) {
            String valStr = value.toString();
            if (valStr.startsWith("links:")) {
                linkedPages = valStr.substring(7);  // Extracts links after "links: "
            } else {
                accumulatedRank += Double.parseDouble(valStr);
            }
        }

        context.write(page, new Text(linkedPages + " " + accumulatedRank));
    }
}
