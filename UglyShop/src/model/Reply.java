package model;

public class Reply {
	private int replyID;
	private String farmID;
	private String replyContant;
	private int reviewID;
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}
	
	public Reply(String farmID, String replyContant, int reviewID) {
		// 리뷰덧글테스트툥
		super();
		this.farmID = farmID;
		this.replyContant = replyContant;
		this.reviewID = reviewID;
	}


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
	public String getReplyContant() {
		return replyContant;
	}
	public void setReplyContant(String replyContant) {
		this.replyContant = replyContant;
	}
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	@Override
	public String toString() {
		return "Reply [ replyID=" + replyID + ", farmID=" + farmID + ", replyContant=" + replyContant + ", reviewID=" + reviewID + " ]";
	}
	
}
