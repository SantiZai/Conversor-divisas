package Conversor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Objects;

public class Layout extends JFrame implements ActionListener {
    JPanel panel;
    JLabel tituloSeleccion, divisas, temperaturas, resultado;
    JButton btnAceptar, btnConvertir;
    JTextField inputValor;
    JComboBox<String> menu, tiposDivisas, tiposDivisasIntroducidos, tiposTemperaturas;

    Layout() {
        // Labels
        tituloSeleccion = new JLabel("Selecciona el método de conversión");
        tituloSeleccion.setBounds(150, 0, 250, 20);

        resultado = new JLabel("");
        resultado.setBounds(150, 250, 250, 20);
        resultado.setVisible(false);

        // Inputs
        inputValor = new JTextField();
        inputValor.setBounds(150, 50, 250, 20);
        inputValor.setVisible(false);

        // ComboBox
        menu = new JComboBox<String>();
        menu.setBounds(150, 50, 250, 20);
        add(menu);
        menu.addItem("Divisas");
        menu.addItem("Temperaturas");

        tiposDivisasIntroducidos = new JComboBox<String>();
        tiposDivisasIntroducidos.setBounds(10, 100, 100, 20);
        add(tiposDivisasIntroducidos);
        tiposDivisasIntroducidos.addItem("Euros");
        tiposDivisasIntroducidos.addItem("Dólares");
        tiposDivisasIntroducidos.addItem("Pesos");
        tiposDivisasIntroducidos.setVisible(false);


        tiposDivisas = new JComboBox<String>();
        tiposDivisas.setBounds(200, 100, 100, 20);
        add(tiposDivisas);
        tiposDivisas.addItem("Euros");
        tiposDivisas.addItem("Dólares");
        tiposDivisas.addItem("Pesos");
        tiposDivisas.setVisible(false);

        // Botones
        btnAceptar = new JButton();
        btnAceptar.setBounds(150, 300, 250, 20);
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String seleccionado = (String) menu.getSelectedItem();
                if(Objects.equals(seleccionado, "Divisas")) {
                    tituloSeleccion.setVisible(false);
                    menu.setVisible(false);
                    inputValor.setVisible(true);
                    tiposDivisasIntroducidos.setVisible(true);
                    tiposDivisas.setVisible(true);
                    btnConvertir.setVisible(true);
                }
            }
        });

        btnConvertir = new JButton();
        btnConvertir.setBounds(150, 300, 250, 20);
        btnConvertir.setText("Convertir");
        btnConvertir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ingresoSeleccionado = (String) tiposDivisasIntroducidos.getSelectedItem();
                String conversionSeleccionado = (String) tiposDivisas.getSelectedItem();
                resultado.setVisible(true);
                if (!(Objects.equals(inputValor.getText(), ""))) {
                    if (Objects.equals(ingresoSeleccionado, conversionSeleccionado)) {
                        JOptionPane.showMessageDialog(null, "Las divisas deben ser distintas");
                    } else {
                        int ingresado = Integer.parseInt(inputValor.getText());
                        resultado.setText(String.valueOf(ingresado));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingresar un valor");
                }
            }
        });
        btnConvertir.setVisible(false);

        // Panel
        panel = new JPanel();
        panel.setLayout(null);

        panel.add(inputValor);
        panel.add(tiposDivisas);
        panel.add(tiposDivisasIntroducidos);
        panel.add(tituloSeleccion);
        panel.add(menu);
        panel.add(resultado);
        panel.add(btnConvertir);
        panel.add(btnAceptar);

        add(panel);
        setSize(600, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        Layout layout = new Layout();
        layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) { }

}
