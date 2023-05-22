import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class HanoiUI extends JFrame{
    private JTable tableA;
    private JTable tableB;
    private JTable tableC;
    private JButton bButtonTorreA;
    private JButton cButtonTorreA;
    private JButton aButtonTorreB;
    private JButton cButtonTorreB;
    private JButton aButtonTorreC;
    private JButton bButtonTorreC;
    private JTextField textFieldMinMovimientos;
    private JTextField textFieldNumMovimientos;
    private JButton iniciarButton;
    private JButton reiniciarButton;
    private JButton resolverButton;
    private JComboBox comboBoxNumDiscos;
    private JPanel JPanelPrincipal;
    private HanoiController Hanoi;
    private Stack<String> PilaTorreA;
    private Stack <String> PilaTorreB;
    private Stack <String> PilaTorreC;
    private DefaultTableModel ModeloTorreA;
    private DefaultTableModel ModeloTorreB;
    private DefaultTableModel ModeloTorreC;
public HanoiUI() {

    Hanoi = new HanoiController(tableA,tableB,tableC);
    PilaTorreA = new Stack<String>();
    PilaTorreB = new Stack<String>();
    PilaTorreC = new Stack<String>();
    ModeloTorreA = new DefaultTableModel();
    ModeloTorreB = new DefaultTableModel();
    ModeloTorreC = new DefaultTableModel();

    iniciarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Iniciar();
        }
    });

    bButtonTorreA.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MoverDeA_B();
        }
    });

    reiniciarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            reiniciar();
        }
    });

    cButtonTorreA.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MoverDeA_C();
        }
    });

    aButtonTorreB.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MoverDeB_A();
        }
    });

    cButtonTorreB.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MoverDeB_C();
        }
    });

    aButtonTorreC.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MoverDeC_A();
        }
    });

    bButtonTorreC.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MoverDeC_B();
        }
    });

    resolverButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            /*Hanoi.ResolverPorRecursividad(PilaTorreA.size(),PilaTorreA,PilaTorreB,PilaTorreC);

            dibujarModeloDeTablas(PilaTorreA,ModeloTorreA,tableA);
            dibujarModeloDeTablas(PilaTorreB,ModeloTorreB,tableB);
            dibujarModeloDeTablas(PilaTorreC,ModeloTorreC,tableC);
            mostrarCantMovimientos();*/

            // Obtener el estado actual de las torres
            Stack<String> torreAActual = obtenerEstadoActual(tableA);
            Stack<String> torreBActual = obtenerEstadoActual(tableB);
            Stack<String> torreCActual = obtenerEstadoActual(tableC);

            System.out.println("\nElementos dentro de la Pila Torre A: \n");
            for (String x:PilaTorreA) {
                System.out.println(x+";");
            }
            System.out.println("\nElementos dentro de la Pila Torre B: \n");
            for (String x:PilaTorreB) {
                System.out.println(x+";");
            }
            System.out.println("\nElementos dentro de la Pila Torre C: \n");
            for (String x:PilaTorreC) {
                System.out.println(x+";");
            }
            // Resolver el juego de las Torres de Hanoi desde el estado actual
            resolverTorresDeHanoi(Hanoi.getObjetivo(), torreAActual, torreBActual, torreCActual);

            // Actualizar las pilas y las tablas con el estado final
            PilaTorreA = torreAActual;
            PilaTorreB = torreBActual;
            PilaTorreC = torreCActual;

            System.out.println("\nElementos dentro de la Pila Torre A actualizados: \n");
            for (String x:PilaTorreA) {
                System.out.println(x+";");
            }
            System.out.println("\nElementos dentro de la Pila Torre B actualizados: \n");
            for (String x:PilaTorreB) {
                System.out.println(x+";");
            }
            System.out.println("\nElementos dentro de la Pila Torre C actualizados: \n");
            for (String x:PilaTorreC) {
                System.out.println(x+";");
            }
            //actualizarTablas();




        }
    });
}

    private Stack<String> obtenerEstadoActual(JTable tabla) {
        Stack<String> estadoActual = new Stack<>();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            String disco = (String) model.getValueAt(i, 0);
            if (disco != null && !disco.isEmpty()) {
                estadoActual.push(disco);
            }
        }

        return estadoActual;
    }

    private void resolverTorresDeHanoi(int n, Stack<String> origen, Stack<String> auxiliar, Stack<String> destino) {
        System.out.println(n);
        if(!origen.isEmpty()) {
            origen.sort(((s1, s2) -> s2.compareTo(s1) )); //Ordeno la pila de inicio para tener el primer disco arriba del stack
        }
        if (!auxiliar.isEmpty()) {
            auxiliar.sort((s1, s2) -> s2.compareTo(s1));
        }
        if (!destino.isEmpty()){
           destino.sort((s1, s2) -> s2.compareTo(s1));
        }
        if (n == 1) {
            if (!origen.isEmpty()) {
                String disco = origen.pop();
                destino.push(disco);
                return;
            }

        } else {

            resolverTorresDeHanoi(n -1, origen, destino,auxiliar);
            if (!origen.isEmpty()) {
                String disco = origen.pop();
                destino.push(disco);
            }


            resolverTorresDeHanoi(n -1, auxiliar, origen,destino);
        }



    }


    public JPanel getJPanelPrincipal() {
        return JPanelPrincipal;
    }

    public void LimpiarTables(JTable TablaParaLimpiar) {
        DefaultTableModel ModeloParaLimpiar = (DefaultTableModel) TablaParaLimpiar.getModel();
        int columnaALimpiar = 0;
        for (int i = 0; i < ModeloParaLimpiar.getRowCount(); i++) {
            ModeloParaLimpiar.setValueAt("", i, columnaALimpiar);
        }
        ModeloParaLimpiar.fireTableDataChanged();
        TablaParaLimpiar.setModel(ModeloParaLimpiar);
    }

    public void ordenarTablas() {
        DefaultTableCellRenderer rendererA = new DefaultTableCellRenderer();
        rendererA.setHorizontalAlignment(SwingConstants.CENTER);
        tableA.getColumnModel().getColumn(0).setCellRenderer(rendererA);
        tableB.getColumnModel().getColumn(0).setCellRenderer(rendererA);
        tableC.getColumnModel().getColumn(0).setCellRenderer(rendererA);
    }

    public void moverEntreTorres(Stack<String> StackInicio,Stack<String> StackDestino, JTable TablaInicio,JTable TablaDestino,DefaultTableModel ModeloInicio,DefaultTableModel ModeloDestino) {
        try {
            StackDestino = Hanoi.MoverDiscos(StackInicio,StackDestino); //Actualizo torre B que es la torre destino
            //PilaTorreB = Hanoi.armarTorre(PilaTorreB); //Armo de nuevo la torre B
            if (StackDestino != null) {
                dibujarModeloDeTablas(StackDestino,ModeloDestino,TablaDestino); //Dibujo e inserto la tabla de la torre B
                dibujarModeloDeTablas(StackInicio,ModeloInicio,TablaInicio); //Dibujo e inserto la tabla de la torre A actualizada
                ordenarTablas(); //Ordeno las tablas
                mostrarCantMovimientos();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error manejado en tiempo de ejecución.\n" +
                    "Descripcion del error:\n" +
                    e.getMessage());
        }
    }

    public void moverHaciaTorreC(Stack<String> StackInicio,Stack<String> StackDestino, JTable TablaInicio,JTable TablaDestino,DefaultTableModel ModeloInicio,DefaultTableModel ModeloDestino) {
        try {
            StackDestino = Hanoi.MoverDiscosHaciaTorreC(StackInicio,StackDestino); //Actualizo torre B que es la torre destino
            //PilaTorreB = Hanoi.armarTorre(PilaTorreB); //Armo de nuevo la torre B
            if (PilaTorreB != null) {
                dibujarModeloDeTablas(StackDestino,ModeloDestino,TablaDestino); //Dibujo e inserto la tabla de la torre B
                dibujarModeloDeTablas(StackInicio,ModeloInicio,TablaInicio); //Dibujo e inserto la tabla de la torre A actualizada
                ordenarTablas(); //Ordeno las tablas
                mostrarCantMovimientos();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error manejado en tiempo de ejecución.\n" +
                    "Descripcion del error:\n" +
                    e.getMessage());
        }
    }

    public void dibujarModeloDeTablas(Stack<String> StackPila,DefaultTableModel ModeloTabla, JTable TablaParaInsertar) {

        DefaultTableModel ModeloTablaActualizado =  Hanoi.dibujarTorre(StackPila,ModeloTabla);

        if (TablaParaInsertar.equals(tableA)) {
            tableA.setModel(ModeloTablaActualizado);
        } else if (TablaParaInsertar.equals(tableB)) {
            tableB.setModel(ModeloTablaActualizado);
        } else {
            tableC.setModel(ModeloTablaActualizado);
        }
    }

    public void mostrarCantMovimientos() {
        textFieldNumMovimientos.setText(String.valueOf(Hanoi.getContNumMovimientos()));
    }

    private void Iniciar() {
        try {
            Hanoi.LimpiarNumMovimientos();
            mostrarCantMovimientos();
            LimpiarTables(tableB);
            LimpiarTables(tableC);
            PilaTorreB.clear();
            PilaTorreC.clear();

            Hanoi.setObjetivo(Integer.parseInt(comboBoxNumDiscos.getSelectedItem().toString())); //Seteo el objetivo
            Hanoi.inicializarStackTorres(); //Inicializo las torres

            int MinNumMovements = Hanoi.calcularMinNumMovements(); //Calculo el número mínimo de movimientos
            textFieldNumMovimientos.setText(String.valueOf(Hanoi.getContNumMovimientos())); //Imprimo el número de movimientos
            textFieldMinMovimientos.setText(String.valueOf(MinNumMovements)); //Imprimo el mínimo número de movimientos


            PilaTorreA = Hanoi.armarTorre(PilaTorreA); //Armo la torre mandando una Pila
            //PilaTorreB = Hanoi.armarTorre(PilaTorreB);

            dibujarModeloDeTablas(PilaTorreA,ModeloTorreA,tableA); //Dibujo

            ordenarTablas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error manejado en tiempo de ejecución.\n" +
                    "Descripcion del error:\n" +
                    e.getMessage());

        }
    }

    public void limpiar() {
        comboBoxNumDiscos.setSelectedItem(String.valueOf(Hanoi.getObjetivo())); //Establezco nuevamente mi objetivo
        Hanoi.LimpiarNumMovimientos(); //Metodo limpiar del video
        Hanoi.LimpiarMinNumMovements(); //Metodo limpiar del video
    }

    public void reiniciar() {
        try {

            if (!textFieldMinMovimientos.getText().equals("")) {
                limpiar();
                Iniciar();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error manejado en tiempo de ejecución.\n" +
                    "Descripcion del error:\n" +
                    e.getMessage());
        }
    }

    public void MoverDeA_B() {

        moverEntreTorres(PilaTorreA,PilaTorreB,tableA,tableB,ModeloTorreA,ModeloTorreB);

    }

    public void MoverDeA_C() {

        moverHaciaTorreC(PilaTorreA,PilaTorreC,tableA,tableC,ModeloTorreA,ModeloTorreC);

    }

    public void MoverDeB_A() {

        moverEntreTorres(PilaTorreB,PilaTorreA,tableB,tableA,ModeloTorreB,ModeloTorreA);

    }

    public void MoverDeB_C() {

        moverHaciaTorreC(PilaTorreB,PilaTorreC,tableB,tableC,ModeloTorreB,ModeloTorreC);

    }

    public void MoverDeC_A() {

        moverEntreTorres(PilaTorreC,PilaTorreA,tableC,tableA,ModeloTorreC,ModeloTorreA);

    }

    public void MoverDeC_B() {

        moverEntreTorres(PilaTorreC,PilaTorreB,tableC,tableB,ModeloTorreC,ModeloTorreB);

    }
}
