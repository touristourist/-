package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:48:45 
* class statement:  存放已有的所有书籍目录
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
	//通过isbn或title找书
	public _16211478_zhugezhengyang_7_BookSpecification getBookSpecification(String tag)
	{
		for(int i=0;i<books.size();i+=1)
		{
			if(books.get(i).isbn.equals(tag)||books.get(i).title.equals(tag))
				return books.get(i);
		}
		return null;   //未找到
	}
}