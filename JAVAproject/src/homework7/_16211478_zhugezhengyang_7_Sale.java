package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:50:17 
* class statement:
*/

import java.util.ArrayList;

public class _16211478_zhugezhengyang_7_Sale implements _16211478_zhugezhengyang_7_Subject{
	
	public ArrayList<_16211478_zhugezhengyang_7_SaleLineItem> items;
	public ArrayList<_16211478_zhugezhengyang_7_Observer> observers;
	
	public _16211478_zhugezhengyang_7_Sale()
	{
		items=new ArrayList<_16211478_zhugezhengyang_7_SaleLineItem>();
		observers=new ArrayList<_16211478_zhugezhengyang_7_Observer>();			
	}
	
	public double getTotal() //获得此次购物最终的总价格
	{
		double sumPrice=0;
		for(int i=0;i<items.size();i+=1)
		{
			sumPrice+=items.get(i).getSubTotal();
		}
		return sumPrice;
	}

	public void registerObserver(_16211478_zhugezhengyang_7_Observer o)
	{
		observers.add(o);
		
	}

	public void removeRegister(_16211478_zhugezhengyang_7_Observer o) {
		if(!observers.isEmpty())
			observers.remove(o);
	}

	public void notifyObservers() {
		for(int i=0;i<observers.size();i+=1)
		{
			_16211478_zhugezhengyang_7_Observer tempob=observers.get(i);
			tempob.update(this);   //调用自己这个对象传给购物车界面  //?????????????????
		}
	}
}
 