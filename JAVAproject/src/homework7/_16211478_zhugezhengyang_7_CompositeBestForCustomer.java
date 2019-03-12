package homework7;

import java.util.ArrayList;

/** 
* @author Tourist  E-mail: 
* @version time��2019��1��6�� ����8:56:46 
* class statement:
*/
public class _16211478_zhugezhengyang_7_CompositeBestForCustomer extends _16211478_zhugezhengyang_7_CompositeStrategy
{
	public _16211478_zhugezhengyang_7_CompositeBestForCustomer(String num,String name,int type,int apply,ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy> stra)
	{
		super(num,name,type,apply,stra);
	}
	public _16211478_zhugezhengyang_7_CompositeBestForCustomer(String num,String name,int type,int apply,String componame)
	{
		super(num,name,type,apply,componame);
	}

	
	public double getSubTotal(_16211478_zhugezhengyang_7_SaleLineItem sLineItem) //�ҵ������������С��ֵ
	{
		double minPrice=100000000;
		int minTagIndex=-1;
		for(int i=0;i<strategies.size();i+=1)
		{
			double nowStratgyPrice=strategies.get(i).getSubTotal(sLineItem);
			if(nowStratgyPrice<minPrice)
			{
				minTagIndex=i;
				minPrice=nowStratgyPrice;
			}
				
		}
		return minPrice;
	}

}
 