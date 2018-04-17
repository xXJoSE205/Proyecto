package p3.src;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Esta clase contiene el programa principal de la aplicacion MUZSKA
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class Muszka {
    /**
     * Programa principal de MUZSKA, al finalizar guarda en "muzska.ser" toda la informacion del sistema
     *
     * @param args "clientes.txt" si se quieren cargar los clientes desde el fichero
     *             nada si se quiere recuperar el sistema desde "muzska.ser"
     */
    public static void main(String[] args) {
        Sistema muzska;
        Cliente cliente = null;
        try {
            if (args.length == 1 && args[0].equals("clientes.txt")) {
                Cliente ofertante;
                Inmueble inm;
                Oferta ofe;
                muzska = new Sistema();
                System.out.println("Cargando clientes...");
                cargarClientes(muzska, args[0]);
                ofertante = new Ofertante("Enrique","Cedeno","12378970G","xPeke","8252358967346235");
                muzska.anadirUsuario(ofertante);
                inm = new Inmueble(3,1,80,"Paseo Castellana", 2, false, (Ofertante)ofertante);
                muzska.anadirInmueble(inm);
                ofe = new Oferta(1200, LocalDate.now(), LocalDate.now().plusMonths(4), false, 200, inm);
                inm.anadirOferta(ofe);
                muzska.anadirOferta(ofe);
            } else if (args.length == 0) {
                System.out.println("Cargando datos de \"muzska.ser\"...");
                FileInputStream fileIn = new FileInputStream("muzska.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                muzska = (Sistema) in.readObject();
                in.close();
                fileIn.close();
            } else {
                System.out.println("Numero de argumentos invalido: " + args.length+", esperados 0 o 1");
                System.out.println("Fichero de entrada distinto a \"clientes.txt\"");
                return;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Clase de Sistema inexistente");
            c.printStackTrace();
            return;
        }

        try {
            System.out.println("¿Quieres iniciar sesion?: <Si> <No>");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            if (line.equals("Si") || line.equals("si")) {
                System.out.println("¿Como quieres iniciar sesion?: <Demandante> <Ofertante> <Gerente>");
                line = br.readLine();
                String split1[];
                switch (line) {
                    case "Gerente":
                    case "gerente":
                        for (int i = 0; i < 3; i++) {
                            System.out.println("Introduce tu NIF y tu contrasena: ");
                            line = br.readLine();
                            split1 = line.split("\\s+");
                            if (split1.length != 2) {
                                System.out.println("Argumentos invalidos");
                            } else {
                                if (muzska.login(split1[0], split1[1])) {
                                    System.out.println("Inicio de sesion exitoso");
                                    break;
                                } else {
                                    System.out.println("NIF y/o contrasenas incorrectos");
                                }
                            }
                            if (i == 2) {
                                System.out.println("Intentos agotados");
                                guardarDatos(muzska);
                                return;
                            }
                        }
                        break;
                    case "Demandante":
                    case "demandante": {
                        Demandante clReg = null;
                        for (int i = 0; i < 3; i++) {
                            System.out.println("Introduce tu NIF y tu contrasena: ");
                            line = br.readLine();
                            split1 = line.split("\\s+");
                            if (split1.length != 2) {
                                System.out.println("Argumentos invalidos");
                            } else {
                                List<Cliente> clientes = muzska.getUsuarios();
                                for (Cliente d : clientes) {
                                    if (d.getNif().equals(split1[0])) {
                                        if (d instanceof Demandante) {
                                            clReg = (Demandante) d;
                                            cliente = clReg;
                                            break;
                                        }
                                    }
                                }
                                if (clReg != null) {
                                    if (muzska.login(clReg, split1[0], split1[1])) {
                                        System.out.println("Inicio de sesion exitoso");
                                        break;
                                    } else {
                                        System.out.println("NIF y/o contrasenas incorrectos");
                                    }
                                } else {
                                    System.out.println("No esta registrado nadie con ese NIF");
                                }
                            }
                            if (i == 2) {
                                System.out.println("Intentos agotados");
                                if (clReg != null) {
                                    clReg.bloquear();
                                    System.out.println("Este NIF ha sido bloqueado. pongase en contacto con el gerente");
                                }
                                guardarDatos(muzska);
                                return;
                            }
                            cliente = null;
                            clReg = null;
                        }
                        break;
                    }
                    case "Ofertante":
                    case "ofertante": {
                        Ofertante clReg = null;
                        for (int i = 0; i < 3; i++) {
                            System.out.println("Introduce tu NIF y tu contrasena: ");
                            line = br.readLine();
                            split1 = line.split("\\s+");
                            if (split1.length != 2) {
                                System.out.println("Argumentos invalidos");
                            } else {
                                List<Cliente> clientes = muzska.getUsuarios();
                                for (Cliente d : clientes) {
                                    if (d.getNif().equals(split1[0])) {
                                        if (d instanceof Ofertante) {
                                            clReg = (Ofertante) d;
                                            cliente = clReg;
                                            break;
                                        }
                                    }
                                }
                                if (clReg != null) {
                                    if (muzska.login(clReg, split1[0], split1[1])) {
                                        System.out.println("Inicio de sesion exitoso");
                                        break;
                                    } else {
                                        System.out.println("NIF y/o contrasenas incorrectos");
                                    }
                                } else {
                                    System.out.println("No esta registrado nadie con ese NIF");
                                }
                            }
                            if (i == 2) {
                                System.out.println("Intentos agotados");
                                guardarDatos(muzska);
                                return;
                            }
                            cliente = null;
                            clReg = null;
                        }
                        break;
                    }
                    default:
                        System.out.println("Error, no existe el tipo indicado\nFin de programa");
                        guardarDatos(muzska);
                        return;
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            guardarDatos(muzska);
            return;
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
            npe.printStackTrace();
            guardarDatos(muzska);
            return;
        }

        try {
            if(cliente instanceof Demandante){
                if(((Demandante) cliente).isBloqueado()){
                    System.out.println("Estas bloqueado. Ponte en contacto con el gerente");
                    guardarDatos(muzska);
                    return;
                }
            }
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String line, split[];
            List<Inmueble> busquedaIN;
            List<Oferta> busqeudaOF = null;
            List<Demandante> usuarosBlock = new ArrayList<>();
            label:
            do {
                System.out.println("Que quieres hacer: <buscar>, <busqueda avanzada> ,<reservar>, <cancelar reserva>, <manejar ofertas>,\n" +
                        " <modificar ofertas>, <alquilar>, <desbloquear>, <anadir inmueble>, <anadir oferta>");
                line = br.readLine();
                switch (line) {
                    case "buscar":
                    case "Buscar":
                        System.out.println("Introduce los campos de busqueda: hab., banos, dimen., planta, ¿ascensor?"
                                + ", direccion (-1 o null(direccion) si no se quieren tener en cuenta)");
                        line = br.readLine();
                        split = line.split("\\s+");
                        if (split.length != 6) {
                            System.out.println("Numero de campos inscorrecto: " + split.length);
                        } else {
                            if (split[5].equals("null")) {
                                split[5] = null;
                            }
                            busquedaIN = muzska.buscar(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
                                    Integer.parseInt(split[2]), Integer.parseInt(split[3]),
                                    Boolean.parseBoolean(split[4]), split[5]);
                            if (busquedaIN.size() == 0) {
                                System.out.println("Busqueda fallida, no se han encontrado resultados");
                            }
                            busquedaIN.sort(Comparator.comparingInt(Inmueble::getnHabitaciones));
                            int i = 1;
                            label1:
                            for (Inmueble inm : busquedaIN) {
                                System.out.println("Inmueble " + i);
                                System.out.println(inm.toString());
                                i++;
                                line = br.readLine();
                                switch (line) {
                                    case "stop":
                                    case "Stop":
                                        break label1;
                                    case "anadir comentario":
                                    case "Anadir comentario":
                                        if (cliente == null || cliente instanceof Ofertante && !muzska.getGerente().isLogeado()) {
                                            System.out.println("No tienes permiso para anadir un comantario");
                                        } else {
                                            try {
                                                System.out.println("Introduce tu comentario");
                                                line = br.readLine();
                                                Opinion comentario = new Comentario((Demandante) cliente, line);
                                                inm.anadirOpinion(comentario);
                                                muzska.anadirOpinion(comentario);
                                            } catch (NullPointerException npe) {
                                                System.out.println(npe.getMessage());
                                            }
                                        }
                                        break;
                                    case "anadir valoracion":
                                    case "Anadir valoracion":
                                        if (cliente == null || cliente instanceof Ofertante && !muzska.getGerente().isLogeado()) {
                                            System.out.println("No tienes permiso para anadir una puntuacion");
                                        } else {
                                            try {
                                                System.out.println("Introduce tu puntuacion");
                                                line = br.readLine();
                                                Opinion comentario = new Valoracion((Demandante) cliente, Integer.parseInt(line));
                                                inm.anadirOpinion(comentario);
                                                muzska.anadirOpinion(comentario);
                                            } catch (IllegalArgumentException iae) {
                                                System.out.println(iae.getMessage());
                                            }
                                        }
                                        break;
                                }
                            }
                        }
                        break;
                    case "busqueda avanzada":
                    case "Busqueda avanzada":
                    case "b. avanzada":
                        if (cliente == null && !muzska.getGerente().isLogeado()) {
                            System.out.println("No tienes permiso para la busqueda avanzada");
                        } else {
                            try {
                                System.out.println("Introduce los campos de busqueda: hab., banos, dimen., planta, ¿ascensor?, "
                                        + "precio, ¿vacacional?, direccion (-1 o null(direccion) si no se quieren tener en cuenta)");
                                line = br.readLine();
                                split = line.split("\\s+");
                                if (split.length != 8) {
                                    System.out.println("Numero de campos inscorrecto: " + split.length);
                                } else {
                                    if (split[5].equals("null")) {
                                        split[5] = null;
                                    }
                                    busqeudaOF = muzska.avanzada(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
                                            Integer.parseInt(split[2]), Integer.parseInt(split[3]),
                                            Boolean.parseBoolean(split[4]), split[5], Double.parseDouble(split[6]),
                                            Boolean.parseBoolean(split[7]), cliente);
                                    if (busqeudaOF.size() == 0) {
                                        System.out.println("Busqueda fallida, no se han encontrado resultados");
                                    }
                                    busqeudaOF.sort(Comparator.comparingDouble(Oferta::getPrecio));
                                    int i = 1;
                                    for (Oferta ofer : busqeudaOF) {
                                        System.out.println("Oferta " + i);
                                        System.out.println(ofer.toString());
                                        i++;
                                        line = br.readLine();
                                        if (line.equals("stop") || line.equals("Stop")) {
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "reservar":
                    case "Reservar":
                        if ((cliente == null || cliente instanceof Ofertante) && !muzska.getGerente().isLogeado()) {
                            System.out.println("No tienes permiso para una reserva");
                        } else if (busqeudaOF == null) {
                            System.out.println("Primero realiza una busqueda avanzada");
                        } else {
                            try {
                                System.out.println("Indica el nuemro de la oferta a reservar");
                                line = br.readLine();
                                if (Integer.parseInt(line) < 1) {
                                    System.out.println("Numero invalido");
                                } else {
                                    if (busqeudaOF.get(Integer.parseInt(line) - 1).reservar((Demandante) cliente)) {
                                        System.out.println("Reserva realizada con exito");
                                    } else {
                                        System.out.println("Error en la reserva");
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "cancelar reserva":
                    case "Cancelra reserva":
                        if ((cliente == null || cliente instanceof Ofertante) && !muzska.getGerente().isLogeado()) {
                            System.out.println("No tienes permiso para cancelar una reserva");
                        } else {
                            try {
                                System.out.println("Cancelando reserva...");
                                if (((Demandante) cliente).getReserva().getOferta().cancelarReserva()) {
                                    System.out.println("Reserva cancelada correcatmente");
                                } else {
                                    System.out.println("No tienes ninguna reserva activa");
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "manejar ofertas":
                    case "Manejar ofertas":
                        List<Oferta> pendientes;
                        if (!muzska.getGerente().isLogeado()) {
                            System.out.println("No tienes permiso para manejar ofertas");
                        } else {
                            pendientes = muzska.getOfertas();
                            for (Oferta of : pendientes) {
                                if (of.getEstado() != Estado.PENDIENTE) {
                                    pendientes.remove(of);
                                }
                            }
                            if (pendientes.size() == 0) {
                                System.out.println("No hay ofertas pendientes de revision");
                            } else {
                                int i = 1;
                                label1:
                                for (Oferta of : pendientes) {
                                    System.out.println("Oferta " + i);
                                    System.out.println(of.toString());
                                    i++;
                                    line = br.readLine();
                                    switch (line) {
                                        case "stop":
                                        case "Stop":
                                            break label1;
                                        case "aprobar":
                                        case "Aprobar":
                                            of.aprobar();
                                            pendientes.remove(of);
                                            break;
                                        case "rechazar":
                                        case "Rechazar":
                                            of.rechazar();
                                            pendientes.remove(of);
                                            System.out.println("¿Quieres proponer modificaciones?:");
                                            line = br.readLine();
                                            if (line.equals("si") || line.equals("Si")) {
                                                System.out.println("Introduce las propuestas:");
                                                line = br.readLine();
                                                of.getInmueble().getDueno().anadirModificaciones(line, false);
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                        break;
                    case "Modificar oferta":
                    case "modificar ofertas": {
                        List<Oferta> rechazadas;
                        String split2[];
                        if ((cliente == null || cliente instanceof Demandante) && !muzska.getGerente().isLogeado()) {
                            System.out.println("No tienes permiso para modificar una oferta");
                        } else {
                            System.out.println("Cancelando reserva...");


                            rechazadas = muzska.getOfertas();
                            for (Oferta of : rechazadas) {
                                if (of.getInmueble().getDueno() != cliente) {
                                    rechazadas.remove(of);
                                } else if (of.getEstado() != Estado.RECHAZADO) {
                                    rechazadas.remove(of);
                                }
                            }
                            if (rechazadas.size() == 0) {
                                System.out.println("No hay ofertas posibles para modificar");
                            } else {
                                int i = 1;
                                for (Oferta of : rechazadas) {
                                    System.out.println("Oferta " + i);
                                    System.out.println(of.toString());
                                    i++;
                                    line = br.readLine();
                                    if (line.equals("stop") || line.equals("Stop")) {
                                        break;
                                    } else if (line.equals("modificar") || line.equals("Modificar")) {
                                        int flag = 0;
                                        while (flag != 1) {
                                            try {
                                                System.out.println("Introduce las nuevas caracteristicas: precio, fecha de inicio"
                                                        + ", fecha fin, ¿vacacional?, fianza");
                                                line = br.readLine();
                                                if (line.equals("stop") || line.equals("Stop")) {
                                                    break;
                                                }
                                                split = line.split("\\s+");
                                                if (split.length != 5) {
                                                    System.out.println("Numero de argumentos invalido, se esperan 5");
                                                } else {
                                                    of.setPrecio(Integer.parseInt(split[0]));
                                                    split2 = split[1].split("-");
                                                    of.setFechaInicio(LocalDate.of(Integer.parseInt(split2[0]),
                                                            Integer.parseInt(split2[1]), Integer.parseInt(split2[2])));
                                                    split2 = split[2].split("-");
                                                    of.setFechaFin(LocalDate.of(Integer.parseInt(split2[0]),
                                                            Integer.parseInt(split2[1]), Integer.parseInt(split2[2])));
                                                    of.setVacacional(Boolean.parseBoolean(split[3]));
                                                    of.setFianza(Integer.parseInt(split[4]));
                                                    flag = 1;
                                                }
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case "alquilar":
                    case "Alquilar":
                        if ((cliente == null || cliente instanceof Ofertante) && !muzska.getGerente().isLogeado()) {
                            System.out.println("No tienes permiso para alquilar");
                        } else if (busqeudaOF == null) {
                            System.out.println("Primero realiza una busqueda avanzada");
                        } else {
                            try {
                                System.out.println("Indica el nuemro de la oferta a alquilar");
                                line = br.readLine();
                                if (Integer.parseInt(line) < 1) {
                                    System.out.println("Numero invalido");
                                } else {
                                    if (muzska.alquilar((Demandante) cliente, busqeudaOF.get(Integer.parseInt(line) - 1))) {
                                        System.out.println("Oferta alquilada con exito");
                                    } else {
                                        System.out.println("Erroro al alquilar la oferta");
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "Desbloquear":
                    case "desbloquear":
                        if (!muzska.getGerente().isLogeado()) {
                            System.out.println("No tienes permisos para desbloquear un usuario");
                        } else {
                            try {
                                for (Cliente dem : muzska.getUsuarios()) {
                                    if (dem instanceof Demandante && ((Demandante) dem).isBloqueado()) {
                                        usuarosBlock.add((Demandante) dem);
                                    }
                                }
                                if (usuarosBlock.isEmpty()) {
                                    System.out.println("No hay usuarios bloqueados");
                                } else {
                                    usuarosBlock.sort(Comparator.comparing(Demandante::getNif));
                                }
                                int i = 1;
                                for (Demandante dem : usuarosBlock) {
                                    System.out.println("Usuario" + i);
                                    System.out.println(dem.toString());
                                    i++;
                                }
                                System.out.println("Numero de usuario que desbloquear");
                                line = br.readLine();
                                if (line.equals("stop") || line.equals("Stop")) {
                                    break;
                                } else {
                                    if (usuarosBlock.get(Integer.parseInt(line) - 1).desbloquear()) {
                                        System.out.println("Usuario desbloqueado");
                                    } else {
                                        System.out.println("Error al desbloquear");
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "Anadir Inmueble":
                    case "anadir inmueble":
                        if (((cliente == null || cliente instanceof Demandante) && !muzska.getGerente().isLogeado())) {
                            System.out.println("No tienes permisos para crear un inmueble");
                        } else {
                            try {
                                System.out.println("Introduce los datos del inmueble: nHabitaciones, nBanos, dimensiones, direccion, planta, ascensor");
                                line = br.readLine();
                                split = line.split("\\s+");
                                if (split.length != 6) {
                                    System.out.println("Numero de campos inscorrecto: " + split.length);
                                } else {
                                    Inmueble in = new Inmueble(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
                                            Integer.parseInt(split[2]), split[3], Integer.parseInt(split[4]),
                                            Boolean.parseBoolean(split[5]), (Ofertante) cliente);
                                    if (muzska.anadirInmueble(in)) {
                                        System.out.println("Inmueble anadido correctamente");
                                    } else {
                                        System.out.println("Error al anadir el inmueble");
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "Anadir Oferta":
                    case "anadir oferta": {
                        List<Inmueble> list = new ArrayList<>();
                        String split2[];
                        String split3[];
                        if (((cliente == null || cliente instanceof Demandante) && !muzska.getGerente().isLogeado())) {
                            System.out.println("No tienes permisos para crear una oferta ");
                        } else {
                            for (Inmueble inm : muzska.getInmuebles()) {
                                if (inm.getDueno().equals(cliente)) {
                                    list.add(inm);
                                }
                            }
                            if (list.isEmpty()) {
                                System.out.println("No tienes inmuebles");
                            } else {
                                System.out.println("Estos son tus inmuebles:");
                                int i = 0;
                                for (Inmueble inm : list) {
                                    System.out.println("Inmueble:" + i);
                                    System.out.println(inm.toString());
                                    i++;
                                }
                                line = br.readLine();
                                System.out.println("Numero de inmueble que ofertar");
                                if (line.equals("stop") || line.equals("Stop")) {
                                    break label;
                                } else {
                                    Inmueble aux;
                                    aux = list.get(Integer.parseInt(line) - 1);
                                    if (aux == null) {
                                        System.out.println("Numero incorrecto");
                                    } else {
                                        try {
                                            System.out.println("Introduce los datos de la oferta: precio, fecha de inicio"
                                                    + ", fecha fin, ¿vacacional?, fianza");
                                            line = br.readLine();
                                            if (line.equals("stop") || line.equals("Stop")) {
                                                break;
                                            }
                                            split = line.split("\\s+");
                                            if (split.length != 5) {
                                                System.out.println("Numero de argumentos invalido, se esperan 5");
                                            } else {
                                                split2 = split[1].split("-");
                                                split3 = split[2].split("-");
                                                Oferta ofer = new Oferta(Integer.parseInt(split[0]),
                                                        LocalDate.of(Integer.parseInt(split2[0]), Integer.parseInt(split2[1]),
                                                                Integer.parseInt(split2[2])), LocalDate.of(Integer.parseInt(split3[0]),
                                                        Integer.parseInt(split3[1]), Integer.parseInt(split3[2])),
                                                        Boolean.parseBoolean(split[3]), Integer.parseInt(split[4]), aux);
                                                if (muzska.anadirOferta(ofer)) {
                                                    System.out.println("Oferta anadida correctamente");
                                                } else {
                                                    System.out.println("Error al anadir la oferta");
                                                }
                                            }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
                muzska.comprobarReservas();
            } while (!line.equals("salir") && !line.equals("exit"));
        }catch (IOException ioe) {
            ioe.printStackTrace();
            guardarDatos(muzska);
            return;
        }
        if(cliente!=null) {
            System.out.println("Cerrando sesion...");
            muzska.logout(cliente);
        }else if(muzska.getGerente().isLogeado()){
            System.out.println("Cerrando sesion...");
            muzska.logout();
        }
        guardarDatos(muzska);
    }

    /**
     * Registra los clientes de un fichero en el sistema
     *
     * @param sistema Sistema en el que registrar los clientes
     * @param fichero Nombre del fichero del cual cargar los clientes
     * @throws IOException Si ocurre algun error al manejar el fichero
     */
    private static void cargarClientes(Sistema sistema, String fichero) throws IOException{
        BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fichero)));
        String linea, rol, nif, nombre, apellidos, pwd, tarjeta;
        Ofertante ofertante;
        Demandante demandante;
        int i=0;

        while ((linea = buffer.readLine()) != null) {
            try {
                String split1[] = linea.split(";");
                if (i != 0) {
                    rol = split1[0];
                    nif = split1[1];
                    String split2[] = split1[2].split(", ");
                    apellidos = split2[0];
                    nombre = split2[1];
                    pwd = split1[3];
                    tarjeta = split1[4];

                    switch (rol) {
                        case "O":
                            ofertante = new Ofertante(nombre, apellidos, nif, pwd, tarjeta);
                            sistema.anadirUsuario(ofertante);
                            break;
                        case "D":
                            demandante = new Demandante(nombre, apellidos, nif, pwd, tarjeta);
                            sistema.anadirUsuario(demandante);
                            break;
                        case "OD":
                        case "DO":
                            ofertante = new Ofertante(nombre, apellidos, nif, pwd, tarjeta);
                            demandante = new Demandante(nombre, apellidos, nif, pwd, tarjeta);
                            sistema.anadirUsuario(ofertante);
                            sistema.anadirUsuario(demandante);
                            break;
                        default:
                            throw new IllegalArgumentException("Rol de usuario invalido: " + rol);
                    }
                }
                i = 1;
            }catch (NullPointerException npe) {
                System.out.println(npe.getMessage());
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage() + ", Usuario no anadido");
            }
        }
    }

    /**
     * Guarda los datos del Sistema en el fichero "muzska.ser"
     *
     * @param sistema Sistema del cual se quieren guardar los datos
     */
    private static void guardarDatos(Sistema sistema){
        try {
            FileOutputStream fileOut = new FileOutputStream("muzska.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(sistema);
            out.close();
            fileOut.close();
            System.out.println("Datos serializables guardados en \"muzska.ser\"");
        }catch(IOException ioe) {
            System.out.println("Error al manejar el fichero \"muzska.ser\"");
            ioe.printStackTrace();
        }
    }
}
