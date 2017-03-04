import java.util.Random;
import java.io.Writer;
import java.io.FileWriter;


public class p1jferris {
    public static void main (String[] args) {
        String filename = args[0];
        int numValues = Integer.parseInt(args[1]);

        System.out.println(filename);
        System.out.println(numValues);
        
        DataGenerator dataGenerator = new DataGenerator(filename, numValues);
        dataGenerator.makeFile();
        
    }
}

class DataGenerator {
    String filename;
    int numValues;

    public DataGenerator(String filename, int numValues) {
        this.numValues = numValues;
        this.filename = filename;
    }
    public void makeFile() {
        try {
            Writer writer = new FileWriter(filename);
            Random randomGenerator = new Random();
            int next;
            String str;
            for(int i = 0; i < numValues; i++) {
                next = randomGenerator.nextInt();
                str = Integer.toString(next);
                writer.write(str);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("failed to make file");
        }
    }
}