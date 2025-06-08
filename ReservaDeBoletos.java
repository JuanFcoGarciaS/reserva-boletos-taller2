
import java.util.Scanner;

public class ReservaDeBoletos {
    public static void main(String[] args) throws Exception {
        int horaActual, pesoEquipaje, pesoEquipajePermitido = 40, /*Kg*/ cantidadEquipajePermitida = 2, asientosDisponibles = 5, edad, compraDeBoletos, costoDeBoleto, cantidadDeMaletas, totalAPagar=0;
        double multa;
        String nombre = "", correo="" , genero ="" , rutaDeVuelo, respuestaMulta, cancelaVuelo;
        boolean entradaValida = false, reiniciarAplicacion = false;

        Scanner leer = new Scanner(System.in);

        while (asientosDisponibles > 0) {

            //Reseteo los booleanos
            entradaValida = false;
            reiniciarAplicacion = false;
            
            System.out.println("Bienvenido a su Sistema de Reserva @JuanFcoGarciaS-Airlines 2025");
            System.out.println("Número de asientos disponibles: "+asientosDisponibles);

            
            
            System.out.println("Porfavor, ingrese su nombre completo");
            nombre = leer.nextLine();
            System.out.println("Ingrese su edad");
            edad = leer.nextInt();
            if(edad >= 18){
                System.out.println("Cumple la edad requerida");

            } else {
                System.out.println("Lo sentimos no cuenta con la edad minima para reservar, saliendo de la aplicación....");
                break;
            }

            System.out.println("Ingrese su correo");
            correo = leer.next();
            System.out.println("Ingrese su genero");
            genero = leer.next();

            System.out.println("Bienvenido/a: " +nombre);

            System.out.println("Digite la cantidad de asientos a comprar");
            compraDeBoletos = leer.nextInt();

            if(compraDeBoletos > asientosDisponibles ){
                System.out.println("A superado el número maximo, reiniciando la aplicación....");
                compraDeBoletos = 0;
                leer.nextLine();
                continue;
            }

            asientosDisponibles = asientosDisponibles - compraDeBoletos;

            System.out.println("Seleccione su vuelo (Nacional o Internacional), si ingresa otro valor se considerara como Nacional");
            rutaDeVuelo = leer.next();
            

            if("Internacional".equals(rutaDeVuelo)) {
                costoDeBoleto = 120;
                System.out.println("Vuelo seleccionado: Internacional");
            } else {
                costoDeBoleto = 70;
                System.out.println("Vuelo seleccionado: Nacional");
            }

            System.out.println("Ingrese la cantidad de equipaje a llevar: (Cantidad Máx: 2)");
            cantidadDeMaletas = leer.nextInt();

            if(cantidadDeMaletas > cantidadEquipajePermitida) {
                System.out.println("Problema: Excede el número permitido de equipaje");
                System.out.println("-Desea agregar un costo de $10 a su total (Si o No), si cancela saldrá de la aplicación.");

            while (true) {
            respuestaMulta = leer.next();

            if (respuestaMulta.equalsIgnoreCase("Si")) {
            totalAPagar = totalAPagar + 10;
            break;
            } else if (respuestaMulta.equalsIgnoreCase("No")) {
            System.out.println("Saliendo de la aplicación por no aceptar la multa, vuelva a intentar...");
            // Se devuelve disponibilidad a los asientos reservados
            asientosDisponibles = asientosDisponibles + compraDeBoletos;
            totalAPagar = 0;
            nombre = "";
            // Variable para reiniciar la aplicación
            reiniciarAplicacion = true;
            // Limpio el buffer de leer para que no quede un salto de linea residual
            leer.nextLine();
            break;
            
            } else {
            System.out.println("Error: Input incorrecto. Por favor, responda 'Si' o 'No'.");
            }
            }
            
            if(reiniciarAplicacion){
                //Se reinicia la aplicación
                continue;
            }


            } else {
                System.out.println("Cantidad de equipaje permitido");
            }

            System.out.println("Ingrese el peso de su Equipaje: (Peso Máx: 40kg)");
            pesoEquipaje = leer.nextInt();

            if(pesoEquipaje > pesoEquipajePermitido ){
                System.out.println("Multa automática debido al exceso de peso: + $20 al total");
                totalAPagar = totalAPagar + 20;
            } else {
                System.out.println("Peso aprobado: "+pesoEquipaje+" kg");
            }
            totalAPagar = totalAPagar + costoDeBoleto * compraDeBoletos;

            System.out.println("Reserva realizada con éxito, Sr/a "+nombre);
            System.out.println("Gracias por usar nuestros servicios.\n Total a pagar: $"+totalAPagar);

            System.out.println("Desea cancelar su reserva (Si o No), se aplicara una multa en la devolución de su transacción si excede las 13:00pm");
            
            //Aqui anide un switch adentro de un while, otra forma de comprobar los si y no.

            while(!entradaValida){
                cancelaVuelo = leer.next();
                switch (cancelaVuelo) {
                case "Sí":
                case "sí":
                case "SI":
                case "si":
                case "Si":
                
                System.out.println("Ingrese la hora actual (0 - 23)");
                horaActual = leer.nextInt();
                if(horaActual<= 13){
                    System.out.println("Se procederá con la cancelación sin multa");
                    System.out.println("Devolución sin multa aplicada: $" +totalAPagar);
                    // Se devuelve disponibilidad a los asientos reservados
                    asientosDisponibles = asientosDisponibles + compraDeBoletos;
                    totalAPagar = 0;
                    // Limpio el buffer de leer para que no quede un salto de linea residual
                    leer.nextLine();
                } else {
                    multa = totalAPagar * 0.2;
                    System.out.println("Cancelación fuera de horario permitido. Multa del 20%: $" + multa);
                    System.out.println("Devolución con multa aplicada: $" + (totalAPagar - multa));
                    // Se devuelve disponibilidad a los asientos reservados
                    asientosDisponibles = asientosDisponibles + compraDeBoletos;
                    totalAPagar = 0;
                    // Limpio el buffer de leer para que no quede un salto de linea residual
                    leer.nextLine();
                }
                entradaValida = true;
                break;

                case "No":
                case "no":
                case "NO":
                
                System.out.println("No haz cancelado la reserva, los asientos han sido reservados");
                if(asientosDisponibles != 0){
                    System.out.println("Asientos disponibles restantes: " +asientosDisponibles+"\n Se reiniciara la aplicación..");
                }
                leer.nextLine();
                entradaValida= true;
                break;

                default:
                System.out.println("Error: Input incorrecto. Por favor, responda 'Sí' o 'No'.");
                // como entradaValida sigue en falso se repite el bucle.
                }
            }
            totalAPagar = 0;
        }
        if(asientosDisponibles == 0){
            System.out.println("Haz salido de la aplicación, ya no quedan asientos disponibles");
            System.out.println("Consulte en otra fecha, disculpe. ");
        }
        
        System.out.println("Gracias por usar nuestros servicios Copyright @JuanFcoGarciaS-Airlines 2025 - Uteg Fundamentos de programación");
    }
}
