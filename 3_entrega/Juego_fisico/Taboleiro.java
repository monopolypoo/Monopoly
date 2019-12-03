package Juego_fisico;

import Casilla.*;
import Monopoly.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Taboleiro {
    private Grupo ladoNorte;
    private Grupo ladoOeste;
    private Grupo ladoLeste;
    private Grupo ladoSur;
    private HashMap<String, Casilla> casillas;
    private HashMap<Integer, Casilla> casillasPosicion;
    private boolean sePuedeSubirPrecio;
    private int contadorVueltas;
    private ArrayList<String> idCasas;
    private ArrayList<String> idHoteles;
    private ArrayList<String> idPiscinas;
    private ArrayList<String> idPistas;
    private HashMap<String, Solar> edificaciones;


    public Taboleiro(Juego juego) {
        this.sePuedeSubirPrecio = false;
        this.contadorVueltas = 0;

        // Casillas UDC Ferrol (SUR)
        Especial saida = new Especial(" Salida           ", 0);
        Solar solar1 = new Solar(" Ing. Eléctrica   ", 1, Valor.PRECIO_GRUPO1);
        AccionCajaComunidad caixa1 = new AccionCajaComunidad(" Caja Comunidad   ", 2, Valor.BLACK_BOLD);
        Solar solar2 = new Solar(" Ing. Mecánica    ", 3, Valor.PRECIO_GRUPO1);
        Impuesto imp1 = new Impuesto(" Imp. Militar     ", 4, Valor.RED);
        Transportes trans1 = new Transportes(" Pto. Ferrol      ", 5, Valor.VUELTA);
        Solar solar3 = new Solar(" Podología        ", 6, Valor.PRECIO_GRUPO2);
        AccionSuerte sorte1 = new AccionSuerte(" Suerte           ", 7, Valor.BLACK_BOLD);
        Solar solar4 = new Solar(" Humanidades      ", 8, Valor.PRECIO_GRUPO2);
        Solar solar5 = new Solar(" Documentación    ", 9, Valor.PRECIO_GRUPO2);

        // Casillas UDC Coruña (OESTE)
        Especial carcere = new Especial(" Cárcel           ", 10);
        Solar solar6 = new Solar(" Fisioterapia     ", 11, Valor.PRECIO_GRUPO3);
        Servicio serv1 = new Servicio(" Serv. Teleco     ", 12, 0.75 * Valor.VUELTA);
        Solar solar7 = new Solar(" Turismo          ", 13, Valor.PRECIO_GRUPO3);
        Solar solar8 = new Solar(" Náutica          ", 14, Valor.PRECIO_GRUPO3);
        Transportes trans2 = new Transportes(" Base Esp. Coruña ", 15, Valor.VUELTA);
        Solar solar9 = new Solar(" Arquitectura     ", 16, Valor.PRECIO_GRUPO4);
        AccionCajaComunidad caixa2 = new AccionCajaComunidad(" Caja Comunidad   ", 17, Valor.BLACK_BOLD);
        Solar solar10 = new Solar(" Ing. Caminos     ", 18, Valor.PRECIO_GRUPO4);
        Solar solar11 = new Solar(" FIC              ", 19, Valor.PRECIO_GRUPO4);

        // Casillas USC Santiago (NORTE)
        Especial parking = new Especial(" Parking          ", 20);
        Solar solar12 = new Solar(" Historia         ", 21, Valor.PRECIO_GRUPO5);
        AccionSuerte sorte2 = new AccionSuerte(" Suerte           ", 22, Valor.BLACK_BOLD);
        Solar solar13 = new Solar(" Filogía          ", 23, Valor.PRECIO_GRUPO5);
        Solar solar14 = new Solar(" C. de Educación  ", 24, Valor.PRECIO_GRUPO5);
        Transportes trans3 = new Transportes(" Apto. A Valleta  ", 25, Valor.VUELTA);
        Solar solar15 = new Solar(" Medicina         ", 26, Valor.PRECIO_GRUPO6);
        Solar solar16 = new Solar(" Enfermería       ", 27, Valor.PRECIO_GRUPO6);
        Servicio serv2 = new Servicio(" Serv. Eléctrico  ", 28, 0.75 * Valor.VUELTA);
        Solar solar17 = new Solar(" ADE              ", 29, Valor.PRECIO_GRUPO6);

        // Casillas USC Santiago (LESTE)
        Especial irCarcere = new Especial(" Ir Cárcel        ", 30);
        Solar solar18 = new Solar(" Farmacia         ", 31, Valor.PRECIO_GRUPO7);
        Solar solar19 = new Solar(" Bioloxía         ", 32, Valor.PRECIO_GRUPO7);
        AccionCajaComunidad caixa3 = new AccionCajaComunidad(" Caja Comunidad   ", 33, Valor.BLACK_BOLD);
        Solar solar20 = new Solar(" Química          ", 34, Valor.PRECIO_GRUPO7);
        Transportes trans4 = new Transportes(" E. tren Santiago ", 35, Valor.VUELTA);
        AccionSuerte sorte3 = new AccionSuerte(" Suerte           ", 36, Valor.BLACK_BOLD);
        Solar solar21 = new Solar(" Física           ", 37, Valor.PRECIO_GRUPO8);
        Impuesto imp2 = new Impuesto(" Imp. de la Luz   ", 38, Valor.RED);
        Solar solar22 = new Solar(" ETSE             ", 39, Valor.PRECIO_GRUPO8);

        //Creamos los grupos
        ArrayList<Casilla> casillasGrupo1 = new ArrayList<>();
        casillasGrupo1.add(solar1);
        casillasGrupo1.add(solar2);
        Grupo grupo1 = new Grupo(casillasGrupo1, 1, Valor.BLUE, "sur");
        grupo1.SetGrupo();
        juego.añadirGrupo(grupo1);

        ArrayList<Casilla> casillasGrupo2 = new ArrayList<>();
        casillasGrupo2.add(solar3);
        casillasGrupo2.add(solar4);
        casillasGrupo2.add(solar5);
        Grupo grupo2 = new Grupo(casillasGrupo2, 2, Valor.YELLOW, "sur");
        grupo2.SetGrupo();
        juego.añadirGrupo(grupo2);

        ArrayList<Casilla> casillasGrupo3 = new ArrayList<>();
        casillasGrupo3.add(solar6);
        casillasGrupo3.add(solar7);
        casillasGrupo3.add(solar8);
        Grupo grupo3 = new Grupo(casillasGrupo3, 3, Valor.PURPLE, "oeste");
        grupo3.SetGrupo();
        juego.añadirGrupo(grupo3);

        ArrayList<Casilla> casillasGrupo4 = new ArrayList<>();
        casillasGrupo4.add(solar9);
        casillasGrupo4.add(solar10);
        casillasGrupo4.add(solar11);
        Grupo grupo4 = new Grupo(casillasGrupo4, 4, Valor.YELLOW_BRIGHT, "oeste");
        grupo4.SetGrupo();
        juego.añadirGrupo(grupo4);

        ArrayList<Casilla> casillasGrupo5 = new ArrayList<>();
        casillasGrupo5.add(solar12);
        casillasGrupo5.add(solar13);
        casillasGrupo5.add(solar14);
        Grupo grupo5 = new Grupo(casillasGrupo5, 5, Valor.GREEN, "norte");
        grupo5.SetGrupo();
        juego.añadirGrupo(grupo5);

        ArrayList<Casilla> casillasGrupo6 = new ArrayList<>();
        casillasGrupo6.add(solar15);
        casillasGrupo6.add(solar16);
        casillasGrupo6.add(solar17);
        Grupo grupo6 = new Grupo(casillasGrupo6, 6, Valor.RED, "norte");
        grupo6.SetGrupo();
        juego.añadirGrupo(grupo6);

        ArrayList<Casilla> casillasGrupo7 = new ArrayList<>();
        casillasGrupo7.add(solar18);
        casillasGrupo7.add(solar19);
        casillasGrupo7.add(solar20);
        Grupo grupo7 = new Grupo(casillasGrupo7, 7, Valor.GREEN_BRIGHT, "leste");
        grupo7.SetGrupo();
        juego.añadirGrupo(grupo7);

        ArrayList<Casilla> casillasGrupo8 = new ArrayList<>();
        casillasGrupo8.add(solar21);
        casillasGrupo8.add(solar22);
        Grupo grupo8 = new Grupo(casillasGrupo8, 8, Valor.CYAN, "leste");
        grupo8.SetGrupo();
        juego.añadirGrupo(grupo8);

        ArrayList<Casilla> casillasEspeciales = new ArrayList<>();
        casillasEspeciales.add(saida);
        casillasEspeciales.add(carcere);
        casillasEspeciales.add(parking);
        casillasEspeciales.add(irCarcere);
        Grupo grupo9 = new Grupo(casillasEspeciales, Valor.BLACK_BRIGHT, 9);
        grupo9.SetGrupo();
        juego.añadirGrupo(grupo9);

        ArrayList<Casilla> casillasTransportes = new ArrayList<>();
        casillasTransportes.add(trans1);
        casillasTransportes.add(trans2);
        casillasTransportes.add(trans3);
        casillasTransportes.add(trans4);
        Grupo grupo10 = new Grupo(casillasTransportes, Valor.BLACK_BOLD_BRIGHT, 10);
        grupo10.SetGrupo();
        juego.añadirGrupo(grupo10);

        ArrayList<Casilla> casillasServicios = new ArrayList<>();
        casillasServicios.add(serv1);
        casillasServicios.add(serv2);
        Grupo grupo11 = new Grupo(casillasServicios, Valor.CYAN_BRIGHT, 11);
        grupo11.SetGrupo();
        juego.añadirGrupo(grupo11);

        ArrayList<Casilla> casillasImpuestos = new ArrayList<>();
        casillasImpuestos.add(imp1);
        casillasImpuestos.add(imp2);
        Grupo grupo12 = new Grupo(casillasImpuestos, Valor.RED, 12);
        grupo12.SetGrupo();
        juego.añadirGrupo(grupo12);

        ArrayList<Casilla> casillasCaixasSortes = new ArrayList<>();
        casillasCaixasSortes.add(caixa1);
        casillasCaixasSortes.add(caixa2);
        casillasCaixasSortes.add(caixa3);
        casillasCaixasSortes.add(sorte1);
        casillasCaixasSortes.add(sorte2);
        casillasCaixasSortes.add(sorte3);
        Grupo grupo13 = new Grupo(casillasCaixasSortes, Valor.BLACK_BOLD, 13);
        grupo13.SetGrupo();
        juego.añadirGrupo(grupo13);


        //hacemos los grupos de los lados
        ArrayList<Casilla> casillasLadoNorte = new ArrayList<>();
        casillasLadoNorte.add(parking);
        casillasLadoNorte.add(solar12);
        casillasLadoNorte.add(sorte2);
        casillasLadoNorte.add(solar13);
        casillasLadoNorte.add(solar14);
        casillasLadoNorte.add(trans3);
        casillasLadoNorte.add(solar15);
        casillasLadoNorte.add(solar16);
        casillasLadoNorte.add(serv2);
        casillasLadoNorte.add(solar17);
        casillasLadoNorte.add(irCarcere);
        this.ladoNorte = new Grupo(casillasLadoNorte, 14, "norte");

        ArrayList<Casilla> casillasLadoOeste = new ArrayList<>();
        casillasLadoOeste.add(solar11);
        casillasLadoOeste.add(solar10);
        casillasLadoOeste.add(caixa2);
        casillasLadoOeste.add(solar9);
        casillasLadoOeste.add(trans2);
        casillasLadoOeste.add(solar8);
        casillasLadoOeste.add(solar7);
        casillasLadoOeste.add(serv1);
        casillasLadoOeste.add(solar6);
        this.ladoOeste = new Grupo(casillasLadoOeste, 15, "oeste");

        ArrayList<Casilla> casillasLadoLeste = new ArrayList<>();
        casillasLadoLeste.add(solar18);
        casillasLadoLeste.add(solar19);
        casillasLadoLeste.add(caixa3);
        casillasLadoLeste.add(solar20);
        casillasLadoLeste.add(trans4);
        casillasLadoLeste.add(sorte3);
        casillasLadoLeste.add(solar21);
        casillasLadoLeste.add(imp2);
        casillasLadoLeste.add(solar22);
        this.ladoLeste = new Grupo(casillasLadoLeste, 16, "leste");

        ArrayList<Casilla> casillasLadoSur = new ArrayList<>();
        casillasLadoSur.add(carcere);
        casillasLadoSur.add(solar5);
        casillasLadoSur.add(solar4);
        casillasLadoSur.add(sorte1);
        casillasLadoSur.add(solar3);
        casillasLadoSur.add(trans1);
        casillasLadoSur.add(imp1);
        casillasLadoSur.add(solar2);
        casillasLadoSur.add(caixa1);
        casillasLadoSur.add(solar1);
        casillasLadoSur.add(saida);
        this.ladoSur = new Grupo(casillasLadoSur, 17, "sur");


        //añadimos las casillas a un HashMap
        this.casillas = new HashMap<>();
        this.casillas.put("Salida", saida);
        this.casillas.put("Ing.Eléctrica", solar1);
        this.casillas.put("CajaComunidad", caixa1);
        this.casillas.put("Ing.Mecánica", solar2);
        this.casillas.put("Imp.Militar", imp1);
        this.casillas.put("Pto.Ferrol", trans1);
        this.casillas.put("Podología", solar3);
        this.casillas.put("Carta.Suerte", sorte1);
        this.casillas.put("Humanidades", solar4);
        this.casillas.put("Documentación", solar5);

        this.casillas.put("Cárcel", carcere);
        this.casillas.put("Fisioterapia", solar6);
        this.casillas.put("Serv.Teleco", serv1);
        this.casillas.put("Turismo", solar7);
        this.casillas.put("Náutica", solar8);
        this.casillas.put("BaseEsp.Coruña", trans2);
        this.casillas.put("Arquitectura", solar9);
        this.casillas.put("Ing.Caminos", solar10);
        this.casillas.put("FIC", solar11);

        this.casillas.put("Parking", parking);
        this.casillas.put("Historia", solar12);
        this.casillas.put("Filología", solar13);
        this.casillas.put("C.deEducación", solar14);
        this.casillas.put("Apto.AValleta", trans3);
        this.casillas.put("Medicina", solar15);
        this.casillas.put("Enfermería", solar16);
        this.casillas.put("Serv.Eléctrico", serv2);
        this.casillas.put("ADE", solar17);

        this.casillas.put("IrCárcel", irCarcere);
        this.casillas.put("Farmacia", solar18);
        this.casillas.put("Biología", solar19);
        this.casillas.put("Química", solar20);
        this.casillas.put("E.trenSantiago", trans4);
        this.casillas.put("Física", solar21);
        this.casillas.put("Imp.delaLuz", imp2);
        this.casillas.put("ETSE", solar22);


        this.casillasPosicion = new HashMap<>();
        //añadimos las casillas a un HashMap
        this.casillasPosicion.put(0, saida);
        this.casillasPosicion.put(1, solar1);
        this.casillasPosicion.put(2, caixa1);
        this.casillasPosicion.put(3, solar2);
        this.casillasPosicion.put(4, imp1);
        this.casillasPosicion.put(5, trans1);
        this.casillasPosicion.put(6, solar3);
        this.casillasPosicion.put(7, sorte1);
        this.casillasPosicion.put(8, solar4);
        this.casillasPosicion.put(9, solar5);

        this.casillasPosicion.put(10, carcere);
        this.casillasPosicion.put(11, solar6);
        this.casillasPosicion.put(12, serv1);
        this.casillasPosicion.put(13, solar7);
        this.casillasPosicion.put(14, solar8);
        this.casillasPosicion.put(15, trans2);
        this.casillasPosicion.put(16, solar9);
        this.casillasPosicion.put(17, caixa2);
        this.casillasPosicion.put(18, solar10);
        this.casillasPosicion.put(19, solar11);

        this.casillasPosicion.put(20, parking);
        this.casillasPosicion.put(21, solar12);
        this.casillasPosicion.put(22, sorte2);
        this.casillasPosicion.put(23, solar13);
        this.casillasPosicion.put(24, solar14);
        this.casillasPosicion.put(25, trans3);
        this.casillasPosicion.put(26, solar15);
        this.casillasPosicion.put(27, solar16);
        this.casillasPosicion.put(28, serv2);
        this.casillasPosicion.put(29, solar17);

        this.casillasPosicion.put(30, irCarcere);
        this.casillasPosicion.put(31, solar18);
        this.casillasPosicion.put(32, solar19);
        this.casillasPosicion.put(33, caixa3);
        this.casillasPosicion.put(34, solar20);
        this.casillasPosicion.put(35, trans4);
        this.casillasPosicion.put(36, sorte3);
        this.casillasPosicion.put(37, solar21);
        this.casillasPosicion.put(38, imp2);
        this.casillasPosicion.put(39, solar22);

    }

    public Grupo getLadoNorte() {
        return ladoNorte;
    }

    public Grupo getLadoOeste() {
        return ladoOeste;
    }

    public Grupo getLadoLeste() {
        return ladoLeste;
    }

    public Grupo getLadoSur() {
        return ladoSur;
    }

    public HashMap<String, Casilla> getCasillas() {
        return casillas;
    }

    public Casilla getCasillaPosicion(int posicion) {
        return casillasPosicion.get(posicion);
    }

    public void setLadoNorte(Grupo lado) {
        this.ladoNorte = lado;
    }

    public void setLadoOeste(Grupo lado) {
        this.ladoOeste = lado;
    }

    public void setLadoLeste(Grupo lado) {
        this.ladoLeste = lado;
    }

    public void setLadoSur(Grupo lado) {
        this.ladoSur = lado;
    }

    public void setContadorVueltas(int contadorVueltas) {
        this.contadorVueltas = contadorVueltas;
    }

    public HashMap<String, Solar> getEdificaciones() {
        return edificaciones;
    }

    public void subirPreciosTotal(Juego juego) {
        this.contadorVueltas++;
        if (this.contadorVueltas == 4 * juego.getJugadores().size()) {
            this.sePuedeSubirPrecio = true;
        }
    }

    public void subirPrecios() {
        ArrayList<Grupo> todasLasCasillas = new ArrayList<>();
        todasLasCasillas.add(this.ladoNorte);
        todasLasCasillas.add(this.ladoSur);
        todasLasCasillas.add(this.ladoOeste);
        todasLasCasillas.add(this.ladoLeste);

        if (this.sePuedeSubirPrecio) {

            for (Casilla casilla : todasLasCasillas.get(0).getCasillas()) {
                if (casilla instanceof Solar) {
                    ((Solar) casilla).setValor(((Solar) casilla).getValor() * 1.05);
                }
            }
            for (Casilla casilla : todasLasCasillas.get(1).getCasillas()) {
                if (casilla instanceof Solar) {
                    ((Solar) casilla).setValor(((Solar) casilla).getValor() * 1.05);
                }
            }
            for (Casilla casilla : todasLasCasillas.get(2).getCasillas()) {
                if (casilla instanceof Solar) {
                    ((Solar) casilla).setValor(((Solar) casilla).getValor() * 1.05);
                }
            }
            for (Casilla casilla : todasLasCasillas.get(3).getCasillas()) {
                if (casilla instanceof Solar) {
                    ((Solar) casilla).setValor(((Solar) casilla).getValor() * 1.05);
                }
            }
        }
    }


    public int getContadorVueltas() {
        return contadorVueltas;
    }

    public void listarEnVenta() {
        String texto = "";
        Propiedad propiedad;
        for (Casilla casilla : this.casillas.values()) {
            if (casilla instanceof Propiedad) {
                propiedad = (Propiedad) casilla;
                if (propiedad.getDuenho() == null) {
                    texto += "{\n\tNombre: " + propiedad.getNombreSinEspacio() + "\n\tTipo: ";
                    if (propiedad instanceof Solar) {
                        texto += "solar";
                    } else if (propiedad instanceof Transportes) {
                        texto += "transporte";
                    } else if (propiedad instanceof Servicio) {
                        texto += "servicio";
                    }
                    texto += "\n\tMonopoly.Valor: " + propiedad.getValor() + "\n}\n";
                }
            }
        }
        System.out.println(texto);
    }

    public boolean sePuedeComprar(Casilla casilla) {
        if ((casilla instanceof Propiedad) && (((Propiedad) casilla).getDuenho() == null)) {
            return true;
        } else
            return false;
    }

    public void eliminarCasa(String id) {
        if (this.edificaciones.containsKey(id)) {
            this.edificaciones.remove(id);
            this.idCasas.remove(id);
        }
    }

    public void eliminarHotel(String id) {
        if (this.edificaciones.containsKey(id)) {
            this.edificaciones.remove(id);
            this.idHoteles.remove(id);
        }
    }

    public void eliminarPiscina(String id) {
        if (this.edificaciones.containsKey(id)) {
            this.edificaciones.remove(id);
            this.idPiscinas.remove(id);
        }
    }

    public void eliminarPista(String id) {
        if (this.edificaciones.containsKey(id)) {
            this.edificaciones.remove(id);
            this.idPistas.remove(id);
        }
    }

    public String idCasa(Solar solar) {
        String id;
        String[] aux;
        int numero;
        if (this.idCasas == null) {
            this.idCasas = new ArrayList<>();
        }
        if (this.edificaciones == null) {
            this.edificaciones = new HashMap<>();
        }
        if (this.idCasas.size() == 0) {
            id = "casa-1";
        } else {
            aux = this.idCasas.get(this.idCasas.size() - 1).split("-");
            numero = Integer.parseInt(aux[1]);
            numero++;
            id = "casa-" + numero;
        }
        this.idCasas.add(id);
        this.edificaciones.put(id, solar);
        return id;
    }

    public String idHotel(Solar solar) {
        String id;
        String[] aux;
        int numero;
        if (this.idHoteles == null) {
            this.idHoteles = new ArrayList<>();
        }
        if (this.edificaciones == null) {
            this.edificaciones = new HashMap<>();
        }
        if (this.idHoteles.size() == 0) {
            id = "hotel-1";
        } else {
            aux = this.idHoteles.get(this.idHoteles.size() - 1).split("-");
            numero = Integer.parseInt(aux[1]);
            numero++;
            id = "hotel-" + numero;
        }
        this.idHoteles.add(id);
        this.edificaciones.put(id, solar);
        return id;
    }

    public String idPiscina(Solar solar) {
        String id;
        String[] aux;
        int numero;
        if (this.idPiscinas == null) {
            this.idPiscinas = new ArrayList<>();
        }
        if (this.edificaciones == null) {
            this.edificaciones = new HashMap<>();
        }
        if (this.idPiscinas.size() == 0) {
            id = "piscina-1";
        } else {
            aux = this.idPiscinas.get(this.idPiscinas.size() - 1).split("-");
            numero = Integer.parseInt(aux[1]);
            numero++;
            id = "piscina-" + numero;
        }
        this.idPiscinas.add(id);
        this.edificaciones.put(id, solar);
        return id;
    }

    public String idPista(Solar solar) {
        String id;
        String[] aux;
        int numero;
        if (this.idPistas == null) {
            this.idPistas = new ArrayList<>();
        }
        if (this.edificaciones == null) {
            this.edificaciones = new HashMap<>();
        }
        if (this.idPistas.size() == 0) {
            id = "pista-1";
        } else {
            aux = this.idPistas.get(this.idPistas.size() - 1).split("-");
            numero = Integer.parseInt(aux[1]);
            numero++;
            id = "pista-" + numero;
        }
        this.idPistas.add(id);
        this.edificaciones.put(id, solar);
        return id;
    }


    @Override
    public String toString() {
        String BLANCO = Valor.WHITE;
        String textoAvataresNorte = "";
        String textoAvataresSur = "";
        String textoPropiedadesNorte = "";
        String textoPropiedadesSur = "";
        String textoProps;

        String textoTope = BLANCO + "┌";
        for (int i = 0; i < 10; i++) {
            textoTope += "──────────────────┬";
        }
        textoTope += "──────────────────┐\n";

        String textoTopeAbajo = BLANCO + "└";
        for (int i = 0; i < 10; i++) {
            textoTopeAbajo += "──────────────────┴";
        }
        textoTopeAbajo += "──────────────────┘\n";


        String textoTopeMedioArriba = BLANCO + "├──────────────────┼";
        for (int i = 0; i < 8; i++) {
            textoTopeMedioArriba += "──────────────────┴";
        }
        textoTopeMedioArriba += "──────────────────┼──────────────────┤\n";

        String textoTopeMedioAbajo = BLANCO + "├──────────────────┼";
        for (int i = 0; i < 8; i++) {
            textoTopeMedioAbajo += "──────────────────┬";
        }
        textoTopeMedioAbajo += "──────────────────┼──────────────────┤\n";


        String textoEspaciado = BLANCO + "│ ";
        for (int i = 0; i < 169; i++) {
            textoEspaciado += " ";
        }
        textoEspaciado += "│";
        // ┬
        String textoTopeEspaciado = BLANCO + "├──────────────────┤";
        for (int i = 0; i < 170; i++) {
            textoTopeEspaciado += " ";
        }
        textoTopeEspaciado += "├──────────────────┤\n";

        String textoNorte = "";
        String textoSur = "";
        String textoOesteLeste = "";
        int nCasillas = ladoNorte.getCasillas().size();
        for (int i = 0; i < nCasillas; i++) {
            textoNorte += BLANCO + "│" + ladoNorte.getCasillas().get(i).getColorGrupo() + ladoNorte.getCasillas().get(i).getNombre();
            textoAvataresNorte += BLANCO + "│";
            textoAvataresSur += BLANCO + "│";
            if (ladoNorte.getCasillas().get(i).haiAvatar()) {
                textoAvataresNorte += ladoNorte.getCasillas().get(i).getIds();
            } else {
                for (int j = 0; j < 18; j++) {
                    textoAvataresNorte += " ";
                }
            }
            //textoPropiedadesNorte += BLANCO + "│" + ladoNorte.getCasillas().get(i).getIdsEdificaciones();
            //textoPropiedadesSur += BLANCO + "│" + ladoSur.getCasillas().get(i).getIdsEdificaciones();

            if (ladoSur.getCasillas().get(i).haiAvatar()) {
                textoAvataresSur += ladoSur.getCasillas().get(i).getIds();
            } else {
                for (int j = 0; j < 18; j++) {
                    textoAvataresSur += " ";
                }
            }
            textoSur += BLANCO + "│" + ladoSur.getCasillas().get(i).getColorGrupo() + ladoSur.getCasillas().get(i).getNombre();
            if (i < 9) {
                // textoProps = BLANCO + "│" + ladoOeste.getCasillas().get(i).getIdsEdificaciones() + textoEspaciado +
                // ladoLeste.getCasillas().get(i).getIdsEdificaciones() + BLANCO + "│\n";
                textoOesteLeste += BLANCO + "│" + ladoOeste.getCasillas().get(i).getColorGrupo() +
                        ladoOeste.getCasillas().get(i).getNombre() + textoEspaciado + ladoLeste.getCasillas().get(i).getColorGrupo() +
                        ladoLeste.getCasillas().get(i).getNombre() + BLANCO + "│\n│";
                if (ladoOeste.getCasillas().get(i).haiAvatar()) {
                    textoOesteLeste += ladoOeste.getCasillas().get(i).getIds();
                } else {
                    for (int j = 0; j < 18; j++) {
                        textoOesteLeste += " ";
                    }
                }
                textoOesteLeste += textoEspaciado;
                if (ladoLeste.getCasillas().get(i).haiAvatar()) {
                    textoOesteLeste += ladoLeste.getCasillas().get(i).getIds();
                } else {
                    for (int j = 0; j < 18; j++) {
                        textoOesteLeste += " ";
                    }
                }
                textoOesteLeste += BLANCO + "│\n";//+ textoProps;

            }
            if (i < 8)
                textoOesteLeste += BLANCO + textoTopeEspaciado;
        }
        String texto = BLANCO + textoTope + textoNorte + BLANCO + "│\n" + textoAvataresNorte + BLANCO + "│\n" + textoTopeMedioArriba +
                textoOesteLeste + textoTopeMedioAbajo + textoSur + BLANCO + "│\n" + textoAvataresSur + BLANCO + "│\n" + textoTopeAbajo;
        return texto;
    }
}
