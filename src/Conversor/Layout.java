package Conversor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Layout extends JFrame implements ActionListener {
    JPanel panel;
    JLabel tituloSeleccion, output;
    JButton convertir;
    JTextField input;
    JComboBox<String> menu, valoresIntroducidos, valoresOutput;

    public double aDolar = 1.08701;
    public double aEuro = 0.919958;
    public double vKelvin = 273.15;

    private String seleccionado = "Divisas";

    public String getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(String seleccionado) {
        this.seleccionado = seleccionado;
    }

    public ArrayList<String> valores;

    public void setValores(ArrayList<String> valores) {
        this.valores = valores;
    }

    List<String> elemsDi = Arrays.asList("Euros", "Dólares");
    ArrayList<String> divis = new ArrayList<>(elemsDi);

    List<String> elemsTe = Arrays.asList("Celsius", "Kelvin");
    ArrayList<String> tempe = new ArrayList<>(elemsTe);

    public void setearTipoInput(String tipo) {
        if (Objects.equals(tipo, "Divisas")) {
            setValores(divis);
        } else {
            setValores(tempe);
        }
    }

    public String convertirTipo(String tipoConversion, double valor) {
        if(Objects.equals(tipoConversion, "Divisas")) {
            if(Objects.equals(valoresIntroducidos.getSelectedItem(), "Euros")) {
                return String.valueOf(valor * aDolar);
            } else {
                return String.valueOf(valor * aEuro);
            }
        } else {
            if(Objects.equals(valoresIntroducidos.getSelectedItem(), "Celsius")) {
                return String.valueOf(valor + vKelvin);
            } else {
                return String.valueOf(valor - vKelvin);
            }
        }
    }

    public void traerUI() {
        output.setText("");
        output.setVisible(true);
        input.setVisible(true);
        convertir.setVisible(true);
        valoresIntroducidos.removeAllItems();
        valoresOutput.removeAllItems();
        valoresIntroducidos.addItem(valores.get(0));
        valoresIntroducidos.addItem(valores.get(1));
        valoresOutput.addItem(valores.get(0));
        valoresOutput.addItem(valores.get(1));
        valoresIntroducidos.setVisible(true);
        valoresOutput.setVisible(true);
    }

    Layout() {

        // Labels
        tituloSeleccion = new JLabel("Selecciona el método de conversión");
        tituloSeleccion.setBounds(150, 0, 250, 20);

        output = new JLabel("");
        output.setBounds(190, 140, 100, 20);
        add(output);
        output.setVisible(false);

        // Input valor a convertir
        input = new JTextField("");
        input.setBounds(10, 140, 100, 20);
        add(input);
        input.setVisible(false);

        // Combos introducidos
        valoresIntroducidos = new JComboBox<>();
        valoresIntroducidos.setBounds(10, 100, 100, 20);
        add(valoresIntroducidos);
        valoresIntroducidos.setVisible(false);

        valoresOutput = new JComboBox<>();
        valoresOutput.setBounds(190, 100, 100, 20);
        add(valoresOutput);
        valoresOutput.setVisible(false);

        // ComboBox tipo de conversión
        menu = new JComboBox<>();
        menu.setBounds(150, 50, 250, 20);
        add(menu);
        menu.addItem("Divisas");
        menu.addItem("Temperaturas");
        menu.addActionListener(e -> {
            if(menu.getSelectedItem() == "Divisas") {
                setSeleccionado("Divisas");
                tituloSeleccion.setText("Conversor de " + getSeleccionado());
                setearTipoInput("Divisas");
            } else {
                setSeleccionado("Temperaturas");
                tituloSeleccion.setText("Conversor de " + getSeleccionado());
                setearTipoInput("Temperaturas");
            }
        traerUI();
        });

        // Botones
        convertir = new JButton();
        convertir.setBounds(100, 200, 70, 20);
        add(convertir);
        convertir.addActionListener(e -> output.setText(convertirTipo(getSeleccionado(), Double.parseDouble(input.getText()))));
        convertir.setVisible(false);

        // Panel
        panel = new JPanel();
        panel.setLayout(null);

        panel.add(tituloSeleccion);
        panel.add(menu);

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
