package t7;


import javafx.beans.property.SimpleStringProperty;

public class DataEntry {
	private SimpleStringProperty  repname;
    private SimpleStringProperty  numcommits;
    private SimpleStringProperty  tammedmsg;
	private SimpleStringProperty  newestcommit;
	private SimpleStringProperty  oldestcommit;
    
    public DataEntry(String repname,String  numcommits,String tammedmsg, String firstcommitdata, String lastcommitdata) {
   	 	this.repname    = new SimpleStringProperty(repname);
    	this.numcommits = new SimpleStringProperty(numcommits);
   	 	this.tammedmsg  = new SimpleStringProperty(tammedmsg);
   	 	this.newestcommit = new SimpleStringProperty(firstcommitdata);
   	 	this.oldestcommit = new SimpleStringProperty(lastcommitdata);
    }
    
	public SimpleStringProperty repnamProperty() {return repname;	}
    public SimpleStringProperty numcommitsProperty() {return numcommits;}
    public SimpleStringProperty tammedmsgProperty() {return tammedmsg;}
    public SimpleStringProperty newestcommitProperty() {return newestcommit;}
    public SimpleStringProperty oldestcommitProperty() {return oldestcommit;}
    
	public String getRepname() {return repname.get();	}
    public String getNumcommits() { return numcommits.get(); }
    public String getTammedmsg() { return tammedmsg.get(); }
    public String getNewestcommit() { return newestcommit.get(); }
    public String getOldestcommit() { return oldestcommit.get(); }
    
    public void setNumcommits(String  numcommits) { this.numcommits.set(numcommits); }
    public void setTammedmsg(String  tammedmsg) { this.tammedmsg.set(tammedmsg); }
    public void setNewestcommit(String  newestcommit) { this.numcommits.set(newestcommit); }
    public void setOldestcommit(String  oldestcommit) { this.tammedmsg.set(oldestcommit); }
    public void setRepname(SimpleStringProperty repname) {this.repname = repname;}


}