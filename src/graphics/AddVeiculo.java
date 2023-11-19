package graphics;

import resources.BombaResource;
import resources.CarsResource;
import simulators.PostoGasolinaSimulador;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddVeiculo {

    private JFrame janela = new JFrame("Adicionar Veiculo");
    private JPanel painel = new JPanel();
    private JTextField campoModelo = new JTextField(20);
    private JTextField campoPlaca = new JTextField(20);
    private JComboBox<String> comboBomba;
    private JButton botaoCadastrar = new JButton("Cadastrar");

    AddVeiculo() {
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 400 + 20);

        painel.setLayout(new BorderLayout());
        painel.setBackground(Color.WHITE);

        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(4, 2));
        formulario.add(new JLabel("Modelo:"));
        formulario.add(campoModelo);
        formulario.add(new JLabel("Placa:"));
        formulario.add(campoPlaca);
        formulario.add(new JLabel("Tipo de Combustível:"));

        List<String> opcoesCombustivel = Arrays.asList("Gasolina", "Etanol");
        comboBomba = new JComboBox<>(opcoesCombustivel.toArray(new String[0]));
        formulario.add(comboBomba);

        botaoCadastrar.addActionListener(e -> cadastrarVeiculo());

        formulario.add(botaoCadastrar);

        painel.add(formulario, BorderLayout.CENTER);

        janela.add(painel);

        janela.pack();

        janela.setVisible(true);
    }

    private void cadastrarVeiculo() {
        String modelo = campoModelo.getText();
        String bomba = (String) comboBomba.getSelectedItem();
        String placa = campoPlaca.getText();

        List<CarsResource> veiculos = new ArrayList<>();
        CarsResource carro = new CarsResource(modelo, placa, List.of(bomba));
        veiculos.add(carro);

        List<BombaResource> bombas = new ArrayList<>();
        if (bomba.equals("Gasolina")) {
            BombaResource tipoBomba = new BombaResource("Gasolina", 2.90, 10);
            bombas.add(tipoBomba);
            PostoGasolinaSimulador posto = new PostoGasolinaSimulador(veiculos,bombas);
        }else if(bomba.equals("Etanol")){
            BombaResource tipoBomba = new BombaResource("Etanol", 2.27, 12);
            bombas.add(tipoBomba);
            PostoGasolinaSimulador posto = new PostoGasolinaSimulador(veiculos,bombas);
        }
    }
}

