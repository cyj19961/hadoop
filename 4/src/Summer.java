import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;

import java.io.*;
import java.util.*;

import pryazhennikov.hadoop_course.TextIntWritable;


public class Summer
extends Reducer<Text, Text, Text, TextIntWritable>
{
    public void reduce(Text key, Iterable<Text> values, Context context)
	throws IOException, InterruptedException
	{
        HashMap<Text, Integer> hash_map = new HashMap();
        Integer count;

        for(Text val :values)
        {
            count = hash_map.get(val);
            if (count == null) hash_map.put(val, new Integer(1));
            else hash_map.put(val, count + 1);
        }

        Text max_repeated_word = new Text("");
        count = 0;
        for (Map.Entry<Text, Integer> entry : hash_map.entrySet()){
            if ( entry.getValue() > count ) {
                count = entry.getValue();
                max_repeated_word = entry.getKey();
            }
        }

        TextIntWritable word_freq_str = new TextIntWritable(max_repeated_word.toString(),
                        count.intValue());
        context.write(key, word_freq_str);
    }
}
