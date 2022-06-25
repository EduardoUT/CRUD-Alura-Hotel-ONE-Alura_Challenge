/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mx.com.alurahotel.view;

import java.awt.Component;
import mx.com.alurahotel.util.ColoresComponentesUtil;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mx.com.alurahotel.controller.HuespedController;
import mx.com.alurahotel.controller.ReservaController;
import mx.com.alurahotel.modelo.Huesped;
import mx.com.alurahotel.modelo.Reserva;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Busqueda extends javax.swing.JFrame {

    int xMouse;
    int yMouse;
    int vColumnaIndex = 1;
    int margen = 2;
    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloTablaDos;
    private final HuespedController huespedController;
    private final ReservaController reservaController;

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
    }
    
    private void autoajustarColumnas (JTable tablaReservas, int vColIndex, int margin) {
        for (int c = 0; c < this.tablaReservas.getColumnCount(); c++) {
            ajustarColumna(this.tablaReservas, vColIndex, margin);
        }
        /*
        for (int c = 0; c < tablaTwo.getColumnCount(); c++) {
            
        }
        */
    
    }
    
    public void ajustarColumna(JTable TablaUsuarios, int vColIndex, int margin) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) TablaUsuarios.getColumnModel();

        TableColumn col = colModel.getColumn(vColIndex);
        int width;
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = TablaUsuarios.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(TablaUsuarios, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;
        for (int r = 0; r < TablaUsuarios.getRowCount(); r++) {
            renderer = TablaUsuarios.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(TablaUsuarios, TablaUsuarios.getValueAt(r, vColIndex), false, false, r, vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
        width += 2 * margin;
        col.setPreferredWidth(width);
    }

    private void cargarTablaHuespedes() {
        modeloTabla = (DefaultTableModel) tablaHuespedes.getModel();
        List<Huesped> listaHuespedes = this.huespedController.listar();
        listaHuespedes.forEach((huesped) -> {
            modeloTabla.addRow(
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

    private void cargarTablaReservas() {
        modeloTablaDos = (DefaultTableModel) tablaReservas.getModel();
        List<Reserva> listaReservas = this.reservaController.listar();
        listaReservas.forEach((reserva) -> {
            modeloTablaDos.addRow(
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

        jLabelIconoHotelAlura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIconoHotelAlura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/Ha-100px.png"))); // NOI18N

        jLabelTituloVentanaBuscar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTituloVentanaBuscar.setForeground(new java.awt.Color(12, 138, 199));
        jLabelTituloVentanaBuscar.setText("Sistema de Búsqueda");

        campoBuscar.setBackground(new java.awt.Color(60, 63, 65));
        campoBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoBuscar.setForeground(new java.awt.Color(204, 204, 204));
        campoBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));

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

        tablaHuespedes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Huesped", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Teléfono", "ID Reserva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaHuespedes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaHuespedes.setSelectionBackground(new java.awt.Color(12, 138, 199));
        tablaHuespedes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tablaHuespedes.getTableHeader().setResizingAllowed(false);
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
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaReservas.setSelectionBackground(new java.awt.Color(12, 138, 199));
        tablaReservas.setSelectionForeground(new java.awt.Color(255, 255, 255));
        scrollTablaReservas.setViewportView(tablaReservas);

        panelTablas.addTab("Reservas", new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/calendario.png")), scrollTablaReservas); // NOI18N

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

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btnMenuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabelIconoHotelAlura)
                        .addGap(46, 46, 46)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                                .addComponent(campoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                                .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabelTituloVentanaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabelTituloVentanaBuscar)
                        .addGap(18, 18, 18)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(campoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabelIconoHotelAlura)))
                .addGap(55, 55, 55)
                .addComponent(panelTablas, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnMenuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        Mensaje.confirmarSalida(evt);
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
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JLabel btnCancelar;
    private javax.swing.JLabel btnCerrar;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel btnMenuUsuario;
    private javax.swing.JLabel btnMinimizar;
    private javax.swing.JTextField campoBuscar;
    private javax.swing.JLabel jLabelIconoHotelAlura;
    private javax.swing.JLabel jLabelTituloVentanaBuscar;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTabbedPane panelTablas;
    private javax.swing.JScrollPane scrollTablaHuespedes;
    private javax.swing.JScrollPane scrollTablaReservas;
    private javax.swing.JTable tablaHuespedes;
    private javax.swing.JTable tablaReservas;
    // End of variables declaration//GEN-END:variables
}
