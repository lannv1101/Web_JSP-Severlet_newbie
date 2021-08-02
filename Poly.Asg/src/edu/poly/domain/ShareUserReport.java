package edu.poly.domain;

import java.util.Date;

public class ShareUserReport {
 private String Sdname;
 private String Sdemail;
 private String Reemail;
 private Date SeDate;
 
 
public ShareUserReport() {
	
}


public ShareUserReport(String sdname, String sdemail, String reemail, Date seDate) {
	super();
	Sdname = sdname;
	Sdemail = sdemail;
	Reemail = reemail;
	SeDate = seDate;
}


public String getSdname() {
	return Sdname;
}


public void setSdname(String sdname) {
	Sdname = sdname;
}


public String getSdemail() {
	return Sdemail;
}


public void setSdemail(String sdemail) {
	Sdemail = sdemail;
}


public String getReemail() {
	return Reemail;
}


public void setReemail(String reemail) {
	Reemail = reemail;
}


public Date getSeDate() {
	return SeDate;
}


public void setSeDate(Date seDate) {
	SeDate = seDate;
}
 
 
}
