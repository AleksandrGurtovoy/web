/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Counselor;

/**
 *
 * @author dnepr
 */
@Stateless
public class CounselorFacade extends AbstractFacade<Counselor> {

    @PersistenceContext(unitName = "ConsultingAgencyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CounselorFacade() {
        super(Counselor.class);
    }
    
}
