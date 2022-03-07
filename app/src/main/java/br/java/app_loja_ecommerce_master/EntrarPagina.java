package br.java.app_loja_ecommerce_master;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import br.java.app_loja_ecommerce_master.R;

public class EntrarPagina extends AppCompatActivity {

    EditText telefoneNo;
    EditText senha;
    Button entrar;
    TextView status;
    String ph;
    String pa;
    static String tmpNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar_pagina);

        telefoneNo = (EditText) findViewById(R.id.entrarTelefone);
        senha = (EditText) findViewById(R.id.entrarSenha);
        entrar = (Button) findViewById(R.id.btn_entrar);
        status = (TextView) findViewById(R.id.tvstatus);
        status.setText("");

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarPagina.getUsuario();

                ph = telefoneNo.getText().toString();
                pa = senha.getText().toString();

                RegistrarPagina.databaseUsuarios.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int x = 0;

                        for(DataSnapshot usuarioSnapShot : snapshot.getChildren()) {
                            MembroReg membroReg = usuarioSnapShot.getValue(MembroReg.class);
                            String dpn = membroReg.getUsuarionome();
                            String dph = membroReg.getTelefone();
                            String dpa = membroReg.getSenha();

                            if (dph.equals(ph) && dpa.equals(pa)) {
                                Intent i = new Intent(EntrarPagina.this, PaginaPrincipalActivity.class);
                                i.putExtra("Nome", dpn);
                                i.putExtra("Telefone", dph);
                                i.putExtra("Senha", dpa);
                                i.putExtra("CHAMADAACTIVITY", "EntrarPagina");
                                startActivity(i);
                                x = 1;
                            }
                        }
                        if(x == 0)
                            status.setText("Credenciais inválidas");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}