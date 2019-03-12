package homework7;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:39:39 
* class statement:
*/
public class _16211478_zhugezhengyang_7_BuyUI extends JFrame{
	JFrame buyFrame;
	JTable table;
	JLabel isbnLabel;
	JLabel copiesLabel;
	JTextField isbnField;
	JTextField copiedField;
	JButton buybtn;
//	JPanel jpup;
	JPanel jpmid;
	JPanel jpdown;
	JScrollPane scrollPane;
	_16211478_zhugezhengyang_7_ShoppingCarUI cartui;
	
	String[] columnNames= {"isbn","书名","价格","书类型"};
	Object[][] data;
	_16211478_zhugezhengyang_7_BookCatalog bookcat;
	_16211478_zhugezhengyang_7_StrategyCatalog strcat;
	int booksorts;
	
	String isbn;
	int copies;
	
	
	public _16211478_zhugezhengyang_7_BuyUI(_16211478_zhugezhengyang_7_BookCatalog bc,_16211478_zhugezhengyang_7_StrategyCatalog sc) {
		this.bookcat=bc;
		this.strcat=sc;
		buyFrame=new JFrame();
		isbnLabel=new JLabel("isbn");
		isbnField=new JTextField(20);
		copiedField=new JTextField(20);
		copiesLabel=new JLabel("本数");
		buybtn=new JButton("加入购物车");
		jpmid=new JPanel();
		jpdown=new JPanel();
		
		booksorts=bookcat.books.size();
		data=new Object[booksorts][];
		for(int i=0;i<booksorts;i+=1)
		{
			_16211478_zhugezhengyang_7_BookSpecification tempbk=bookcat.books.get(i);
			Object[] tempob= {tempbk.isbn,tempbk.title,tempbk.price,tempbk.type};
			data[i]=tempob;
		}
		
		table=new JTable(new DefaultTableModel(data, columnNames));
		
		scrollPane = new JScrollPane(table);  //放入滚动条中,设置产生滚动条
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		jpmid.setLayout(new GridLayout(2,2,10,10));
		jpmid.add(isbnLabel); jpmid.add(isbnField);
		jpmid.add(copiesLabel); jpmid.add(copiedField);
		jpdown.add(buybtn);
		
		buyFrame.setTitle("选择购买");
		buyFrame.add(scrollPane);
		buyFrame.add(jpmid);
		buyFrame.add(jpdown);
		buyFrame.setLayout(new FlowLayout());
		buyFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		buyFrame.setSize(600,580);
		buyFrame.setLocation(200, 100);
		buyFrame.setVisible(true);
		
		buybtn.addActionListener(new buyListen());
		
	}
	
	class buyListen implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			isbn=isbnField.getText();
			String copstr=copiedField.getText();
			
			
			System.out.print(isbn.length());
			System.out.print(copstr.length());
			
			if(isbn.length()==0||copstr.length()==0)
			{
				JOptionPane.showMessageDialog(null, "输入不为空");
			}
			else {
				copies=Integer.parseInt(copiedField.getText());
				
				_16211478_zhugezhengyang_7_Controller.buyBook(isbn, copies);
				//在controller。buybook中创建cartUI
//				cartui=new ShoppingCarUI(Controller.getSale());
				
//				buyFrame.dispose();
			}
			
		}
		
		
	}
}
 