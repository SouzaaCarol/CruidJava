import javax.swing.SwingUtilities;
import br.edu.unicid.view.TelaPrincipal;

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        TelaPrincipal tela = new TelaPrincipal();
        tela.setVisible(true);

    });
}

