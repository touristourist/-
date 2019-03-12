package homework7;

import java.util.Map;

/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:50:55 
* class statement:  设计成单子模式的工厂
*/
public class _16211478_zhugezhengyang_7_PricingStrategyFactory {
	//该类的唯一对象,static说明是类变量
	private static _16211478_zhugezhengyang_7_PricingStrategyFactory instance=new _16211478_zhugezhengyang_7_PricingStrategyFactory();
	//构造函数设置成private，则不能在外部实例化对象
	private _16211478_zhugezhengyang_7_PricingStrategyFactory() {}
	//获得对象的唯一途径,是类方法
	public static _16211478_zhugezhengyang_7_PricingStrategyFactory getInstance()
	{
		return instance;    //返回类变量创建的实例工厂对象
	}
	//catalog中以hashmap保存了所有的策略
	private _16211478_zhugezhengyang_7_StrategyCatalog catalog;
	
	public void setCatalog(_16211478_zhugezhengyang_7_StrategyCatalog catl)
	{
		this.catalog=catl;
	}
	
	//从catalog中选择
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
		//策略需要提前放入catalog中
//		switch (bookType)
//		{
//		case 1:   //非教材类计算机图书  discount-3
//			return new PercentageStrategy(3);
//		case 2:   //教材类
//			return new FlatRateStrategy(1);
//		case 3:   //连环画
//			return new PercentageStrategy(7);
//		case 4:    //养生类
//			return new CompositeStrategy();
//		case 5:    //其他
//			return new NoDiscountStrategy();
//		default:
//			return null;
//		}
	}

}
 