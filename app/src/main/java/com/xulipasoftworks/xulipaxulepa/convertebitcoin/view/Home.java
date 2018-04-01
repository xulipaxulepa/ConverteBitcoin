package com.xulipasoftworks.xulipaxulepa.convertebitcoin.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xulipasoftworks.xulipaxulepa.convertebitcoin.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle toggle;
    List<Criptomoeda> criptomoedas = new ArrayList<>();
    String linguagem;
    TextView textViewBrl;
    TextView valorConversaoBtcBrl;


    private boolean verificaConexão() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private void exibeMensagem(String titulo, String mensagem) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(titulo)
                .setMessage(mensagem)
                .setPositiveButton("OK", null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //pega a linguagem do sistema, como String
        linguagem = Resources.getSystem().getConfiguration().locale.getLanguage();
        desabilitarColunaReal();

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (verificaConexão() == false) {
            exibeMensagem(getString(R.string.TituloSemConexão), getString(R.string.MensagemSemConexão));
        }
    }

    public void desabilitarColunaReal() {
        if (!(this.linguagem.equals("pt"))) {
            LinearLayout linearLayoutLabel;
            LinearLayout linearLayoutDados;
            linearLayoutLabel = (LinearLayout) findViewById(R.id.LayoutLabelBtc);
            linearLayoutDados = (LinearLayout) findViewById(R.id.LayoutDadosBtc);
            this.textViewBrl = (TextView)findViewById(R.id.textViewBrl);
            this.valorConversaoBtcBrl = (TextView)findViewById(R.id.textViewValorConversao);
            linearLayoutLabel.removeView(this.textViewBrl);
            linearLayoutDados.removeView(this.valorConversaoBtcBrl);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**@Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.home, menu);
    return true;
    }**/

    /**
     * @Override public boolean onOptionsItemSelected(MenuItem item) {
     * // Handle action bar item clicks here. The action bar will
     * // automatically handle clicks on the Home/Up button, so long
     * // as you specify a parent activity in AndroidManifest.xml.
     * int id = item.getItemId();
     * <p>
     * //noinspection SimplifiableIfStatement
     * if (id == R.id.action_settings) {
     * return true;
     * }
     * <p>
     * return super.onOptionsItemSelected(item);
     * }
     **/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.
        // Create a new fragment and specify the fragment to show based on nav item clicked

        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(this, ShareActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_exit) {
            this.finish();
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);

        // Close the navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }


    public void escondeTeclado() {
        //Faz o teclado virtual sumir.
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void Converter(View view) {
        this.escondeTeclado();

        //Pega o valor digitado pelo usuário.
        EditText valorparaconverter = (EditText) this.findViewById(R.id.editTextValorConversion);
        List<Criptomoeda> moedas;
        ListView mLvListagem;

        TextView valorInseridoBtc = (TextView) findViewById(R.id.textViewValorInserido);
        TextView valorConversaoBtcBrl = (TextView) findViewById(R.id.textViewValorConversao);
        TextView valorConversaoBtcUsd = (TextView) findViewById(R.id.textViewValorConversaoUSD);
        TextView textViewBrl = (TextView) findViewById(R.id.textViewBrl);


        //Verifica se há um valor digitado, caso não houver demonstra uma mensagem.
        if (valorparaconverter.getText().toString().equals("")) {
            exibeMensagem(getString(R.string.TituloSemValor), getString(R.string.MensagemSemValor));
        }//Verifica se o valor digitado não é um ".", caso seja, exibe uma mensagem.
        else if (valorparaconverter.getText().toString().equals(".")) {
            exibeMensagem(getString(R.string.TituloPonto), getString(R.string.MensagemPonto));
        } else {
            //Transforma o valor digitado em String.
            String valor = valorparaconverter.getText().toString();
            valorparaconverter.setText("");

            //Instancia a classe assincrona de conversão.
            mLvListagem = (ListView) findViewById(R.id.listViewOthersConversions);
            moedas = new ArrayList<>();

            MyAsyncTask minhaTarefaAssincrona = new MyAsyncTask(this, valorInseridoBtc, valorConversaoBtcBrl, valorConversaoBtcUsd, valor, moedas, mLvListagem, linguagem, textViewBrl);
            //Executa a função assincrona de conversão.
            minhaTarefaAssincrona.execute(valor);

        }
    }

}
