package com.apps4you.wellington.convertebitcoin.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;
import android.widget.TextView;

import com.apps4you.wellington.convertebitcoin.R;
import com.apps4you.wellington.convertebitcoin.adapter.CriptoMoedaAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xulipasoftworks on 24/02/2018.
 */

public class MyAsyncTask extends AsyncTask<String,String,String> {

    //Declarando variaveis.
    ProgressDialog progessDialo;
    Context context;
    String valorparaconverter;
    List<Criptomoeda> moedas = new ArrayList<>();
    ListView mListaDeMoedas;
    Float resultado;
    TextView valorInseridoBtc;
    TextView valorConversaoBtcBrl;
    TextView valorConversaoBtcUsd;

    String valor;



    //Construtor da classe.
    public MyAsyncTask(Context context, TextView valorInseridoBtc, TextView valorConversaoBtcBrl,
                       TextView valorConversaoBtcUsd,
                       String valor, List<Criptomoeda> moedas, ListView mListaDeMoedas){
        this.valorInseridoBtc = valorInseridoBtc;
        this.valorConversaoBtcBrl = valorConversaoBtcBrl;
        this.valorConversaoBtcUsd = valorConversaoBtcUsd;
        this.context = context;
        this.valor = valor;
        this.mListaDeMoedas = mListaDeMoedas;

    }

    //Responsavel por carregar o Objeto JSON
    public static String getJSONFromAPI(String url){
        String retorno = "";
        try {
            URL apiEnd = new URL(url);
            int codigoResposta;
            HttpURLConnection conexao;
            InputStream is;

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect();

            codigoResposta = conexao.getResponseCode();
            if(codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST){
                is = conexao.getInputStream();
            }else{
                is = conexao.getErrorStream();
            }

            retorno = converterInputStreamToString(is);
            is.close();
            conexao.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return retorno;
    }

    @NonNull
    private static String converterInputStreamToString(InputStream is){
        StringBuffer buffer = new StringBuffer();
        try{
            BufferedReader br;
            String linha;

            br = new BufferedReader(new InputStreamReader(is));
            while((linha = br.readLine())!=null){
                buffer.append(linha);
            }

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return buffer.toString();
    }

    @Override
    protected void onPreExecute() {
        this.progessDialo = ProgressDialog.show(context, context.getString(R.string.TituloEspera), context.getString(R.string.MensagemEspera), false, false);
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            //Atribui o valor de params na posição 0(neste caso o valor a ser computado) a valorparaconverter.
            this.valorparaconverter = strings[0];

            //Chama o método para conectar com a API
            //Passa como parametro a URL da API em formato String
            return this.getJSONFromAPI("https://api.coinmarketcap.com/v1/ticker/?convert=BRL");

            } catch (Exception e){

                String erro = e.getMessage();

                return erro;
        }
    }

    @Override
    protected void onPostExecute(String result){
        JSONArray json = null;
        JSONObject mJsonObject = new JSONObject();

        try {
            json = new JSONArray(result);
            for (int i=0; i < json.length(); i++) {
                mJsonObject = (JSONObject) json.get(i);
                Criptomoeda moeda = new Criptomoeda();
                moeda.setId(mJsonObject.getString("id"));
                moeda.setNome(mJsonObject.getString("name"));
                moeda.setPrice_btc(mJsonObject.getString("price_btc"));
                moeda.setPrice_usd_unit(String.format(mJsonObject.getString("price_usd"), "%.2f"));
                moeda.setVariacao(mJsonObject.getString("percent_change_24h"));
                resultado = Float.parseFloat(mJsonObject.getString("price_usd"))*Float.parseFloat(valorparaconverter);
                moeda.setPrice_usd(String.format(resultado.toString(), "%.2f"));
                moeda.setPrice_brl_unit(String.format(mJsonObject.getString("price_brl"), "%.2f"));
                resultado = Float.parseFloat(mJsonObject.getString("price_brl"))*Float.parseFloat(valorparaconverter);
                moeda.setPrice_brl(String.format(resultado.toString(), "%.2f"));
                this.moedas.add(moeda);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.exibirDados();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.progessDialo.hide();
    }

    @SuppressLint("ResourceType")
    public void exibirDados() throws Exception {
//        ArrayAdapter<Criptomoeda> adapter;
//        int adapterLayout = android.R.layout.simple_list_item_1;
//
//        // O objeto ArrayAdapter sabe converter listas ou vetores em View
//        adapter = new ArrayAdapter<Criptomoeda>(context, adapterLayout, this.moedas);

        // Associacao do Adapter a ListView
        this.mListaDeMoedas.setAdapter(new CriptoMoedaAdapter(context, this.moedas));

        Criptomoeda cripto = moedas.get(0);
        new AlertDialog.Builder(context)
                .setTitle(R.string.TituloConversão)
                .setMessage(context.getString(R.string.MensagemConversão)+" "+cripto.getPrice_brl())
                .setPositiveButton("OK", null).show();

        this.valorInseridoBtc.setText(valor);
        this.valorConversaoBtcBrl.setText(cripto.getPrice_brl());
        this.valorConversaoBtcUsd.setText(cripto.getPrice_usd());

    }

}
