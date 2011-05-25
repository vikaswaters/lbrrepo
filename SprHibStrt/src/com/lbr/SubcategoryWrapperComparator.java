package com.lbr;

import java.util.Comparator;

public class SubcategoryWrapperComparator implements Comparator
{
	public int compare(Object o1, Object o2)
	{
		if((o1 instanceof SubcategoryWrapper) && (o2 instanceof SubcategoryWrapper))
		{
			SubcategoryWrapper rtVO1 = (SubcategoryWrapper)o1;
			SubcategoryWrapper rtVO2 = (SubcategoryWrapper)o2;
			if(rtVO1.getUserPreference().getSubCatId().equals(rtVO2.getUserPreference().getSubCatId()))
				return  0;
			else if(rtVO1.getUserPreference().getSubCatId() < (rtVO2.getUserPreference().getSubCatId()))
				return -1;
			else
				return 1;
		}
		else
		{
			return o1.hashCode() - o2.hashCode();
		}
	}

	public boolean equals(Object o)
	{
		return (compare(this, o) == 0);
	}
}
