package br.java.app_loja_ecommerce_master;

public class Pedidos {

    String personalizadonome;
    String personalizadotelefone;
    String personalizadoaddr;
    String personalizadosenha;
    String spec;
    String id;
    int preco;

    public Pedidos(){}

    public Pedidos(String personalizadonome, String personalizadotelefone, String personalizadoaddr, String personalizadosenha, String spec, String id, int preco) {
        this.personalizadonome = personalizadonome;
        this.personalizadotelefone = personalizadotelefone;
        this.personalizadoaddr = personalizadoaddr;
        this.personalizadosenha = personalizadosenha;
        this.spec = spec;
        this.id = id;
        this.preco = preco;
    }

    public String getPersonalizadonome() {
        return personalizadonome;
    }

    public void setPersonalizadonome(String personalizadonome) {
        this.personalizadonome = personalizadonome;
    }

    public String getPersonalizadotelefone() {
        return personalizadotelefone;
    }

    public void setPersonalizadotelefone(String personalizadotelefone) {
        this.personalizadotelefone = personalizadotelefone;
    }

    public String getPersonalizadoaddr() {
        return personalizadoaddr;
    }

    public void setPersonalizadoaddr(String personalizadoaddr) {
        this.personalizadoaddr = personalizadoaddr;
    }

    public String getPersonalizadosenha() {
        return personalizadosenha;
    }

    public void setPersonalizadosenha(String personalizadosenha) {
        this.personalizadosenha = personalizadosenha;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
}
