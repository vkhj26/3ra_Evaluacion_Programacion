package tercer_examen;

import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Agenda {

    public static void mostrarContactos() {

        try {
        
            String url = "jdbc:mysql://10.230.109.190:3306/agenda?serverTimezone=UTC";
            Connection conexion = DriverManager.getConnection(url, "root", "");

            //Statement de la conexión
            Statement ejecutor1 = conexion.createStatement();

            // Consulta SELECT para obtener la tabla contactos
            String consulta1 = "SELECT * FROM contactos";
            ResultSet rs1 = ejecutor1.executeQuery(consulta1);

            System.out.println("+----------CONTACTOS------------+");
            // Recorremos todo el ResultSet y mostramos sus datos
            while (rs1.next()) {
                int id = rs1.getInt("id");
                String nombre = rs1.getString("nombre");
                String telefono = rs1.getString("telefono");
                System.out.println(id + " " + nombre + " " + telefono);
            }
            System.out.println("---------------------------------");

            ejecutor1.close(); //Cerramos el stament
        } catch (SQLException e) {
            System.out.println("Se ha producido un error durante el acceso a la base de datos");
            e.printStackTrace();
        }
    }

    public static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            // Clase que implementa el Driver
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            // Conexión a la base de datos 'agenda'
            String url = "jdbc:mysql://10.230.109.190:3306/agenda?serverTimezone=UTC";
            Connection conexion = DriverManager.getConnection(url, "root", "");

            //-------------------------------------------MENU-----------------------------------------------------------------
            boolean salir = true; // variable para salir del bucle

            while (salir) {
                System.out.println(" ");   //imprimimos el menu con todas las opciones disponibles
                System.out.println("----------------------------------MENU AGENDA---------------------------------");
                System.out.println("1. Mostrar todos lo contactos");
                System.out.println("2. Añadir contactos");
                System.out.println("3. Editar contacto");
                System.out.println("4. Eliminar contacto");
                System.out.println("5. Salir");

                int opcion = lector.nextInt();

                switch (opcion) {
                    case (1): //------------------MUESTRA TODOS LOS CONTACTOS                        
                        mostrarContactos();                        
                        break;
                    case (2)://------------------AÑADE CONTACTOS
                        // Creamos un Statement scrollable y modificable
                        Statement ejecutor2 = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);

                        // Ejecutamos un SELECT y obtenemos la tabla contactos en un ResultSet
                        String consulta2 = "SELECT * FROM contactos";
                        ResultSet rs2 = ejecutor2.executeQuery(consulta2);

                        // Creamos un nuevo registro y lo insertamos
                        System.out.println("Introduzca el valor para id siguiendo el orden de contactos");
                        mostrarContactos();
                        int idNuevo = lector.nextInt();
                        System.out.println("Introduzca el valor para nombre");
                        String nombreDado = lector.next();
                        System.out.println("Introduzca el valor para apellido");
                        String apellidoDado = lector.next();
                        String nombreCompleto = nombreDado + " " + apellidoDado;

                        System.out.println("Introduzca el valor para telefono");
                        String telefonoDado = lector.next();

                        rs2.moveToInsertRow();
                        rs2.updateInt(1, idNuevo);
                        rs2.updateString(2, nombreCompleto);
                        rs2.updateString(3, telefonoDado);
                        rs2.insertRow();

                        ejecutor2.close(); //Cerramos el stament                        
                        break;
                    case (3)://------------------EDITAR CONTACTOS
                                               
                        // Creamos un Statement scrollable y modificable
                        Statement ejecutor3 = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);

                        // Ejecutamos un SELECT y obtenemos la tabla contactos en un ResultSet
                        String consulta3 = "SELECT * FROM contactos";
                        ResultSet rs3 = ejecutor3.executeQuery(consulta3);

                        // Modificamos y actualizamos la base de datos
                        System.out.println("Introduzca el numero de fila del registro que desea editar");
                        mostrarContactos();
                        int posicion1 = lector.nextInt();

                        System.out.println("Introduzca el valor para nombre");
                        String nombreDado2 = lector.next();
                        System.out.println("Introduzca el valor para apellido");
                        String apellidoDado2 = lector.next();
                        String nombreCompleto2 = nombreDado2 + " " + apellidoDado2;

                        System.out.println("Introduzca el valor para telefono");
                        String telefonoDado2 = lector.next();

                        rs3.absolute(posicion1);
                        rs3.updateString(2, nombreCompleto2);
                        rs3.updateString(3, telefonoDado2);
                        rs3.updateRow();

                        //Cerramos el stament    
                        ejecutor3.close();
                        break;
                    case (4)://------------------ELIMINAR CONTACTOS
                        // Creamos un Statement scrollable y modificable
                        Statement ejecutor4 = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);

                        // Ejecutamos un SELECT y obtenemos la tabla contactos en un ResultSet
                        String consulta4 = "SELECT * FROM contactos";
                        ResultSet rs4 = ejecutor4.executeQuery(consulta4);

                        // Desplazamos el cursor al tercer registro y borramos
                        System.out.println("Introduzca el numero de fila del registro que desea borrar");
                        mostrarContactos();
                        int posicion2 = lector.nextInt();

                        rs4.absolute(posicion2);
                        rs4.deleteRow();

                        //Cerramos el stament 
                        ejecutor4.close();

                        break;
                    case (5)://------------------SALE DEL BUCLE
                        System.out.println("Ha salido de la agenda c:");
                        salir = false;  // cambiamos el valor de salir para finalizar el bucle
                        break;
                }
            }

            // Cerramos  la conexión           
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Se ha producido un error durante el acceso a la base de datos");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se ha producido un error");
            e.printStackTrace();
        }

    }
}
