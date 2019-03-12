package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:50:06 
* class statement:  存放所有已有的策略目录
*/

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class _16211478_zhugezhengyang_7_StrategyCatalog {
	
	public Map<Integer,_16211478_zhugezhengyang_7_IPricingStrategy> strategies;
	public _16211478_zhugezhengyang_7_StrategyCatalog() 
	{
		strategies=new LinkedHashMap<Integer, _16211478_zhugezhengyang_7_IPricingStrategy>();
	}
	public _16211478_zhugezhengyang_7_StrategyCatalog(Map<Integer, _16211478_zhugezhengyang_7_IPricingStrategy> stra)
	{
		this.strategies=stra;
	}
	
	
	public Map<Integer, _16211478_zhugezhengyang_7_IPricingStrategy> getCatalog()
	{
		return strategies;
	}
}
 