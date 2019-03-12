package homework7;

import java.text.DecimalFormat;

import javax.swing.Spring;

/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:54:38 
* class statement:
*/
public class _16211478_zhugezhengyang_7_NoDiscountStrategy implements _16211478_zhugezhengyang_7_IPricingStrategy
{
	
	public double getSubTotal(_16211478_zhugezhengyang_7_SaleLineItem sLineItem) {
		DecimalFormat df=new DecimalFormat("0.00");
		int booknum=sLineItem.getCopies();
		double simprice=sLineItem.getBookSpec().price;
		return Double.parseDouble(df.format(booknum*simprice));
	}

	public String getSimpNum() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getApplyType() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getelseinfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNum(String s) {
		// TODO Auto-generated method stub
		
	}

	public void setName(String s) {
		// TODO Auto-generated method stub
		
	}

	public void setType(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setApplyType(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setelseinfo(String s) {
		// TODO Auto-generated method stub
		
	}

	
	
}
 