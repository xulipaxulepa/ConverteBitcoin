package com.apps4you.wellington.convertebitcoin.view;

/**
 * Created by xulipasoftworks on 25/02/2018.
 */

public class Criptomoeda {

    private String id;
    private String price_btc;
    private String price_usd;
    private String price_usd_unit;
    private String price_brl;
    private String price_brl_unit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(String price_btc) {
        this.price_btc = price_btc;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getPrice_brl() {
        return price_brl;
    }

    public void setPrice_brl(String price_brl) {
        this.price_brl = price_brl;
    }

    public String getPrice_usd_unit() {
        return price_usd_unit;
    }

    public void setPrice_usd_unit(String price_usd_unit) {
        this.price_usd_unit = price_usd_unit;
    }

    public String getPrice_brl_unit() {
        return price_brl_unit;
    }

    public void setPrice_brl_unit(String price_brl_unit) {
        this.price_brl_unit = price_brl_unit;
    }
}
