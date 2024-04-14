import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.analysis.tokenattributes.*;

import java.io.*;
import java.util.*;

public class Mapper2
        extends Mapper<LongWritable, Text, TextIntWritable, Text> {

    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

            String[] word_freq_str = value.toString().split("\t");

            int frequency = Integer.parseInt(word_freq_str[2]);
            TextIntWritable word_freq = new TextIntWritable(word_freq_str[1],
                                                            frequency);

            context.write(word_freq, new Text(word_freq_str[0]));
    }
}
