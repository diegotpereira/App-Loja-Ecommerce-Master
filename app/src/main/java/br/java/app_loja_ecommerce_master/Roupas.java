package br.java.app_loja_ecommerce_master;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Roupas extends AppCompatActivity {

    Button homem;
    Button mulher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roupas);

        homem = findViewById(R.id.btn_homem);
        mulher = findViewById(R.id.btn_mulher);

        final String sna = getIntent().getStringExtra("NOME");
        final String sph = getIntent().getStringExtra("TELEFONE");
        final String spa = getIntent().getStringExtra("SENHA");

        homem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Roupas.this, HomensRoupas.class);
                intent.putExtra("NOME", sna);
                intent.putExtra("TELEFONE", sph);
                intent.putExtra("SENHA", spa);
                startActivity(intent);
            }
        });
    }
}