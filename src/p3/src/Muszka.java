package p3.src;

import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

import java.io.*;

public class Muszka {

    public static void main(String[] args) throws IOException {
        TeleChargeAndPaySystem pasarelaPago = new TeleChargeAndPaySystem();
        Sistema muzska;
        if(args!=null) {
            muzska = new Sistema(pasarelaPago);
            try {
                cargarClientes(muzska, args[0]);
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                return;
            }
        }else {
            try {
                FileInputStream fileIn = new FileInputStream("Proyecto/muszka.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                muzska = (Sistema) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("Clase de Sistema inexistente");
                c.printStackTrace();
                return;
            }
        }









        try {
            FileOutputStream fileOut = new FileOutputStream("Proyecto/muzska.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(muzska);
            out.close();
            fileOut.close();
            System.out.println("Datos serializables guardados en Proyecto/muzska.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static void cargarClientes(Sistema sistema, String fichero) throws IOException, IllegalArgumentException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fichero)));
        String linea, rol, nif, nombre, apellidos, pwd, tarjeta;
        int i=0;

        while ((linea = buffer.readLine())!=null) {
            String split1[] = linea.split(";");
            if(i!=0) {
                rol = split1[0];
                nif = split1[1];
                String split2[] = split1[2].split(", ");
                apellidos = split2[0];
                nombre = split2[1];
                pwd = split1[3];
                tarjeta = split1[4];

                try {
                    if (rol.equals("O")) {
                        Ofertante ofertante = new Ofertante(nombre, apellidos, nif, pwd, tarjeta);
                        sistema.anadirUsuario(ofertante);
                    } else if (rol.equals("D")) {
                        Demandante demandante = new Demandante(nombre, apellidos, nif, pwd, tarjeta);
                        sistema.anadirUsuario(demandante);
                    } else if (rol.equals("OD") || rol.equals("DO")) {
                        Ofertante ofertante = new Ofertante(nombre, apellidos, nif, pwd, tarjeta);
                        Demandante demandante = new Demandante(nombre, apellidos, nif, pwd, tarjeta);
                        sistema.anadirUsuario(ofertante);
                        sistema.anadirUsuario(demandante);
                    } else {
                        throw new IllegalArgumentException("Rol de usuario invalido: " + rol);
                    }
                }catch(NullPointerException npe){
                    System.out.println(npe.getMessage());
                }
            }
            i=1;
        }
    }
}
