package homework7;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle.Control;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:37:35 
* class statement:
*/
public class _16211478_zhugezhengyang_7_MainUI extends JFrame
{
	JFrame mainFrame;
	JPanel jp;
	JButton addbookbtn;
	JButton addstrategybtn;
	JButton buybookbtn;
	
	public _16211478_zhugezhengyang_7_MainUI()
	{
		mainFrame=new JFrame();
		jp=new JPanel(new GridLayout(3,1,10,10));
		addbookbtn=new JButton("添加书目");
		addstrategybtn=new JButton("添加策略");
		buybookbtn=new JButton("购买书籍");
		
		jp.add(addbookbtn);
		jp.add(addstrategybtn);
		jp.add(buybookbtn);
		mainFrame.add(jp);
		mainFrame.setTitle("购书系统");
		mainFrame.setLayout(new FlowLayout());
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFrame.setSize(600,450);
		mainFrame.setLocation(200, 100);
		mainFrame.setVisible(true);
		
		addbookbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				_16211478_zhugezhengyang_7_AddBookUI addBookUI=new _16211478_zhugezhengyang_7_AddBookUI(_16211478_zhugezhengyang_7_Controller.bookcat);
			}
		});
		
		addstrategybtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_16211478_zhugezhengyang_7_StrategiesUI strategyUI=new _16211478_zhugezhengyang_7_StrategiesUI(_16211478_zhugezhengyang_7_Controller.stracat);
			}
		});
		
		buybookbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_16211478_zhugezhengyang_7_BuyUI buyUI=new _16211478_zhugezhengyang_7_BuyUI(_16211478_zhugezhengyang_7_Controller.bookcat, _16211478_zhugezhengyang_7_Controller.stracat);
				
				_16211478_zhugezhengyang_7_ShoppingCarUI cartui=new _16211478_zhugezhengyang_7_ShoppingCarUI(_16211478_zhugezhengyang_7_Controller.getSale());
				_16211478_zhugezhengyang_7_Controller.sale.registerObserver(cartui);
				
			}
		});
	}
	
	public static void main(String[] args)
	{
		_16211478_zhugezhengyang_7_Controller.bookcat=new _16211478_zhugezhengyang_7_BookCatalog();
		_16211478_zhugezhengyang_7_Controller.stracat=new _16211478_zhugezhengyang_7_StrategyCatalog();
		_16211478_zhugezhengyang_7_PricingStrategyFactory.getInstance().setCatalog(_16211478_zhugezhengyang_7_Controller.stracat);
		
		_16211478_zhugezhengyang_7_MainUI mainUI=new _16211478_zhugezhengyang_7_MainUI();
//		ShoppingCarUI cartui = new ShoppingCarUI(Controller.sale);
		
//		StrategiesUI strategyUI=new StrategiesUI(Controller.stracat);
		
//		
//		
		
	}
}
 