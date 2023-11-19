package simulators;

import resources.BombaResource;
import resources.CarsResource;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PostoGasolinaSimulador {

    public PostoGasolinaSimulador(List<CarsResource> veiculos,  List<BombaResource> bombas){
        carregarVeiculos(veiculos);
        carregarBombas(bombas);

        simularAbastecimento(veiculos, bombas);

        gerarResultados(veiculos);

        gerarResumo(bombas);
    }

    private static void carregarVeiculos(List<CarsResource> veiculos) {

    }

    private static void carregarBombas(List<BombaResource> bombas) {

    }

    private static void simularAbastecimento(List<CarsResource> veiculos, List<BombaResource> bombas) {

        bombas.sort(Comparator.comparing(BombaResource::getPrecoLitro));

        for (CarsResource veiculo : veiculos) {

            List<BombaResource> bombasCompativeis = bombas.stream()
                    .filter(bomba -> veiculo.getTiposCombustivel().contains(bomba.getTipoCombustivel()))
                    .collect(Collectors.toList());

            if (!bombasCompativeis.isEmpty()) {

                BombaResource bombaEscolhida = bombasCompativeis.get(0);

                double quantidadeAbastecida = realizarAbastecimento(veiculo, bombaEscolhida);

                System.out.println("[" + new Date() + "] Veículo modelo " + veiculo.getModelo() +
                        ", placa " + veiculo.getPlaca() + " foi abastecido com " +
                        quantidadeAbastecida + " litros de " + bombaEscolhida.getTipoCombustivel() + ".");
            } else {
                System.out.println("[" + new Date() + "] Veículo modelo " + veiculo.getModelo() +
                        ", placa " + veiculo.getPlaca() + " não pôde ser abastecido devido à falta de combustível compatível nas bombas.");
            }
        }
    }

    private static double realizarAbastecimento(CarsResource veiculo, BombaResource bomba) {
        return bomba.getVelocidadeAbastecimento();
    }

    private static void gerarResultados(List<CarsResource> veiculos) {
    }

    private static void gerarResumo(List<BombaResource> bombas) {
    }
}

