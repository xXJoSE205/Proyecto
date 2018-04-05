package p3.src;

import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

import java.io.*;

public class Muszka {

    public static void main(String[] args) throws IOException {
        TeleChargeAndPaySystem pasarelaPago = new TeleChargeAndPaySystem();
        Sistema muzska = new Sistema(pasarelaPago);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
        String linea;

        while ((linea = buffer.readLine())!=null) {
            int i=0;
            String split[] = linea.split(";");
            if(i) {
                for (String n : split) {

                }
            }
            i=1;
        }
    }
}
