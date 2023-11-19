package graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.imageio.ImageIO;

public class Interface {

    private static final int WINDOW_WIDTH = 1612;
    private static final int WINDOW_HEIGHT = 533;
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 30;

    private JFrame janela = new JFrame("Logus Tecnologia");
    private JButton botaoVeiculos = new JButton("Importar Veículos");
    private JButton botaoModelos = new JButton("Importar Modelos");
    private JButton botaoAddVeiculo = new JButton("Adicionar Novo");
    private JLabel telaFundo  = new JLabel("");
    private JPanel painel = new JPanel();

    public Interface() {
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(WINDOW_WIDTH,WINDOW_HEIGHT+20);

        painel.setLayout(new BorderLayout());
        painel.setBackground(Color.WHITE);

        botaoVeiculos.setBounds(10, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
        botaoModelos.setBounds(300, 10, BUTTON_WIDTH, BUTTON_HEIGHT);

        botaoAddVeiculo.setBounds(600, 10, BUTTON_WIDTH, BUTTON_HEIGHT);

        painel.add(botaoVeiculos);
        painel.add(botaoModelos);
        painel.add(botaoAddVeiculo);

        unirDadosCSV();


        try {
            BufferedImage img = ImageIO.read(getClass().getResource("/logusAlta.png"));
            telaFundo.setIcon(new ImageIcon(img));
            telaFundo.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            painel.add(telaFundo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        janela.add(painel);

        janela.setVisible(true);

        botaoVeiculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importarCSV("veiculos");
            }
        });

        botaoModelos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importarCSV("modelos");
            }
        });

        botaoAddVeiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               AddVeiculo addVeiculo = new AddVeiculo();
            }
        });
    }

    private void unirDadosCSV() {
       try {
           List<String[]> veiculosCsv = load("src/veiculos.csv");
           List<String[]> modelosCsv = load("src/modelos.csv");

           String text = "";

           List<String> listaFormatada = new ArrayList<>();

           for (String[] modeloInfo : modelosCsv) {
               text = modeloInfo[0];
               for (int i = 1; i < modeloInfo.length; i++) {
                   if(modeloInfo[i].isBlank()){
                   }
                   text += "," + modeloInfo[i];
               }
               text = text.replace(",",".");
               listaFormatada.add(text);
           }


           Map<String, String[]> modelosMap = new HashMap<>();
           for (String listInfo : listaFormatada) {
               String[] partes = listInfo.split(";");
               if (partes.length >= 2) {
                   modelosMap.put(partes[0], partes);
               }
           }

           Map<String, JLabel> valorLabels = new HashMap<>();
           int y = 90;
           int x = 150;
           int x2 = 200;
           int posicao = 1;
           for (String[] veiculo : veiculosCsv) {
               if (veiculo.length >= 1) {
                   String[] partesVeiculo = veiculo[0].split(";");
                   if (partesVeiculo.length >= 2) {
                       String modelo = partesVeiculo[0];
                       String placa = partesVeiculo[1];
                       String[] modeloInfo = modelosMap.get(modelo);
                       if (modeloInfo != null) {

                           JLabel labelModelo = new JLabel(modelo);
                           labelModelo.setBounds((x * posicao), y, 300, 20);
                           labelModelo.setFont(new Font("Serif", Font.BOLD, 11));
                           labelModelo.setForeground(Color.RED);
                           valorLabels.put(modelo, labelModelo);
                           painel.add(labelModelo);

                           JLabel labelPlaca = new JLabel(placa);
                           labelPlaca.setBounds((x * (posicao + 1)), y, 300, 20);
                           labelPlaca.setFont(new Font("Serif", Font.BOLD, 11));
                           labelPlaca.setForeground(Color.RED);
                           valorLabels.put(placa, labelPlaca);
                           painel.add(labelPlaca);
                            for (String valor : modeloInfo) {
                                if(posicao >= 2 ){
                                    JLabel label = new JLabel(valor);
                                    label.setBounds(( x2*posicao), y, 300, 20);
                                    label.setFont(new Font("Serif", Font.BOLD, 11));
                                    label.setForeground(Color.RED);
                                    valorLabels.put(valor, label);
                                    painel.add(label);
                                }else{
                                    x = 150;
                                }
                               if(posicao < 4){
                                   posicao++;
                               }else{
                                   posicao = 1;
                                   y += 30;
                               }
                           }
                       }
                   }
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    private List<String[]> load(String caminho) throws IOException {
        List<String[]> linhasCSV = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] colunas = linha.split(",");
                linhasCSV.add(colunas);
            }
        }

        return linhasCSV;
    }

    private void importarCSV(String type) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos CSV", "csv");
        fileChooser.setFileFilter(filter);

        int resultado = fileChooser.showOpenDialog(janela);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            String caminhoArquivo = fileChooser.getSelectedFile().getAbsolutePath();
            if("veiculos".equals(type)){
                TabelaCSV tabelaCSV = new TabelaCSV(caminhoArquivo, "veiculos");
            }else if("modelos".equals(type)){
                TabelaCSV tabelaCSV = new TabelaCSV(caminhoArquivo, "modelos");
            }
        }
    }


}
