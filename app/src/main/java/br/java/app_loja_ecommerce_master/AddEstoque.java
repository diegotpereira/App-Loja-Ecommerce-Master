package br.java.app_loja_ecommerce_master;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddEstoque extends AppCompatActivity {

    public static DatabaseReference databaseReferenceEstoque;
    EditText itemID;
    EditText addQuantidade;
    Button btnAtualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estoque);

        itemID = findViewById(R.id.etId);
        addQuantidade = findViewById(R.id.etAdd);
        btnAtualizar = findViewById(R.id.btnAtualizarEstoque);

        databaseReferenceEstoque = FirebaseDatabase.getInstance().getReference("estoquesReg");

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty((itemID.getText()).toString()))itemIdNaoInserido();
                else if (TextUtils.isEmpty((addQuantidade.getText()).toString()))
                    quantidadeNaoInserida();
                else {
                    databaseReferenceEstoque.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int tmp = 0;
                            for(DataSnapshot estoqueSnapshot : snapshot.getChildren()) {
                                EstoqueReg estoqueReg = estoqueSnapshot.getValue(EstoqueReg.class);
                                int id = estoqueReg.getItem_id();

                                if (id == Integer.valueOf(String.valueOf(itemID.getText()))) {
                                    int atual = estoqueReg.getAtualEstoqueDisponível() +
                                            Integer.parseInt(addQuantidade.getText().toString());
                                    System.out.println(Integer.parseInt(addQuantidade.getText()
                                            .toString()));
                                    databaseReferenceEstoque.child(estoqueReg.getId())
                                            .child("atualEstoqueDisponível").setValue(atual);
                                    tmp = 1;

                                    exibirSucesso();
                                    Intent intent = new Intent(AddEstoque.this,
                                            AdminPrincipalPagina.class);
                                    intent.putExtra("CHAMADAACTIVITY", "AddEstoque");
                                    startActivity(intent);
                                    android.os.Process.killProcess(android.os.Process.myPid());

                                    break;
                                }
                            }
                            if(tmp == 1)
                                return;

                            if (tmp == 0) {
                                invalidoItemId();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
    public void itemIdNaoInserido() {
        Toast.makeText(this, "Por Favor Insira o ID do Item", Toast.LENGTH_SHORT).show();
    }
    public void quantidadeNaoInserida() {
        Toast.makeText(this, "Por favor, insira a quantidade para adicionar",
                Toast.LENGTH_SHORT).show();
    }
    public void exibirSucesso() {
        Toast.makeText(this, "Estoque atualizado com sucesso", Toast.LENGTH_SHORT).show();
    }
    public void invalidoItemId() {
        Toast.makeText(this, "Inválido id item", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddEstoque.this, AdminPrincipalPagina.class);
        intent.putExtra("CHAMADAACTIVITY", "AddEstoque1");
        startActivity(intent);
    }
    public static  void getEstoques() {
        databaseReferenceEstoque = FirebaseDatabase.getInstance().getReference("estoquesReg");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}