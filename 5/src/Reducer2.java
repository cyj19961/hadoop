import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;

import java.io.*;
import java.util.*;

public class Reducer2
        extends Reducer<TextIntWritable, Text, Text, TextIntWritable>
{
    public void reduce(TextIntWritable key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException
    {

        for (Text text : values) {
            context.write(text, key);
        }
    }
}
