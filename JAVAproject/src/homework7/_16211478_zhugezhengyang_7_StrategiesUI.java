package homework7;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle.Control;

import javax.naming.NameAlreadyBoundException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.ComponentInputMapUIResource;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import homework7._16211478_zhugezhengyang_7_AddBookUI.addListen;

/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:39:27 
* class statement:
*/
public class _16211478_zhugezhengyang_7_StrategiesUI extends JFrame{
	
	JPanel jpup;
	JPanel jp1;
	JPanel jp2;
	JPanel jp3;
	JPanel jp4;
	JPanel jp5;
	JPanel jpmid;
	JTextField numField;
	JTextField nameField;
	JTextField strtypeField;
	JTextField booktypeField;
	JTextField discountField; 
	JLabel numLabel;
	JLabel nameLabel;
	JLabel strtypeLabel;
	JLabel booktypeLabel;
	JLabel discountLabel;
	
	JButton addbtn;
	JButton delbtn;
	JButton edibtn;
	JButton seabtn;

	JTable table;
	JScrollPane scrollPane;
	String[] columnNames= {"策略编号","策略名称","策略类型","适用图书类型","每本优惠/策略组合编号"};
	Object[][] data;
	DefaultTableModel tbmodel;
	_16211478_zhugezhengyang_7_IPricingStrategy seletedP;
	ArrayList<Integer> serows; //保存搜索到的几行的行号
	

	_16211478_zhugezhengyang_7_StrategyCatalog strcat;
	List<_16211478_zhugezhengyang_7_IPricingStrategy> strlist;
	
	
	public _16211478_zhugezhengyang_7_StrategiesUI(_16211478_zhugezhengyang_7_StrategyCatalog strcat)
	{
		this.strcat=strcat;
		
		setTitle("添加策略");
		
		//输入框
		jpup=new JPanel(new GridLayout(3,2,10,10));
		numLabel=new JLabel("策略编号");
		nameLabel=new JLabel("策略名称");
		strtypeLabel=new JLabel("策略类型");
		booktypeLabel=new JLabel("适用图书类型");
		discountLabel=new JLabel("每本优惠/策略组合编号");
		numField=new JTextField(20);  numField.setText("");
		nameField=new JTextField(20); nameField.setText("");
		strtypeField=new JTextField(20); strtypeField.setText("");
		booktypeField=new JTextField(20); booktypeField.setText("");
		discountField=new JTextField(20); discountField.setText("");
		jp1=new JPanel(); jp2=new JPanel(); jp3=new JPanel();
		jp4=new JPanel(); jp5=new JPanel();
		jp1.add(numLabel); 
		jp1.add(numField);
		jp2.add(nameLabel);  jp2.add(nameField);
		jp3.add(strtypeLabel); jp3.add(strtypeField);
		jp4.add(booktypeLabel);  jp4.add(booktypeField);
		jp5.add(discountLabel); jp5.add(discountField);
		jpup.add(jp1); jpup.add(jp2); jpup.add(jp3); jpup.add(jp4); jpup.add(jp5);
		
		//按钮
		jpmid=new JPanel(new GridLayout(1,4,10,10));
		addbtn=new JButton("增加");
		delbtn=new JButton("删除");
		edibtn=new JButton("修改");
		seabtn=new JButton("查找");
		jpmid.add(addbtn); jpmid.add(delbtn); jpmid.add(edibtn); jpmid.add(seabtn);
		
		//表格
		
		strlist=new ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy>();
		Iterator<Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy>> it= strcat.strategies.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy> entry=it.next();
			strlist.add(entry.getValue());
		} //将《Map中的元素加入arraylist
		
		data= new Object[strlist.size()][];
		for(int i=0;i<strlist.size();i+=1)
		{
			_16211478_zhugezhengyang_7_IPricingStrategy stra=strlist.get(i);
			Object[] tempstr= {stra.getSimpNum(),stra.getName(),String.valueOf(stra.getType()),String.valueOf(stra.getApplyType()),stra.getelseinfo()};
			data[i]=tempstr;
		}
		
//		tbmodel=new DefaultTableModel(data, columnNames);
		table=new JTable(new DefaultTableModel(data, columnNames));
		
		scrollPane = new JScrollPane(table);  //放入滚动条中,设置产生滚动条
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		//监听添加按钮
		addbtn.addActionListener(new addListen());
		
		delbtn.addActionListener(new delListen());
		
		edibtn.addActionListener(new editListen());
		
		seabtn.addActionListener(new seaListen());
		
		
		
