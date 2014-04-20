package com.gulin.restaurant.jsf;

import com.gulin.restaurant.ejb.MenuItemFacade;
import com.gulin.restaurant.jsf.util.JsfUtil;
import com.gulin.restaurant.model.ItemDataModel;
import com.gulin.restaurant.model.MenuItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

@Named("menuItemController")
@SessionScoped
public class MenuItemController implements Serializable {

    private MenuItem selectedItem;
    private MenuItem[] selectedItems;
    private static int count;
    
    private List<MenuItem> menuItems = null;
    
    private ItemDataModel itemsModel;  
    
    @Inject
    private MenuItemFacade itemEao;
    private int selectedItemIndex;

    public MenuItemController() {
	
    }

    @PostConstruct
    public void loadMenuItems() {
	
	this.setMenuItems(getItemEao().findAll());
	itemsModel = new ItemDataModel(this.getMenuItems());
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
    
     public void handleItemSelect() {
        this.selectedItem = this.getSelectedItem();
	
    }
    
    public void onCellEdit(CellEditEvent event) {  
        Object oldValue = event.getOldValue();  
        Object newValue = event.getNewValue();  
          
        if(newValue != null && !newValue.equals(oldValue)) {  
	    this.getItemEao().edit(getSelectedItem());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
		    "Cell Changed", "Old Value: " + oldValue + ", New Value: " + newValue);  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        }  
    }  

     public synchronized void incrementOrderItems() {
        count++;

        // This is how you do Primefaces Push...
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        /*
         * In this example '/counter' is a channel name. Every push must be 
         * to a distinct channel. This channel is then referenced on the client
         * side. The second parameter must be a String value. This is the 
         * data sent to the client. This data can be simple or complex. For 
         * example you could create a complex series of data points separated 
         * by commas. Of course, on the client side you would need to parse 
         * this text a pick out the data you need using JavaScript.
         */
        pushContext.push("/counter", String.valueOf(count));
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

    public static int getCount() {
	return count;
    }

    public static void setCount(int count) {
	MenuItemController.count = count;
    }

    public MenuItem[] getSelectedItems() {
	return selectedItems;
    }

    public void setSelectedItems(MenuItem[] selectedItems) {
	this.selectedItems = selectedItems;
    }

    public int getSelectedItemIndex() {
	return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
	this.selectedItemIndex = selectedItemIndex;
    }

    public ItemDataModel getItemsModel() {
	return itemsModel;
    }

    public void setItemsModel(ItemDataModel itemsModel) {
	this.itemsModel = itemsModel;
    }
    
    
}


