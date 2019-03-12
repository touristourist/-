package homework7;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import homework7._16211478_zhugezhengyang_7_StrategiesUI.delListen;
import homework7._16211478_zhugezhengyang_7_StrategiesUI.editListen;
import homework7._16211478_zhugezhengyang_7_StrategiesUI.seaListen;

/** 
* @author Tourist  E-mail: 
* @version time��2019��1��6�� ����8:38:45 
* class statement:
*/
public class _16211478_zhugezhengyang_7_AddBookUI extends JFrame{
	
	JPanel jpup;
	JPanel jp1;
	JPanel jp2;
	JPanel jp3;
	JPanel jp4;
	JPanel jpmid;
	JTextField isbnField;
	JTextField nameField;
	JTextField priceField;
	JTextField typeField;
	JLabel isbnLabel;
	JLabel nameLabel;
	JLabel priceLabel;
	JLabel typeLabel;
	
	JButton addbtn;
	JButton delbtn;
	JButton edibtn;
	JButton seabtn;

	JTable table;
	JScrollPane scrollPane;
	String[] columnNames= {"isbn","����","�۸�","������"};
	Object[][] data;
	ArrayList<Integer> serows; //�����������ļ��е��к�
	

	_16211478_zhugezhengyang_7_BookCatalog bookcat;
	_16211478_zhugezhengyang_7_BookSpecification seletedP=new _16211478_zhugezhengyang_7_BookSpecification();
	
	public _16211478_zhugezhengyang_7_AddBookUI(_16211478_zhugezhengyang_7_BookCatalog bookcat)
	{
		this.bookcat=bookcat;
		
		setTitle("����鼮");
		
		//�����
		jpup=new JPanel(new GridLayout(2,2,10,10));
		isbnLabel=new JLabel("isbn");
		nameLabel=new JLabel("����");
		priceLabel=new JLabel("�۸�");
		typeLabel=new JLabel("������");
		isbnField=new JTextField(20);
		nameField=new JTextField(20);
		priceField=new JTextField(20);
		typeField=new JTextField(20);
		jp1=new JPanel(); jp2=new JPanel(); jp3=new JPanel(); jp4=new JPanel();
		jp1.add(isbnLabel); 
		jp1.add(isbnField);
		jp2.add(nameLabel);  jp2.add(nameField);
		jp3.add(priceLabel); jp3.add(priceField);
		jp4.add(typeLabel);  jp4.add(typeField);
		jpup.add(jp1); jpup.add(jp2); jpup.add(jp3); jpup.add(jp4);
		
		//��ť
		jpmid=new JPanel(new GridLayout(1,4,10,10));
		addbtn=new JButton("����");
		delbtn=new JButton("ɾ��");
		edibtn=new JButton("�޸�");
		seabtn=new JButton("����");
		jpmid.add(addbtn); jpmid.add(delbtn); jpmid.add(edibtn); jpmid.add(seabtn);
		
		//���
		
		List<_16211478_zhugezhengyang_7_BookSpecification> booklist= bookcat.books;
		
		Object[][] data= new Object[booklist.size()][];
		for(int i=0;i<booklist.size();i+=1)
		{
			_16211478_zhugezhengyang_7_BookSpecification bkspec=booklist.get(i);
			Object[] tempbk= {bkspec.getIsbn(),bkspec.getTitle(),bkspec.getPrice(),bkspec.getType()};
			data[i]=tempbk;
		}
		table=new JTable(new DefaultTableModel(data, columnNames));
		
		scrollPane = new JScrollPane(table);  //�����������,���ò���������
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		//������Ӱ�ť
		addbtn.addActionListener(new addListen());
		
		delbtn.addActionListener(new delListen());
		
		edibtn.addActionListener(new editListen());
//		
		seabtn.addActionListener(new seaListen());
		
		
		add(jpup);
		add(jpmid);
		add(scrollPane);
		setLayout(new FlowLayout());
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(600,450);
		setLocation(200, 100);
		setVisible(true);
		
	}
	
	//��� ��ť
	class addListen implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			String isbn=isbnField.getText().trim();
			String name=nameField.getText().trim();
			String price=priceField.getText().trim();
			String type=typeField.getText().trim();
			
