
package com.hopapp.pojos;

import java.io.Serializable;
import java.util.Date;

public class VDataObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Long id;
	
	protected Date createTime;
	
	protected Date updateTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
