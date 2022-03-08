package br.java.app_loja_ecommerce_master;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ExibirItem extends AppCompatActivity {

    ImageView imageView;
    TextView imagemDetalhes;
    TextView imagemPreco;
    Button pedidoExibirItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_item);

        imageView = findViewById(R.id.item_exibir);
        imagemDetalhes = findViewById(R.id.item_detalhes_exibir);
        imagemPreco = findViewById(R.id.item_preco_exibir);
        pedidoExibirItem = findViewById(R.id.pedido_exibir_item);

        imageView.setImageResource(getIntent().getIntExtra("imagem_id", 00));
        imagemDetalhes.setText(getIntent().getStringExtra("imagem_detalhes"));
        imagemPreco.setText(Integer.toString(getIntent().getIntExtra("item_preco", 00)));

        final String sna = getIntent().getStringExtra("NOME");
        final String sph = getIntent().getStringExtra("TELEFONE");
        final String spa = getIntent().getStringExtra("SENHA");
        final String ca = getIntent().getStringExtra("CHAMARACTIVITY");

        final int img_id = getIntent().getIntExtra("imagem_id", 00);
        final String item_detalhes = getIntent().getStringExtra("item_detalhes");
        final int item_preco = getIntent().getIntExtra("item_preco", 00);

        AddEstoque.getEstoques();

        AddEstoque.databaseReferenceEstoque.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot estoqueSnaphot : snapshot.getChildren()) {
                    EstoqueReg estoqueReg = estoqueSnaphot.getValue(EstoqueReg.class);

                    String item_det = estoqueReg.getItemNome();

                    if (item_det.equals(item_detalhes)) {
                        if (estoqueReg.getAtualEstoqueDisponível() == 0) {
                            pedidoExibirItem.setEnabled(false);
                            pedidoExibirItem.setText("ATUALMENTE FORA DE ESTOQUE");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        pedidoExibirItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExibirItem.this, SeuPedido.class);
                intent.putExtra("PERSONALIZADONOME", sna);
                intent.putExtra("PERSONALIZADOTELEFONE", sph);
                intent.putExtra("PERSONALIZADOSENHA", spa);
                intent.putExtra("ITEMDETALHE", imagemDetalhes.getText());
                intent.putExtra("CHAMAR_ACTIVITY", ca);
                intent.putExtra("imagem_id", img_id);
                intent.putExtra("item_detalhes", item_detalhes);
                intent.putExtra("item_preco", item_preco);
                startActivity(intent);
            }
        });
    }
}