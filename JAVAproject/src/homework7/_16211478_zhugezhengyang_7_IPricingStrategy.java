package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time��2019��1��6�� ����9:01:16 
* class statement:
*/
public interface _16211478_zhugezhengyang_7_IPricingStrategy {

	double getSubTotal(_16211478_zhugezhengyang_7_SaleLineItem sLineItem);
	String getSimpNum();  //��ü򵥲��Եı��
	String getName();   //��������
	int getType();  //��������
	int getApplyType(); //������������
	String getelseinfo();  //�ۿ�/�������
	void setNum(String s);
	void setName(String s);
	void setType(int i);
	void setApplyType(int i);
	void setelseinfo(String s);
}
 