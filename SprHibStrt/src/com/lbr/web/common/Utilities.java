package com.lbr.web.common;

import java.util.ArrayList;
import java.util.List;

public class Utilities {



	public static List getYNCombo() {
		List list = new ArrayList();
		//Yes
		DataBean y = new DataBean();
		y.setId("Y");
		y.setValue("Yes");
		list.add(y);
		//NO
		DataBean n = new DataBean();
		n.setId("N");
		n.setValue("No");
		list.add(n);
		return list;
	}

	}
