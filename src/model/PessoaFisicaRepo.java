package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> listaPessoasFisicas;
    
    public PessoaFisicaRepo(){
        listaPessoasFisicas = new ArrayList<>();
    }
    
    public void inserir(PessoaFisica pessoa){
        listaPessoasFisicas.add(pessoa);
    }
    
    public void alterar(PessoaFisica pessoa){
        excluir(pessoa.getId());
        inserir(pessoa);
    }
    
    public boolean excluir(int idPessoaF){
        for(PessoaFisica pessoa : listaPessoasFisicas){
            if(pessoa.getId() == idPessoaF){
                return listaPessoasFisicas.remove(pessoa);
            }
        }
        return false;
    }
    
    public PessoaFisica obter(int id){
        PessoaFisica pessoaF = null;
        
        for(PessoaFisica pessoa : listaPessoasFisicas){
            if(pessoa.getId() == id){
                pessoaF = pessoa;
            }
        }
        return pessoaF;
    }
    
    public ArrayList<PessoaFisica> obterTodos(){
        return listaPessoasFisicas;
    }
    
    public void persistir(String nomeArquivo) throws IOException{
        File arq = new File(nomeArquivo);
        
        arq.delete();
        arq.createNewFile();
        
        ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
        objOutput.writeObject(listaPessoasFisicas);
        objOutput.close();
        
        //System.out.println("Dados de Pessoa Fisica Armazenados.");
    }
    
    public  void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException{
        File arq = new File(nomeArquivo);
        
        if(arq.exists()){
            ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
            listaPessoasFisicas = (ArrayList<PessoaFisica>)objInput.readObject();
            objInput.close();
        }
        
        //System.out.println("Dados de Pessoa Fisica Recuperados.");
    }
    
    public boolean verificaIdCadastrado(int id){
        for(var pf : listaPessoasFisicas){
            if(pf.getId() == id){
                return true;
            }
        }
        return false;
    }
    
    public boolean verificaListaVazia(){
        return listaPessoasFisicas.isEmpty();
    }
}
