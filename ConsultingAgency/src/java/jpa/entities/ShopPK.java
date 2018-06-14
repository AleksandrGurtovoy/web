/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dnepr
 */
@Embeddable
public class ShopPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "article")
    private int article;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "dealer")
    private String dealer;

    public ShopPK() {
    }

    public ShopPK(int article, String dealer) {
        this.article = article;
        this.dealer = dealer;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) article;
        hash += (dealer != null ? dealer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShopPK)) {
            return false;
        }
        ShopPK other = (ShopPK) object;
        if (this.article != other.article) {
            return false;
        }
        if ((this.dealer == null && other.dealer != null) || (this.dealer != null && !this.dealer.equals(other.dealer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.ShopPK[ article=" + article + ", dealer=" + dealer + " ]";
    }
    
}
