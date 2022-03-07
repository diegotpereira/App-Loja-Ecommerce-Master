package br.java.app_loja_ecommerce_master;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.java.app_loja_ecommerce_master.R;

public class RegistrarPagina extends AppCompatActivity {

    EditText etNome;
    EditText etTelefone;
    EditText etSenha;
    Button registrar;

    static DatabaseReference databaseUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pagina);

        databaseUsuarios = FirebaseDatabase.getInstance().getReference("membroReg");

        registrar = (Button) findViewById(R.id.btn_registrar);

        etNome = (EditText) findViewById(R.id.etNome);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etSenha = (EditText) findViewById(R.id.etSenha);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastro();
            }
        });
    }
    public void cadastro() {
        String nome = etNome.getText().toString();
        String telefone = etTelefone.getText().toString();
        String senha = etSenha.getText().toString();
        
        if (TextUtils.isEmpty(nome)) {
            Toast.makeText(this, "Por favor, digite seu nome", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(telefone)) {
            Toast.makeText(this, "Por favor, digite seu número de telefone", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(senha)) {
            Toast.makeText(this, "Por favor, digite sua senha", Toast.LENGTH_SHORT).show();
        } else {
            String id = databaseUsuarios.push().getKey();
            MembroReg membroReg = new MembroReg(id, nome, senha, telefone);
            databaseUsuarios.child(id).setValue(membroReg);

            Toast.makeText(this, "Usuário registrado", Toast.LENGTH_SHORT).show();
        }
    }
    public static void getUsuario() {
        databaseUsuarios = FirebaseDatabase.getInstance().getReference("membroReg");
    }
}