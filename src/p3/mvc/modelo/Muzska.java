package p3.mvc.modelo;

import p3.mvc.controlador.Controlador;
import p3.mvc.interfaz.GuiInmobiliaria;

import java.io.*;

public class Muzska {

    public static void main(String[] args){
        GuiInmobiliaria gui = new GuiInmobiliaria("Muzska");
        Sistema muzska/* = new Sistema()*/;
        //Controlador  controlador = new Controlador(gui, muzska);
        //gui.setControlador( controlador );

        try {
            if (args.length == 1 && args[0].equals("clientes.txt")) {
                muzska = new Sistema();
                System.out.println("Cargando clientes...");
                cargarClientes(muzska, args[0]);
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
            Controlador controlador = new Controlador(gui, muzska);
            gui.setControlador(controlador);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Clase de Sistema inexistente");
            c.printStackTrace();
            return;
        }
        //guardarDatos(muzska);
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
}
