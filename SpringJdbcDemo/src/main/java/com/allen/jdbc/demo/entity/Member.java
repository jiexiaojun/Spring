package com.allen.jdbc.demo.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_member")
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private String name;
	private String addr;
	private Long createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
