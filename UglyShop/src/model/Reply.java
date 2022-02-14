package model;

public class Reply {
	private int replyID;
	private String farmID;
	private Long replyContant;
	private int reviewID;
	
	
	public int getReplyID() {
		return replyID;
	}
	public void setReplyID(int replyID) {
		this.replyID = replyID;
	}
	public String getFarmID() {
		return farmID;
	}
	public void setFarmID(String farmID) {
		this.farmID = farmID;
	}
	public Long getReplyContant() {
		return replyContant;
	}
	public void setReplyContant(Long replyContant) {
		this.replyContant = replyContant;
	}
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
}
