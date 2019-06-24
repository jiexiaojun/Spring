package com.allen.transcation.entity;

public class Member {

	private long id;

	private String name;

	private String addr;

	private Long createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * addr.
	 *
	 * @return  the addr
	 * @since   JDK 1.8
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * addr.
	 *
	 * @param   addr    the addr to set
	 * @since   JDK 1.8
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * createTime.
	 *
	 * @return  the createTime
	 * @since   JDK 1.8
	 */
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * createTime.
	 *
	 * @param   createTime    the createTime to set
	 * @since   JDK 1.8
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}


}
