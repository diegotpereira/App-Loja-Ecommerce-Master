package br.java.app_loja_ecommerce_master.ui.principal;

import androidx.lifecycle.*;

public class PrincipalViewModel extends ViewModel {

    private MutableLiveData<String> mTexto;

    public PrincipalViewModel() {
        mTexto = new MutableLiveData<>();
        mTexto.setValue("");
    }
    public LiveData<String> getTexto() {
        return mTexto;
    }
}
