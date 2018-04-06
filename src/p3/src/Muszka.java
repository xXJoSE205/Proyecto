package p3.src;

import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

import java.io.*;
import java.util.List;

public class Muszka {

    public static void main(String[] args) {
        Sistema muzska;
        Cliente cliente = null;
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
            System.out.println("¿Quieres iniciar sesion?: <Si> <No>");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            if (line.equals("Si") || line.equals("si")) {
                System.out.println("¿Como quieres iniciar sesion?: <Demandante> <Ofertante> <Gerente>");
                line = br.readLine();
                String split1[];
                if (line.equals("Gerente") || line.equals("gerente")) {
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Introduce tu NIF y tu contraseña: ");
                        line = br.readLine();
                        split1 = line.split("\\s+");
                        if (muzska.login(split1[0], split1[1])) {
                            System.out.println("Inicio de sesion exitoso");
                            break;
                        } else {
                            System.out.println("NIF y/o contraseñas incorrectos");
                        }
                        if (i == 2) {
                            System.out.println("Intentos agotados");
                            guardarDatos(muzska);
                            return;
                        }
                    }
                } else if (line.equals("Demandante") || line.equals("demandante")) {
                    Demandante clReg = null;
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Introduce tu NIF y tu contraseña: ");
                        line = br.readLine();
                        split1 = line.split("\\s+");
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
                                System.out.println("NIF y/o contraseñas incorrectos");
                            }
                        } else {
                            System.out.println("No esta registrado nadie con ese NIF");
                        }
                        if (i == 2) {
                            System.out.println("Intentos agotados");
                            if (clReg != null) {
                                clReg.bloquear();
                                System.out.println("Este NIF ha sido bloqueado");
                            }
                            guardarDatos(muzska);
                            return;
                        }
                        cliente = null;
                        clReg = null;
                    }
                } else if (line.equals("Ofertante") || line.equals("ofertante")) {
                    Ofertante clReg = null;
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Introduce tu NIF y tu contraseña: ");
                        line = br.readLine();
                        split1 = line.split("\\s+");
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
                                System.out.println("NIF y/o contraseñas incorrectos");
                            }
                        } else {
                            System.out.println("No esta registrado nadie con ese NIF");
                        }
                        if (i == 2) {
                            System.out.println("Intentos agotados");
                            guardarDatos(muzska);
                            return;
                        }
                        cliente = null;
                        clReg = null;
                    }
                } else {
                    System.out.println("Error, no existe el tipo indicado\nFin de programa");
                    guardarDatos(muzska);
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

///////////////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String line;
            do {
                System.out.println("Que quieres hacer: ");
                line = br.readLine();
            } while (!line.equals("salir") && !line.equals("exit"));
        }catch (IOException ioe) {
            ioe.printStackTrace();
            guardarDatos(muzska);
            return;
        }
        if(cliente!=null) {
            System.out.println("Cerrando sesion...");
            muzska.logout(cliente);
        }
        guardarDatos(muzska);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
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
            }catch (NullPointerException npe) {
                System.out.println(npe.getMessage());
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage() + ", Usuario no añadido");
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
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
