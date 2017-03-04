import java.util.Random;
import java.io.PrintWriter;


public class DataGenerator {
	public static void main (String[] args) {
		String fileName = args[0];
		int numberValues = Integer.parseInt(args[1]);
		writeFile(fileName, numberValues);

	}

	public static void writeFile(String fileName, int numberValues) {
		try {
			Random randomGenerator = new Random();
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			for (int i = 0; i < numberValues; i++) {
				int random = randomGenerator.nextInt();
				writer.println(Integer.toString(random));
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("Failed to write to file");
		}
	}
}