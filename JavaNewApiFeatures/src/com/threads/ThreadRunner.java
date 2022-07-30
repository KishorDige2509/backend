package com.threads;

public class ThreadRunner{
	public static void main(String[] args) throws InterruptedException {
		Thread mainThread1;
		Thread mainThread2;
		
		mainThread1 = new Thread(new OldThread());
		mainThread2 = new Thread(() -> {System.out.println("New Thread by lambda");});
		
		
		mainThread1.start();
		mainThread2.start();	
			
		
		System.out.println(mainThread1.getState().toString());
		System.out.println(mainThread2.getState().toString());
		
//		mainThread2.join();
//		mainThread1.join();
		
		
		System.out.println("Main Thread");
	}
	
	
}

// Thread without lambda -- old way
class OldThread implements Runnable{

	@Override
	public void run() {
		System.out.println("I am old thread... running");		
	}
	
}
