/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gulin.restaurant.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Machi
 */
@Entity
@Table(name = "menu_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuItem.findAll", query = "SELECT m FROM MenuItem m"),
    @NamedQuery(name = "MenuItem.findByItemId", query = "SELECT m FROM MenuItem m WHERE m.itemId = :itemId"),
    @NamedQuery(name = "MenuItem.findByItemName", query = "SELECT m FROM MenuItem m WHERE m.itemName = :itemName"),
    @NamedQuery(name = "MenuItem.findByItemPrice", query = "SELECT m FROM MenuItem m WHERE m.itemPrice = :itemPrice")})
public class MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_id")
    private Integer itemId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "item_name")
    private String itemName;
    @Lob
    @Size(max = 65535)
    @Column(name = "item_info")
    private String itemInfo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "item_price")
    private BigDecimal itemPrice;

    public MenuItem() {
    }

    public MenuItem(Integer itemId) {
	this.itemId = itemId;
    }

    public MenuItem(Integer itemId, String itemName) {
	this.itemId = itemId;
	this.itemName = itemName;
    }

    public Integer getItemId() {
	return itemId;
    }

    public void setItemId(Integer itemId) {
	this.itemId = itemId;
    }

    public String getItemName() {
	return itemName;
    }

    public void setItemName(String itemName) {
	this.itemName = itemName;
    }

    public String getItemInfo() {
	return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
	this.itemInfo = itemInfo;
    }

    public BigDecimal getItemPrice() {
	return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
	this.itemPrice = itemPrice;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (itemId != null ? itemId.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof MenuItem)) {
	    return false;
	}
	MenuItem other = (MenuItem) object;
	if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.gulin.restaurant.model.MenuItem[ itemId=" + itemId + " ]";
    }
    
}
