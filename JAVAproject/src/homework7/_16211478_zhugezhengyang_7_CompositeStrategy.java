package homework7;

import java.util.ArrayList;
import java.util.List;

/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:56:09 
* class statement:
*/
public class _16211478_zhugezhengyang_7_CompositeStrategy implements _16211478_zhugezhengyang_7_IPricingStrategy
{
	//存放所有放入组合的策略,存放discount-1和discount-3
	public List<_16211478_zhugezhengyang_7_IPricingStrategy> strategies=new ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy>();
	public String number;  //策略编号
	public String name;   //策略名称
	public int type;  //1-绝对值，2-百分比，3-组合 
	public int applyBookType;
	public String compoName;
	
	public _16211478_zhugezhengyang_7_CompositeStrategy(String num,String name,int type,int apply,ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy> stra)
	{
		this.number=num;
		this.name=name;
		this.type=type;
		this.applyBookType=apply;
		this.strategies=stra;
		compoName="";
		for(int i=0;i<strategies.size();i+=1)
		{
			String simpNum=strategies.get(i).getSimpNum();
			if(i>0)
				compoName+='|';
			compoName+=simpNum;
		}
	}
	public _16211478_zhugezhengyang_7_CompositeStrategy(String num,String name,int type,int apply,String componame)
	{
		this.number=num;
		this.name=name;
		this.type=type;
		this.applyBookType=apply;
//		this.strategies=stra;
		this.compoName=componame;
//		for(int i=0;i<strategies.size();i+=1)
//		{
//			String simpNum=strategies.get(i).getSimpNum();
//			if(i>0)
//				compoName+='|';
//			compoName+=simpNum;
//		}
	}

	public void add(_16211478_zhugezhengyang_7_IPricingStrategy iPrStr)
	{
		strategies.add(iPrStr);
	}
	
	
	public double getSubTotal(_16211478_zhugezhengyang_7_SaleLineItem sLineItem) {
		//交由CompositeBsetForCustomer实现
		return 0;
	}

	public String getSimpNum() { //对于复杂策略返回其组合的名字
		
		return number;
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
		return compoName;
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
		this.compoName=s;
		
	}

}
 