package integracao2;

import java.util.List;
import java.util.Scanner;

public class PRINCIPAL {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        XDAO xDAO = new XDAO();

        int opcao;
        do {
            System.out.println("Menu:");
            System.out.println("1. Listar");
            System.out.println("2. Inserir");
            System.out.println("3. Excluir");
            System.out.println("4. Atualizar");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    List<X> xList = xDAO.listarTodos();
                    for (X x : xList) {
                        System.out.println(x);
                    }
                    break;
                case 2:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.next();
                    System.out.print("Digite a idade: ");
                    int idade = scanner.nextInt();
                    X novoX = new X(0, nome, idade);
                    xDAO.inserir(novoX);
                    System.out.println("Registro inserido com sucesso.");
                    break;
                case 3:
                    System.out.print("Digite o ID do registro a ser excluído: ");
                    int idExcluir = scanner.nextInt();
                    xDAO.excluir(idExcluir);
                    System.out.println("Registro excluído com sucesso.");
                    break;
                case 4:
                    System.out.print("Digite o ID do registro a ser atualizado: ");
                    int idAtualizar = scanner.nextInt();
                    X xAtualizar = xDAO.consultarPorId(idAtualizar);
                    if (xAtualizar != null) {
                        System.out.print("Digite o novo nome: ");
                        String novoNome = scanner.next();
                        System.out.print("Digite a nova idade: ");
                        int novaIdade = scanner.nextInt();
                        xAtualizar.setNome(novoNome);
                        xAtualizar.setIdade(novaIdade);
                        xDAO.atualizar(xAtualizar);
                        System.out.println("Registro atualizado com sucesso.");
                    } else {
                        System.out.println("Registro não encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("Encerrando o programa...");
                    xDAO.fecharConexao();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}
