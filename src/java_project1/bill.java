package java_project1;

import java.util.Date;

public class bill {
	
	private int billNo;
	private double cgst;
	private double sgst;
	private double total;
	private double finalTotal;
	
	public bill(int billNo, double cgst, double sgst, double total, double finalTotal) {
		super();
		this.billNo = billNo;
		this.cgst = cgst;
		this.sgst = sgst;
		this.total = total;
		this.finalTotal = finalTotal;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public double getCgst() {
		return cgst;
	}

	public void setCgst(double cgst) {
		this.cgst = cgst;
	}

	public double getSgst() {
		return sgst;
	}

	public void setSgst(double sgst) {
		this.sgst = sgst;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getFinalTotal() {
		return finalTotal;
	}

	public void setFinalTotal(double finalTotal) {
		this.finalTotal = finalTotal;
	}
	
	

}
