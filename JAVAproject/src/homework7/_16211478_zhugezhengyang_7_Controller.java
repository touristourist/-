package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:48:19 
* class statement:
*/

import java.awt.print.Book;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

public class _16211478_zhugezhengyang_7_Controller {
	
	public static _16211478_zhugezhengyang_7_BookCatalog bookcat=new _16211478_zhugezhengyang_7_BookCatalog();  //所有书籍
	public static _16211478_zhugezhengyang_7_StrategyCatalog stracat=new _16211478_zhugezhengyang_7_StrategyCatalog();  //所有策略
	public static _16211478_zhugezhengyang_7_Sale sale=new _16211478_zhugezhengyang_7_Sale();
	public static _16211478_zhugezhengyang_7_ShoppingCarUI cartui;
	
	public static _16211478_zhugezhengyang_7_SaleLineItem tempslineItem;
	
	public static void addBook(_16211478_zhugezhengyang_7_BookSpecification book)
	{
		bookcat.books.add(book);
	}
	public static void addCompositeStrategy(int booktype,_16211478_zhugezhengyang_7_CompositeBestForCustomer compo)
	{
		//增加对应booktype的复杂策略
		stracat.strategies.put(booktype, compo);
	}
	public static void addSimpleStrategy(int booktype,_16211478_zhugezhengyang_7_IPricingStrategy simp)
	{
		//要传入booktype和简单策略
		stracat.strategies.put(booktype, simp);
	}
	//根据书的类型删除
	public static void deleteStrategy(int type)
	{
		stracat.strategies.remove(type);
	}
	//根据策略编号删除,遍历hashmap删除对应项
	public static void deleteStrategy(String num)
	{
		Iterator<Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy> > it=stracat.strategies.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy> entry=it.next();
			if(entry.getValue().getSimpNum()==num)
				it.remove();
		}
	}
	//修改原来booktype的策略为pristr
	public static void updateStrategy(int booktype,_16211478_zhugezhengyang_7_IPricingStrategy pristr)
	{
		stracat.strategies.put(booktype, pristr);
	}
	//向当前的sale中加入新的saleitem
	public static void buyBook(String isbn,int copies)
	{
		//对于相同的书合并，不同的添加
		int booktype;
		
		_16211478_zhugezhengyang_7_BookSpecification tempbook=bookcat.getBookSpecification(isbn);
		if(tempbook==null)  //未找到该书
			JOptionPane.showConfirmDialog(null, "书目中未找到此书");
		else   //找到此书
		{
			boolean isfind=false;
			booktype=tempbook.getType();
			//合并多批购买中的相同isbn号的书目
			for(int i=0;i<sale.items.size();i+=1)
			{
				if(sale.items.get(i).getBookSpec().isbn.equals(isbn))
				{
					int excopies=sale.items.get(i).getCopies();
					sale.items.get(i).setcopies(excopies+copies);
					isfind=true;
				}
			}
			if(isfind==false)
			{
				_16211478_zhugezhengyang_7_IPricingStrategy stra=_16211478_zhugezhengyang_7_PricingStrategyFactory.getInstance().getPricingStrategy(booktype);
				tempslineItem=new _16211478_zhugezhengyang_7_SaleLineItem(copies, tempbook, stra);
				sale.items.add(tempslineItem);
			}
			
			//更新购物车
			_16211478_zhugezhengyang_7_Controller.sale.notifyObservers();
			
		}
	}
	//获得当前的Sale
	public static _16211478_zhugezhengyang_7_Sale getSale()
	{
		return sale;
	}
}




 