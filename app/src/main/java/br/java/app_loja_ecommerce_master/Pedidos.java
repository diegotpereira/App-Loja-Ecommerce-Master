package br.java.app_loja_ecommerce_master;

public class Pedidos {

    String personalnome;
    String personaltelefone;
    String personaladdr;
    String personalsenha;
    String spec;
    String id;
    int preco;

    public Pedidos(){}

    public Pedidos(String personalnome, String personaltelefone, String personaladdr,
                   String personalsenha, String spec, String id, int preco) {
        this.personalnome = personalnome;
        this.personaltelefone = personaltelefone;
        this.personaladdr = personaladdr;
        this.personalsenha = personalsenha;
        this.spec = spec;
        this.id = id;
        this.preco = preco;
    }

    public String getPersonalnome() {
        return personalnome;
    }

    public void setPersonalnome(String personalnome) {
        this.personalnome = personalnome;
    }

    public String getPersonaltelefone() {
        return personaltelefone;
    }

    public void setPersonaltelefone(String personaltelefone) {
        this.personaltelefone = personaltelefone;
    }

    public String getPersonaladdr() {
        return personaladdr;
    }

    public void setPersonaladdr(String personaladdr) {
        this.personaladdr = personaladdr;
    }

    public String getPersonalsenha() {
        return personalsenha;
    }

    public void setPersonalsenha(String personalsenha) {
        this.personalsenha = personalsenha;
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
