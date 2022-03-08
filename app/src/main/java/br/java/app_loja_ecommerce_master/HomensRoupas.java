package br.java.app_loja_ecommerce_master;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomensRoupas extends AppCompatActivity {

    Button camisetas;
    Button calcados;
    Button vestir;
    Button formais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homens_roupas);

        camisetas = findViewById(R.id.Camisetas);
        calcados = findViewById(R.id.Calcados);
        vestir = findViewById(R.id.vestir);
        formais = findViewById(R.id.formais);

        final String sna = getIntent().getStringExtra("Nome");
        final String sph = getIntent().getStringExtra("TELEFONE");
        final String spa = getIntent().getStringExtra("SENHA");

        camisetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomensRoupas.this, Camisetas.class);
                intent.putExtra("NOME", sna);
                intent.putExtra("TELEFONE", sph);
                intent.putExtra("TELEFONE", spa);
                startActivity(intent);
            }
        });
    }
}