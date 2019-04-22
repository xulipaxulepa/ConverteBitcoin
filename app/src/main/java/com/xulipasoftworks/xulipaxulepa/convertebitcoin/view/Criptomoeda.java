package com.xulipasoftworks.xulipaxulepa.convertebitcoin.view;

import android.os.Parcel;
import android.os.Parcelable;

import com.xulipasoftworks.xulipaxulepa.convertebitcoin.R;

import java.io.Serializable;

/**
 * Created by xulipasoftworks on 25/02/2018.
 */

public class Criptomoeda {

    private String id;
    private String nome;
    private String price_btc;
    private String price_usd;
    private String price_usd_unit;
    private String price_brl;
    private String price_brl_unit;
    private String variacao;


    public Criptomoeda() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getVariacao() {
        return variacao;
    }

    public void setVariacao(String variacao) {
        this.variacao = variacao;
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


    public int getImagen(int position){
        switch (position){
            case 0:
                return(R.mipmap.bitcoin);
            case 1:
                return(R.mipmap.ethereum);
            case 2:
                return(R.mipmap.ripple);
            case 3:
                return (R.mipmap.iota);
            case 4:
                return (R.mipmap.dash);
            default:
                return (R.mipmap.ic_launcher);
        }
    }

    public int getImagenbyName(String nome){
        if(nome.equals("Bitcoin")) {
            return(R.mipmap.bitcoin);
        } else if(nome.equals("Ethereum")) {
            return(R.mipmap.ethereum);
        } else if(nome.equals("Ripple")) {
            return(R.mipmap.ripple);
        } else if(nome.equals("IOTA")) {
            return (R.mipmap.iota);
        } else if(nome.equals("Dash")) {
            return (R.mipmap.dash);
        } else {
            return (R.mipmap.ic_launcher);
        }
    }
}
