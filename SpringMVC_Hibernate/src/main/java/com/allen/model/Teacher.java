package com.allen.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "teacher")
public class Teacher implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String password;
	private int age;
	private Set<Student> students = new HashSet<Student>();



	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_s", joinColumns = {@JoinColumn(name = "teacher_id")},
			inverseJoinColumns = {@JoinColumn(name = "student_id")})
	public Set<Student> getStudents() {
		return students;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "age")
	public int getAge() {
		return age;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}


}
