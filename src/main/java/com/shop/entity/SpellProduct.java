package com.shop.entity;

public class SpellProduct {
    private Spelllist spelllist;
    private Product product;

    public Spelllist getSpelllist() {
        return spelllist;
    }

    public void setSpelllist(Spelllist spelllist) {
        this.spelllist = spelllist;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