			if(judgenull(isbn, name, type, price)) //�ǿշ���true
			{
				if(applybookjudge(type))
				{
					if(repeatnum(isbn))
					{
						_16211478_zhugezhengyang_7_BookSpecification abook=new _16211478_zhugezhengyang_7_BookSpecification(isbn,name,Double.parseDouble(price),Integer.parseInt(type));
//						bookcat.books.add(abook);
						_16211478_zhugezhengyang_7_Controller.addBook(abook);
						
						putdata();
						
						isbnField.setText("");
						nameField.setText("");
						priceField.setText("");
						typeField.setText("");
					}
				}
			}
		}
		
	}
	
	//ɾ�� �¼�
	class delListen implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) {
			
//			tbmodel=new DefaultTableModel(data,columnNames);
//			table.setModel(tbmodel);
			//��ʵ����һ��ԭ���ı�Ȼ����ɾ����Ӧ��
			int count=table.getSelectedRow();
//			int cccc=table.getSelectedRow();
//			System.out.print(count.length);
			
//			for(int i=0;i<count.length;i+=1)
//				System.out.print(" co"+count[i]+" ");
			
			if(count<0)
				;
			else
			{

				
//				System.out.print(delbookisbn.length);
				
				String delbookisbn= (String)data[count][0];
				
//				for(int i=0;i<delbookisbn.length;i+=1)
//					System.out.print(" de"+delbookisbn[i]+" ");
				
				for(int j=0;j<bookcat.books.size();j+=1)
				{
					if(bookcat.books.get(j).isbn.equals(delbookisbn))
					{
						bookcat.books.remove(j);
					}
				}
			}
			
			putdata();
			
		}
		
	}			
	
	//�༭��ť
	class editListen implements ActionListener
	{
		
		JFrame editFrame=new JFrame();
	
		JTextField isbnField=new JTextField(20);
		JTextField nameField=new JTextField(20);
		
		JTextField booktypeField=new JTextField(20);
		JTextField priceField=new JTextField(20); 
		JLabel isbnLabel=new JLabel("isbn���");
		JLabel nameLabel=new JLabel("����");
		
		JLabel booktypeLabel=new JLabel("ͼ������");
		JLabel priceLabel=new JLabel("�۸�");
		JPanel jpe=new JPanel(new GridLayout(5,2,10,10));
		JButton isok=new JButton("ȷ��");
		JButton cancel=new JButton("ȡ��");

		public void actionPerformed(ActionEvent e) {
			getSelectedInfo();
			String isbn=seletedP.isbn;
			String name=seletedP.title;
		
			int applytype=seletedP.type;
			double price=seletedP.price;
			
			editFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			editFrame.setTitle("�༭�鼮");
			
			jpe.add(isbnLabel); jpe.add(isbnField);
			jpe.add(nameLabel); jpe.add(nameField);
			
			jpe.add(booktypeLabel); jpe.add(booktypeField);
			jpe.add(priceLabel); jpe.add(priceField);
			isbnField.setText(isbn);
			nameField.setText(name);
			
			booktypeField.setText(String.valueOf(applytype));
			priceField.setText(String.valueOf(price));
			
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
					String bktype=(String)booktypeField.getText().trim();
					String isbn=isbnField.getText().trim();
					String name=nameField.getText().trim();
					
					String price=priceField.getText().trim();
					_16211478_zhugezhengyang_7_BookSpecification chgStr;
					
					boolean booktypeerrbit=false;
					boolean componameerr=false;
					boolean compoIncompoErr=false;
					boolean compoDelSimErr=false;
					if(judgenull(isbn, name, bktype, price)) //�ǿշ���true
					{
						if(applybookjudge(bktype))
						{
								_16211478_zhugezhengyang_7_BookSpecification abook=new _16211478_zhugezhengyang_7_BookSpecification(isbn,name,Double.parseDouble(price),Integer.parseInt(bktype));
//								bookcat.books.add(abook);
								
								for(int i=0;i<bookcat.books.size();i+=1)
								{
									if(bookcat.books.get(i).isbn.equals(isbn))
									{
										//����controller��bookcatֵ
										bookcat.books.get(i).isbn=isbn;
										bookcat.books.get(i).title=name;
										bookcat.books.get(i).type=Integer.parseInt(bktype);
										bookcat.books.get(i).price=Double.parseDouble(price);
									}
								}
								
						}
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
			
			JButton applybookbtn;
			JPanel jps;
			String inputstr;
			

			public void actionPerformed(ActionEvent e) {
				seFrame=new JFrame();
				numbtn=new JButton("isbn���");
				namebtn=new JButton("�鼮����");
				
				applybookbtn=new JButton("ͼ������");
				jps=new JPanel();
				
				seFrame.setTitle("����");
				jps.add(numbtn);
				jps.add(namebtn);
				
				jps.add(applybookbtn);
				seFrame.add(jps);
				seFrame.setLayout(new FlowLayout());
				
				seFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//				editFrame.setSize(700,450);
//				editFrame.setLocation(300, 100);
				seFrame.setBounds(400,200,700,450);
				seFrame.setVisible(true);
				
				numbtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						inputstr=(String) JOptionPane.showInputDialog(null, "������", "isbn�������", JOptionPane.INFORMATION_MESSAGE, null,null , "isbn");
						seFrame.dispose();
						searchdata(inputstr,"isbn");
					
					}
				});
				namebtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						inputstr=(String) JOptionPane.showInputDialog(null, "������", "�鼮��������", JOptionPane.INFORMATION_MESSAGE, null,null , "name");
						seFrame.dispose();
						searchdata(inputstr,"name");
					}
				});
				
				applybookbtn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						inputstr=(String) JOptionPane.showInputDialog(null, "������", "ͼ����������", JOptionPane.INFORMATION_MESSAGE, null,null , 1);
						seFrame.dispose();
						searchdata(inputstr,"type");
					}
				});
				
				
			}
			
		}
	
	//���µ�bookspectation���뵽����У�ʵʱ����
	public void putdata()    
	{
//		System.out.print("aaaaaaaaaaaa");
		
		List<_16211478_zhugezhengyang_7_BookSpecification> booklist= bookcat.books;
		
		data= new Object[booklist.size()][];
		for(int i=0;i<booklist.size();i+=1)
		{
			_16211478_zhugezhengyang_7_BookSpecification bkspec=booklist.get(i);
			Object[] tempbk= {bkspec.getIsbn(),bkspec.getTitle(),bkspec.getPrice(),bkspec.getType()};
			data[i]=tempbk;
		}
		table.setModel(new DefaultTableModel(data,columnNames));
	}
	
	//���ѡ�������ݸ�selectedP
	void getSelectedInfo()
	{
		int selectrow=table.getSelectedRow();
		
		System.out.println(selectrow);
		
		if(selectrow<0) ;
		else
		{
			
			
			seletedP.isbn=(String)data[selectrow][0];
			seletedP.title=(String)data[selectrow][1];
			seletedP.type=Integer.parseInt(table.getValueAt(selectrow, 3).toString());
			seletedP.price=Double.parseDouble(table.getValueAt(selectrow, 2).toString());
			
		}
		
	}	
	
	//�ж��������ͣ�������Ӧ����
		void searchdata(String content,String type)
		{
			serows=new ArrayList<Integer>();
			switch (type) {
			case "isbn":
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
			
			case "type":
				for(int i=0;i<data.length;i+=1)
				{
					if(content.equals(data[i][3].toString()))
						serows.add(i);
				}
				break;
				
			default:
				break;
			}
			System.out.print(content.equals((data[0][3].toString())));
			System.out.print(data.length+" "+data[0][3]+" ");
			System.out.print(data[0].length);
			System.out.println("content: "+content);
			System.out.println(serows.size()+"serow:");
			
			for(int i=0;i<serows.size();i+=1)
			{
				System.out.println(data[i][0]);
				System.out.println(serows.get(i));
			}
				
			System.out.println("content: "+content);
			
//			for(int i=0;i<serows.size();i+=1)
//			{
//				table.setRowSelectionInterval(serows.get(i), serows.get(i));
//			}
			
			table.setModel(new DefaultTableModel(data,columnNames));
			//����ֻ��ʾ��һ��
			if(serows.size()>0)
				table.setRowSelectionInterval(serows.get(0), serows.get(0));
				
		}
		
	
	
	//isbn����ظ�����
	boolean repeatnum(String strnum)
	{
		 List<_16211478_zhugezhengyang_7_BookSpecification> temstrlist=bookcat.books;
		 
		 for(int i=0;i<temstrlist.size();i+=1)
			{
				_16211478_zhugezhengyang_7_BookSpecification tbook=temstrlist.get(i);
				if(tbook.isbn.equals(strnum))
				{
					JOptionPane.showConfirmDialog(null, "����isbn����ظ�");
					return false;
				}
					
			}		
			return true;	 		
	}	
	// ����Ϊ�ռ���
		boolean judgenull(String isbn,String name,String ty,String price)
		{
			if(isbn.length()==0||name.length()==0||ty.length()==0||price.length()==0)
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
	
	
	
}
 