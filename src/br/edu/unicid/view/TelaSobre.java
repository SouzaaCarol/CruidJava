package br.edu.unicid.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TelaSobre extends JFrame {

    public TelaSobre() {
        setTitle("Sobre");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(Color.WHITE);

        // FOTO
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/carol.jpg"));
        Image imagemRedimensionada = getScaledImage(icon.getImage(), 120, 120);
        JLabel imagemLabel = new JLabel(new ImageIcon(imagemRedimensionada));
        imagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagemLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // NOME
        JLabel nomeLabel = new JLabel("Ana Carolina de Souza");
        nomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // RGM
        JLabel rgmLabel = new JLabel("RGM: 37981099");
        rgmLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        rgmLabel.setForeground(Color.DARK_GRAY);
        rgmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adicionando os elementos
        painelPrincipal.add(imagemLabel);
        painelPrincipal.add(nomeLabel);
        painelPrincipal.add(rgmLabel);

        add(painelPrincipal, BorderLayout.CENTER);
    }

    // Método para redimensionar imagem com qualidade
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
}
