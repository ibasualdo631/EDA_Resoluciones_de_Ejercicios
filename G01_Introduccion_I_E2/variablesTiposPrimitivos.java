public class variablesTiposPrimitivos {
    public static void main(String[] args) {
        // byte: entero de 8 bits, útil para valores pequeños.
        byte edadByte = 127;

        // short: entero de 16 bits, mayor rango que byte.
        short edadShort = 32000;

        // int: entero estándar, usado para valores comunes.
        int edadInt = 100000;

        // long: entero largo, útil para valores grandes.
        long poblacion = 9000000000L;

        // float: decimal de precisión simple, requiere 'f' al final.
        float temperatura = 23.5f;

        // double: decimal de precisión doble, más preciso que float.
        double pi = 3.141592653589793;

        // boolean: solo puede tomar true o false.
        boolean activo = true;

        // char: almacena un único carácter y se escribe entre comillas simples.
        char inicial = 'A';

        // Impresión de los valores por pantalla.
        System.out.println("byte: " + edadByte);
        System.out.println("short: " + edadShort);
        System.out.println("int: " + edadInt);
        System.out.println("long: " + poblacion);
        System.out.println("float: " + temperatura);
        System.out.println("double: " + pi);
        System.out.println("boolean: " + activo);
        System.out.println("char: " + inicial);
    }
}
