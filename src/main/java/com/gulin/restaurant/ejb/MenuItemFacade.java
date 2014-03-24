/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gulin.restaurant.ejb;

import com.gulin.restaurant.model.MenuItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Machi
 */
@Stateless
public class MenuItemFacade extends AbstractFacade<MenuItem> {
    @PersistenceContext(unitName = "com.gulin_MavenRestarantJsf_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public MenuItemFacade() {
	super(MenuItem.class);
    }
    
}
