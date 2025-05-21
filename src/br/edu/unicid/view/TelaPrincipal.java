package br.edu.unicid.view;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import br.edu.unicid.model.*;
import br.edu.unicid.dao.*;

public class TelaPrincipal extends JFrame {

    // Componentes
    private JTextField txtCodLeitor, txtNomeLeitor;
    private JComboBox<String> cmbTipoLeitor;
    private JTextArea txtListar;
    private JLabel lblMensagem;
    private JButton btnNovo, btnSalvar, btnConsultar, btnAlterar, btnExcluir, btnListar;

    // DAO e Leitor
    LeitorDAO dao;
    Leitor leitor;

    public TelaPrincipal() {
        setTitle("Sistema de Cadastro de Leitores");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // MENU - AJUDA
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem menuSobre = new JMenuItem("Sobre");

        menuAjuda.add(menuSobre);
        barraMenu.add(menuAjuda);
        setJMenuBar(barraMenu);

        // AÇÃO DO MENU "Sobre"
        menuSobre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaSobre().setVisible(true);
            }
        });

        // CAMPOS
        JLabel lblCod = new JLabel("Código:");
        lblCod.setBounds(20, 20, 100, 20);
        add(lblCod);

        txtCodLeitor = new JTextField();
        txtCodLeitor.setBounds(120, 20, 100, 20);
        add(txtCodLeitor);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 50, 100, 20);
        add(lblNome);

        txtNomeLeitor = new JTextField();
        txtNomeLeitor.setBounds(120, 50, 200, 20);
        add(txtNomeLeitor);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(20, 80, 100, 20);
        add(lblTipo);

        cmbTipoLeitor = new JComboBox<>(new String[]{"Selecione", "Aluno", "Professor", "Administrativo"});
        cmbTipoLeitor.setBounds(120, 80, 200, 20);
        add(cmbTipoLeitor);

        lblMensagem = new JLabel("");
        lblMensagem.setBounds(20, 110, 400, 20);
        add(lblMensagem);

        // BOTÕES
        btnNovo = new JButton("Novo");
        btnNovo.setBounds(20, 140, 80, 25);
        add(btnNovo);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(110, 140, 80, 25);
        add(btnSalvar);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(200, 140, 100, 25);
        add(btnConsultar);

        btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(310, 140, 80, 25);
        add(btnAlterar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(400, 140, 80, 25);
        add(btnExcluir);

        btnListar = new JButton("Listar");
        btnListar.setBounds(20, 180, 80, 25);
        add(btnListar);

        txtListar = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtListar);
        scroll.setBounds(20, 220, 440, 200);
        add(scroll);

        // AÇÕES DOS BOTÕES

        // Botão Novo
        btnNovo.addActionListener(e -> {
            txtCodLeitor.setText(null);
            txtNomeLeitor.setText(null);
            txtListar.setText(null);
            cmbTipoLeitor.setSelectedIndex(0);
            lblMensagem.setText(null);
        });

        // Botão Salvar
        btnSalvar.addActionListener(e -> {
            try {
                leitor = new Leitor();
                leitor.setCodLeitor(Integer.parseInt(txtCodLeitor.getText()));
                leitor.setNomeLeitor(txtNomeLeitor.getText());
                leitor.setTipoLeitor((String) cmbTipoLeitor.getSelectedItem());

                dao = new LeitorDAO();
                dao.salvar(leitor);
                lblMensagem.setText("Salvo com Sucesso!");
            } catch (Exception e1) {
                lblMensagem.setText("Erro ao Salvar");
            }
        });

        // Botão Consultar
        btnConsultar.addActionListener(e -> {
            try {
                dao = new LeitorDAO();
                int codigo = Integer.parseInt(txtCodLeitor.getText());
                leitor = dao.consultar(codigo);
                txtNomeLeitor.setText(leitor.getNomeLeitor());

                String tipo = leitor.getTipoLeitor();
                switch (tipo) {
                    case "Aluno":
                        cmbTipoLeitor.setSelectedIndex(1);
                        break;
                    case "Professor":
                        cmbTipoLeitor.setSelectedIndex(2);
                        break;
                    case "Administrativo":
                        cmbTipoLeitor.setSelectedIndex(3);
                        break;
                }
            } catch (Exception e1) {
                lblMensagem.setText("Erro ao Consultar");
            }
        });

        // Botão Alterar
        btnAlterar.addActionListener(e -> {
            try {
                leitor = new Leitor();
                leitor.setCodLeitor(Integer.parseInt(txtCodLeitor.getText()));
                leitor.setNomeLeitor(txtNomeLeitor.getText());
                leitor.setTipoLeitor((String) cmbTipoLeitor.getSelectedItem());

                dao = new LeitorDAO();
                dao.alterar(leitor);
                lblMensagem.setText("Alterado com Sucesso!");
            } catch (Exception e1) {
                lblMensagem.setText("Erro ao Alterar");
            }
        });

        // Botão Excluir
        btnExcluir.addActionListener(e -> {
            try {
                dao = new LeitorDAO();
                int codigo = Integer.parseInt(txtCodLeitor.getText());
                dao.excluir(codigo);
                lblMensagem.setText("Excluído com Sucesso!");
            } catch (Exception e1) {
                lblMensagem.setText("Erro ao Excluir");
            }
        });

        // Botão Listar
        btnListar.addActionListener(e -> {
            try {
                dao = new LeitorDAO();
                List<Leitor> lista = dao.listarTodos();
                txtListar.setText("");
                for (Leitor leitor : lista) {
                    txtListar.append("Código: " + leitor.getCodLeitor() + "\n");
                    txtListar.append("Nome: " + leitor.getNomeLeitor() + "\n");
                    txtListar.append("Tipo: " + leitor.getTipoLeitor() + "\n\n");
                }
            } catch (Exception e1) {
                lblMensagem.setText("Erro ao Listar");
            }
        });
    }

    public static void main(String[] args) {
        new TelaPrincipal().setVisible(true);
    }
}
