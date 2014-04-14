package com.gulin.restaurant.jsf;

import com.gulin.restaurant.model.MenuItem;
import com.gulin.restaurant.jsf.util.JsfUtil;
import com.gulin.restaurant.ejb.MenuItemFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@Named("menuItemController")
@SessionScoped
public class MenuItemController implements Serializable {

    private MenuItem selectedItem;
    private List<MenuItem> menuItems = null;
    @Inject
    private MenuItemFacade itemEao;
    private int selectedItemIndex;

    public MenuItemController() {
    }

    @PostConstruct
    public void loadMenuItems() {
	this.setMenuItems(getItemEao().findAll());
    }

    public String displayItemDetails() {
	if (this.getSelectedItem() == null) {
	    JsfUtil.addSuccessMessage("Please select an item first by click on a row.");
	}
	return "ItemDetails";
    }

    public String displayMenuItems() {
	return "menu_items";
    }

    public String prepareCreate() {
	selectedItem = new MenuItem();
	selectedItemIndex = -1;
	return "create";
    }

    public String create() {
	try {
	    this.getItemEao().create(selectedItem);
	    JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MenuItemCreated"));
	    return prepareCreate();
	} catch (Exception e) {
	    JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
	    return null;
	} finally {
	    loadMenuItems();
	}
    }

    public String deleteItem() {

	try {
	    this.getItemEao().remove(selectedItem);
	    JsfUtil.addSuccessMessage("Item Successfuly Deleted");
	    loadMenuItems();
	} catch (Exception e) {
	    JsfUtil.addErrorMessage("Please, click on a row to select the item you want to remove.");
	    return null;
	}
	return "menu_items";

    }

    public MenuItem getMenuItem(int id) {
	return itemEao.find(id);
    }

    public List<MenuItem> getMenuItems() {
	return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
	this.menuItems = menuItems;
    }

    public MenuItem getSelectedItem() {
	return selectedItem;
    }

    public void setSelectedItem(MenuItem selectedItem) {
	this.selectedItem = selectedItem;
    }

    public MenuItemFacade getItemEao() {
	return itemEao;
    }

    public void setItemEao(MenuItemFacade itemEao) {
	this.itemEao = itemEao;
    }

}


