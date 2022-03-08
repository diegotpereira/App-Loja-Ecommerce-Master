package br.java.app_loja_ecommerce_master;

public class EstoqueReg {

    String itemNome;
    String id;
    int item_id;
    int atualEstoqueDisponível;

    public EstoqueReg() {}

    public EstoqueReg(String itemNome, String id, int item_id, int atualEstoqueDisponível) {
        this.itemNome = itemNome;
        this.id = id;
        this.item_id = item_id;
        atualEstoqueDisponível = atualEstoqueDisponível;
    }

    public String getItemNome() {
        return itemNome;
    }

    public void setItemNome(String itemNome) {
        this.itemNome = itemNome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getAtualEstoqueDisponível() {
        return atualEstoqueDisponível;
    }

    public void setAtualEstoqueDisponível(int atualEstoqueDisponível) {
        atualEstoqueDisponível = atualEstoqueDisponível;
    }
}
