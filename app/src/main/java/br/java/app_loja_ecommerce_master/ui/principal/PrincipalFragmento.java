package br.java.app_loja_ecommerce_master.ui.principal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.java.app_loja_ecommerce_master.R;

public class PrincipalFragmento extends Fragment {

    private PrincipalViewModel principalViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        principalViewModel = ViewModelProviders.of(this).get(PrincipalViewModel.class);
        View root = inflater.inflate(R.layout.fragmento_principal, container, false);
        final TextView textView = root.findViewById(R.id.texto_principal);
        principalViewModel.getTexto().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}