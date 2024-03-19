package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> listaPessoasJuridicas;
    
    public PessoaJuridicaRepo(){
        listaPessoasJuridicas = new ArrayList<>();
    }
    
    public void inserir(PessoaJuridica pessoa){
        listaPessoasJuridicas.add(pessoa);
    }
    
    public void alterar(PessoaJuridica pessoa){
        excluir(pessoa.getId());
        inserir(pessoa);
    }
    
    public boolean excluir(int idPessoaJ){
        for(PessoaJuridica pessoa : listaPessoasJuridicas){
            if(pessoa.getId() == idPessoaJ){
                return listaPessoasJuridicas.remove(pessoa);
            }
        }
        return false;
    }
    
    public PessoaJuridica obter(int id){
        PessoaJuridica pessoaJ = null;
        
        for(PessoaJuridica pessoa : listaPessoasJuridicas){
            if(pessoa.getId() == id){
                pessoaJ = pessoa;
            }
        }
        return pessoaJ;
    }
    
    public ArrayList<PessoaJuridica> obterTodos(){
        return listaPessoasJuridicas;
    }
    
    public void persistir(String nomeArquivo) throws IOException{
        File arq = new File(nomeArquivo);
        
        arq.delete();
        arq.createNewFile();
        
        ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
        objOutput.writeObject(listaPessoasJuridicas);
        objOutput.close();
        
        //System.out.println("Dados de Pessoa Juridica Armazenados.");
    }
    
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException{
        File arq = new File(nomeArquivo);
        
        if(arq.exists()){
            ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
            listaPessoasJuridicas = (ArrayList<PessoaJuridica>)objInput.readObject();
            objInput.close();
        }
        
        //System.out.println("Dados de Pessoa Juridica Recuperados.");
    }
    
    public boolean verificaIdCadastrado(int id){
        for(var pj : listaPessoasJuridicas){
            if(pj.getId() == id){
                return true;
            }
        }
        return false;
    }
    
    public boolean verificaListaVazia(){
        return listaPessoasJuridicas.isEmpty();
    }
}
