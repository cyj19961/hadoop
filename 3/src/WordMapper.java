import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.analysis.tokenattributes.*;


import java.util.regex.*;


import java.io.*;
import java.util.*;

public class WordMapper
extends Mapper<Object, Text, Text, Text>
{
    @Override
    public void run(Context context) throws IOException, InterruptedException {
		StringBuilder stringBuilder = new StringBuilder();
        while (context.nextKeyValue()) {
            stringBuilder.append(" ").append(context.getCurrentValue().toString());
            String[] split = stringBuilder.toString().split("\\p{Punct}\\s*");
            if (split.length > 1) {
                for (int i = 0; i < split.length - 1; i++) {
                    map(context.getCurrentKey(), new Text(split[i]), context);
                }
                stringBuilder = new StringBuilder(split[split.length - 1]);
            }
        }
        map(new Object(), new Text(stringBuilder.toString()), context);
    }

    public void map(Object key, Text value, Context context)
	throws IOException, InterruptedException
	{
        Reader reader = new StringReader(value.toString());
        Analyzer analyzer = new StandardAnalyzer();

        TokenStream stream = analyzer.tokenStream(null, reader);

        stream.reset();

        String prev_word;
        String cur_word;

        if (stream.incrementToken()) {
            prev_word = stream
                    .getAttribute(CharTermAttribute.class)
                    .toString();
            while (stream.incrementToken()) {
                cur_word = stream
                    .getAttribute(CharTermAttribute.class)
                    .toString();

                context.write(new Text(prev_word), new Text(cur_word));
                prev_word = cur_word;
            }
        }

        stream.end();
        stream.close();
    }
}
