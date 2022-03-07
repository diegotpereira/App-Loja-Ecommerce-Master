package br.java.app_loja_ecommerce_master.ui;

public class MembroReg {

    String usuarionome;
    String senha;
    String telefone;
    String id;

    public MembroReg() {
    }

    public MembroReg(String usuarionome, String senha, String telefone, String id) {
        this.usuarionome = usuarionome;
        this.senha = senha;
        this.telefone = telefone;
        this.id = id;
    }

    public String getUsuarionome() {
        return usuarionome;
    }

    public void setUsuarionome(String usuarionome) {
        this.usuarionome = usuarionome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
