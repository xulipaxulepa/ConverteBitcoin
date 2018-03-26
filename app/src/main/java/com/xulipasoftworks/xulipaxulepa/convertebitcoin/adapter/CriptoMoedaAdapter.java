package com.xulipasoftworks.xulipaxulepa.convertebitcoin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xulipasoftworks.xulipaxulepa.convertebitcoin.R;
import com.xulipasoftworks.xulipaxulepa.convertebitcoin.view.Criptomoeda;

import java.util.List;

/**
 * Created by wellington on 04/03/18.
 */

public class CriptoMoedaAdapter extends BaseAdapter {
    private Context context;
    private List<Criptomoeda> listaCriptomeda;


    public CriptoMoedaAdapter(Context context, List<Criptomoeda> listaCriptomeda){
        this.context= context;
        this.listaCriptomeda = listaCriptomeda;
    }


    @Override
    public int getCount() {
        return listaCriptomeda.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCriptomeda.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Criptomoeda criptomoeda = listaCriptomeda.get(position);


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.cryptomoeda_adapter_item_list, null);


        TextView txtNome = (TextView)layout.findViewById(R.id.textViewValorNomeCriptomoeda);
        txtNome.setText(criptomoeda.getNome());

        TextView txtPrecobtc = (TextView)layout.findViewById(R.id.textViewValorBtc);
        double valorbtc = Double.parseDouble(criptomoeda.getPrice_btc());
        txtPrecobtc.setText(String.format("%.2f", valorbtc));

        TextView txtvwbrl = (TextView)layout.findViewById(R.id.textViewValorBrl);
        double valorbrlunit = Double.parseDouble(criptomoeda.getPrice_brl_unit());
        txtvwbrl.setText(String.format("%.2f",valorbrlunit));


        TextView txtvwusd = (TextView)layout.findViewById(R.id.textViewValorUsd);
        double valorusdunit = Double.parseDouble(criptomoeda.getPrice_usd_unit());
        txtvwusd.setText(String.format("%.2f",valorusdunit));

        TextView txtvwbtc = (TextView)layout.findViewById(R.id.textViewValorPercent);
        double valorvariacao = Double.parseDouble(criptomoeda.getVariacao());
        txtvwbtc.setText(String.format("%.2f",valorvariacao));


        ImageView imageView = (ImageView)layout.findViewById(R.id.imageViewCriptoMoeda);
        imageView.setImageResource(criptomoeda.getImagen(position));

        return layout;
    }
}
