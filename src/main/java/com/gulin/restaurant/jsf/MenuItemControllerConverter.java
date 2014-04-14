package com.gulin.restaurant.jsf;

import com.gulin.restaurant.model.MenuItem;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = MenuItem.class)
public class MenuItemControllerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
	if (value == null || value.length() == 0) {
	    return null;
	}
	MenuItemController controller = (MenuItemController) facesContext.getApplication().getELResolver().
		getValue(facesContext.getELContext(), null, "menuItemController");
	return controller.getMenuItem(getKey(value));
    }

    java.lang.Integer getKey(String value) {
	java.lang.Integer key;
	key = Integer.valueOf(value);
	return key;
    }

    String getStringKey(java.lang.Integer value) {
	StringBuilder sb = new StringBuilder();
	sb.append(value);
	return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
	if (object == null) {
	    return null;
	}
	if (object instanceof MenuItem) {
	    MenuItem o = (MenuItem) object;
	    return getStringKey(o.getItemId());
	} else {
	    throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MenuItem.class.getName());
	}
    }
}
