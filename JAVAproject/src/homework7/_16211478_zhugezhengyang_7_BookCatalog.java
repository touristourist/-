package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time��2019��1��6�� ����8:48:45 
* class statement:  ������е������鼮Ŀ¼
*/

import java.util.ArrayList;
import java.util.List;

public class _16211478_zhugezhengyang_7_BookCatalog {
	
	public List<_16211478_zhugezhengyang_7_BookSpecification> books;
	
	public _16211478_zhugezhengyang_7_BookCatalog()
	{
		this.books=new ArrayList<_16211478_zhugezhengyang_7_BookSpecification>();
	}
	public _16211478_zhugezhengyang_7_BookCatalog(ArrayList<_16211478_zhugezhengyang_7_BookSpecification> books)
	{
		this.books=books;
	}
	//ͨ��isbn��title����
	public _16211478_zhugezhengyang_7_BookSpecification getBookSpecification(String tag)
	{
		for(int i=0;i<books.size();i+=1)
		{
			if(books.get(i).isbn.equals(tag)||books.get(i).title.equals(tag))
				return books.get(i);
		}
		return null;   //δ�ҵ�
	}
}