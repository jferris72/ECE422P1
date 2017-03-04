import java.lang.Thread;
import java.util.Scanner;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;




public class DataSorter {

    public static void main (String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        float failurePrimary = Float.parseFloat(args[2]);
        float failureBackup = Float.parseFloat(args[3]);
        float timeLimit = Float.parseFloat(args[4]);

        PrimaryThread pThread = new PrimaryThread("pThread", inputFile, failurePrimary);
        BackupThread bThread = new BackupThread("bThread");
        WatchDogThread wdThread = new WatchDogThread("WatchDogThread", pThread, bThread);
    }
}

class PrimaryThread extends Thread {
	private String fileName;
	private float fail;
	private int hazard;

	PrimaryThread() {}

	PrimaryThread(String threadName, String fileName, float fail) {
		super(threadName);
		this.fileName = fileName;
		this.fail = fail;
		start();
	}

	public void run() {
		hazard = 0;
		Random generator = new Random();
		double failCheck = generator.nextDouble();

		System.out.println(Thread.currentThread().getName());
		System.out.println(fileName);

		try {

			int i = 0;
			Scanner scanner = new Scanner(new File(fileName));
			while(scanner.hasNextInt()) {
		        i++;
		        scanner.nextInt();
		        hazard++;
			}

			int [] arr = new int [i];
			i = 0;
			scanner = new Scanner(new File(fileName));

			while(scanner.hasNextInt()) {
		        arr[i++] = scanner.nextInt();
		        hazard++;
			}

			if(failCheck < 0.5 + (fail*hazard) && failCheck > 0.5) {
				throw new Exception();
			}

			HeapSort.sort(arr);

			for(int j = 0; j < i; j++) {
				System.out.println(Integer.toString(arr[j]));
			}
		} 

		catch (ThreadDeath td) {
			System.out.println("thread died");
		}

		catch (Exception e) {
			System.out.println("Failed to read from file");
		}
		// HeapSort heapSort = new HeapSort(fileName);
	}
}

class BackupThread extends Thread {

	BackupThread() {}

	BackupThread(String threadName) {
		super(threadName);
		start();
	}

	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}

class WatchDog extends TimerTask {

	private Thread watched;

	WatchDog() {}

	WatchDog (Thread target){
		// Constructor sets the class variable 'watched'
		watched = target;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		watched.stop();
		System.out.println("You're dead!");
	}

}

class WatchDogThread extends Thread {
	private Thread watchedThreadP;
	private Thread watchedThreadB;

	WatchDogThread() {}

	WatchDogThread(String threadName, Thread watchedThreadP, Thread watchedThreadB) {
		super(threadName);
		this.watchedThreadP = watchedThreadP;
		this.watchedThreadB = watchedThreadB;
		start();
	}

	public void run() {
		System.out.println(Thread.currentThread().getName());
		WatchDog wdP = new WatchDog(watchedThreadP);
		WatchDog wdB = new WatchDog(watchedThreadB);

		Timer tP = new Timer();
		Timer tB = new Timer();

		tP.schedule(wdP, 1000);
		tB.schedule(wdB, 1000);
	}
}

