package java_project1;

public class cart {
	private int productNo;
	private int prodQty;
	private String productName;
	public cart(int productNo, int prodQty) {
		super();
		this.productNo = productNo;
		this.prodQty = prodQty;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getProdQty() {
		return prodQty;
	}
	public void setProdQty(int prodQty) {
		this.prodQty = prodQty;
	}
	/*public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}*/
	
	

}
