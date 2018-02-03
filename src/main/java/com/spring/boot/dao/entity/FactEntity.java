package com.spring.boot.dao.entity;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="facts_tbl")
public class FactEntity {
	 private int fid;
	 private int num;
	 private String result;
	 private Date doc;
	 private int version;
	 private byte[] image;
	 private String description;
	
	 
	 /**
	 * @return the fid
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 public int getFid() {
		return fid;
	}
	/**
	 * @param fid the fid to set
	 */
	public void setFid(int fid) {
		this.fid = fid;
	}
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the doc
	 */
	public Date getDoc() {
		return doc;
	}
	/**
	 * @param doc the doc to set
	 */
	public void setDoc(Date doc) {
		this.doc = doc;
	}
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return the image
	 */
	
	@Column(columnDefinition="longblob")
	public byte[] getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FactEntity [fid=" + fid + ", num=" + num + ", result=" + result + ", doc=" + doc + ", version="
				+ version + ", image=" + Arrays.toString(image) + ", description=" + description + "]";
	}
	 
	 
	 
	 
	 
}
