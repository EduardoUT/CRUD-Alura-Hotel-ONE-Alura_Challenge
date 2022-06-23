/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mx.com.alurahotel.view;

import java.awt.Color;
import mx.com.alurahotel.util.ColoresComponentesUtil;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import mx.com.alurahotel.util.ValidarFormulariosUtil;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class RegistrarHuesped extends javax.swing.JFrame {

    int xMouse;
    int yMouse;
    public static String[] nacionalidades = {"Afghanistan – Afeganistão", "Afghan – afegão", "Andorra – Andorra", "Andorran – andorrano", "Angola – Angola", "Angolan – angolano", "Antigua e Barbuda – Antígua e Barbuda", "Antiguan/Barbudan – antiguano", "Algeria – Argélia", "Algerian – argelino", "Argentina – Argentina", "Argentinian – argentino", "Armenia – Armênia", "Armenian – armênio", "Australia – Austrália", "Australian – australiano", "Austria – Áustria", "Austrian – austríaco", "Azerbaijan – Azerbaijão", "Azerbaijani – azeri", "The Bahamas – Bahamas", "Bahamian – bahamense", "Bangladesh – Bangladesh", "Bangladeshi – bangladês", "Barbados – Barbados", "Barbadian – barbadiano", "Bahrain – Barém", "Bahraini – baremita", "Belarus – Bielorrússia", "Belarusian – bielorrusso", "Belgium – Bélgica", "Belgian – belga", "Belize – Belize", "Belizean – belizenho", "Benin – Benim", "Beninese – beninense", "Bolivia – Bolívia", "Bolivian – boliviano", "Bosnia; Bosnia and Herzegovina – Bósnia; Bósnia e Herzegovina", "Bosnian – bósnio", "Botswana – Botsuana", "Motswana – bechuano", "Brazil – Brasil", "Brazilian – brasileiro", "Brunei – Brunei", "Bruneian – bruneano", "Bulgaria – Bulgária", "Bulgarian – búlgaro", "BurkinaFaso – BurkinaFaso", "Burkinabé – burquinense", "Burundi – Burundi", "Burundian – burundês", "Bhutan – Butão", "Bhutanese – butanense", "Cape Verde – Cabo Verde", "Cape Verdean – cabo-verdiano", "Cameroon – Camarões", "Cameroonian – camaronense", "Cambodia – Camboja", "Cambodian – cambojano", "", "Canada – Canadá", "Canadian – canadense", "", "Central African Republic – República Centro-Africana", "Central-african – centroafricano", "", "Chad – Chade", "Chadian – chadiano", "", "China – China", "Chinese – chinês", "", "Chile – Chile", "Chilean – chileno", "", "Cook Islands – Ilhas Cook", "Cook Islander – cookiano", "", "Colombia – Colômbia", "Colombian – colombiano", "", "Comoros – Comores", "Comoran – comoriano", "", "Costa Rica – Costa Rica", "Costa Rican – costa-riquenho", "", "Croatia – Croácia", "Croatian – croata", "", "Cuba – Cuba", "Cuban – Cubano", "", "Cyprus – Chipre", "Cypriot – cipriota", "", "Czech Republic – República Tcheca", "Czech – tcheco", "", "Democratic Republic of Congo – República Democrática do Congo", "Congolese – congolense", "", "Denmark – Dinamarca", "Danish – dinamarquês", "", "Djibouti – Djibuti", "Djiboutian – djibutiense", "", "Dominica – Dominica", "Dominican – dominiquense", "", "Dominican Republic – República Dominicana", "Dominican – dominicano", "", "East Timor – Timor Leste", "East Timorese – timorense", "", "Ecuador – Equador", "Ecuadorian – equatoriano", "", "Egypt – Egito", "Egyptian – egípcio", "", "El Salvador – El Salvador", "Salvadorean – salvadorenho", "", "England – Inglaterra", "English – inglês", "", "Equatorial Guinea – Guiné Equatorial", "Equatoguinean – guinéu-equatoriano", "", "Eritrea – Eritreia", "Eritrean – eritreu", "", "Estônia – Estônia", "Estonian – estoniano", "", "Fiji – Fiji", "Fijian – fijiano", "", "Finland – Finlândia", "Finnish – finlandês", "", "France – França", "French – francês", "", "Gabon – Gabão", "Gabonese – gabonense", "", "Gambia – Gâmbia", "Gambian – gambiano", "", "Georgia – Geórgia", "Georgian – geórgico", "", "Germany – Alemanha", "German – alemão", "", "Grenada – Granada", "Grenadian – granadino", "", "Greece – Grécia", "Greek – grego", "", "Guatemala – Guatemala", "Guatemalan – guatemalteco", "", "Guinea – Guiné", "Guinean – guineano", "", "Guinea–Bissau – GuinéBissau", "Bissau–guinean – guineense", "", "Guyana – Guiana", "Guyanese – guianense", "", "Haiti – Haiti", "Haitian – haitiano", "", "Holland – Holanda", "Dutch – holandês", "", "Honduras – Honduras", "Honduran – hondurenho", "", "Hungary – Hungria", "Hungarian – húngaro", "", "Iceland – Islândia", "Icelander – islandês", "", "India – Índia", "Indian – indiano", "", "Indonesia – Indonésia", "Indonesian – indonésio", "", "Iran – Irã", "Iranian – iraniano", "", "Ireland – Irlanda", "Irish – irlandês", "", "Israel – Israel", "Israeli – israelita", "", "Italy – Itália", "Italian – italiano", "", "Ivory Coast – Costa do Marfim", "Ivorian– costa-marfinense", "", "Jamaica – Jamaica", "Jamaican – jamaicano", "", "Japan – Japão", "Japanese – japonês", "", "Jordan – Jordânia", "Jordanian – jordão", "", "Kazakhstan – Cazaquistão", "Kazakh – cazaque", "", "Kenya – Quênia", "Kenyan – queniano", "", "Kiribati – Quiribati", "I-kiribati – quiribatiano", "", "Kyrgyzstan – Quirguistão", "Kyrgyzstani – quirguistanês", "", "Kwait – Kuwait", "Kwaiti – kuwaitiano", "", "Laos – Laos", "Laotian – laosiano", "", "Latvia – Letônia", "Latvian – letoniano", "", "Lebanon – Líbano", "Lebanese – libanês", "", "Lesotho – Lesoto", "Basotho – lesotiano", "", "Liberia – Libéria", "Liberian – liberiano", "", "Liechtenstein – Liechtenstein", "Liechtensteiner – liechtensteinense", "", "Lithuania – Lituânia", "Lithuanian – lituano", "", "Luxembourg – Luxemburgo", "Luxembourgish – luxemburguês", "", "Lybia – Líbia", "Lybian – líbio", "", "Macedonia – Macedônia", "Macedonian – macedônio", "", "Madagascar – Madagascar", "Malagasy – madagascarense", "", "Malaysia – Malásia", "Malaysian – malaio", "", "Malawi – Malaui", "Malawian – malauiano", "", "Maldives – Maldivas", "Maldivian – maldivo", "", "Mali – Máli", "Malian – maliano", "", "Malta – Malta", "Maltese – maltês", "", "Mauritius – Maurício", "Mauritian – mauriciano", "", "Mauritia – Mauritânia", "Mauritanian – mauritano", "", "Marshall Island – Ilhas Marshall", "Marshall Islander – marshallino", "", "Micronesia/Federated States of Micronesia – Estados Federados da Micronésia", "Micronesian – micronésio", "", "Mexico – México", "Mexican – mexicano", "", "Morocco – Marrocos", "Moroccan – marroquino", "", "Moldova – Moldavia", "Moldovan – moldávio", "", "Monaco – Mônaco", "Monacan – monegasco", "", "Mongolia – Mongólia", "Mongolian – mongol", "", "Montenegro – Montenegro", "Montenegrin – montenegrino", "", "Mozambique – Moçambique", "Mozambican – moçambicano", "", "Myanmar – Myanmar", "Burmese – birmanês", "", "Namibia – Namíbia", "Namibian – namibiano", "", "Nauru – Nauru", "Nauruan – nauruano", "", "Nepal – Nepal", "Nepali – nepalês", "", "New Zealand – Nova Zelândia", "NewZealander – neozelandês", "", "Nicaragua – Nicarágua", "Nicaraguan – nicaraguense", "", "Niger – Níger", "Nigerien – nigerino", "", "Nigeria – Nigéria", "Nigerian – nigeriano", "", "Niue – Niue", "Niuean – niuano", "", "North Korea – Coréia do Norte", "North korean – norte-coreano", "", "Norway – Noruega", "Norwegian – norueguês", "", "Oman – Omã", "Omani – omanense", "", "Palestine – Palestina", "Palestinian – palestino", "", "Pakistan – Paquistão", "Pakistanese – paquistanês", "", "Palau – Palau", "Palauan – palauense", "", "Panama – Panamá", "Panamanian – panamenho", "", "Papua New Guinea – Papua Nova Guiné", "Papua New Guinean – papuásio", "", "Paraguay – Paraguai", "Paraguayan – paraguaio", "", "Peru – Peru", "Peruvian – peruano", "", "Philippines – Philippines", "Philippine – filipino", "", "Poland – Polônia", "Polish – polonês", "", "Portugal – Portugal", "Portuguese – português", "", "Qatar – Catar", "Qatari – catarense", "", "Romania – Romênia", "Romanian – romeno", "", "Russia – Rússia", "Russian – russo", "", "Rwanda – Ruanda", "Rwandan – ruandês", "", "Samoa – Samoa", "Samoan – samoano", "", "Saint Lucia – Santa Lúcia", "Saint Lucian – santa-lucense", "", "Saint Kitts and Nevis – São Cristóvão e Nevis", "Kittian – são-cristovense", "", "San Marino – São Marino", "San Marinan – são-marinense", "", "Sao Tomé and Principe – São Tomé e Príncipe", "Sao Tomean – são-tomense", "", "Saint Vincent and the Grenadines – São Vicente e Granadinas", "Vicentinian – são-vicentino", "", "Scotland – Escócia", "Scottish – escocês", "", "Senegal – Senegal", "Senegalese – senegalense", "", "Serbia – Sérvia", "Serbian – sérvio", "", "Seychelles – Seicheles", "Seychellois – seichelense", "", "Sierra Leone – Serra Leoa", "Sierra Leonean – serra-leonês", "", "Singapore – Singapura", "Singaporean – singapurense", "", "Slovakia – Eslováquia", "Slovak – eslovaco", "", "Solomon Islands – Ilhas Salomão", "Solomon Islander – salomônico", "", "Somalia – Somália", "Somali – somali", "", "South Africa – África do Sul", "South African – sul–africano", "", "South Korea – Coréia do Sul", "Korean – coreano", "", "South Sudan – Sudão do Sul", "South Sudanese – sul-sudanense", "", "Spain – Espanha", "Spanish – espanhol", "", "Sri Lanka – Sri Lanka", "Sri Lankan – srilankês", "", "Sudan – Sudão", "Sudanese – sudanense", "", "Suriname – Suriname", "Surinamese – surinamês", "", "Swaziland – Suazilândia", "Swazi – suazi", "", "Sweden – Suécia", "Swedish – sueco", "", "Switzerland – Suíça", "Swiss – suíço", "", "Syria – Síria", "Syrian – sírio", "", "Tajikistan – Tadiquistão", "Tajiki – tajique", "Tanzanian – tanzaniano", "Thailand – Tailândia", "Thai – tailandês", "Togo – Togo", "Togolese – togolês", "Tonga – Tonga", "Tongan – tonganês", "Trinidad and Tobago – Trindade e Tobago", "Trinidadian – trinitário", "", "Tunisia – Tunísia", "Tunisian – tunisiano", "Turkmenistan – Turcomenistão", "Turkmen – turcomeno", "Turkey – Turquia", "Turkish – turco", "Tuvalu – Tuvalu", "Tuvaluan – tuvaluano", "Ukraine – Ucrânia", "Ukrainian – ucraniano", "Uganda – Uganda", "Ugandan – ugandês", "Uruguay – Uruguai", "Uruguayan – uruguaio", "United Arab Emirates – Emirados Árabes Unidos", "Emirati – árabe", "United Kingdom – Reino Unido", "British – britânico", "United States of America – Estados Unidos", "American – americano", "Uzbekistan – Usbequistão", "Uzbek – uzbeque", "Vanuatu – Vanuatu", "Ni-vanuatu – vanuatuano", "Venezuela – Venezuela", "Venezuelan – venezuelano", "Vietnam – Vietnã", "Vietnamese – vietnamita", "Wales – País de Gales", "Welsh – galês", "Yemen – Iêmen", "Yemeni – iemenita", "Zambia – Zâmbia", "Zambian – zambiano", "Zimbabwe – Zimbábue", "Zimbabwean – zimbabueano"};

    /**
     * Creates new form Copia
     */
    public RegistrarHuesped() {
        initComponents();
        setBackground(ColoresComponentesUtil.TRANSPARENTE);
        panelFormularioRegistroHuesped.setBackground(ColoresComponentesUtil.TRANSPARENTE);
        btnCerrar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
        btnMinimizar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
        btnGuardar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
        btnCancelar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
        btnMenuUsuario.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
        //Agregando el arreglo String[] de 190 nacionalidades al ComboBox.
        seleccionNacionalidad.setModel(new DefaultComboBoxModel<>(filtrarNacionalidades(nacionalidades)));
        campoNumeroReserva.setEnabled(false);
    }

    /**
     * Método que devuelve un nuevo arreglo String[]nacionalidades con 190
     * nacionalidades filtradas del mismo arreglo String[]nacionalidades.
     *
     * @param nacionalidades - Arreglo con más de 500 nacionalidades.
     * @return nacionalidades - Devuelve el mismo arreglo de tipo String[] con
     * 190 nacionalidades.
     */
    public static String[] filtrarNacionalidades(String[] nacionalidades) {
        ArrayList<String> listaNacionalidades = new ArrayList<>();
        for (String nacionalidad : nacionalidades) {
            if (!nacionalidad.isEmpty()) {
                if (esNacionalidadEspanhol(nacionalidad)) {
                    listaNacionalidades.add(nacionalidad);
                }
            }
        }
        nacionalidades = listaNacionalidades.toArray(new String[0]);
        return nacionalidades;
    }

    /**
     * Regex para nacionalidad. Agregando método que evalua una nacionalidad
     * compuesta por el nombre en inglés(puede ser compuesto), ej: South
     * African, un guión entre dos espacios y el nombre en español p.ej
     * (sul-africano). Teniendo: South African - sul-africano Mexican - mexicano
     *
     * Ej: Sierra Leonean – serra-leonês
     *
     * Coincidirá si es: Nacionalidad(EN) Nacionalidad(EN) -
     *
     * @param nacionalidad
     * @return boolean - Devuelve true si el patrón coincide con la Expresión
     * Regular.
     */
    public static boolean esNacionalidadEspanhol(String nacionalidad) {
        String regEx = "(([A-Z][\\-]?[a-záéíóúâêôãõç]+)(\\s|\\-)?"
                + "([A-Z]?[\\-]?[a-záéíóúâêôãõç]+\\s)?–\\s"
                + "([a-záéíóúâêôãõç]+)[\\-]?([a-záéíóúâêôãõç]+))";
        Pattern pattern = Pattern.compile(regEx);
        Matcher mat = pattern.matcher(nacionalidad);
        return mat.find();
    }

    /**
     * Para logo de escritorio.
     */
    @Override
    public Image getIconImage() {
        Image retImage = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("mx/com/alurahotel/imagenes/persona.png"));
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
        bannerRegistroHuesped = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JLabel();
        btnMinimizar = new javax.swing.JLabel();
        panelFormularioRegistroHuesped = new javax.swing.JPanel();
        jLabelIconoHotelAlura = new javax.swing.JLabel();
        jLabelTituloFormulario = new javax.swing.JLabel();
        jLabelTextoApellido = new javax.swing.JLabel();
        campoApellido = new javax.swing.JTextField();
        jLabelTextoNombre = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabelTextoFechaNacimiento = new javax.swing.JLabel();
        fechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabelTextoNacionalidad = new javax.swing.JLabel();
        seleccionNacionalidad = new javax.swing.JComboBox<>();
        jLabelTextoTelefono = new javax.swing.JLabel();
        campoTelefono = new javax.swing.JTextField();
        jLabelTextoNumeroReserva = new javax.swing.JLabel();
        campoNumeroReserva = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JLabel();
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
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bannerRegistroHuesped.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/registro.png"))); // NOI18N
        panelPrincipal.add(bannerRegistroHuesped, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 502, -1));

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
        panelPrincipal.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 60, 30));

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
        panelPrincipal.add(btnMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 60, 30));

        jLabelIconoHotelAlura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIconoHotelAlura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/Ha-100px.png"))); // NOI18N

        jLabelTituloFormulario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTituloFormulario.setForeground(new java.awt.Color(12, 138, 199));
        jLabelTituloFormulario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTituloFormulario.setText("Registro de Huésped");

        jLabelTextoApellido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelTextoApellido.setForeground(new java.awt.Color(204, 204, 204));
        jLabelTextoApellido.setText("Apellido:");

        campoApellido.setBackground(new java.awt.Color(60, 63, 65));
        campoApellido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoApellido.setForeground(new java.awt.Color(204, 204, 204));
        campoApellido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoApellido.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));

        jLabelTextoNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelTextoNombre.setForeground(new java.awt.Color(204, 204, 204));
        jLabelTextoNombre.setText("Nombre:");

        campoNombre.setBackground(new java.awt.Color(60, 63, 65));
        campoNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoNombre.setForeground(new java.awt.Color(204, 204, 204));
        campoNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        campoNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNombreKeyTyped(evt);
            }
        });

        jLabelTextoFechaNacimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelTextoFechaNacimiento.setForeground(new java.awt.Color(204, 204, 204));
        jLabelTextoFechaNacimiento.setText("Fecha de Nacimiento:");

        fechaNacimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabelTextoNacionalidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelTextoNacionalidad.setForeground(new java.awt.Color(204, 204, 204));
        jLabelTextoNacionalidad.setText("Nacionalidad:");

        seleccionNacionalidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        seleccionNacionalidad.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));

        jLabelTextoTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelTextoTelefono.setForeground(new java.awt.Color(204, 204, 204));
        jLabelTextoTelefono.setText("Teléfono:");

        campoTelefono.setBackground(new java.awt.Color(60, 63, 65));
        campoTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoTelefono.setForeground(new java.awt.Color(204, 204, 204));
        campoTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoTelefono.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));

        jLabelTextoNumeroReserva.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelTextoNumeroReserva.setForeground(new java.awt.Color(204, 204, 204));
        jLabelTextoNumeroReserva.setText("Número de Reserva:");

        campoNumeroReserva.setBackground(new java.awt.Color(60, 63, 65));
        campoNumeroReserva.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoNumeroReserva.setForeground(new java.awt.Color(204, 204, 204));
        campoNumeroReserva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoNumeroReserva.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));

        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/com/alurahotel/imagenes/disquete.png"))); // NOI18N
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setOpaque(true);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
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

        javax.swing.GroupLayout panelFormularioRegistroHuespedLayout = new javax.swing.GroupLayout(panelFormularioRegistroHuesped);
        panelFormularioRegistroHuesped.setLayout(panelFormularioRegistroHuespedLayout);
        panelFormularioRegistroHuespedLayout.setHorizontalGroup(
            panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormularioRegistroHuespedLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTextoNumeroReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoTelefono, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTextoTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(seleccionNacionalidad, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTextoNacionalidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fechaNacimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTextoFechaNacimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoApellido, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTextoApellido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTextoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFormularioRegistroHuespedLayout.createSequentialGroup()
                        .addGroup(panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTituloFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(campoNumeroReserva))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormularioRegistroHuespedLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelIconoHotelAlura))
                    .addGroup(panelFormularioRegistroHuespedLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMenuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
        );
        panelFormularioRegistroHuespedLayout.setVerticalGroup(
            panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioRegistroHuespedLayout.createSequentialGroup()
                .addGroup(panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormularioRegistroHuespedLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelTituloFormulario)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelTextoNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(panelFormularioRegistroHuespedLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabelIconoHotelAlura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)))
                .addGroup(panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelFormularioRegistroHuespedLayout.createSequentialGroup()
                        .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelTextoApellido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelTextoFechaNacimiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelTextoNacionalidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seleccionNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormularioRegistroHuespedLayout.createSequentialGroup()
                        .addComponent(jLabelTextoTelefono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormularioRegistroHuespedLayout.createSequentialGroup()
                        .addComponent(jLabelTextoNumeroReserva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoNumeroReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnMenuUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        panelPrincipal.add(panelFormularioRegistroHuesped, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 410, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        Mensaje.ConfirmarSalida(evt);
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        evt.consume();
        btnCerrar.setBackground(ColoresComponentesUtil.ROJO_OSCURO);
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        evt.consume();
        btnCerrar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
    }//GEN-LAST:event_btnCerrarMouseExited

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

    private void panelPrincipalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMousePressed
        evt.consume();
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_panelPrincipalMousePressed

    private void panelPrincipalMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMouseDragged
        evt.consume();
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_panelPrincipalMouseDragged

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        Mensaje.ConfirmarSalida(evt);
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        evt.consume();
        btnCancelar.setBackground(ColoresComponentesUtil.GRIS_CLARO);
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        evt.consume();
        btnCancelar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
    }//GEN-LAST:event_btnCancelarMouseExited

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
        if (ValidarFormulariosUtil.esFormularioHuespedValido(campoNombre.getText(), campoApellido.getText(), fechaNacimiento, campoTelefono.getText())) {
            evt.consume();
            this.dispose();
            Exito e = new Exito();
            e.setVisible(true);
        }
    }//GEN-LAST:event_btnGuardarMouseClicked

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        evt.consume();
        btnGuardar.setBackground(ColoresComponentesUtil.GRIS_CLARO);
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        evt.consume();
        btnGuardar.setBackground(ColoresComponentesUtil.GRIS_OSCURO);
    }//GEN-LAST:event_btnGuardarMouseExited

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

    private void campoNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNombreKeyTyped

    }//GEN-LAST:event_campoNombreKeyTyped

    private void campoNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNombreKeyPressed

    }//GEN-LAST:event_campoNombreKeyPressed

    private void campoNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNombreKeyReleased
        //AdmitirTipoCaracterUtil.teclaEspacio(evt);
    }//GEN-LAST:event_campoNombreKeyReleased

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistrarHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarHuesped().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bannerRegistroHuesped;
    private javax.swing.JLabel btnCancelar;
    private javax.swing.JLabel btnCerrar;
    private javax.swing.JLabel btnGuardar;
    private javax.swing.JLabel btnMenuUsuario;
    private javax.swing.JLabel btnMinimizar;
    private javax.swing.JTextField campoApellido;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoNumeroReserva;
    private javax.swing.JTextField campoTelefono;
    private com.toedter.calendar.JDateChooser fechaNacimiento;
    private javax.swing.JLabel jLabelIconoHotelAlura;
    private javax.swing.JLabel jLabelTextoApellido;
    private javax.swing.JLabel jLabelTextoFechaNacimiento;
    private javax.swing.JLabel jLabelTextoNacionalidad;
    private javax.swing.JLabel jLabelTextoNombre;
    private javax.swing.JLabel jLabelTextoNumeroReserva;
    private javax.swing.JLabel jLabelTextoTelefono;
    private javax.swing.JLabel jLabelTituloFormulario;
    private javax.swing.JPanel panelFormularioRegistroHuesped;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JComboBox<String> seleccionNacionalidad;
    // End of variables declaration//GEN-END:variables
}
