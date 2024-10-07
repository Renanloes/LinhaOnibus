import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<Onibus> frota = new ArrayList<Onibus>();
    public static ArrayList<LinhaOnibus> linha = new ArrayList<LinhaOnibus>();

    public static void Cadastrar() {
        Scanner input = new Scanner(System.in);

        System.err.println("Qual a placa do Ônibus?");
        String placa = input.nextLine();
        System.err.println("Qual é a lotação máxima?");
        int quantMax = input.nextInt();

        Onibus busao = new Onibus(placa, quantMax);
        frota.add(busao);

        try {
            FileWriter bloco = new FileWriter("frota.txt", true);
            PrintWriter anotar = new PrintWriter(bloco);
            anotar.println(busao);
            anotar.close();

            System.out.println("Cadastro finalixado com sucesso!");

        } catch (IOException e) {
            System.out.println("Falha na execução do arquivo");
        }
    }

    private static void ListarFrota() {
        try (BufferedReader leitor = new BufferedReader(new FileReader("frota.txt"))) {
            if (leitor.readLine() == null) {
                System.out.println("Nenhum onibus registrado");
                return;
            }

            String linha;
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha + "\n");
            }

        } catch (IOException e) {
            System.out.println("Erro na execução do código");
        }
    }

    private static void Remover() {
        Scanner input = new Scanner(System.in);

        System.out.println("Qual a placa do ônibus que deseja remover?");
        String cancelarPedido = input.nextLine();

        String caminhoArquivoTemporario = "temporarioOnibus.txt";

        try (BufferedReader leitor = new BufferedReader(new FileReader("frota.txt"));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivoTemporario))) {

            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (!linha.contains(cancelarPedido)) {
                    escritor.write(linha);
                    escritor.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao manipular arquivos: " + e.getMessage());
            return;
        }

        File arquivoOriginal = new File("frota.txt");
        File arquivoTemporario = new File(caminhoArquivoTemporario);

        if (arquivoOriginal.delete() && arquivoTemporario.renameTo(arquivoOriginal)) {
            System.out.println("Ônibus com placa " + cancelarPedido + " removido com sucesso.");
        } else {
            System.out.println("Falha ao cancelar o pedido " + cancelarPedido + ".");
        }
    }

    public static void Onibus() {
        Scanner input = new Scanner(System.in);

        int escolha = 0;
        String listaOnibus = "-----------Menu-Ônibus------------- \n"
                           + "1-Cadastrar novo ônibus \n"
                           + "2-Listar frota de ônibus\n"
                           + "3-Remover ônibus da frota\n"
                           + "4-Sair \n";

        while (escolha != 4) {
            System.out.println(listaOnibus);
            escolha = input.nextInt();

            switch (escolha) {
                case 1:
                    Cadastrar();
                    break;
                case 2:
                    ListarFrota();
                    break;
                case 3:
                    Remover();
                    break;
                case 4:
                    System.err.println("Saindo do menu-ônibus");
                    break;
                default:
                    System.err.println("Valor Inválido!");
                    break;
            }
        }
    }

    public static void CadastrarLinha() {
        Scanner input = new Scanner(System.in);

        System.out.println("Qual o início e o destino da linha?");
        String inicioFim = input.nextLine();
        System.out.println("Qual a quantidade de paradas?");
        int paradas = input.nextInt();
        input.nextLine();

        LinhaOnibus linhabusao = new LinhaOnibus(inicioFim, paradas);
        linha.add(linhabusao);

        try (FileWriter bloco = new FileWriter("linhas.txt", true);
             PrintWriter anotar = new PrintWriter(bloco)) {

            anotar.println(linhabusao);
            System.out.println("Linha cadastrada com sucesso!");

        } catch (IOException e) {
            System.out.println("Falha na execução do arquivo");
        }
    }

    public static void ListarLinhas() {
        try (BufferedReader leitor = new BufferedReader(new FileReader("linhas.txt"))) {
            String linha;
            boolean empty = true;
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
                empty = false;
            }
            if (empty) {
                System.out.println("Nenhuma linha registrada");
            }
        } catch (IOException e) {
            System.out.println("Erro na execução do código");
        }
    }

    private static void RemoverLinha() {
        Scanner input = new Scanner(System.in);

        System.out.println("A rota da linha que deseja remover?");
        String removerLinha = input.nextLine();

        String caminhoArquivoTemporario = "temporarioLinha.txt";

        try (BufferedReader leitor = new BufferedReader(new FileReader("linhas.txt"));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivoTemporario))) {

            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (!linha.contains(removerLinha)) {
                    escritor.write(linha);
                    escritor.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao manipular arquivos: " + e.getMessage());
            return;
        }

        File arquivoOriginal = new File("linhas.txt");
        File arquivoTemporario = new File(caminhoArquivoTemporario);

        if (arquivoOriginal.delete() && arquivoTemporario.renameTo(arquivoOriginal)) {
            System.out.println("Linha " + removerLinha + " removido com sucesso.");
        } else {
            System.out.println("Falha ao cancelar o pedido " + removerLinha + ".");
        }
    }

    public static void linhaOnibus() {
        Scanner input = new Scanner(System.in);

        int escolha = 0;
        String listaOnibus = "-----------Menu-Linha------------- \n"
                           + "1-Cadastrar nova Linha \n"
                           + "2-Listar linhas\n"
                           + "3-Remover uma linha\n"
                           + "4-Sair \n";

        while (escolha != 4) {
            System.out.println(listaOnibus);
            escolha = input.nextInt();

            switch (escolha) {
                case 1:
                    CadastrarLinha();
                    break;
                case 2:
                    ListarLinhas();
                    break;
                case 3:
                    RemoverLinha();
                    break;
                case 4:
                    System.err.println("Saindo do menu");
                    break;
                default:
                    System.err.println("Valor Inválido!");
                    break;
            }
        }
    }

    public static void viagemOnibus() {
        Scanner input = new Scanner(System.in);

        System.out.println("Escolha a linha para iniciar a viagem:");
        ListarLinhas();

        System.out.println("Digite o nome da linha:");
        String linhaEscolhida = input.nextLine();

        LinhaOnibus linhaSelecionada = null;
        for (LinhaOnibus l : linha) {
            if (l.toString().contains(linhaEscolhida)) {
                linhaSelecionada = l;
                break;
            }
        }

        if (linhaSelecionada == null) {
            System.out.println("Linha não encontrada.");
            return;
        }

        System.out.println("Escolha o ônibus para a viagem:");
        ListarFrota();

        System.out.println("Digite a placa do ônibus:");
        String placaEscolhida = input.nextLine();

        Onibus onibusSelecionado = null;
        for (Onibus o : frota) {
            if (o.getPlaca().equals(placaEscolhida)) {
                onibusSelecionado = o;
                break;
            }
        }

        if (onibusSelecionado == null) {
            System.out.println("Ônibus não encontrado.");
            return;
        }

        ViagemOnibus viagem = new ViagemOnibus(linhaSelecionada.toString(), linhaSelecionada.getParadas(), onibusSelecionado);
        viagem.iniciarViagem();
    }

    private static void Menu() {
        Scanner input = new Scanner(System.in);

        int choose = 0;
        String lista = "-----------------Menu------------------ \n"
                     + "1-ônibus \n"
                     + "2-Linha \n"
                     + "3-Viagem \n"
                     + "4-Sair";

        while (choose != 4) {
            System.out.println(lista);
            choose = input.nextInt();

            switch (choose) {
                case 1:
                    Onibus();
                    break;
                case 2:
                    linhaOnibus();
                    break;
                case 3:
                    viagemOnibus();
                    break;
                case 4:
                    System.err.println("Saindo do menu");
                    break;
                default:
                    System.err.println("Valor Inválido!");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Menu();
        System.err.println("Conclusão do Programa");
    }
}
