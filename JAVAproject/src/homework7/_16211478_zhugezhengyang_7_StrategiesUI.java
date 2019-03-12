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
* @version time��2019��1��6�� ����8:39:27 
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
	String[] columnNames= {"���Ա��","��������","��������","����ͼ������","ÿ���Ż�/������ϱ��"};
	Object[][] data;
	DefaultTableModel tbmodel;
	_16211478_zhugezhengyang_7_IPricingStrategy seletedP;
	ArrayList<Integer> serows; //�����������ļ��е��к�
	

	_16211478_zhugezhengyang_7_StrategyCatalog strcat;
	List<_16211478_zhugezhengyang_7_IPricingStrategy> strlist;
	
	
	public _16211478_zhugezhengyang_7_StrategiesUI(_16211478_zhugezhengyang_7_StrategyCatalog strcat)
	{
		this.strcat=strcat;
		
		setTitle("��Ӳ���");
		
		//�����
		jpup=new JPanel(new GridLayout(3,2,10,10));
		numLabel=new JLabel("���Ա��");
		nameLabel=new JLabel("��������");
		strtypeLabel=new JLabel("��������");
		booktypeLabel=new JLabel("����ͼ������");
		discountLabel=new JLabel("ÿ���Ż�/������ϱ��");
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
		
		//��ť
		jpmid=new JPanel(new GridLayout(1,4,10,10));
		addbtn=new JButton("����");
		delbtn=new JButton("ɾ��");
		edibtn=new JButton("�޸�");
		seabtn=new JButton("����");
		jpmid.add(addbtn); jpmid.add(delbtn); jpmid.add(edibtn); jpmid.add(seabtn);
		
		//���
		
		strlist=new ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy>();
		Iterator<Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy>> it= strcat.strategies.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy> entry=it.next();
			strlist.add(entry.getValue());
		} //����Map�е�Ԫ�ؼ���arraylist
		
		data= new Object[strlist.size()][];
		for(int i=0;i<strlist.size();i+=1)
		{
			_16211478_zhugezhengyang_7_IPricingStrategy stra=strlist.get(i);
			Object[] tempstr= {stra.getSimpNum(),stra.getName(),String.valueOf(stra.getType()),String.valueOf(stra.getApplyType()),stra.getelseinfo()};
			data[i]=tempstr;
		}
		
