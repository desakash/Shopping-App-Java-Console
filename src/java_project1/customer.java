package java_project1;

public class customer {
	private int custId;
	private String custName;
	private String mobNo;
	private String password;
	public customer(int custId, String custName, String mobNo, String password) {
		this.custId = custId;
		this.custName = custName;
		this.mobNo = mobNo;
		this.password = password;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
