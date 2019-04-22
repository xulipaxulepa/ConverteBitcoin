package com.xulipasoftworks.xulipaxulepa.convertebitcoin.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.xulipasoftworks.xulipaxulepa.convertebitcoin.R;
import com.xulipasoftworks.xulipaxulepa.convertebitcoin.adapter.ChooseCriptoMoedaAdapter;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle toggle;
    List <Criptomoeda> criptomoedas = new ArrayList<>();
    String linguagem;
    String criptocoinChoosed;
    TextView textViewBrl;
    TextView valorConversaoBtcBrl;
    ListView mListaDeMoedas = null;


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

        //pega a linguagem do sistema, como String e desabilita a coluna Real caso não seja PT
        linguagem = Resources.getSystem().getConfiguration().locale.getLanguage();
        desabilitarColunaReal();
    }


    public void showAlertDialogListView(View view) {
        criarAdapterView();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", null);
        builder.setView(this.mListaDeMoedas);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void criarAdapterView() {
        this.mListaDeMoedas = new ListView(this);
        String[] moedas = {"Bitcoin", "Ethereum", "Ripple", "IOTA", "Dash"};

        // Associacao do Adapter a ListView
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.choose_criptomoeda_adapter_item_list, R.id.textViewValorNomeCriptomoeda, moedas);
        ChooseCriptoMoedaAdapter adapter = new ChooseCriptoMoedaAdapter(this, moedas);
        this.mListaDeMoedas.setAdapter(adapter);
        this.mListaDeMoedas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViewGroup vg = (ViewGroup) view;
                TextView txt = vg.findViewById(R.id.textViewValorNomeCriptomoeda);
                criptocoinChoosed = txt.getText().toString();
                Toast.makeText(Home.this, txt.getText().toString(), Toast.LENGTH_SHORT).show();
                modificarColunaCritoCoin();
            }
        });

    }

    private void modificarColunaCritoCoin() {
        TextView labelValorInseridoCriptoCoin = findViewById(R.id.textViewBtc);
        EditText editText = findViewById(R.id.editTextValorConversion);

        labelValorInseridoCriptoCoin.setText(criptocoinChoosed);
        //editText.setHint(R.string.mensagem_editText+""+criptocoinChoosed);
        editText.setHint(criptocoinChoosed);
    }

    public void desabilitarColunaReal() {
        if (!(this.linguagem.equals("pt"))) {
            LinearLayout linearLayoutLabel = findViewById(R.id.LayoutLabelBtc);
            LinearLayout  linearLayoutDados = findViewById(R.id.LayoutDadosBtc);
            this.textViewBrl =  findViewById(R.id.textViewBrl);
            this.valorConversaoBtcBrl = findViewById(R.id.textViewValorConversao);
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
        EditText valorparaconverter = this.findViewById(R.id.editTextValorConversion);
        List<Criptomoeda> moedas;
        ListView mLvListagem;

        TextView valorInseridoCriptoCoin = findViewById(R.id.textViewValorInserido);
        TextView valorConversaoBtcBrl = findViewById(R.id.textViewValorConversao);
        TextView valorConversaoBtcUsd = findViewById(R.id.textViewValorConversaoUSD);
        TextView textViewBrl = findViewById(R.id.textViewBrl);


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

            MyAsyncTask minhaTarefaAssincrona = new MyAsyncTask(this, valorInseridoCriptoCoin, valorConversaoBtcBrl, valorConversaoBtcUsd, valor, moedas, mLvListagem, linguagem, textViewBrl, criptocoinChoosed);
            //Executa a função assincrona de conversão.
            minhaTarefaAssincrona.execute(valor);

        }
    }

}
