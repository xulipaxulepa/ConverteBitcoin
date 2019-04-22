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
import com.xulipasoftworks.xulipaxulepa.convertebitcoin.view.Home;

import java.util.List;

public class ChooseCriptoMoedaAdapter extends BaseAdapter {
    private Context context;
    private String[] listaCriptomeda;

    public ChooseCriptoMoedaAdapter(Home home, String[] listaCriptomeda) {
        this.context = home;
        this.listaCriptomeda = listaCriptomeda;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Criptomoeda criptomoeda = new Criptomoeda();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.choose_criptomoeda_adapter_item_list, null);

        TextView txtNome = (TextView)layout.findViewById(R.id.textViewValorNomeCriptomoeda);
        txtNome.setText(listaCriptomeda[i]);

        ImageView imageView = (ImageView)layout.findViewById(R.id.imageViewCriptoMoeda);
        imageView.setImageResource(criptomoeda.getImagenbyName(listaCriptomeda[i]));

        return layout;
    }
}
