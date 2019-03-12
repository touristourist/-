package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:49:35 
* class statement:
*/
public class _16211478_zhugezhengyang_7_BookSpecification {
	
	protected double price;
	protected String title;
	protected int type;
	protected String isbn;
	
	public _16211478_zhugezhengyang_7_BookSpecification() {}
	public _16211478_zhugezhengyang_7_BookSpecification(String is,String ti,double p,int ty)
	{
		this.price=p;
		this.title=ti;
		this.type=ty;
		this.isbn=is;
	}
	
	public double getPrice()
	{
		return price;
	}
	public String getTitle()
	{
		return title;
	}
	public int getType()
	{
		return type;
	}
	public String getIsbn()
	{
		return isbn;
	}
}
 