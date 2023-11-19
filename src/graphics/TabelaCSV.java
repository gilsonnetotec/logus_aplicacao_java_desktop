package graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TabelaCSV {

    public TabelaCSV(String caminhoArquivo, String type) {
        String conteudoCSV = lerCSV(caminhoArquivo);
        SwingUtilities.invokeLater(() -> criarJanelaTabela(conteudoCSV, type));
    }


    private static String lerCSV(String caminhoArquivo) {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conteudo.toString();
    }

    private static void criarJanelaTabela(String conteudoCSV, String type) {
        JFrame frame = new JFrame("Tabela CSV");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        List<String> colunas = new ArrayList<>();

        if (type.equals("veiculos")) {
            colunas.add("");
            colunas.add("");
        }else if(type.equals("modelos")){
            colunas.add("");
            colunas.add("");
            colunas.add("");
            colunas.add("");
        }

        DefaultTableModel modelo = new DefaultTableModel();
        for (String coluna : colunas) {
            modelo.addColumn(coluna);
        }

        JTable tabela = new JTable(modelo);

        JScrollPane scrollPane = new JScrollPane(tabela);

        frame.add(scrollPane);

        String[] linhas = conteudoCSV.split("\n");
        for (String linha : linhas) {
            String[] valores = linha.split(";");
            modelo.addRow(new Vector<>(java.util.Arrays.asList(valores)));
        }

        frame.pack();
        frame.setVisible(true);
    }
}

