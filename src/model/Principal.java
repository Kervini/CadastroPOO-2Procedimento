package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String args[]) {
        Scanner entrada = new Scanner(System.in);
        String arqPessoaF = "pessoasfisicas";
        String arqPessoaJ = "pessoasjuridicas";
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
        boolean continuar = true;
        int opcao;

        do {
            System.out.println("========================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("========================================");
            try {
                opcao = entrada.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite apenas numeros inteiros.");
                continue;
            } finally {
                entrada.nextLine();
            }

            switch (opcao) {
                case 1 -> {
                    int id, idade = 0;
                    System.out.println("\nF - Pessoa Fisica | J - Pessoa Juridica:");
                    String tipoPessoa = entrada.nextLine().toUpperCase();

                    if (!("F".equals(tipoPessoa) || "J".equals(tipoPessoa))) {
                        System.out.println("Tipo invalido! Tente novamente.");
                        break;
                    }

                    System.out.println("\nDigite o Id: ");
                    try {
                        id = entrada.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, digite apenas numeros inteiros.");
                        break;
                    } finally {
                        entrada.nextLine();
                    }

                    if (tipoPessoa.equals("F")) {
                        if (repoFisica.verificaIdCadastrado(id)) {
                            System.out.println("Id ja cadastrado!");
                            break;
                        }

                        System.out.println("\nDigite o nome: ");
                        String nome = entrada.nextLine();

                        System.out.println("\nDigite o CPF: ");
                        String cpf = entrada.nextLine();

                        System.out.println("\nDigite a idade: ");
                        try {
                            idade = entrada.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor, digite apenas numeros inteiros.");
                            System.out.println("Cadastro nao efetuado!");
                            break;
                        } finally {
                            entrada.nextLine();
                        }

                        PessoaFisica pf = new PessoaFisica(id, nome, cpf, idade);
                        repoFisica.inserir(pf);
                    } else {
                        if (repoJuridica.verificaIdCadastrado(id)) {
                            System.out.println("Id ja cadastrado!");
                            break;
                        }

                        System.out.println("\nDigite o nome: ");
                        String nome = entrada.nextLine();

                        System.out.println("\nDigite o CNPJ: ");
                        String cnpj = entrada.nextLine();

                        PessoaJuridica pj = new PessoaJuridica(id, nome, cnpj);
                        repoJuridica.inserir(pj);
                    }
                    System.out.println("\nCadastrado com sucesso!");
                }
                case 2 -> {
                    System.out.println("\nAlteracao de dados");
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    String tipoPessoa = entrada.nextLine().toUpperCase();

                    if (!("F".equals(tipoPessoa) || "J".equals(tipoPessoa))) {
                        System.out.println("Tipo invalido! Tente novamente.");
                        break;
                    }

                    int id;

                    if (tipoPessoa.equals("F")) {
                        if (repoFisica.verificaListaVazia()) {
                            System.out.println("Nao existem pessoas fisicas cadastradas.");
                            break;
                        }

                        System.out.println("\nDigite o Id: ");
                        try {
                            id = entrada.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Id invalido! Tente novamente.");
                            break;
                        } finally {
                            entrada.nextLine();
                        }

                        PessoaFisica pf = repoFisica.obter(id);

                        if (pf == null) {
                            System.out.println("Id nao encontrado! Verifique a lista.");
                            break;
                        }

                        System.out.println("\nNome: " + pf.getNome());
                        System.out.println("Digite o nome ou Enter para manter o atual.");
                        String novoNome = entrada.nextLine().trim();
                        if (!novoNome.isEmpty()) {
                            pf.setNome(novoNome);
                        }

                        System.out.println("\nCPF: " + pf.getCpf());
                        System.out.println("Digite o CPF ou enter para manter o atual");
                        String novoCpf = entrada.nextLine().trim();
                        if (!novoCpf.isEmpty()) {
                            pf.setCpf(novoCpf);
                        }

                        System.out.println("\nIdade: " + pf.getIdade());
                        System.out.println("Digite a idade ou enter para manter o atual");
                        String novaIdade = entrada.nextLine();

                        if (!novaIdade.isEmpty()) {
                            try {
                                pf.setIdade(Integer.parseInt(novaIdade));
                            } catch (NumberFormatException e) {
                                System.out.println("Idade invalida! O valor atual foi mantido");
                            }
                        }
                    } else {
                        if (repoJuridica.verificaListaVazia()) {
                            System.out.println("Nao existem pessoas juridicas cadastradas!");
                            break;
                        }

                        System.out.println("\nDigite o Id: ");
                        try {
                            id = entrada.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Id invalido! Tente novamente.");
                            break;
                        } finally {
                            entrada.nextLine();
                        }

                        PessoaJuridica pj = repoJuridica.obter(id);

                        if (pj == null) {
                            System.out.println("Id nao encontrado! Verifique a lista.");
                            break;
                        }

                        System.out.println("\nNome: " + pj.getNome());
                        System.out.println("Digite o nome ou Enter para manter o atual");
                        String novoNome = entrada.nextLine().trim();
                        if (!novoNome.isEmpty()) {
                            pj.setNome(novoNome);
                        }

                        System.out.println("\nCNPJ: " + pj.getCnpj());
                        System.out.println("Digite o CNPJ ou enter para manter o atual");
                        String novoCnpj = entrada.nextLine().trim();
                        if (!novoCnpj.isEmpty()) {
                            pj.setCnpj(novoCnpj);
                        }
                    }
                    System.out.println("\nAlterado com sucesso!");
                }
                case 3 -> {
                    System.out.println("\nExcluir pessoa");
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    String tipoPessoa = entrada.nextLine().toUpperCase();
                    int id;

                    if (!("F".equals(tipoPessoa) || "J".equals(tipoPessoa))) {
                        System.out.println("Tipo invalido! Tente novamente.");
                        break;
                    }

                    if (tipoPessoa.equals("F")) {
                        if (repoFisica.verificaListaVazia()) {
                            System.out.println("Nao existem pessoas fisicas cadastradas.");
                            break;
                        }

                        System.out.println("\nDigite o Id: ");
                        try {
                            id = entrada.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Id invalido! Tente novamente.");
                            break;
                        } finally {
                            entrada.nextLine();
                        }

                        if (repoFisica.excluir(id)) {
                            System.out.println("Excluido com sucesso!");
                        } else {
                            System.out.println("Id nao encontrado!");
                        }
                    } else {
                        if (repoJuridica.verificaListaVazia()) {
                            System.out.println("Nao existem pessoas juridicas cadastradas!");
                            break;
                        }

                        System.out.println("\nDigite o Id: ");
                        try {
                            id = entrada.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Id invalido! Tente novamente.");
                            break;
                        } finally {
                            entrada.nextLine();
                        }

                        if (repoJuridica.excluir(id)) {
                            System.out.println("Excluido com sucesso!");
                        } else {
                            System.out.println("Id nao encontrado!");
                        }
                    }
                }
                case 4 -> {
                    System.out.println("\nBuscar pessoa por ID");

                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    String tipoPessoa = entrada.nextLine().toUpperCase();
                    int id;

                    if (!("F".equals(tipoPessoa) || "J".equals(tipoPessoa))) {
                        System.out.println("Tipo invalido! Tente novamente.");
                        break;
                    }

                    if (tipoPessoa.equals("F")) {
                        if (repoFisica.verificaListaVazia()) {
                            System.out.println("Nao existem pessoas fisicas cadastradas.");
                            break;
                        }

                        System.out.println("\nDigite o Id: ");
                        try {
                            id = entrada.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Id invalido! Tente novamente.");
                            break;
                        } finally {
                            entrada.nextLine();
                        }

                        PessoaFisica pf = repoFisica.obter(id);

                        if (pf == null) {
                            System.out.println("Id nao encontrado! Verifique a lista.");
                            break;
                        }

                        System.out.println("\nId: " + pf.getId());
                        System.out.println("Nome: " + pf.getNome());
                        System.out.println("CPF: " + pf.getCpf());
                        System.out.println("Idade: " + pf.getIdade());

                    } else {
                        if (repoJuridica.verificaListaVazia()) {
                            System.out.println("Nao existem pessoas juridicas cadastradas!");
                            break;
                        }

                        System.out.println("\nDigite o Id: ");
                        try {
                            id = entrada.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Id invalido! Tente novamente.");
                            break;
                        } finally {
                            entrada.nextLine();
                        }

                        if (repoJuridica.excluir(id)) {
                            System.out.println("Excluido com sucesso!");
                        } else {
                            System.out.println("Id nao encontrado!");
                        }

                        PessoaJuridica pj = repoJuridica.obter(id);

                        if (pj == null) {
                            System.out.println("Id nao encontrado! Verifique a lista.");
                            break;
                        }

                        System.out.println("Id: " + pj.getId());
                        System.out.println("Nome: " + pj.getNome());
                        System.out.println("CNPJ: " + pj.getCnpj());
                    }
                }
                case 5 -> {
                    System.out.println("\n=== Pessoas Fisicas cadastradas ===");
                    ArrayList<PessoaFisica> pessoasFisicas = repoFisica.obterTodos();

                    for (PessoaFisica pessoa : pessoasFisicas) {
                        System.out.println(pessoa.Exibir());
                    }

                    System.out.println("\n=== Pessoas Juridicas cadastradas ===");
                    ArrayList<PessoaJuridica> pessoasJuridicas = repoJuridica.obterTodos();

                    for (PessoaJuridica pessoa : pessoasJuridicas) {
                        System.out.println(pessoa.Exibir());
                    }
                }
                case 6 -> {
                    if (repoFisica.verificaListaVazia() && repoJuridica.verificaListaVazia()) {
                        System.out.println("Nao ha registros a serem persistidos.");
                        break;
                    }

                    System.out.println("A persistencia de dados podera subtistuir registro salvos anteriormente.");
                    System.out.println("Digite 1 para confirmar: ");
                    String ok = entrada.nextLine();

                    if (!ok.equals("1")) {
                        System.out.println("Persistencia de dados cancelada!");
                        break;
                    }

                    try {
                        repoFisica.persistir(arqPessoaF);
                        repoJuridica.persistir(arqPessoaJ);
                    } catch (IOException ex) {
                        System.out.printf("Erro: %s", ex.getMessage());
                    }
                    System.out.println("Dados armazenados com sucesso!");
                }
                case 7 -> {
                    try {
                        repoFisica.recuperar(arqPessoaF);
                        repoJuridica.recuperar(arqPessoaJ);
                    } catch (IOException | ClassNotFoundException ex) {
                        System.out.printf("Erro: %s", ex.getMessage());
                    }
                    System.out.println("Dados recuperados com sucesso!");
                }
                case 0 -> {
                    continuar = false;
                    System.out.println("Finalizando o sistema.");
                }
                default -> {
                    System.out.println("Digito invalido!");
                }
            }
        } while (continuar);
    }
}
