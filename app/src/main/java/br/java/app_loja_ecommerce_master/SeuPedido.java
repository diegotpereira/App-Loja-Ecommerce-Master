package br.java.app_loja_ecommerce_master;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SeuPedido extends AppCompatActivity {

    TextView cnome;
    TextView ctelefone;
    TextView pedidospec;
    TextView pedidoErro;
    TextView pedidopreco;
    EditText addr;
    Button plpedido;
    static DatabaseReference databaseReferencePedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seu_pedido);

        cnome = (TextView) findViewById(R.id.personal_nome);
        ctelefone = (TextView) findViewById(R.id.personal_telefone);
        pedidospec = (TextView) findViewById(R.id.item_detalhe);
        pedidopreco = (TextView) findViewById(R.id.item_preco);
        addr = (EditText) findViewById(R.id.et_add);
        plpedido = (Button) findViewById(R.id.btn_plpedido);
        pedidoErro = (TextView) findViewById(R.id.pedido_erro);
        pedidoErro.setText("");

        databaseReferencePedidos = FirebaseDatabase.getInstance().getReference("pedidos");

        final String n = getIntent().getStringExtra("PERSONALIZADONOME");
        final String ph = getIntent().getStringExtra("PERSONALIZADOTELEFONE");
        final String pa = getIntent().getStringExtra("PERSONALIZADOSENHA");
        final String it = getIntent().getStringExtra("ITEMDETALHE");
        final int itp = getIntent().getIntExtra("item_preco", 00);

        cnome.setText(n);
        ctelefone.setText(ph);
        pedidospec.setText(it);
        pedidopreco.setText(Integer.toString(itp));

        plpedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(addr.getText().toString())) {
                    pedidoErro.setText("Por favor, digite seu endereço");
                } else {
                    AddEstoque.getEstoques();
                    AddEstoque.databaseReferenceEstoque.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot estoqueSnapshot : snapshot.getChildren()) {
                                EstoqueReg estoqueReg = estoqueSnapshot.getValue(EstoqueReg.class);

                                if (estoqueReg.getItemNome().equals(it)) {
                                    int atual = estoqueReg.getAtualEstoqueDisponível();
                                    atual--;
                                    AddEstoque.databaseReferenceEstoque.child(estoqueReg.getId())
                                            .child("atualEstoqueDisponível").setValue(atual);
                                    String id = databaseReferencePedidos.push().getKey();
                                    Pedidos pedidos = new Pedidos(id, it, n, ph, addr.getText()
                                            .toString(), pa, itp);
                                    databaseReferencePedidos.child(id).setValue(pedidos);
                                    exibir();
                                    Intent i = new Intent(SeuPedido.this,
                                            PaginaPrincipalActivity.class);
                                    i.putExtra("NOME", n);
                                    i.putExtra("TELEFONE", ph);
                                    i.putExtra("SENHA", pa);
                                    i.putExtra("CHAMADAACTIVITY", "SeuPedido");
                                    startActivity(i);
                                    android.os.Process.killProcess(android.os.Process.myPid());

                                    break;
                                }
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
    public void exibir() {
        Toast.makeText(this, "Pedido feito com sucesso", Toast.LENGTH_SHORT).show();
    }
    public static void getPedido() {
        databaseReferencePedidos = FirebaseDatabase.getInstance().getReference("pedidos");
    }

    @Override
    public void onBackPressed() {
        final String n = getIntent().getStringExtra("PERSONALIZADONOME");
        final String ph = getIntent().getStringExtra("PERSONALIZADOTELEFONE");
        final String pa = getIntent().getStringExtra("PERSONALIZADOSENHA");
        final String ca = getIntent().getStringExtra("CAHAMANDO_ACTIVITY");
        final int img_id = getIntent().getIntExtra("imagem_id", 00);
        final int img_preco = getIntent().getIntExtra("item_preco", 00);
        final String item_detalhes = getIntent().getStringExtra("item_detalhes");

        Intent intent = new Intent(SeuPedido.this, ExibirItem.class);
        intent.putExtra("NOME", n);
        intent.putExtra("TELEFONE", ph);
        intent.putExtra("SENHA", pa);
        intent.putExtra("CHAMANDO_ACTIVITY", ca);
        intent.putExtra("imagem_id", img_id);
        intent.putExtra("item_detalhes", item_detalhes);
        intent.putExtra("item_preco", img_preco);
        startActivity(intent);
    }
}