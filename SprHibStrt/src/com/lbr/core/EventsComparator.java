package com.lbr.core;

import java.util.Comparator;

public class EventsComparator implements Comparator
{
	public int compare(Object o1, Object o2)
	{
		if((o1 instanceof EventRecommendationVO) && (o2 instanceof EventRecommendationVO))
		{
			EventRecommendationVO rtVO1 = (EventRecommendationVO)o1;
			EventRecommendationVO rtVO2 = (EventRecommendationVO)o2;
			if(rtVO1.getEvent().getStartDate().equals(rtVO2.getEvent().getStartDate()))
				return  0;
			else if(rtVO1.getEvent().getStartDate().before(rtVO2.getEvent().getStartDate()))
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