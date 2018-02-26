package com.apps4you.wellington.convertebitcoin.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.apps4you.wellington.convertebitcoin.R;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle toggle;

    private boolean verificaConexão() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private void exibeMensagem(String titulo, String mensagem){
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

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(verificaConexão() == false){
            exibeMensagem(getString(R.string.TituloSemConexão),getString(R.string.MensagemSemConexão));
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

    /**@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }**/

    /**@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }**/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass = null;

        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {

            fragmentClass = HomeFragment.class;

        } else if (id == R.id.nav_conversion) {

            fragmentClass = ConversionFragment.class;

        } else if (id == R.id.nav_cotacao) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_settings) {

        }
        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // drawer.closeDrawer(GravityCompat.START);

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set titulo
        TextView textViewTitulo =  (TextView) findViewById(R.id.textViewTittle);
        textViewTitulo.setText(menuItem.getTitle());

        // Close the navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }

    public void Converter(View view) {
        //Pega o valor digitado pelo usuário.
        EditText valorparaconverter = (EditText) this.findViewById(R.id.editTextValorConversion);

        List<Criptomoeda> moedas;
        ListView lvListagem;

        //Verifica se há um valor digitado, caso não houver demonstra uma mensagem.
        if(valorparaconverter.getText().toString().equals("")){
            exibeMensagem(getString(R.string.TituloSemValor),getString(R.string.MensagemSemValor));
        }//Verifica se o valor digitado não é um ".", caso seja, exibe uma mensagem.
        else if(valorparaconverter.getText().toString().equals(".")){
            exibeMensagem(getString(R.string.TituloPonto),getString(R.string.MensagemPonto));
        } else {
            //Transforma o valor digitado em String.
            String valor = valorparaconverter.getText().toString();

            //Instancia a classe assincrona de conversão.
            lvListagem = (ListView) view.findViewById(R.id.ListViewOthersConversions);
            moedas = new ArrayList<>();
            MyAsyncTask minhaTarefaAssincrona = new MyAsyncTask(this, lvListagem, moedas);

            //Executa a função assincrona de conversão.
            minhaTarefaAssincrona.execute(valor);
        }
    }
}
