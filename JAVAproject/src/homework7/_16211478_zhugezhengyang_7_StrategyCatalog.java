package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time��2019��1��6�� ����8:50:06 
* class statement:  ����������еĲ���Ŀ¼
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
 