package com.yc.entity.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

//职位
@Entity
@DiscriminatorValue("position")
@JsonIgnoreProperties(value = {"DepartAndPositions","children" })
public class Positions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer positionID;// 职位ID；

	@Column(length = 32)
	private String positionName;// 职位名称；

	@OneToMany(mappedBy = "positions")
	private Set<DepartAndPositions> departAndPositions;
	
	@Column
	private String significance;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "parentLevel")
	private Positions parentLevel;//父节点；
	
	@OneToMany(mappedBy = "parentLevel",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Positions> children = new HashSet<Positions>();
	
	@JsonIgnore
	public Set<DepartAndPositions> getDepartAndPositions() {
		return departAndPositions;
	}

	@JsonIgnore
	public void setDepartAndPositions(Set<DepartAndPositions> departAndPositions) {
		this.departAndPositions = departAndPositions;
	}

	public Positions getParentLevel() {
		return parentLevel;
	}

	public void setParentLevel(Positions parentLevel) {
		this.parentLevel = parentLevel;
	}

	public Set<Positions> getChildren() {
		return children;
	}

	public void setChildren(Set<Positions> children) {
		this.children = children;
	}

	public String getSignificance() {
		return significance;
	}

	public void setSignificance(String significance) {
		this.significance = significance;
	}

	public Integer getPositionID() {
		return positionID;
	}

	public void setPositionID(Integer positionID) {
		this.positionID = positionID;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
