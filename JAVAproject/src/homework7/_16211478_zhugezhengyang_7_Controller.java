package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time��2019��1��6�� ����8:48:19 
* class statement:
*/

import java.awt.print.Book;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

public class _16211478_zhugezhengyang_7_Controller {
	
	public static _16211478_zhugezhengyang_7_BookCatalog bookcat=new _16211478_zhugezhengyang_7_BookCatalog();  //�����鼮
	public static _16211478_zhugezhengyang_7_StrategyCatalog stracat=new _16211478_zhugezhengyang_7_StrategyCatalog();  //���в���
	public static _16211478_zhugezhengyang_7_Sale sale=new _16211478_zhugezhengyang_7_Sale();
	public static _16211478_zhugezhengyang_7_ShoppingCarUI cartui;
	
	public static _16211478_zhugezhengyang_7_SaleLineItem tempslineItem;
	
	public static void addBook(_16211478_zhugezhengyang_7_BookSpecification book)
	{
		bookcat.books.add(book);
	}
	public static void addCompositeStrategy(int booktype,_16211478_zhugezhengyang_7_CompositeBestForCustomer compo)
	{
		//���Ӷ�Ӧbooktype�ĸ��Ӳ���
		stracat.strategies.put(booktype, compo);
	}
	public static void addSimpleStrategy(int booktype,_16211478_zhugezhengyang_7_IPricingStrategy simp)
	{
		//Ҫ����booktype�ͼ򵥲���
		stracat.strategies.put(booktype, simp);
	}
	//�����������ɾ��
	public static void deleteStrategy(int type)
	{
		stracat.strategies.remove(type);
	}
	//���ݲ��Ա��ɾ��,����hashmapɾ����Ӧ��
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
	//�޸�ԭ��booktype�Ĳ���Ϊpristr
	public static void updateStrategy(int booktype,_16211478_zhugezhengyang_7_IPricingStrategy pristr)
	{
		stracat.strategies.put(booktype, pristr);
	}
	//��ǰ��sale�м����µ�saleitem
	public static void buyBook(String isbn,int copies)
	{
		//������ͬ����ϲ�����ͬ�����
		int booktype;
		
		_16211478_zhugezhengyang_7_BookSpecification tempbook=bookcat.getBookSpecification(isbn);
		if(tempbook==null)  //δ�ҵ�����
			JOptionPane.showConfirmDialog(null, "��Ŀ��δ�ҵ�����");
		else   //�ҵ�����
		{
			boolean isfind=false;
			booktype=tempbook.getType();
			//�ϲ����������е���ͬisbn�ŵ���Ŀ
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
			
			//���¹��ﳵ
			_16211478_zhugezhengyang_7_Controller.sale.notifyObservers();
			
		}
	}
	//��õ�ǰ��Sale
	public static _16211478_zhugezhengyang_7_Sale getSale()
	{
		return sale;
	}
}




 