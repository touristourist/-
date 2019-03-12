package homework7; 
/** 
* @author Tourist  E-mail: 
* @version time：2019年1月6日 下午8:59:54 
* class statement:
*/
public interface _16211478_zhugezhengyang_7_Subject {
	void registerObserver(_16211478_zhugezhengyang_7_Observer o);  //加入观察者
	void removeRegister(_16211478_zhugezhengyang_7_Observer o);   //移除观察者
	void notifyObservers();   //对所有观察者处理
}
 