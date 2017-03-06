import java.lang.Thread;
import java.util.Scanner;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.io.PrintWriter;





public class DataSorter {
	static {
    	System.loadLibrary("insertsort");
    }

    public static void main (String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        float failurePrimary = Float.parseFloat(args[2]);
        float failureBackup = Float.parseFloat(args[3]);
        float timeLimit = Float.parseFloat(args[4]);



        //Get length of file by running through each int
        int length = 0;

        try {
			Scanner scanner = new Scanner(new File(inputFile));
			while(scanner.hasNextInt()) {
		       length++;
		       scanner.nextInt();
		    }
		} catch (Exception e) {
			System.out.println("failed to open file");
			return;
		}

	    int [] arr = new int [length];
	    int [] outarr = new int [length];

	    int j = 0;
	    try {
			Scanner scanner = new Scanner(new File(inputFile));

			while(scanner.hasNextInt()) {
			    arr[j++] = scanner.nextInt();
			}
		} catch (Exception e) {
			System.out.println("failed to open file");
		}

        PrimaryThread pThread = new PrimaryThread("pThread", inputFile, failurePrimary, length, arr);
        // WatchDogThread wdThread = new WatchDogThread("WatchDogThread", pThread, bThread);

        WatchDog wdP = new WatchDog(pThread);
		// WatchDog wdB = new WatchDog(bThread);

		Timer tP = new Timer();
		// Timer tB = new Timer();

		pThread.start();

		tP.schedule(wdP, 1000);
		// tB.schedule(wdB, 1000);

		try {
			pThread.join();
			tP.cancel();

			Random generator = new Random();
			double failCheck = generator.nextDouble();
			if(failCheck < 0.5 + (failurePrimary * pThread.getHazard()) && failCheck > 0.5) {
				throw new Exception();
			} else {
				outarr = pThread.getArray();
				PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
				for (int i = 0; i < length; i++) {
					writer.println(Integer.toString(outarr[i]));
				}
				writer.close();
			}
		}

		catch (Exception e) {
			System.out.println("Primary Exception");
			BackupThread bThread = new BackupThread("bThread", length, arr);
			WatchDog wdB = new WatchDog(bThread);
			Timer tB = new Timer();
			bThread.start();
			tB.schedule(wdB, 1000);
			try {
				bThread.join();
				tB.cancel();

				Random generator = new Random();
				double failCheck = generator.nextDouble();
				System.out.println("Hazard" + pThread.getHazard());
				if(failCheck < 0.5 + (failurePrimary * bThread.getHazard()) && failCheck > 0.5) {
					throw new Exception();
				} else {
					outarr = bThread.getArray();
					PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
					for (int i = 0; i < length; i++) {
						writer.println(Integer.toString(outarr[i]));
					}
					writer.close();
				}
			}

			catch (Exception e2) {
				System.out.println("System failed to sort file");
			}
		}
    }
}

class PrimaryThread extends Thread {
	private String fileName;
	private float fail;
	private int hazard;
	private int [] arr;
	private int length;

	PrimaryThread() {}

	PrimaryThread(String threadName, String fileName, float fail, int length, int[] arr) {
		super(threadName);
		this.fileName = fileName;
		this.fail = fail;
		this.length = length;
		this.hazard = 0;
		this.arr = arr;
	}

	public void run() {

		
		System.out.println(Thread.currentThread().getName());
		System.out.println(fileName);

		try {

			this.arr = new int [this.length];

			HeapSort heapSort = new HeapSort();

			heapSort.sort(arr);



			// for(int j = 0; j < i; j++) {
			// 	System.out.println(Integer.toString(arr[j]));
			// }

			this.hazard = heapSort.getHazard();
		} 

		catch (ThreadDeath td) {
			System.out.println("thread died");
		}

		catch (Exception e) {
			System.out.println("Failed to read from file");
		}
		// HeapSort heapSort = new HeapSort(fileName);
	}

	public int[] getArray() {
		return this.arr;
	}

	public int getHazard() {
		return this.hazard;
	}
}

class BackupThread extends Thread {
	private int hazard;
	private int length;
	private int[] arr;
	private int[] outarr;

	BackupThread() {}

	BackupThread(String threadName, int length, int[] arr) {
		super(threadName);
		this.hazard = 0;
		this.length = length;
		this.arr = arr;
		this.outarr = new int [length + 1];
	}

	public void run() {
		System.out.println(Thread.currentThread().getName());
		InsertionSort sort = new InsertionSort();
		this.outarr = sort.insertsort(this.arr, this.length);
		this.hazard = outarr[length+1];
	}

	public int getHazard() {
		return this.hazard;
	}

	public int[] getArray() {
		return this.arr;
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

// class WatchDogThread extends Thread {
// 	private Thread watchedThreadP;
// 	private Thread watchedThreadB;

// 	WatchDogThread() {}

// 	WatchDogThread(String threadName, Thread watchedThreadP, Thread watchedThreadB) {
// 		super(threadName);
// 		this.watchedThreadP = watchedThreadP;
// 		this.watchedThreadB = watchedThreadB;
// 		start();
// 	}

// 	public void run() {
// 		System.out.println(Thread.currentThread().getName());
// 		WatchDog wdP = new WatchDog(watchedThreadP);
// 		WatchDog wdB = new WatchDog(watchedThreadB);

// 		Timer tP = new Timer();
// 		Timer tB = new Timer();

// 		tP.schedule(wdP, 1000);
// 		tB.schedule(wdB, 1000);
// 	}
// }

