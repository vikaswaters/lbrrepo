package com.lbr;

public enum VICINITY_POLICY {
	LOCATION(0), PINCODE(1), MULTIPLE_PINCODES(2), CITY(3), DISTRICT(4), STATE(5);

	private int policy;

	private VICINITY_POLICY(int c) {
		policy = c;
	}

	public int getVICINITY_POLICY() {
		return policy;
	}
	
	public String getVICINITY_POLICYName() {
		return this.name();
	}
	
	public int getVICINITY_POLICYOrdinal() {
		return this.ordinal();
	}
	
	public static VICINITY_POLICY getVICINITY_POLICYForOrdinal(int ordinal){
		VICINITY_POLICY vc = null;
		switch (ordinal) {
		case 1:
			vc = PINCODE;
			break;
		case 2:
			vc = MULTIPLE_PINCODES;
			break;
		case 3:
			vc = CITY;
			break;
		case 4:
			vc = DISTRICT;
			break;
		case 5:
			vc = STATE;
			break;
		default:
			vc = CITY;
			break;
		}
		return vc;
		
	}
}	