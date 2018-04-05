package p3.src;

import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

import java.io.*;
import java.util.List;

public class Muszka {

    public static void main(String[] args) {
        TeleChargeAndPaySystem pasarelaPago = new TeleChargeAndPaySystem();
        Sistema muzska;
        try {
            if (args.length == 1 && args[0].equals("clientes.txt")) {
                muzska = new Sistema(pasarelaPago);
                System.out.println("Cargando clientes...");
                cargarClientes(muzska, args[0]);
            } else if (args.length == 0) {
                System.out.println("Cargando datos de \"muzska.ser\"");
                FileInputStream fileIn = new FileInputStream("muzska.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                muzska = (Sistema) in.readObject();
                muzska.setPasarelaPago(pasarelaPago);
                in.close();
                fileIn.close();
            } else {
                System.out.println("Numero de argumentos invalido: " + args.length);
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
            FileOutputStream fileOut = new FileOutputStream("muzska.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(muzska);
            out.close();
            fileOut.close();
            System.out.println("Datos serializables guardados en \"muzska.ser\"");
        } catch (IOException i) {
            i.printStackTrace();
        }
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

        try {

            while ((linea = buffer.readLine()) != null) {
                String split1[] = linea.split(";");
                if (i != 0) {
                    rol = split1[0];
                    nif = split1[1];
                    String split2[] = split1[2].split(", ");
                    apellidos = split2[0];
                    nombre = split2[1];
                    pwd = split1[3];
                    tarjeta = split1[4];

                    if (rol.equals("O")) {
                        ofertante = new Ofertante(nombre, apellidos, nif, pwd, tarjeta);
                        sistema.anadirUsuario(ofertante);
                    } else if (rol.equals("D")) {
                        demandante = new Demandante(nombre, apellidos, nif, pwd, tarjeta);
                        sistema.anadirUsuario(demandante);
                    } else if (rol.equals("OD") || rol.equals("DO")) {
                        ofertante = new Ofertante(nombre, apellidos, nif, pwd, tarjeta);
                        demandante = new Demandante(nombre, apellidos, nif, pwd, tarjeta);
                        sistema.anadirUsuario(ofertante);
                        sistema.anadirUsuario(demandante);
                    } else {
                        throw new IllegalArgumentException("Rol de usuario invalido: " + rol);
                    }
                }
                i = 1;
            }
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage() + ", Usuario no añadido");
        }
    }
}
