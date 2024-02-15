import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author juanpa
 */
public class Pruebas {
    
    public static void main(String[] args){
        
        String cadena;
        String res = "si";
        Scanner lectura = new Scanner(System.in);
        
        while (res.equalsIgnoreCase("si")){
            System.out.print("\nTexto: ");
            cadena = lectura.next();
            if (E1_1.cumple(cadena))
                System.out.println("Cumple");
            else
                System.out.println("No cumple");
            System.out.print("¿Seguir?: ");
            res = lectura.next();
        }
        
        
        
        
        
    }
    
}