		add(jpup);
		add(jpmid);
		add(scrollPane);
		setLayout(new FlowLayout());
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(700,450);
		setLocation(300, 100);
		setVisible(true);
	
	}
	//删除 事件
	class delListen implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) {
			
//			tbmodel=new DefaultTableModel(data,columnNames);
//			table.setModel(tbmodel);
			//先实例化一个原来的表，然后再删除对应行
			int count[]=table.getSelectedRows();
//			int cccc=table.getSelectedRow();
//			System.out.print(count.length);
			
			for(int i=0;i<count.length;i+=1)
				System.out.print(" co"+count[i]+" ");
			
			if(count.length<=0)
				;
			else
			{

				int[] delbooktype=new int[count.length];
//				System.out.print(delbooktype.length);
				for(int i=0;i<delbooktype.length;i+=1)
				{
					delbooktype[i]=Integer.parseInt((String)data[count[i]][3]);
				}
				
//				for(int i=0;i<delbooktype.length;i+=1)
//					System.out.print(" de"+delbooktype[i]+" ");
				
				for(int j=0;j<delbooktype.length;j+=1)
				{
//					strcat.strategies.remove(delbooktype[j]);
					_16211478_zhugezhengyang_7_Controller.deleteStrategy(delbooktype[j]);
				}
			}
			
			putdata();
			
		}
		
	}
	
	//添加 事件
	class addListen implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			String number=numField.getText().trim();
			String name=nameField.getText().trim();
			String strtype=strtypeField.getText().trim();
			String applytype=booktypeField.getText().trim();
			String discount=discountField.getText().trim();
			
			boolean notnull=judgenull(number, name, strtype, applytype, discount);
			
			if(notnull) //判空输入,空则返回false
			{
				if(applybookjudge(applytype)) //判断书号范围,若正确
				{
					if(repeatnum(number))  //无重复返回true
					{
						boolean booktypeerrbit=false;
						boolean componameerr=false;
						boolean compoIncompoErr=false;
						boolean compoDelSimErr=false;
						int stype=Integer.parseInt(strtype);
						_16211478_zhugezhengyang_7_IPricingStrategy stra;
						switch (stype) {
						case 1:  //  百分比
							stra=new _16211478_zhugezhengyang_7_PercentageStrategy(number, name,Integer.parseInt(strtype), Integer.parseInt(applytype), Double.parseDouble(discount));
//							strcat.strategies.put(Integer.parseInt(applytype), stra);
							_16211478_zhugezhengyang_7_Controller.addSimpleStrategy(Integer.parseInt(applytype), stra);
							putdata();
							break;
						case 2: //绝对值
							stra=new _16211478_zhugezhengyang_7_FlatRateStrategy(number, name, Integer.parseInt(strtype),  Integer.parseInt(applytype), Double.parseDouble(discount));
//							strcat.strategies.put(Integer.parseInt(applytype), stra);
							_16211478_zhugezhengyang_7_Controller.addSimpleStrategy(Integer.parseInt(applytype), stra);
							putdata();
							break;
						case 3:  //组合
							
							//首先验证输入的组合是否存在
							
							String[] simStr=discount.split("\\|");  //"|"做分割要注意
							
//							System.out.print(discount+"  "+simStr[0]+simStr[1]+"  ");
//							System.out.print(strlist.size());
//							for(int i=0;i<strlist.size();i+=1)
//								System.out.print(" "+strlist.get(i).getSimpNum()+" ");
							
							ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy> templist=new ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy>();
							boolean juresult=true;
							boolean judge=false;
							for(int i=0;i<simStr.length;i+=1)
							{
								judge=false;
								for(int j=0;j<strlist.size();j++)
								{
									if(simStr[i].equals(strlist.get(j).getSimpNum()))
									{
										if(strlist.get(j).getType()==3)   //若要加入的是组合策略
										{
											compoIncompoErr=true;
											compoInerr();
											judge=false;
											break;
										}
										//若新加复合策略包含要被替换的简单策略
										else if(strlist.get(j).getApplyType()==Integer.parseInt(applytype))
										{
											compoDelSim();
											compoDelSimErr=true;
											judge=false;
											break;
										}
										templist.add(strlist.get(j));
										judge=true;
										break;
									}		
								}
								if(judge==false)
								{
									juresult=false;
									break;
								}
							}
							
							for(int i=0;i<templist.size();i+=1)
								System.out.print(" "+templist.get(i).getSimpNum()+" ");
							
							
							
							if(juresult==false)  //组合的策略号不合法
							{
								componameerr=true;
								compoerr();
							}
							else if(juresult==true)
							{
								_16211478_zhugezhengyang_7_CompositeBestForCustomer compostr=new _16211478_zhugezhengyang_7_CompositeBestForCustomer(number, name, Integer.parseInt(strtype),Integer.parseInt(applytype), templist);
//								strcat.strategies.put(Integer.parseInt(applytype), compostr);
								_16211478_zhugezhengyang_7_Controller.addCompositeStrategy(Integer.parseInt(applytype), compostr);
								putdata();
							}
							
							break;
						
						default:   //输入的书类型不对
							booktypeerrbit=true;
							booktypeerr();
							break;
						}
						
//						putdata();
						
						if(!booktypeerrbit&&!componameerr&&!compoIncompoErr)  //若没出错
						{
							numField.setText("");
							nameField.setText("");
							strtypeField.setText("");
							booktypeField.setText("");
							discountField.setText("");
						}
					}//若序号重复，报错
					
				}
				//若使用图书类型错误会提示
				
			}
			//若输入为空会提示
			
			
			
		}
		
	}
	
	//编辑按钮
	class editListen implements ActionListener
	{
		
		JFrame editFrame=new JFrame();
	
		JTextField numField=new JTextField(20);
		JTextField nameField=new JTextField(20);
		JTextField strtypeField=new JTextField(20);
		JTextField booktypeField=new JTextField(20);
		JTextField discountField=new JTextField(20); 
		JLabel numLabel=new JLabel("策略编号");
		JLabel nameLabel=new JLabel("策略名称");
		JLabel strtypeLabel=new JLabel("策略类型");
		JLabel booktypeLabel=new JLabel("适用图书类型");
		JLabel discountLabel=new JLabel("每本优惠/简单策略组合");
		JPanel jpe=new JPanel(new GridLayout(6,2,10,10));
		JButton isok=new JButton("确定");
		JButton cancel=new JButton("取消");

		public void actionPerformed(ActionEvent e) {
			getSelectedInfo();
			String number=seletedP.getSimpNum();
			String name=seletedP.getName();
			int strtype=seletedP.getType();
			int applytype=seletedP.getApplyType();
			String discount=seletedP.getelseinfo();
			
			editFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			editFrame.setTitle("编辑策略");
			
			jpe.add(numLabel); jpe.add(numField);
			jpe.add(nameLabel); jpe.add(nameField);
			jpe.add(strtypeLabel); jpe.add(strtypeField);
			jpe.add(booktypeLabel); jpe.add(booktypeField);
			jpe.add(discountLabel); jpe.add(discountField);
			numField.setText(number);
			nameField.setText(name);
			strtypeField.setText(String.valueOf(strtype));
			booktypeField.setText(String.valueOf(applytype));
			discountField.setText(discount);
			
			jpe.add(isok);
			jpe.add(cancel);
			
			editFrame.add(jpe);
			editFrame.setLayout(new FlowLayout());
			editFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//			editFrame.setSize(700,450);
//			editFrame.setLocation(300, 100);
			editFrame.setBounds(350,150,700,450);
			editFrame.setVisible(true);
			
			isok.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					int bktype=Integer.parseInt((String)booktypeField.getText());
					String number=numField.getText();
					String name=nameField.getText();
					int strtype=Integer.parseInt(strtypeField.getText());
					int applytype=Integer.parseInt(booktypeField.getText());
					String discount=discountField.getText();
					_16211478_zhugezhengyang_7_IPricingStrategy chgStr;
					
					boolean booktypeerrbit=false;
					boolean componameerr=false;
					boolean compoIncompoErr=false;
					boolean compoDelSimErr=false;
					switch (strtype) {
					case 1:
						chgStr=new _16211478_zhugezhengyang_7_FlatRateStrategy(number, name, strtype, applytype, Double.parseDouble(discount));
//						strcat.strategies.put(bktype, chgStr);
						_16211478_zhugezhengyang_7_Controller.updateStrategy(bktype, chgStr);
						break;
					case 2:
						chgStr=new _16211478_zhugezhengyang_7_PercentageStrategy(number, name, strtype, applytype, Double.parseDouble(discount));
//						strcat.strategies.put(bktype, chgStr);
						_16211478_zhugezhengyang_7_Controller.updateStrategy(bktype, chgStr);
						break;
					case 3:
						
//首先验证输入的组合是否存在
						
						String[] simStr=discount.split("\\|");  //"|"做分割要注意
						
//						System.out.print(discount+"  "+simStr[0]+simStr[1]+"  ");
//						System.out.print(strlist.size());
//						for(int i=0;i<strlist.size();i+=1)
//							System.out.print(" "+strlist.get(i).getSimpNum()+" ");
						
						ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy> templist=new ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy>();
						boolean juresult=true;
						boolean judge=false;
						for(int i=0;i<simStr.length;i+=1)
						{
							judge=false;
							for(int j=0;j<strlist.size();j++)
							{
								if(simStr[i].equals(strlist.get(j).getSimpNum()))
								{
									if(strlist.get(j).getType()==3)   //若要加入的是组合策略
									{
										compoIncompoErr=true;
										compoInerr();
										judge=false;
										break;
									}
									//若新加复合策略包含要被替换的简单策略
									else if(strlist.get(j).getApplyType()==applytype)
									{
										compoDelSim();
										compoDelSimErr=true;
										judge=false;
										break;
									}
									templist.add(strlist.get(j));
									judge=true;
									break;
								}		
							}
							if(judge==false)
							{
								juresult=false;
								break;
							}
						}
						
						for(int i=0;i<templist.size();i+=1)
							System.out.print(" "+templist.get(i).getSimpNum()+" ");
						
						
						
						if(juresult==false)  //组合的策略号不合法
						{
							componameerr=true;
							compoerr();
						}
						else if(juresult==true)
						{
							chgStr=new _16211478_zhugezhengyang_7_CompositeBestForCustomer(number, name, strtype,applytype, templist);
//							strcat.strategies.put(applytype, chgStr);
							_16211478_zhugezhengyang_7_Controller.updateStrategy(applytype, chgStr);
							
						}
						break;
						
					default:
						break;
					}
				editFrame.dispose();
				putdata();
				}
				
			});
			
			cancel.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					editFrame.dispose();
					
				}
			});
			
		}
		
	}
	
	//搜索 按钮
	class seaListen implements ActionListener
	{
		JFrame seFrame;
		JButton numbtn;
		JButton namebtn;
		JButton strbtn;
		JButton applybookbtn;
		JPanel jps;
		String inputstr;
		

		public void actionPerformed(ActionEvent e) {
			seFrame=new JFrame();
			numbtn=new JButton("策略编号");
			namebtn=new JButton("策略名称");
			strbtn=new JButton("策略类型");
			applybookbtn=new JButton("适用图书类型");
			jps=new JPanel();
			
			seFrame.setTitle("搜索");
			jps.add(numbtn);
			jps.add(namebtn);
			jps.add(strbtn);
			jps.add(applybookbtn);
			seFrame.add(jps);
			seFrame.setLayout(new FlowLayout());
			
			seFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//			editFrame.setSize(700,450);
//			editFrame.setLocation(300, 100);
			seFrame.setBounds(400,200,700,450);
			seFrame.setVisible(true);
			
			numbtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					inputstr=(String) JOptionPane.showInputDialog(null, "请输入", "编号搜索", JOptionPane.INFORMATION_MESSAGE, null,null , "Discount001");
					seFrame.dispose();
					searchdata(inputstr,"num");
				
				}
			});
			namebtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					inputstr=(String) JOptionPane.showInputDialog(null, "请输入", "名称搜索", JOptionPane.INFORMATION_MESSAGE, null,null , "绝对值优惠策略1");
					seFrame.dispose();
					searchdata(inputstr,"name");
				}
			});
			strbtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					inputstr=(String) JOptionPane.showInputDialog(null, "请输入", "策略类型搜索", JOptionPane.INFORMATION_MESSAGE, null,null , 1);
					seFrame.dispose();
					searchdata(inputstr,"strtype");
				}
			});
			applybookbtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					inputstr=(String) JOptionPane.showInputDialog(null, "请输入", "适用图书类型搜索", JOptionPane.INFORMATION_MESSAGE, null,null , 1);
					seFrame.dispose();
					searchdata(inputstr,"applytype");
				}
			});
			
			
		}
		
	}
	
	
	
	
	
	//将新的list输入到表格中，实时更新
	public void putdata()    
	{
		System.out.print("aaaaaaaaaaaa");
		
	    strlist=new ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy>();
		Iterator<Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy>> it= strcat.strategies.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy> entry=it.next();
			strlist.add(entry.getValue());
		} //将《Map中的元素加入arraylist
		
	    data= new Object[strlist.size()][];
		for(int i=0;i<strlist.size();i+=1)
		{
			_16211478_zhugezhengyang_7_IPricingStrategy stra=strlist.get(i);
			Object[] tempstr= {stra.getSimpNum(),stra.getName(),String.valueOf(stra.getType()),String.valueOf(stra.getApplyType()),stra.getelseinfo()};
			data[i]=tempstr;
		}
		
