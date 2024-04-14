import java.util.*;


public class TextIntWritableComparator implements Comparator<TextIntWritable>{

    public int compare(TextIntWritable t1, TextIntWritable t2) {
        return -t1.compareTo(t2);
    }

}
