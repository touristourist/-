package homework7;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:40:05 
* class statement:  购物车窗口
*/
public class _16211478_zhugezhengyang_7_ShoppingCarUI extends JFrame implements _16211478_zhugezhengyang_7_Observer{
	
	JFrame cartFrame;
	JTable table;
	JLabel totalPrice;
	JScrollPane scrollPane;
	
	_16211478_zhugezhengyang_7_Sale totalsale;
	String[] columnNames= {"书名","数量"};
	Object[][] data;
	double sumprice=0;
	
	public _16211478_zhugezhengyang_7_ShoppingCarUI(_16211478_zhugezhengyang_7_Sale sale)
	{
		cartFrame=new JFrame();
		
		this.totalsale=sale;
		
		
		
		ArrayList<_16211478_zhugezhengyang_7_SaleLineItem> salelist=totalsale.items;
		data =new Object[salelist.size()][];
		for(int i=0;i<salelist.size();i+=1)
		{
			sumprice+=salelist.get(i).getSubTotal();
			_16211478_zhugezhengyang_7_SaleLineItem tempitem=salelist.get(i);
			Object[] tempb= {tempitem.getBookSpec().title,tempitem.getCopies()};
			data[i]=tempb;
		}
		
		
		table=new JTable(new DefaultTableModel(data,columnNames));
		scrollPane = new JScrollPane(table);  //放入滚动条中,设置产生滚动条
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		String str="总价格： "+sumprice;
		totalPrice=new JLabel(str);
		
		cartFrame.add(scrollPane);
		cartFrame.add(totalPrice);
		
		cartFrame.setLayout(new FlowLayout());
		cartFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		cartFrame.setSize(500,540);
		cartFrame.setLocation(400, 110);
		cartFrame.setVisible(true);
		
	}
	
	
	public void update(_16211478_zhugezhengyang_7_Sale sal) //更新购物车页面
	{
		sumprice=0;
		ArrayList<_16211478_zhugezhengyang_7_SaleLineItem> salelist=totalsale.items;
		data =new Object[salelist.size()][];
		for(int i=0;i<salelist.size();i+=1)
		{
			sumprice+=salelist.get(i).getSubTotal();
			_16211478_zhugezhengyang_7_SaleLineItem tempitem=salelist.get(i);
			Object[] tempb= {tempitem.getBookSpec().title,tempitem.getCopies()};
			data[i]=tempb;
		}
		
		
		table.setModel(new DefaultTableModel(data,columnNames));
		
		String str="总价格： "+sumprice;
		totalPrice.setText(str);
		
	}


}
