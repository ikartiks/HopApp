package com.hopapp.pojos;

import java.util.ArrayList;
import java.util.List;

public class VMomentList extends VList {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2211381023949747999L;
	
	List<VMoments> list = new ArrayList<VMoments>();

	@Override
	public int getListSize() {
		
		return list.size();
	}
	
	@Override
	public List<VMoments> getList() {
		return list;
	}

}
