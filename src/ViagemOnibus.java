import java.io.*;
import java.util.*;

public class ViagemOnibus{
    private String linha;
    private int paradas;
    private int passageirosNaoSubiram;
    private Onibus onibus;

    public ViagemOnibus(String linha, int paradas, Onibus onibus) {
        this.linha = linha;
        this.paradas = paradas;
        this.onibus = onibus;
        this.passageirosNaoSubiram = 0;
    }

    public void iniciarViagem() {
        Random random = new Random();
        int totalPassageiros = 0;

        try (FileWriter fw = new FileWriter("viagens.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("Início da Viagem: " + linha + " com ônibus " + onibus.getPlaca());

            for (int i = 0; i < paradas; i++) {
                int passageirosSubindo = random.nextInt(20); // 20 passageiros tentando subir

                if (passageirosSubindo <= onibus.getQuantMax() - totalPassageiros) {
                    totalPassageiros += passageirosSubindo;
                } else {
                    int subiram = onibus.getQuantMax() - totalPassageiros;
                    totalPassageiros += subiram;
                    passageirosNaoSubiram += (passageirosSubindo - subiram);
                }

                pw.println("Parada " + (i + 1) + ": " + totalPassageiros + " passageiros no ônibus. Total que não subiram: " + passageirosNaoSubiram);
            }

            pw.println("Fim da Viagem\n");

        } catch (IOException e) {
            System.out.println("Erro ao registrar a viagem: " + e.getMessage());
        }
    }
}