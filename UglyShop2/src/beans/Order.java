package beans;

public class Order {
	private String userID;
	private int prodID;
	private int prodPrice;
	private String prodName;
	private int orderQuantity;
	private String farmID;
	private String is_status;
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public int getProdID() {
		return prodID;
	}
	public void setProdID(int prodID) {
		this.prodID = prodID;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getFarmID() {
		return farmID;
	}
	public void setFarmID(String farmID) {
		this.farmID = farmID;
	}
	
	public String getIs_status() {
		return is_status;
	}
	public void setIs_status(String is_status) {
		this.is_status = is_status;
	}
	@Override
	public String toString() {
		return "Order [userID=" + userID + ", prodID=" + prodID + ", prodPrice=" + prodPrice + ", prodName=" + prodName
				+ ", orderQuantity=" + orderQuantity + ", farmID=" + farmID + ", is_status=" + is_status + "]";
	}
	
	
	
}
