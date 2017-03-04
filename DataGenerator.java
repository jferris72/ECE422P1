public class DataGenerator {
	public static void main (String[] args) {
		String fileName = args[0];
		Int numberValues = Integer.parseInt(args[1]);
		writeFile(fileName, numberValues);

	}

	public void writeFile(String fileName, Int numberValues) {
		try {
			Random randomGenerator = new Random();
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			for (int i = 0; i < numberValues; i++) {
				int random = randomGenerator.nextInt();
				writer.println(String.parseInt(random));
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Failed to write to file");
		}
	}
}