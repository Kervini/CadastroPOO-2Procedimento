package model;

public class PessoaJuridica extends Pessoa{
    private String cnpj;
    
    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }
    
    @Override
    public String Exibir(){
        return super.Exibir() + "\nCNPJ: " + this.cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
