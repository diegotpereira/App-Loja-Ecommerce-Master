package br.java.app_loja_ecommerce_master;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageViewHolder> {

    private int[] imagens;
    private String[] detalhes;
    int[] precos;
    String sna, sph, spa;
    String chamarActivity;
    private Context context;

    public RecyclerAdapter(int[] imagens, String[] detalhes, int[] precos, Context context, String sna, String sph,
                           String spa, String chamarActivity) {
        this.imagens = imagens;
        this.detalhes = detalhes;
        this.precos = precos;
        this.sna = sna;
        this.sph = sph;
        this.spa = spa;
        this.chamarActivity = chamarActivity;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view, context, imagens, detalhes, precos, sna, sph, spa, chamarActivity);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int imagem_id = imagens[position];
        holder.img.setImageResource(imagem_id);
        holder.img_det.setText(detalhes[position]);
        holder.img_preco.setText(Integer.toString(precos[position]));
    }

    @Override
    public int getItemCount() {
        return imagens.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView img_det, img_preco;
        Context context;
        int[] imagens;
        String[] detalhes;
        int[] precos;
        String sna, sph, spa;
        String chamarActivity;

        public ImageViewHolder(@NonNull View itemView, Context context, int[] imagens,
                               String[] detalhes, int[] precos,
                               String sna, String sph, String spa, String chamarActivity) {
            super(itemView);
            img = itemView.findViewById(R.id.item_imagem);
            img_det = itemView.findViewById(R.id.item_detalhes);
            img_preco = itemView.findViewById(R.id.item_preco);

            itemView.setOnClickListener(this);

            this.context = context;
            this.imagens = imagens;
            this.detalhes = detalhes;
            this.precos = precos;
            this.sna = sna;
            this.sph = sph;
            this.spa = spa;
            this.chamarActivity = chamarActivity;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ExibirItem.class);

            System.out.println(sna + sph + spa + chamarActivity);
            intent.putExtra("imagem_id", imagens[getAdapterPosition()]);
            intent.putExtra("item_detalhes", detalhes[getAdapterPosition()]);
            intent.putExtra("item_preco", precos[getAdapterPosition()]);
            intent.putExtra("NOME", sna);
            intent.putExtra("TELEFONE", sph);
            intent.putExtra("SENHA", spa);
            intent.putExtra("CHAMARACTIVITY", chamarActivity);
            context.startActivity(intent);
        }
    }
}
