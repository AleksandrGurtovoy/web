/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dnepr
 */
@Entity
@Table(name = "shop")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shop.findAll", query = "SELECT s FROM Shop s")
    , @NamedQuery(name = "Shop.findByArticle", query = "SELECT s FROM Shop s WHERE s.shopPK.article = :article")
    , @NamedQuery(name = "Shop.findByDealer", query = "SELECT s FROM Shop s WHERE s.shopPK.dealer = :dealer")
    , @NamedQuery(name = "Shop.findByPrice", query = "SELECT s FROM Shop s WHERE s.price = :price")})
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ShopPK shopPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;

    public Shop() {
    }

    public Shop(ShopPK shopPK) {
        this.shopPK = shopPK;
    }

    public Shop(ShopPK shopPK, double price) {
        this.shopPK = shopPK;
        this.price = price;
    }

    public Shop(int article, String dealer) {
        this.shopPK = new ShopPK(article, dealer);
    }

    public ShopPK getShopPK() {
        return shopPK;
    }

    public void setShopPK(ShopPK shopPK) {
        this.shopPK = shopPK;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shopPK != null ? shopPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shop)) {
            return false;
        }
        Shop other = (Shop) object;
        if ((this.shopPK == null && other.shopPK != null) || (this.shopPK != null && !this.shopPK.equals(other.shopPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Shop[ shopPK=" + shopPK + " ]";
    }
    
}
