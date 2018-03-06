package com.apps4you.wellington.convertebitcoin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apps4you.wellington.convertebitcoin.R;
import com.apps4you.wellington.convertebitcoin.view.Criptomoeda;

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
        txtPrecobtc.setText(criptomoeda.getPrice_btc());

        TextView txtvwbrl = (TextView)layout.findViewById(R.id.textViewValorBrl);
        txtvwbrl.setText(criptomoeda.getPrice_brl_unit());


        TextView txtvwusd = (TextView)layout.findViewById(R.id.textViewValorUsd);
        txtvwusd.setText(criptomoeda.getPrice_usd_unit());

        TextView txtvwbtc = (TextView)layout.findViewById(R.id.textViewValorPercent);
        txtvwbtc.setText(criptomoeda.getVariacao());

        return layout;
    }
}
