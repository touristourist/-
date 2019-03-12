package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time��2019��1��6�� ����8:50:36 
* class statement:
*/
public class _16211478_zhugezhengyang_7_SaleLineItem
{
	
	private int copies;  //�������м���
	private _16211478_zhugezhengyang_7_BookSpecification bookSpec;   //������
	private _16211478_zhugezhengyang_7_IPricingStrategy strategy;    //����,���ڱ��ಢ��֪������ʵ�����������ֲ���
	
	public _16211478_zhugezhengyang_7_SaleLineItem(int copies,_16211478_zhugezhengyang_7_BookSpecification bookspec,_16211478_zhugezhengyang_7_IPricingStrategy strategy)
	{
		this.copies=copies;
		this.bookSpec=bookspec;
		this.strategy=strategy;  //�����Ǹ��ݲ��Թ�������booktype�õ���
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
	
	//����˼��������󣬿��Լ������һ����һ��������Ǯ
	public double getSubTotal()
	{
		return strategy.getSubTotal(this);
	}
	
}
 