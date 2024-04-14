import java.io.*;
import org.apache.hadoop.io.*;



public class TextIntWritable implements Writable {
    private String word;
    private int frequency;

    public TextIntWritable(String string, int value) {
        word = new String (string);
        frequency = value;
    }

    public void readFields(DataInput in)throws IOException {
        word = in.readUTF();
        frequency = in.readInt();
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(word);
        out.writeInt(frequency);
    }

    public String toString() {
        return (word + ": " + Integer.toString(frequency));
    }

}
