package br.java.app_loja_ecommerce_master;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.java.app_loja_ecommerce_master.R;

public class PaginaPrincipalActivity extends AppCompatActivity {

    Button roupas;
    Button eletronicos;
    Button livros;
    Button outrosItens;

    private AppBarConfiguration mAppBarConfiguracao;
    static DatabaseReference databaseReferencePedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguracao = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawerLayout)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguracao);
        NavigationUI.setupWithNavController(navigationView, navController);

        roupas = findViewById(R.id.roupas);
        final String sna = getIntent().getStringExtra("NOME");
        final String sph = getIntent().getStringExtra("TELEFONE");
        final String spa = getIntent().getStringExtra("SENHA");

        final String ca = getIntent().getStringExtra("CHAMADAACTIVITY");
        if (ca.equals("EntrarPagina"))
            Toast.makeText(this, "Olá , " + sna + "!", Toast.LENGTH_SHORT).show();
        else if(ca.equals("SeuPedido"))
            Toast.makeText(this, "Pedido feito com sucesso!", Toast.LENGTH_SHORT).show();

        roupas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaginaPrincipalActivity.this, Roupas.class);
                intent.putExtra("NOME", sna);
                intent.putExtra("TELEFONE", sph);
                intent.putExtra("SENHA", spa);
                startActivity(intent);
            }
        });
    }
    public static void getPedido() {
        databaseReferencePedidos = FirebaseDatabase.getInstance().getReference("pedidos");
    }
}