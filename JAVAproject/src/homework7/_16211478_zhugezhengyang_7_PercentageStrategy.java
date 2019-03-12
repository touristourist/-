package homework7;

import java.text.DecimalFormat;

import org.w3c.dom.NameList;

/** 
* @author Tourist  E-mail: 
* @version time��2019��1��6�� ����8:53:57 
* class statement:
*/
public class _16211478_zhugezhengyang_7_PercentageStrategy implements _16211478_zhugezhengyang_7_IPricingStrategy
{
	public String number;  //���Ա��
	public String name;   //��������
	public int type;  //1-����ֵ��2-�ٷֱȣ�3-���
	public int applyBookType;
	public double discount;
	
	public _16211478_zhugezhengyang_7_PercentageStrategy(String num,String name,int type,int apply,double disP)
	{
		this.number=num;
		this.name=name;
		this.type=type;
		this.applyBookType=apply;
		this.discount=disP;
	}
	
	public double getSubTotal(_16211478_zhugezhengyang_7_SaleLineItem sLineItem) //�����Żݺ�۸�
	{
		DecimalFormat df=new DecimalFormat("0.00");
		int bookcount=sLineItem.getCopies();
		double simPrice=sLineItem.getBookSpec().getPrice();
		double re=(simPrice*(100.0-discount)/100.0)*bookcount;
		return Double.parseDouble(df.format(re));
	}

	public String getSimpNum() 
	{
		return number;  //���ز��Ա��
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public int getApplyType() {
		return applyBookType;
	}

	public String getelseinfo() {
		return String.valueOf(discount);
	}

	public void setNum(String s) {
		this.number=s;
		
	}

	public void setName(String s) {
		this.name=s;
		
	}

	public void setType(int i) {
		this.type=i;
		
	}

	public void setApplyType(int i) {
		this.applyBookType=i;
		
	}

	public void setelseinfo(String s) {
		this.discount=Double.parseDouble(s);	
	}

	
}
 