//		System.out.print(data[0]);
		
//		tbmodel=new DefaultTableModel(data,columnNames);
		table.setModel(new DefaultTableModel(data,columnNames));
	}
	
	//获得选中行数据给selectedP
	void getSelectedInfo()
	{
		int selectrow=table.getSelectedRow();
		if(selectrow<0) ;
		else
		{
			String num=(String)data[selectrow][0];
			String name=(String)data[selectrow][1];
			int strtype=Integer.parseInt((String)data[selectrow][2]);
			int applytype=Integer.parseInt((String)data[selectrow][3]);
			String discount=(String)data[selectrow][4];
			
			switch (strtype) {
			case 1:
				seletedP=new _16211478_zhugezhengyang_7_FlatRateStrategy(num, name, strtype, applytype, Double.parseDouble(discount));
				break;
			case 2:
				seletedP=new _16211478_zhugezhengyang_7_PercentageStrategy(num, name, strtype, applytype, Double.parseDouble(discount));
				break;
			case 3:
				seletedP=new _16211478_zhugezhengyang_7_CompositeBestForCustomer(num, name, strtype, applytype, discount);
				break;
			default:
				break;
			}
			
		}
		
	}
	
	//判断搜索类型，进行相应搜素
	void searchdata(String content,String type)
	{
		serows=new ArrayList<Integer>();
		switch (type) {
		case "num":
			for(int i=0;i<data.length;i+=1)
			{
				if(data[i][0].equals(content))
					serows.add(i);
				System.out.println("content: ");
			}
			break;
		case "name":
			for(int i=0;i<data.length;i+=1)
			{
				if(data[i][1].equals(content))
					serows.add(i);
			}
			break;
		case "strtype":
			for(int i=0;i<data.length;i+=1)
			{
				if(data[i][2].equals(content))
					serows.add(i);
			}
			break;
		case "applytype":
			for(int i=0;i<data.length;i+=1)
			{
				if(data[i][3].equals(content))
					serows.add(i);
			}
			break;
			
		default:
			break;
		}
//		
		System.out.print(data.length);
		System.out.print(data[0].length);
		System.out.println("content: "+content);
		System.out.println(serows.size());
		
		for(int i=0;i<serows.size();i+=1)
		{
			System.out.println(data[i][0]);
			System.out.println(serows.get(i));
		}
			
		System.out.println("content: "+content);
		
//		for(int i=0;i<serows.size();i+=1)
//		{
//			table.setRowSelectionInterval(serows.get(i), serows.get(i));
//		}
		
		table.setModel(new DefaultTableModel(data,columnNames));
		if(serows.size()>0)
			table.setRowSelectionInterval(serows.get(0), serows.get(0));
			
	}
	
	// 策略类型错误
	void booktypeerr()
	{
		JOptionPane.showMessageDialog(this, "策略类型请输入1~3数字");
	}
	// 输入为空检验
	boolean judgenull(String num,String name,String t,String ty,String dis)
	{
		if(num.length()==0||name.length()==0||t.length()==0||ty.length()==0||dis.length()==0)
		{
			JOptionPane.showMessageDialog(this, "输入不能为空");
			return false;
		}
		else 
			return true;
	}
	//适用图书类型检验
	boolean applybookjudge(String s)
	{
		if(s.equals("1")||s.equals("2")||s.equals("3")||s.equals("4")||s.equals("5"))
			return true;
		else
		{
			JOptionPane.showMessageDialog(this, "适用图书类型请填（1~5）");
			return false;
		}
			
	}
	//序号重复检验
	boolean repeatnum(String strnum)
	{
		 ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy> temstrlist=new ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy>();
		 Iterator<Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy>> it= strcat.strategies.entrySet().iterator();
		 while(it.hasNext())
			{
				Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy> entry=it.next();
				temstrlist.add(entry.getValue());
			} //将《Map中的元素加入arraylist
			
			for(int i=0;i<temstrlist.size();i+=1)
			{
				_16211478_zhugezhengyang_7_IPricingStrategy stra=temstrlist.get(i);
				if(stra.getSimpNum().equals(strnum))
				{
					JOptionPane.showConfirmDialog(null, "输入序号重复");
					return false;
				}
					
			}		
			return true;
	}
	
	//组合名称错误提示
	void compoerr()
	{
		JOptionPane.showMessageDialog(this, "组合的名称错误");
	}
	//组合嵌套提示
	void compoInerr()
	{
		JOptionPane.showMessageDialog(this, "组合策略中不能嵌套");
	}
	//组合包含将删除项提示
	void compoDelSim()
	{
		JOptionPane.showMessageDialog(this, "添加的组合策略含有将要被删除的简单策略");
	}
}



 