package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午9:01:16 
* class statement:
*/
public interface _16211478_zhugezhengyang_7_IPricingStrategy {

	double getSubTotal(_16211478_zhugezhengyang_7_SaleLineItem sLineItem);
	String getSimpNum();  //获得简单策略的编号
	String getName();   //策略名称
	int getType();  //策略类型
	int getApplyType(); //策略适用类型
	String getelseinfo();  //折扣/策略组合
	void setNum(String s);
	void setName(String s);
	void setType(int i);
	void setApplyType(int i);
	void setelseinfo(String s);
}
 