
package com.gulin.restaurant.model;

/**
 *
 * @author Machi
 */
  
import java.util.List;  
import javax.faces.model.ListDataModel;  
import org.primefaces.model.SelectableDataModel;  
  
public class ItemDataModel extends ListDataModel<MenuItem> implements SelectableDataModel<MenuItem> {    
  
    public ItemDataModel() {  
    }  
  
    public ItemDataModel(List<MenuItem> data) {  
        super(data);  
    }  
      
    @Override  
    public MenuItem getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<MenuItem> items = (List<MenuItem>) getWrappedData();  
          
        for(MenuItem item : items) {  
            if(item.getItemName().equals(rowKey))  
                return item;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(MenuItem item) {  
        return item.getItemName();  
    }  
}  