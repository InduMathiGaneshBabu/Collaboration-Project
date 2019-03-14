package com.collaboration.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notification
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int notificationid;
private String Blogtitle;
private String rejectionreason;
private String approvedorrejected;
@ManyToOne//cordinality
private UserDetails usertobenotified;
boolean viewed;
public int getNotificationid() {
	return notificationid;
}
public void setNotificationid(int notificationid) {
	this.notificationid = notificationid;
}
public String getBlogtitle() {
	return Blogtitle;
}
public void setBlogtitle(String blogtitle) {
	Blogtitle = blogtitle;
}
public String getRejectionreason() {
	return rejectionreason;
}
public void setRejectionreason(String rejectionreason) {
	this.rejectionreason = rejectionreason;
}
public String getApprovedorrejected() {
	return approvedorrejected;
}
public void setApprovedorrejected(String approvedorrejected) {
	this.approvedorrejected = approvedorrejected;
}
public UserDetails getUsertobenotified() {
	return usertobenotified;
}
public void setUsertobenotified(UserDetails usertobenotified) {
	this.usertobenotified = usertobenotified;
}
public boolean isViewed() {
	return viewed;
}
public void setViewed(boolean viewed) {
	this.viewed = viewed;
}



}
