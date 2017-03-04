import java.lang.Thread;

public class DataSorter {
    public static void main (String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        int failurePrimary = Integer.parseInt(args[2]);
        int failureBackup = Integer.parseInt(args[3]);
        int timeLimit = Integer.parseInt(args[4]);

        

    }
}

class PrimaryThread extends Thread {

	PrimaryThread(){}

	PrimaryThread(String threadName) {
		super(threadName);
		start();
	}

	public void run() {
		System.out.println(Thread.currentThread().getName());
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