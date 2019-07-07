package t7;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class GitHubAnalyzerCmd {
	public static void main(String[]args) {
		//String filename = "reps.txt";
		File file = new File(args[0]);
		if(file.exists()) {
			System.out.println("Error while opening file "+ args[0]);
			System.exit(0);
		}
		ThreadData data = new ThreadData(new ArrayList<DataEntry>());
		RequesterThread requester = new RequesterThread(data,file);
		System.out.println("Getting Requests");
		requester.run();
		try {requester.join();} catch (InterruptedException e) { }
		
		System.out.println("Rep with the most commits is : "+getRepwithMostCommits(data.repdata));
		System.out.println("Rep with the least commits is: "+getRepwithLeastCommits(data.repdata));
		System.out.println("Rep with the newest commit is: "+getRepwithNewestCommit(data.repdata));
		System.out.println("Rep with the oldest commit is: "+getRepwithOldestCommit(data.repdata));


	}
	public static String getRepwithMostCommits(ArrayList<DataEntry> repdata) {
		int maiorn = -1;
		String maiorrep = "";
		for(DataEntry rep:repdata) {
			Integer n = new Integer(rep.getNumcommits());
			if(n > maiorn) {
				maiorn = n;
				maiorrep = rep.getRepname().toString();
			}
		}
		return maiorrep + "with "+ maiorn + "commits";
	}
	public static String getRepwithLeastCommits(ArrayList<DataEntry> repdata) {
		int menorn = -1;
		String menorep = "";
		for(DataEntry rep:repdata) {
			Integer n = new Integer(rep.getNumcommits());
			if(n < menorn || menorn == -1) {
				menorn = n;
				menorep = rep.getRepname().toString();
			}
		}
		return menorep + "with "+ menorn + "commits";
	}
	public static String getRepwithNewestCommit(ArrayList<DataEntry> repdata) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:'Z'"); //Formato de data do commit do git
		dateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
		String newestdate = "";
		String newestrep = "";
		for(DataEntry rep:repdata) {
			if(newestdate == "") {
				newestdate = rep.getNewestcommit().toString();
				newestrep = rep.getRepname().toString();
			}
			else{				
				try {
					Date repnewestdate = dateformat.parse(rep.getNewestcommit().toString());
					Date actualnewestdate = dateformat.parse(newestdate);
					if(actualnewestdate.before(repnewestdate)) {
						newestdate = rep.getNewestcommit().toString();
						newestrep = rep.getRepname().toString();
					}
					
				} catch (ParseException e) {System.out.println("Error while parsing date string "+ rep.getNewestcommit().toString());}
				
			}

		}
		return newestrep + ": "+ newestdate ;
	}
	public static String getRepwithOldestCommit(ArrayList<DataEntry> repdata) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:'Z'"); //Formato de data do commit do git
		dateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
		String oldestdate = "";
		String oldestrep = "";
		for(DataEntry rep:repdata) {
			if(oldestdate == "") {
				oldestdate = rep.getNewestcommit().toString();
				oldestrep = rep.getRepname().toString();
			}
			else{				
				try {
					Date repoldestdate = dateformat.parse(rep.getOldestcommit().toString());
					Date actualoldestdate = dateformat.parse(oldestdate);
					if(actualoldestdate.after(repoldestdate)) {
						oldestdate = rep.getNewestcommit().toString();
						oldestrep = rep.getRepname().toString();
					}
					
				} catch (ParseException e) {System.out.println("Error while parsing date string "+ rep.getNewestcommit().toString());}
				
			}

		}
		return oldestrep + ": "+ oldestdate ;
	
	
	}

}
