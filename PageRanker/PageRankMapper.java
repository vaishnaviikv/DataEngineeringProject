import java.io.IOException;
import java.util.Arrays;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PageRankMapper extends Mapper<Object, Text, Text, Text> {

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] tokens = value.toString().split("\\s+");
        String currentNode = tokens[0];
        double rankValue = Double.parseDouble(tokens[tokens.length - 1]);
        int totalOutlinks = tokens.length - 2;

        if (totalOutlinks > 0) {
            double distributedRank = rankValue / totalOutlinks;
            String[] outgoingLinks = Arrays.copyOfRange(tokens, 1, totalOutlinks + 1);

            for (String link : outgoingLinks) {
                context.write(new Text(link), new Text(String.valueOf(distributedRank)));
            }

            context.write(new Text(currentNode), new Text("links: " + String.join(" ", outgoingLinks)));
        } else {
            context.write(new Text(currentNode), new Text("links: "));
        }
    }
}
