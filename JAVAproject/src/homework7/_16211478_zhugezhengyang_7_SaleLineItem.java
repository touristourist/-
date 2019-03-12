package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:50:36 
* class statement:
*/
public class _16211478_zhugezhengyang_7_SaleLineItem
{
	
	private int copies;  //该类书有几本
	private _16211478_zhugezhengyang_7_BookSpecification bookSpec;   //书的类别
	private _16211478_zhugezhengyang_7_IPricingStrategy strategy;    //策略,对于本类并不知道具体实例化的是哪种策略
	
	public _16211478_zhugezhengyang_7_SaleLineItem(int copies,_16211478_zhugezhengyang_7_BookSpecification bookspec,_16211478_zhugezhengyang_7_IPricingStrategy strategy)
	{
		this.copies=copies;
		this.bookSpec=bookspec;
		this.strategy=strategy;  //策略是根据策略工厂中由booktype得到的
	}
	public int getCopies()
	{
		return copies;
	}
	public _16211478_zhugezhengyang_7_BookSpecification getBookSpec()
	{
		return bookSpec;
	}
	public _16211478_zhugezhengyang_7_IPricingStrategy getStrategy()
	{
		return strategy;
	}
	public void setcopies(int copies)
	{
		this.copies=copies;
	}
	
	//获得了几个参数后，可以计算出这一类书一共花多少钱
	public double getSubTotal()
	{
		return strategy.getSubTotal(this);
	}
	
}
 