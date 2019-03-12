package homework7;

import java.util.Map;

/** 
* @author Tourist  E-mail: 
* @version time��2019��1��6�� ����8:50:55 
* class statement:  ��Ƴɵ���ģʽ�Ĺ���
*/
public class _16211478_zhugezhengyang_7_PricingStrategyFactory {
	//�����Ψһ����,static˵���������
	private static _16211478_zhugezhengyang_7_PricingStrategyFactory instance=new _16211478_zhugezhengyang_7_PricingStrategyFactory();
	//���캯�����ó�private���������ⲿʵ��������
	private _16211478_zhugezhengyang_7_PricingStrategyFactory() {}
	//��ö����Ψһ;��,���෽��
	public static _16211478_zhugezhengyang_7_PricingStrategyFactory getInstance()
	{
		return instance;    //���������������ʵ����������
	}
	//catalog����hashmap���������еĲ���
	private _16211478_zhugezhengyang_7_StrategyCatalog catalog;
	
	public void setCatalog(_16211478_zhugezhengyang_7_StrategyCatalog catl)
	{
		this.catalog=catl;
	}
	
	//��catalog��ѡ��
	public _16211478_zhugezhengyang_7_IPricingStrategy getPricingStrategy(int bookType)
	{
		Map<Integer, _16211478_zhugezhengyang_7_IPricingStrategy> tempMap=catalog.getCatalog();
		_16211478_zhugezhengyang_7_IPricingStrategy re=tempMap.get(bookType);
		if(re != null)
			return tempMap.get(bookType);
		else {
			_16211478_zhugezhengyang_7_NoDiscountStrategy nostr=new _16211478_zhugezhengyang_7_NoDiscountStrategy();
			return nostr;
		}
		//������Ҫ��ǰ����catalog��
//		switch (bookType)
//		{
//		case 1:   //�ǽ̲�������ͼ��  discount-3
//			return new PercentageStrategy(3);
//		case 2:   //�̲���
//			return new FlatRateStrategy(1);
//		case 3:   //������
//			return new PercentageStrategy(7);
//		case 4:    //������
//			return new CompositeStrategy();
//		case 5:    //����
//			return new NoDiscountStrategy();
//		default:
//			return null;
//		}
	}

}
 