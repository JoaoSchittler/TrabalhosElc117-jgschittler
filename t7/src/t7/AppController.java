package t7;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javafx.collections.ObservableList;

public class AppController {

	private AppView view;
	public AppController(AppView view) {
		this.view = view;
	}


	public void enableFileChooser() {
		this.view.enableFileChooser();
	}

	public String getRepwithMostCommits(ObservableList<DataEntry> tabledata) {
		int maiorn = -1;
		String maiorrep = "";
		for(DataEntry rep:tabledata) {
			Integer n = new Integer(rep.getNumcommits());
			if(n > maiorn) {
				maiorn = n;
				maiorrep = rep.getRepname().toString();
			}
		}
		return maiorrep + " with "+ maiorn + "commits";
	}
	public String getRepwithLeastCommits(ObservableList<DataEntry> tabledata) {
		int menorn = -1;
		String menorep = "";
		for(DataEntry rep:tabledata) {
			Integer n = new Integer(rep.getNumcommits());
			if(n < menorn || menorn == -1) {
				menorn = n;
				menorep = rep.getRepname().toString();
			}
		}
		return menorep + " with "+ menorn + "commits";
	}
	public String getRepwithNewestCommit(ObservableList<DataEntry> tabledata) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:'Z'"); //Formato de data do commit do git
		dateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
		String newestdate = "";
		String newestrep = "";
		for(DataEntry rep:tabledata) {
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
	public String getRepwithOldestCommit(ObservableList<DataEntry> tabledata) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:'Z'"); //Formato de data do commit do git
		dateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
		String oldestdate = "";
		String oldestrep = "";
		for(DataEntry rep:tabledata) {
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
