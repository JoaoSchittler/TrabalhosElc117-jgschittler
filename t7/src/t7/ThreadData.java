package t7;

import java.util.ArrayList;

public class ThreadData {
	ArrayList<DataEntry> repdata;
	public ThreadData(ArrayList<DataEntry> repdata) {
		this.repdata = repdata;
	}
	public synchronized void startrequest() {
		try {this.wait();} catch (InterruptedException e) {	}
	}
	public synchronized void endrequest() {
		this.notifyAll();
	}
}