//		tbmodel=new DefaultTableModel(data, columnNames);
		table=new JTable(new DefaultTableModel(data, columnNames));
		
		scrollPane = new JScrollPane(table);  //�����������,���ò���������
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		//������Ӱ�ť
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
	//ɾ�� �¼�
	class delListen implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) {
			
//			tbmodel=new DefaultTableModel(data,columnNames);
//			table.setModel(tbmodel);
			//��ʵ����һ��ԭ���ı�Ȼ����ɾ����Ӧ��
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
	
	//��� �¼�
	class addListen implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			String number=numField.getText().trim();
			String name=nameField.getText().trim();
			String strtype=strtypeField.getText().trim();
			String applytype=booktypeField.getText().trim();
			String discount=discountField.getText().trim();
			
			boolean notnull=judgenull(number, name, strtype, applytype, discount);
			
			if(notnull) //�п�����,���򷵻�false
			{
				if(applybookjudge(applytype)) //�ж���ŷ�Χ,����ȷ
				{
					if(repeatnum(number))  //���ظ�����true
					{
						boolean booktypeerrbit=false;
						boolean componameerr=false;
						boolean compoIncompoErr=false;
						boolean compoDelSimErr=false;
						int stype=Integer.parseInt(strtype);
						_16211478_zhugezhengyang_7_IPricingStrategy stra;
						switch (stype) {
						case 1:  //  �ٷֱ�
							stra=new _16211478_zhugezhengyang_7_PercentageStrategy(number, name,Integer.parseInt(strtype), Integer.parseInt(applytype), Double.parseDouble(discount));
//							strcat.strategies.put(Integer.parseInt(applytype), stra);
							_16211478_zhugezhengyang_7_Controller.addSimpleStrategy(Integer.parseInt(applytype), stra);
							putdata();
							break;
						case 2: //����ֵ
							stra=new _16211478_zhugezhengyang_7_FlatRateStrategy(number, name, Integer.parseInt(strtype),  Integer.parseInt(applytype), Double.parseDouble(discount));
//							strcat.strategies.put(Integer.parseInt(applytype), stra);
							_16211478_zhugezhengyang_7_Controller.addSimpleStrategy(Integer.parseInt(applytype), stra);
							putdata();
							break;
						case 3:  //���
							
							//������֤���������Ƿ����
							
							String[] simStr=discount.split("\\|");  //"|"���ָ�Ҫע��
							
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
										if(strlist.get(j).getType()==3)   //��Ҫ���������ϲ���
										{
											compoIncompoErr=true;
											compoInerr();
											judge=false;
											break;
										}
										//���¼Ӹ��ϲ��԰���Ҫ���滻�ļ򵥲���
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
							
							
							
							if(juresult==false)  //��ϵĲ��ԺŲ��Ϸ�
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
						
						default:   //����������Ͳ���
							booktypeerrbit=true;
							booktypeerr();
							break;
						}
						
//						putdata();
						
						if(!booktypeerrbit&&!componameerr&&!compoIncompoErr)  //��û����
						{
							numField.setText("");
							nameField.setText("");
							strtypeField.setText("");
							booktypeField.setText("");
							discountField.setText("");
						}
					}//������ظ�������
					
				}
				//��ʹ��ͼ�����ʹ������ʾ
				
			}
			//������Ϊ�ջ���ʾ
			
			
			
		}
		
	}
	
	//�༭��ť
	class editListen implements ActionListener
	{
		
		JFrame editFrame=new JFrame();
	
		JTextField numField=new JTextField(20);
		JTextField nameField=new JTextField(20);
		JTextField strtypeField=new JTextField(20);
		JTextField booktypeField=new JTextField(20);
		JTextField discountField=new JTextField(20); 
		JLabel numLabel=new JLabel("���Ա��");
		JLabel nameLabel=new JLabel("��������");
		JLabel strtypeLabel=new JLabel("��������");
		JLabel booktypeLabel=new JLabel("����ͼ������");
		JLabel discountLabel=new JLabel("ÿ���Ż�/�򵥲������");
		JPanel jpe=new JPanel(new GridLayout(6,2,10,10));
		JButton isok=new JButton("ȷ��");
		JButton cancel=new JButton("ȡ��");

		public void actionPerformed(ActionEvent e) {
			getSelectedInfo();
			String number=seletedP.getSimpNum();
			String name=seletedP.getName();
			int strtype=seletedP.getType();
			int applytype=seletedP.getApplyType();
			String discount=seletedP.getelseinfo();
			
			editFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			editFrame.setTitle("�༭����");
			
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
						
//������֤���������Ƿ����
						
						String[] simStr=discount.split("\\|");  //"|"���ָ�Ҫע��
						
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
									if(strlist.get(j).getType()==3)   //��Ҫ���������ϲ���
									{
										compoIncompoErr=true;
										compoInerr();
										judge=false;
										break;
									}
									//���¼Ӹ��ϲ��԰���Ҫ���滻�ļ򵥲���
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
						
						
						
						if(juresult==false)  //��ϵĲ��ԺŲ��Ϸ�
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
	
	//���� ��ť
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
			numbtn=new JButton("���Ա��");
			namebtn=new JButton("��������");
			strbtn=new JButton("��������");
			applybookbtn=new JButton("����ͼ������");
			jps=new JPanel();
			
			seFrame.setTitle("����");
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
					inputstr=(String) JOptionPane.showInputDialog(null, "������", "�������", JOptionPane.INFORMATION_MESSAGE, null,null , "Discount001");
					seFrame.dispose();
					searchdata(inputstr,"num");
				
				}
			});
			namebtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					inputstr=(String) JOptionPane.showInputDialog(null, "������", "��������", JOptionPane.INFORMATION_MESSAGE, null,null , "����ֵ�Żݲ���1");
					seFrame.dispose();
					searchdata(inputstr,"name");
				}
			});
			strbtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					inputstr=(String) JOptionPane.showInputDialog(null, "������", "������������", JOptionPane.INFORMATION_MESSAGE, null,null , 1);
					seFrame.dispose();
					searchdata(inputstr,"strtype");
				}
			});
			applybookbtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					inputstr=(String) JOptionPane.showInputDialog(null, "������", "����ͼ����������", JOptionPane.INFORMATION_MESSAGE, null,null , 1);
					seFrame.dispose();
					searchdata(inputstr,"applytype");
				}
			});
			
			
		}
		
	}
	
	
	
	
	
	//���µ�list���뵽����У�ʵʱ����
	public void putdata()    
	{
		System.out.print("aaaaaaaaaaaa");
		
	    strlist=new ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy>();
		Iterator<Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy>> it= strcat.strategies.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy> entry=it.next();
			strlist.add(entry.getValue());
		} //����Map�е�Ԫ�ؼ���arraylist
		
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
	
	//���ѡ�������ݸ�selectedP
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
	
	//�ж��������ͣ�������Ӧ����
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
	
	// �������ʹ���
	void booktypeerr()
	{
		JOptionPane.showMessageDialog(this, "��������������1~3����");
	}
	// ����Ϊ�ռ���
	boolean judgenull(String num,String name,String t,String ty,String dis)
	{
		if(num.length()==0||name.length()==0||t.length()==0||ty.length()==0||dis.length()==0)
		{
			JOptionPane.showMessageDialog(this, "���벻��Ϊ��");
			return false;
		}
		else 
			return true;
	}
	//����ͼ�����ͼ���
	boolean applybookjudge(String s)
	{
		if(s.equals("1")||s.equals("2")||s.equals("3")||s.equals("4")||s.equals("5"))
			return true;
		else
		{
			JOptionPane.showMessageDialog(this, "����ͼ���������1~5��");
			return false;
		}
			
	}
	//����ظ�����
	boolean repeatnum(String strnum)
	{
		 ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy> temstrlist=new ArrayList<_16211478_zhugezhengyang_7_IPricingStrategy>();
		 Iterator<Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy>> it= strcat.strategies.entrySet().iterator();
		 while(it.hasNext())
			{
				Map.Entry<Integer, _16211478_zhugezhengyang_7_IPricingStrategy> entry=it.next();
				temstrlist.add(entry.getValue());
			} //����Map�е�Ԫ�ؼ���arraylist
			
			for(int i=0;i<temstrlist.size();i+=1)
			{
				_16211478_zhugezhengyang_7_IPricingStrategy stra=temstrlist.get(i);
				if(stra.getSimpNum().equals(strnum))
				{
					JOptionPane.showConfirmDialog(null, "��������ظ�");
					return false;
				}
					
			}		
			return true;
	}
	
	//������ƴ�����ʾ
	void compoerr()
	{
		JOptionPane.showMessageDialog(this, "��ϵ����ƴ���");
	}
	//���Ƕ����ʾ
	void compoInerr()
	{
		JOptionPane.showMessageDialog(this, "��ϲ����в���Ƕ��");
	}
	//��ϰ�����ɾ������ʾ
	void compoDelSim()
	{
		JOptionPane.showMessageDialog(this, "��ӵ���ϲ��Ժ��н�Ҫ��ɾ���ļ򵥲���");
	}
}



 