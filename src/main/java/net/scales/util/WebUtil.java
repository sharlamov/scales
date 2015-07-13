package net.scales.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.scales.model.CustomItem;

public class WebUtil {
	public static <T> T parse(Object obj, Class<T> type){
		if(obj == null )
			return null;
		else {
			if (obj instanceof BigDecimal) {
				BigDecimal bd = (BigDecimal)obj;
				if(type == Long.class){
					return type.cast(bd.longValue());
				}else if(type == Integer.class){
					return type.cast(bd.intValue());
				}else if(type == Double.class){
					return type.cast(bd.doubleValue());
				}else {
					return type.cast(bd.floatValue());
				}				
			}else{
				return type.cast(obj);	
			}
		}
	}
	
	public static List<CustomItem> toCustomItemList(List<Object> objects){
		List<CustomItem> items = new ArrayList<CustomItem>(objects.size() + 1);
		for (Object obj : objects) {
			Object[] array = (Object[]) obj;
			CustomItem ci = new CustomItem(array[0], null, array[1]);
			items.add(ci);
		}
		return items;
	}
}
