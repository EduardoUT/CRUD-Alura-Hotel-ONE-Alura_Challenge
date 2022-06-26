/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mx.com.alurahotel.view;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import mx.com.alurahotel.util.ColoresComponentesUtil;
import java.awt.Image;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mx.com.alurahotel.controller.HuespedController;
import mx.com.alurahotel.controller.ReservaController;
import mx.com.alurahotel.modelo.Huesped;
import mx.com.alurahotel.modelo.Reserva;
import mx.com.alurahotel.util.ConvertirFecha;
import mx.com.alurahotel.util.ListarNacionalidadesUtil;
import mx.com.alurahotel.util.ValidarFormulariosUtil;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Busqueda extends javax.swing.JFrame {

    int xMouse;
    int yMouse;
    private final int margenColumna = 2;
    private DefaultTableModel modeloTablaHuespedes;
    private DefaultTableModel modeloTablaReservas;
    private final HuespedController huespedController;
    private final ReservaController reservaController;
    Reservas r = new Reservas();
    private long diasTranscurridos;

    /**
     * Creates new form Busqueda
     */
    public Busqueda() {
        initComponents();
        this.huespedController = new HuespedController();
        this.reservaController = new ReservaController();
        configurarColoresComponentes();
        cargarTablaHuespedes();
        cargarTablaReservas();
        configurarAnchoColumnasTabla(tablaHuespedes, tablaReservas, margenColumna);
        seleccionNacionalidad.setModel(new DefaultComboBoxModel<>(ListarNacionalidadesUtil.filtrarNacionalidades()));
        alternarVisualizacionCamposTablas();
        jLabelInstrucionesHuesped.setVisible(true);
        seleccionNacionalidad.setVisible(true);
        fechaNacimiento.setVisible(true);
        alternarEdicionFechasReservas();
    }

    private void configurarColoresComponentes() {
        setBackground(ColoresComponentesUtil.TRANSPARENTE);
        btnCerrar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
        btnMinimizar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
        btnBuscar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
        btnActualizar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
        btnEliminar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
        btnCancelar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
        btnMenuUsuario.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
        btnAyuda.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
    }

    /**
     * Cuando alguna de las tabla es visualizada al dar click en las pestañas,
     * sus campos que permiten alterarlas es visualizado, a fin de evitar que lo
     * campos se solapen o encimen con los de la otra tabla.
     */
    private void alternarVisualizacionCamposTablas() {
        if (tablaHuespedes.isShowing()) {
            jLabelInstrucionesHuesped.setVisible(true);
            seleccionNacionalidad.setVisible(true);
            fechaNacimiento.setVisible(true);
        } else {
            jLabelInstrucionesHuesped.setVisible(false);
            seleccionNacionalidad.setVisible(false);
            fechaNacimiento.setVisible(false);
        }

        if (tablaReservas.isShowing()) {
            jLabelInstrucionesReserva.setVisible(true);
            fechaCheckIn.setVisible(true);
            fechaCheckOut.setVisible(true);
            seleccionFormaPago.setVisible(true);
        } else {
            jLabelInstrucionesReserva.setVisible(false);
            fechaCheckIn.setVisible(false);
            fechaCheckOut.setVisible(false);
            seleccionFormaPago.setVisible(false);
        }
    }

    /**
     * Cuando los campos de fecha en la tabla reservas están vacíos, estos se
     * deshabilitan.
     */
    private void alternarEdicionFechasReservas() {
        if (fechaCheckIn.getDate() == null && fechaCheckOut.getDate() == null) {
            fechaCheckIn.setEnabled(false);
            fechaCheckOut.setEnabled(false);
        } else {
            fechaCheckIn.setEnabled(true);
            fechaCheckOut.setEnabled(true);
        }
    }

    /**
     *
     * @param fechaEntrada - Fecha obtenida del JDateChooser.
     * @param fechaSalida - Fecha obtenida del JDateChooser.
     * @return - Devuelve los días transcurridos entre dos fechas de tipo long.
     */
    private long calcularDiasTranscurridos(JDateChooser fechaEntrada, JDateChooser fechaSalida) {
        LocalDate fechDate = ConvertirFecha.convertirDateALocalDate(fechaEntrada.getDate());
        LocalDate date = ConvertirFecha.convertirDateALocalDate(fechaSalida.getDate());
        return diasTranscurridos = ChronoUnit.DAYS.between(fechDate, date);
    }

    /**
     * Mensaje de confirmación para la cancelación de la actualización de algún
     * registro en el momento de la edición de la tabla Reservas antes de
     * efectuar la acción en la base de datos.
     *
     * @param evt
     */
    private void cancelarActualizacionRegistroReservas(java.awt.event.MouseEvent evt) {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(
                this,
                "¿Desea cancelar la actualización de registro actual?\n"
                + "Los cambios efectuados en la tabla se reestablerecán.",
                "Confirmar cancelación de actualización de registro.",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                opciones,
                "Aceptar"
        );
        if (eleccion == JOptionPane.YES_OPTION) {
            evt.consume();
            limpiarTablaRegistroReservas();
            cargarTablaReservas();
            configurarAnchoColumnasTabla(tablaReservas, tablaHuespedes, margenColumna);
        }
    }

    /**
     * Mensaje de confirmación para la cancelación de la actualización de algún
     * registro en el momento de la edición de la tabla Huespedes antes de
     * efectuar la acción en la base de datos.
     *
     * @param evt
     */
    private void cancelarActualizacionRegistroHuespedes(java.awt.event.MouseEvent evt) {
        /**
         * "¿Desea cancelar la actualización de registro actual?\n" + "Los
         * cambios efectuados en la tabla se reestablerecán.",
         */
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(
                this,
                "¿Desea cancelar la actualización de registro actual?\n"
                + "Los cambios efectuados en la tabla se reestablerecán.",
                "Confirmar cancelación de actualización de registro.",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                opciones,
                "Aceptar"
        );
        if (eleccion == JOptionPane.YES_OPTION) {
            evt.consume();
            limpiarTablaRegistroHuespedes();
            cargarTablaHuespedes();
            configurarAnchoColumnasTabla(tablaHuespedes, tablaReservas, margenColumna);
        }
    }

    private void limpiarTablaRegistroHuespedes() {
        modeloTablaHuespedes.getDataVector().clear();
    }

    private void limpiarTablaRegistroReservas() {
        modeloTablaReservas.getDataVector().clear();
    }

    /**
     * Modifica la seleccion de nacionalidad a ser actualizada en el registro de
     * la fila seleccionada en la tabla.
     */
    private void modificarNacionalidadEnTablaHuespedes() {
        int fila = tablaHuespedes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Para actualizar la nacionalidad "
                    + "de un registro en la tabla,\ndebe seleccionar primero una fila.");
        } else {
            String seleccion = seleccionNacionalidad.getSelectedItem().toString();
            tablaHuespedes.setValueAt(seleccion, fila, 4);

        }
    }

    /**
     * Modifica la fecha de nacimiento a ser actualizada en el registro de la
     * fila seleccionada en la tabla.
     */
    private void modificarFechaNacimientoEnTablaHuespedes() {
        int fila = tablaHuespedes.getSelectedRow();
        if (fila > 0) {
            Date fechaNac = Date.valueOf(ConvertirFecha.convertirDateALocalDate(fechaNacimiento.getDate()));
            tablaHuespedes.setValueAt(fechaNac, fila, 3);
        }
    }

    /**
     * Modifica la fecha de entrada a ser actualizada en el registro de la fila
     * seleccionada en la tabla.
     */
    private void modificarFechaEntradaEnTablaReservas() {
        int fila = tablaReservas.getSelectedRow();
        if (fila > 0) {
            Date fechaEntrada = Date.valueOf(ConvertirFecha.convertirDateALocalDate(fechaCheckIn.getDate()));
            tablaReservas.setValueAt(fechaEntrada, fila, 1);
        }
    }

    /**
     * Modifica la fecha de salida a ser actualizada en el registro de la fila
     * seleccionada en la tabla.
     */
    private void modificarFechaSalidaEnTablaReservas() {
        int fila = tablaReservas.getSelectedRow();
        if (fila > 0) {
            Date fechaNac = Date.valueOf(ConvertirFecha.convertirDateALocalDate(fechaCheckOut.getDate()));
            tablaReservas.setValueAt(fechaNac, fila, 2);
        }
    }

    /**
     * Modifica la selección del tipo de pago a ser actualizado en el registro
     * de la fila seleccionada en la tabla.
     */
    private void modificarSeleccionFormaPagoTablaReservas() {
        int fila = tablaReservas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Para actualizar el tipo de pago "
                    + "de un registro en la tabla,\ndebe seleccionar primero una fila.");
        } else {
            String seleccion = seleccionFormaPago.getSelectedItem().toString();
            tablaReservas.setValueAt(seleccion, fila, 4);
        }
    }
    
    private void calcularValorReservas(int fila) {
            BigDecimal valorTasaReservaPorDia = new BigDecimal("550.99");
            BigDecimal valorReserva = new BigDecimal("0.0"); //Retornar en este if
            System.out.println("Nuevo valor entrada; " + fechaCheckIn.getDate());
            /*
            LocalDate fechDate = ConvertirFecha.convertirDateALocalDate(fechaCheckIn.getDate());
            LocalDate date = ConvertirFecha.convertirDateALocalDate(fechaCheckOut.getDate());
            diasTranscurridos = ChronoUnit.DAYS.between(fechDate, date);
             */
            calcularDiasTranscurridos(fechaCheckIn, fechaCheckOut);
            if (diasTranscurridos > 0) {
                System.out.println(diasTranscurridos);
                BigDecimal diasReservados = new BigDecimal(diasTranscurridos);
                valorReserva = diasReservados.multiply(valorTasaReservaPorDia);
                System.out.println(valorReserva);
                tablaReservas.setValueAt(valorReserva, fila, 3);
            } else {
                ValidarFormulariosUtil.desplegarMensajeError(
                        "Error en el cálculo de la Reserva.",
                        "No es posible cálcular reservas si la"
                        + " fecha de Check-Out es menor o igual a la fecha de \n"
                        + " Check-In, ya que el cálculo se realiza por días."
                );
                tablaReservas.setValueAt(valorReserva, fila, 3);
            }
    }

    /**
     * Toma las tablas a las que se desea ajustar el tamaño de sus columnas, y
     * por medio de un ciclo for, cuenta el número de columnas que posee, lo
     * pasa al método ajustarAnchoColumnas() para realizar el ajuste automático.
     *
     * El modelo de la tabla debe poseer las siguientes configuraciones para que
     * surta efecto:
     *
     * Las columnas deben tener habilitada la opción autoResizeMode ->
     * ALL_COLUMNS.
     *
     * Puede habilitar la opción de TableHeader -> ResizeMode, pero no tendría
     * sentido si no se desea romper el ajuste automático que la función
     * proporciona.
     *
     * @param tablaUno - Tabla a ser ajustada.
     * @param tablaDos - Tabla a ser ajustada.
     * @param margen - Margen de la columna.
     */
    private void configurarAnchoColumnasTabla(JTable tablaUno, JTable tablaDos, int margen) {
        for (int indiceColumna = 0; indiceColumna < tablaUno.getColumnCount(); indiceColumna++) {
            ajustarAnchoColumnas(tablaUno, indiceColumna, margen);
        }

        for (int indiceColumna = 0; indiceColumna < tablaDos.getColumnCount(); indiceColumna++) {
            ajustarAnchoColumnas(tablaDos, indiceColumna, margen);
        }
    }

    /**
     * Permite ajustar el ancho de las columnas y las filas de las tablas,
     * acorde al contenido más largo, a fin de mostrar completamente toda la
     * información de cada fila.
     *
     * @param tabla - Tabla a ser ajustada.
     * @param indiceColumna - Índice tomado del método
     * configurarAnchoColumnasTabla().
     * @param margenColumna - Margen de la columna.
     */
    private void ajustarAnchoColumnas(JTable tabla, int indiceColumna, int margenColumna) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) tabla.getColumnModel();
        TableColumn col = colModel.getColumn(indiceColumna);
        int ancho;
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = tabla.getTableHeader().getDefaultRenderer();
        }
        Component component = renderer.getTableCellRendererComponent(tabla, col.getHeaderValue(), false, false, 0, 0);
        ancho = component.getPreferredSize().width;
        for (int fila = 0; fila < tabla.getRowCount(); fila++) {
            renderer = tabla.getCellRenderer(fila, indiceColumna);
            component = renderer.getTableCellRendererComponent(tabla, tabla.getValueAt(fila, indiceColumna), false, false, fila, indiceColumna);
            ancho = Math.max(ancho, component.getPreferredSize().width);
        }
        ancho += 2 * margenColumna;
        col.setPreferredWidth(ancho);
    }

    /**
     * Obtuebe la lista de registros de huespedes obtenidos en la base de datos
     * al modelo de la tabla.
     */
    private void cargarTablaHuespedes() {
        modeloTablaHuespedes = (DefaultTableModel) tablaHuespedes.getModel();
        List<Huesped> listaHuespedes = this.huespedController.listar();
        listaHuespedes.forEach((huesped) -> {
            modeloTablaHuespedes.addRow(
                    new Object[]{
                        huesped.getIdHuesped(),
                        huesped.getNombre(),
                        huesped.getApellido(),
                        huesped.getFechaNacimiento(),
                        huesped.getNacionalidad(),
                        huesped.getTelefono(),
                        huesped.getIdReserva()
                    }
            );
        });
    }

    /**
     * Obtuebe la lista de registros de reservas obtenidos en la base de datos
     * al modelo de la tabla.
     */
    private void cargarTablaReservas() {
        modeloTablaReservas = (DefaultTableModel) tablaReservas.getModel();
        List<Reserva> listaReservas = this.reservaController.listar();
        listaReservas.forEach((reserva) -> {
            modeloTablaReservas.addRow(
                    new Object[]{
                        reserva.getId_Reserva(),
                        reserva.getFechaEntrada(),
                        reserva.getFechaSalida(),
                        reserva.getValorReserva(),
                        reserva.getFormaPago()
                    }
            );
        });
    }

    /**
     * Ejecuta la actualización de la información en la base de datos, posee
     * validaciones si se modifican los valores en la tabla.
     */
    private void actualizarRegistroHuesped() {
        int fila = tablaHuespedes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
        } else {
            String nombre = String.valueOf(tablaHuespedes.getValueAt(fila, 1));
            String apellido = String.valueOf(tablaHuespedes.getValueAt(fila, 2));
            String telefono = String.valueOf(tablaHuespedes.getValueAt(fila, 5));
            if (ValidarFormulariosUtil.esFormularioHuespedValido(nombre, apellido, fechaNacimiento, telefono)) {
                JOptionPane.showMessageDialog(null, "Registro actualizado éxitosamente.");
            }
        }
    }

    /**
     * Para logo de escritorio.
     */
    @Override
    public Image getIconImage() {
        Image retImage = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("mx/com/alurahotel/imagenes/lupa2.png"));
        return retImage;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new JPanelTransparente();
        btnMinimizar = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JLabel();
        jLabelIconoHotelAlura = new javax.swing.JLabel();
        jLabelTituloVentanaBuscar = new javax.swing.JLabel();
        campoBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JLabel();
        panelTablas = new javax.swing.JTabbedPane();
        scrollTablaHuespedes = new javax.swing.JScrollPane();
        tablaHuespedes = new javax.swing.JTable();
        scrollTablaReservas = new javax.swing.JScrollPane();
        tablaReservas = new javax.swing.JTable();
        btnActualizar = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JLabel();
        btnMenuUsuario = new javax.swing.JLabel();
        jLabelInstrucionesHuesped = new javax.swing.JLabel();
        fechaNacimiento = new com.toedter.calendar.JDateChooser();
        seleccionNacionalidad = new javax.swing.JComboBox<>();
        btnAyuda = new javax.swing.JLabel();
        jLabelInstrucionesReserva = new javax.swing.JLabel();
        fechaCheckIn = new com.toedter.calendar.JDateChooser();
        fechaCheckOut = new com.toedter.calendar.JDateChooser();
        seleccionFormaPago = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);

        panelPrincipal.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelPrincipalMouseDragged(evt);
            }
        });
        panelPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelPrincipalMousePressed(evt);
            }
        });
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMinimizar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMinimizar.setForeground(new java.awt.Color(204, 204, 204));
        btnMinimizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMinimizar.setText("-");
        btnMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimizar.setOpaque(true);
        btnMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseExited(evt);
            }
        });
        panelPrincipal.add(btnMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(888, 0, 60, 30));

        btnCerrar.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(204, 204, 204));
        btnCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCerrar.setText("x");
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.setOpaque(true);
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarMouseExited(evt);
            }
        });
        panelPrincipal.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(948, 0, 60, 30));

        jLabelIconoHotelAlura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIconoHotelAlura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/Ha-100px.png"))); // NOI18N
        panelPrincipal.add(jLabelIconoHotelAlura, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 14, -1, -1));

        jLabelTituloVentanaBuscar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTituloVentanaBuscar.setForeground(new java.awt.Color(12, 138, 199));
        jLabelTituloVentanaBuscar.setText("Sistema de Búsqueda");
        panelPrincipal.add(jLabelTituloVentanaBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 48, 841, -1));

        campoBuscar.setBackground(new java.awt.Color(60, 63, 65));
        campoBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoBuscar.setForeground(new java.awt.Color(204, 204, 204));
        campoBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        panelPrincipal.add(campoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 108, 322, 31));

        btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/lupa2.png"))); // NOI18N
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setOpaque(true);
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarMouseExited(evt);
            }
        });
        panelPrincipal.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(914, 98, 56, 41));

        panelTablas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelTablasMouseClicked(evt);
            }
        });

        tablaHuespedes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Huesped", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Teléfono", "ID Reserva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaHuespedes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaHuespedes.setSelectionBackground(new java.awt.Color(12, 138, 199));
        tablaHuespedes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tablaHuespedes.getTableHeader().setResizingAllowed(false);
        tablaHuespedes.getTableHeader().setReorderingAllowed(false);
        tablaHuespedes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaHuespedesMouseClicked(evt);
            }
        });
        scrollTablaHuespedes.setViewportView(tablaHuespedes);

        panelTablas.addTab("Huéspedes", new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/persona.png")), scrollTablaHuespedes); // NOI18N

        tablaReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Reserva", "Fecha de Entrada", "Fecha de Salida", "Total", "Forma de Pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaReservas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaReservas.setSelectionBackground(new java.awt.Color(12, 138, 199));
        tablaReservas.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tablaReservas.getTableHeader().setResizingAllowed(false);
        tablaReservas.getTableHeader().setReorderingAllowed(false);
        tablaReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaReservasMouseClicked(evt);
            }
        });
        scrollTablaReservas.setViewportView(tablaReservas);

        panelTablas.addTab("Reservas", new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/calendario.png")), scrollTablaReservas); // NOI18N

        panelPrincipal.add(panelTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 220, 978, 305));

        btnActualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/editar-texto.png"))); // NOI18N
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setOpaque(true);
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizarMouseExited(evt);
            }
        });
        panelPrincipal.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(686, 531, 60, 40));

        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/deletar.png"))); // NOI18N
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setOpaque(true);
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });
        panelPrincipal.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(764, 531, 60, 40));

        btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/cancelar.png"))); // NOI18N
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setOpaque(true);
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarMouseExited(evt);
            }
        });
        panelPrincipal.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 531, 60, 40));

        btnMenuUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMenuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/cerrar-sesion 32-px.png"))); // NOI18N
        btnMenuUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuUsuario.setOpaque(true);
        btnMenuUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMenuUsuarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenuUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMenuUsuarioMouseExited(evt);
            }
        });
        panelPrincipal.add(btnMenuUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(933, 531, 60, 40));

        jLabelInstrucionesHuesped.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelInstrucionesHuesped.setForeground(new java.awt.Color(204, 204, 204));
        jLabelInstrucionesHuesped.setText("Para actualizar los campos Fecha de Nacimiento y Nacionalidad, seleccione la fila y actualice el valor que corresponda.");
        panelPrincipal.add(jLabelInstrucionesHuesped, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 141, -1, -1));

        fechaNacimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fechaNacimiento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaNacimientoPropertyChange(evt);
            }
        });
        panelPrincipal.add(fechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 173, 200, 28));

        seleccionNacionalidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        seleccionNacionalidad.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        seleccionNacionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionNacionalidadActionPerformed(evt);
            }
        });
        panelPrincipal.add(seleccionNacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 173, 241, -1));

        btnAyuda.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAyuda.setForeground(new java.awt.Color(0, 153, 0));
        btnAyuda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAyuda.setText("?");
        btnAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAyuda.setOpaque(true);
        btnAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAyudaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAyudaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAyudaMouseExited(evt);
            }
        });
        panelPrincipal.add(btnAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 40, 50, 30));

        jLabelInstrucionesReserva.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelInstrucionesReserva.setForeground(new java.awt.Color(204, 204, 204));
        jLabelInstrucionesReserva.setText("Para actualizar los campos de la tabla seleccione la fila y edite los registros que desee actualizar.");
        panelPrincipal.add(jLabelInstrucionesReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 141, -1, -1));

        fechaCheckIn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fechaCheckIn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaCheckInPropertyChange(evt);
            }
        });
        panelPrincipal.add(fechaCheckIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 173, 197, 28));

        fechaCheckOut.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fechaCheckOut.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaCheckOutPropertyChange(evt);
            }
        });
        panelPrincipal.add(fechaCheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 172, 197, 30));

        seleccionFormaPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        seleccionFormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija forma de pago", "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en Efectivo" }));
        seleccionFormaPago.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        seleccionFormaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionFormaPagoActionPerformed(evt);
            }
        });
        panelPrincipal.add(seleccionFormaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 173, 230, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseClicked
        evt.consume();
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btnMinimizarMouseClicked

    private void btnMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseEntered
        evt.consume();
        btnMinimizar.setBackground(ColoresComponentesUtil.GRIS_CLARO);
    }//GEN-LAST:event_btnMinimizarMouseEntered

    private void btnMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseExited
        evt.consume();
        btnMinimizar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
    }//GEN-LAST:event_btnMinimizarMouseExited

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        Mensaje.confirmarSalida(evt);
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        evt.consume();
        btnCerrar.setBackground(ColoresComponentesUtil.ROJO_OSCURO);
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        evt.consume();
        btnCerrar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
    }//GEN-LAST:event_btnCerrarMouseExited

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        evt.consume();

    }//GEN-LAST:event_btnBuscarMouseClicked

    private void btnBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseEntered
        evt.consume();
        btnBuscar.setBackground(ColoresComponentesUtil.GRIS_CLARO);
    }//GEN-LAST:event_btnBuscarMouseEntered

    private void btnBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseExited
        evt.consume();
        btnBuscar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
    }//GEN-LAST:event_btnBuscarMouseExited

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        evt.consume();
        this.dispose();
        Exito e = new Exito();
        e.setVisible(true);
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseEntered
        evt.consume();
        btnEliminar.setBackground(ColoresComponentesUtil.GRIS_CLARO);
    }//GEN-LAST:event_btnEliminarMouseEntered

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        evt.consume();
        btnEliminar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
    }//GEN-LAST:event_btnEliminarMouseExited

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        evt.consume();
        if (tablaHuespedes.isShowing()) {
            cancelarActualizacionRegistroHuespedes(evt);
        } else {
            cancelarActualizacionRegistroReservas(evt);
        }
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        evt.consume();
        btnCancelar.setBackground(ColoresComponentesUtil.GRIS_CLARO);
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        evt.consume();
        btnCancelar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
    }//GEN-LAST:event_btnCancelarMouseExited

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked
        evt.consume();
        if (tablaHuespedes.isShowing()) {
            actualizarRegistroHuesped();
        } else {
            JOptionPane.showMessageDialog(null, "Otras actualizaciones...");
        }
    }//GEN-LAST:event_btnActualizarMouseClicked

    private void btnActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseEntered
        evt.consume();
        btnActualizar.setBackground(ColoresComponentesUtil.GRIS_CLARO);
    }//GEN-LAST:event_btnActualizarMouseEntered

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        evt.consume();
        btnActualizar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
    }//GEN-LAST:event_btnActualizarMouseExited

    private void panelPrincipalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_panelPrincipalMousePressed

    private void panelPrincipalMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_panelPrincipalMouseDragged

    private void btnMenuUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuUsuarioMouseClicked
        evt.consume();
        this.dispose();
        MenuUsuario menuUsuario = new MenuUsuario();
        menuUsuario.setVisible(true);
    }//GEN-LAST:event_btnMenuUsuarioMouseClicked

    private void btnMenuUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuUsuarioMouseEntered
        evt.consume();
        btnMenuUsuario.setBackground(ColoresComponentesUtil.GRIS_CLARO);
    }//GEN-LAST:event_btnMenuUsuarioMouseEntered

    private void btnMenuUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuUsuarioMouseExited
        evt.consume();
        btnMenuUsuario.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
    }//GEN-LAST:event_btnMenuUsuarioMouseExited

    private void seleccionNacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionNacionalidadActionPerformed
        evt.getActionCommand();
        modificarNacionalidadEnTablaHuespedes();
    }//GEN-LAST:event_seleccionNacionalidadActionPerformed

    private void btnAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAyudaMouseClicked
        evt.consume();
        JOptionPane.showMessageDialog(
                null,
                "Puede actualizar los registros Nombre, Apellido y Fecha de Nacimiento\n"
                + "directamente en la tabla.\n"
                + "Si desea actualizar la Nacionalidad, seleccione la fila que desee "
                + "y cambie el valor en el campo de selección."
        );
    }//GEN-LAST:event_btnAyudaMouseClicked

    private void btnAyudaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAyudaMouseEntered
        evt.consume();
        btnAyuda.setBackground(ColoresComponentesUtil.GRIS_CLARO);
    }//GEN-LAST:event_btnAyudaMouseEntered

    private void btnAyudaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAyudaMouseExited
        evt.consume();
        btnAyuda.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
    }//GEN-LAST:event_btnAyudaMouseExited

    private void fechaNacimientoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaNacimientoPropertyChange
        evt.getPropertyName();
        modificarFechaNacimientoEnTablaHuespedes();
    }//GEN-LAST:event_fechaNacimientoPropertyChange

    private void tablaHuespedesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHuespedesMouseClicked
        if (evt.getClickCount() == 1) {
            int fila = tablaHuespedes.getSelectedRow();
            String nacionalidad = String.valueOf(tablaHuespedes.getValueAt(fila, 4));
            seleccionNacionalidad.setSelectedItem(nacionalidad);
            String fecha = String.valueOf(tablaHuespedes.getValueAt(fila, 3));
            Date dt = Date.valueOf(fecha);
            fechaNacimiento.setDate(dt);
        }
    }//GEN-LAST:event_tablaHuespedesMouseClicked

    private void panelTablasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTablasMouseClicked
        alternarVisualizacionCamposTablas();
    }//GEN-LAST:event_panelTablasMouseClicked

    private void fechaCheckInPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaCheckInPropertyChange
        int fila = tablaReservas.getSelectedRow();
        if (fechaCheckIn.getDate() != null && fechaCheckOut.getDate() != null && evt.getOldValue() != null && fila != -1) {
            calcularValorReservas(fila);
        }
        modificarFechaEntradaEnTablaReservas();

    }//GEN-LAST:event_fechaCheckInPropertyChange

    private void fechaCheckOutPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaCheckOutPropertyChange
        int fila = tablaReservas.getSelectedRow();
        if (fechaCheckIn.getDate() != null && fechaCheckOut.getDate() != null && evt.getOldValue() != null && fila != -1) {
            calcularValorReservas(fila);
        }
        modificarFechaSalidaEnTablaReservas();
    }//GEN-LAST:event_fechaCheckOutPropertyChange

    private void tablaReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaReservasMouseClicked
        if (evt.getClickCount() == 1) {
            int fila = tablaReservas.getSelectedRow();
            String formaPago = String.valueOf(tablaReservas.getValueAt(fila, 4));
            seleccionFormaPago.setSelectedItem(formaPago);
            String fechaEntradaOfTablaReservas = String.valueOf(tablaReservas.getValueAt(fila, 1));
            String fechaSalidaOfTablaReservas = String.valueOf(tablaReservas.getValueAt(fila, 2));
            Date fechaEntrada = Date.valueOf(fechaEntradaOfTablaReservas);
            Date fechaSalida = Date.valueOf(fechaSalidaOfTablaReservas);
            fechaCheckIn.setDate(fechaEntrada);
            fechaCheckOut.setDate(fechaSalida);
            //Aquí se efectua el cálculo de días al seleccionar cualquier fila.
            if (fechaCheckIn.getDate() != null && fechaCheckOut.getDate() != null) {
                //Deshabilitando fechas para evitar que sean editadas si no es seleccionada una fila.
                alternarEdicionFechasReservas();
                calcularDiasTranscurridos(fechaCheckIn, fechaCheckOut);
            }
        }
    }//GEN-LAST:event_tablaReservasMouseClicked

    private void seleccionFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionFormaPagoActionPerformed
        evt.getActionCommand();
        modificarSeleccionFormaPagoTablaReservas();
    }//GEN-LAST:event_seleccionFormaPagoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Busqueda().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnActualizar;
    private javax.swing.JLabel btnAyuda;
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JLabel btnCancelar;
    private javax.swing.JLabel btnCerrar;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel btnMenuUsuario;
    private javax.swing.JLabel btnMinimizar;
    private javax.swing.JTextField campoBuscar;
    private com.toedter.calendar.JDateChooser fechaCheckIn;
    private com.toedter.calendar.JDateChooser fechaCheckOut;
    private com.toedter.calendar.JDateChooser fechaNacimiento;
    private javax.swing.JLabel jLabelIconoHotelAlura;
    private javax.swing.JLabel jLabelInstrucionesHuesped;
    private javax.swing.JLabel jLabelInstrucionesReserva;
    private javax.swing.JLabel jLabelTituloVentanaBuscar;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTabbedPane panelTablas;
    private javax.swing.JScrollPane scrollTablaHuespedes;
    private javax.swing.JScrollPane scrollTablaReservas;
    private javax.swing.JComboBox<String> seleccionFormaPago;
    private javax.swing.JComboBox<String> seleccionNacionalidad;
    private javax.swing.JTable tablaHuespedes;
    private javax.swing.JTable tablaReservas;
    // End of variables declaration//GEN-END:variables
}
