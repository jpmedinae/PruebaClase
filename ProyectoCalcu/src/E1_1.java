/**
 * Método que checa si la operación está bien escrita
 * @author juanpa
 */
public class E1_1 {
    
    
    
    /** Condiciones de la cadena
     * 1) La cadena no puede empezar con un operador que no sea '+' o '-'
     * 2) La cadena no puede terminar en un operador
     * 3) Caracteres admitidos: +, -, *, /, ^, (), '.', números del 1-9
     * 4) Separaciones entre caracteres
     *    No pueden haber dos (o más) operadores juntos
     *       Excepción: '-' y '+' pueden estar después de cualquier operador pero no pueden repetirse
     *    No pueden haber dos (o más) puntos juntos
     * 5) No puede haber un cero (que no tenga un número a la izquierda) a la izquierda de un número
     * 6) El punto debe tener un número como vecino (a la izquierda y/o derecha)
     * 7) No pueden haber dos puntos separados por números
     * 8) Los paréntesis deben estar balanceados
     * 9) Los paréntesis deben contener por lo menos algo
     * 10) El contenido de los paréntesis no puede iniciar ni finalizar con un operador que no sea '+' o '-'
     */
    
    
    
    /**
     * Condiciones operacionales
     * 11) No puede haber divisiones en las que el denominador sea 0
     * 12) No puede haber potencias fraccionarias de números negativos en donde el numerador sea impar y, al mismo tiempo, el denominador sea par
     * 13) No puede haber un valor equivalente a 0 elevado a una potencia negativa
     */
    
    
    
    // Función que verifica que todas las condiciones se cumplan en la cadena
    
    /**
     * 
     * @param cadena
     * @return 
     */
    public static boolean cumple(String cadena){
        boolean res = false;
        if (verificaInicio(cadena) && verificaFinal(cadena) && verificaCaracteresAdmitidos(cadena) && verificaSeparaciones(cadena) && verificaCeros(cadena) && verificaParentesisBalanceados(cadena) && verificaParentesisContenido(cadena)){
            res = true;
        }
        return res;
    }
    
    
    
    // Funciones que indican si un caracter en específico cumple cierta condición (utilizados en el código)
    
    private static boolean esParentesis(char caracter){
        boolean res = false;
        if (caracter == '(' || caracter == ')')
            res = true;
        return res;     
    }
    
    private static boolean esOperador(char caracter){
        boolean res = false;
        if (caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/' || caracter == '^')
            res = true;
        return res;     
    }
    
    private static boolean esPunto(char caracter){
        boolean res = false;
        if (caracter == '.')
            res = true;
        return res;     
    }
    
    private static boolean esNumero(char caracter){
        boolean res = false;
        if (caracter == '0' || caracter == '1' || caracter == '2' || caracter == '3' || caracter == '4' || caracter == '5' || caracter == '6' || caracter == '7' || caracter == '8' || caracter == '9')
            res = true;
        return res;     
    }
    
    private static boolean esMasMenos(char caracter){
        boolean res = false;
        if (caracter == '-' || caracter == '+')
            res = true;
        return res;
    }
    
    private static boolean esCero(char caracter){
        boolean res = false;
        if (caracter == '0')
            res = true;
        return res;
    }
    
    
    
    // Funciones que verifican si se cumple cierta condición en la cadena
    
    // 1)
    private static boolean verificaInicio(String cadena){
        boolean res = true;
        char caracter = cadena.charAt(0);
        if ((esOperador(caracter) && !esMasMenos(caracter)))
            res = false;
        return res;
    }
    
    //2)
    private static boolean verificaFinal(String cadena){
        boolean res = true;
        char caracter = cadena.charAt(cadena.length() - 1);
        if (esOperador(caracter))
            res = false;
        return res;
    }

    // 3)
    private static boolean verificaCaracteresAdmitidos(String cadena){
        boolean res = true;
        char caracter;
        int i = 0;
        while (i < cadena.length() && res){
            caracter = cadena.charAt(i);
            if (!esParentesis(caracter) && !esOperador(caracter) && !esPunto(caracter) && !esNumero(caracter))
                res = false;  
            i++;
        }
        return res;
    }
    
    // 4)
    private static boolean verificaSeparaciones(String cadena){
        boolean res = true;
        boolean excepcionUnica = false;
        char caracter1 = cadena.charAt(0);
        char caracter2;
        int i = 1;
        while (i < cadena.length() && res){
            caracter2 = cadena.charAt(i);
            if ((esOperador(caracter1) && esOperador(caracter2)) || (esPunto(caracter1) && esPunto(caracter2))){
                if (!excepcionUnica && esMasMenos(caracter2) && caracter1 != caracter2)
                    excepcionUnica = true;
                else
                    res = false;
            }
            else
                excepcionUnica = false;
            caracter1 = caracter2;
            i++;
        }
        return res;
    }
    
    // 5)
    private static boolean verificaCeros(String cadena){
        boolean res = true;
        boolean condicion1 = false;
        char caracter1 = cadena.charAt(0);
        char caracter2;
        int i = 1;
        while (i < cadena.length() && res){
            caracter2 = cadena.charAt(i);
            if (i == 1 && esCero(caracter1) && esNumero(caracter2))
                res = false;
            else{
                if (condicion1 && esNumero(caracter2))
                    res = false;
                else {
                    if (esOperador(caracter1) && esCero(caracter2))
                      condicion1 = true;
                    else
                      condicion1 = false;
                }
            }
            caracter1 = caracter2;
            i++;
        }
        return res;
    }
    
    // 6)
    
    
    // 7)
    
    
    // 8)
    private static boolean verificaParentesisBalanceados(String cadena){
        boolean res = false;
        boolean huboExcepcion = false;
        char caracter;
        PilaA <Character> pila = new PilaA<>(cadena.length());
        int i = 0;
        while (i < cadena.length() && !huboExcepcion){
            caracter = cadena.charAt(i);
            if (caracter == '(')
                pila.push(cadena.charAt(i));
            else{
                if (caracter == ')'){
                    try{
                        pila.pop();
                    }
                    catch(Exception error){
                        huboExcepcion = true;
                    }
                }
            }
            i++;   
        }
        if (pila.isEmpty() && !huboExcepcion)
            res = true;
        return res;
    }
    
    // 9)
    private static boolean verificaParentesisContenido(String cadena){
        boolean res = true;
        char caracter1 = cadena.charAt(0);
        char caracter2;
        int i = 1;
        while (i < cadena.length() && res){
            caracter2 = cadena.charAt(i);
            if (caracter1 == '(' && caracter2 == ')')
                res = false;
            caracter1 = caracter2;
            i++;
        }
        return res;
    }
    
    // 10)

}