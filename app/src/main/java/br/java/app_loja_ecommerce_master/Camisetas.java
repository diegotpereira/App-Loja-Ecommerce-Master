package br.java.app_loja_ecommerce_master;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class Camisetas extends AppCompatActivity {

    private RecyclerView recyclerView;
    int[] imagens = {
            R.drawable.t1,
            R.drawable.t2,
            R.drawable.t3,
            R.drawable.t4,
            R.drawable.t5,
            R.drawable.t6,
    };
    String detalhes[] = {
            "Camiseta 1",
            "Camiseta 2",
            "Camiseta 3",
            "Camiseta 4",
            "Camiseta 5",
            "Camiseta 6"
    };
    int[] precos = {
            699,
            1299,
            459,
            899,
            949,
            499
    };
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camisetas);

        recyclerView = findViewById(R.id.rvCamisetas);

        final String sna = getIntent().getStringExtra("NOME");
        final String sph = getIntent().getStringExtra("TELEFONE");
        final String spa = getIntent().getStringExtra("SENHA");

        layoutManager = new GridLayoutManager(this, 1);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(imagens, detalhes, precos, this, sna, sph, spa, "Camisetas");
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        final String sna = getIntent().getStringExtra("NOME");
        final String sph = getIntent().getStringExtra("TELEFONE");
        final String spa = getIntent().getStringExtra("SENHA");

        Intent intent = new Intent(Camisetas.this, HomensRoupas.class);
        intent.putExtra("NOME", sna);
        intent.putExtra("TELEFONE", sph);
        intent.putExtra("SENHA", spa);
        startActivity(intent);
    }
}