package model;
import java.io.IOException;
import java.util.ArrayList;

public class Principal {
    public static void main(String args[]){
        String arqPessoaF = "pessoasfisicas";
        String arqPessoaJ = "pessoasjuridicas";
        
        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
        
        PessoaFisica pessoa1 = new PessoaFisica(1, "Maria Aparecida", "123456789-10", 25);
        PessoaFisica pessoa2 = new PessoaFisica(2, "Jose Carlos", "987654321-00", 32);
        repo1.inserir(pessoa1);
        repo1.inserir(pessoa2);
        
        try{
            repo1.persistir(arqPessoaF);
        } catch (IOException ex) {
            System.out.printf("Erro: %s", ex.getMessage());
        }
        
        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
        
        try{
            repo2.recuperar(arqPessoaF);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.printf("Erro: %s", ex.getMessage());
        }
        
        ArrayList<PessoaFisica> pessoasFisicas = repo2.obterTodos();
        
        for(PessoaFisica pessoa : pessoasFisicas){
            System.out.println(pessoa.Exibir());
        }
        
        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
        
        PessoaJuridica pessoa3 = new PessoaJuridica(3, "Empresa de Testes", "123456000145-12");
        PessoaJuridica pessoa4 = new PessoaJuridica(4, "Testando Codigos", "321654000165-13");
        repo3.inserir(pessoa3);
        repo3.inserir(pessoa4);
        
        try {
            repo3.persistir(arqPessoaJ);
        } catch (IOException ex) {
            System.out.printf("Erro: %s", ex.getMessage());
        }
        
        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
        
        try{
            repo4.recuperar(arqPessoaJ);
        }catch(IOException | ClassNotFoundException ex){
            System.out.printf("Erro: %s", ex.getMessage());
        }
        
        ArrayList<PessoaJuridica> pessoasJuridicas = repo4.obterTodos();
        
        for(PessoaJuridica pessoa : pessoasJuridicas){
            System.out.println(pessoa.Exibir());
        }
    }
}
