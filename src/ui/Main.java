package ui;

import java.util.Scanner;
import model.Controller;
import model.Paciente;
import model.Medico;

public class Main {
    private Controller controller;
    private Scanner scanner;

    public Main() {
        controller = new Controller(100);
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Sistema Clinica ---");
            System.out.println("1. Registrar medico");
            System.out.println("2. Registrar paciente");
            System.out.println("3. Registrar atencion medica");
            System.out.println("4. Deshacer ultima accion");
            System.out.println("5. Agregar paciente a lista de espera");
            System.out.println("6. Atender paciente en espera");
            System.out.println("7. Salir");
            System.out.print("Seleccione opcion: ");

            int opcion = Integer.parseInt(scanner.nextLine());

            try {
                switch (opcion) {
                    case 1:
                        registrarMedico();
                        break;
                    case 2:
                        registrarPaciente();
                        break;
                    case 3:
                        registrarAtencion();
                        break;
                    case 4:
                        deshacerAccion();
                        break;
                    case 5:
                        agregarPacienteEspera();
                        break;
                    case 6:
                        atenderPacienteEspera();
                        break;
                    case 7:
                        System.out.println("Hasta luegooo");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void registrarMedico() throws Exception {
        System.out.print("ID medico: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Especialidad: ");
        String esp = scanner.nextLine();
        System.out.print("Telefono: ");
        String tel = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Consultorio: ");
        String consultorio = scanner.nextLine();

        controller.registrarMedico(id, nombre, esp, tel, email, consultorio);
        System.out.println("Medico registrado.");
    }

    private void registrarPaciente() throws Exception {
        System.out.print("ID paciente: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.print("Telefono: ");
        String tel = scanner.nextLine();
        System.out.print("Direccion: ");
        String dir = scanner.nextLine();
        System.out.print("Correo electronico: ");
        String email = scanner.nextLine();

        controller.registrarPaciente(id, nombre, edad, tel, dir, email);
        System.out.println("Paciente registrado.");
    }

    private void registrarAtencion() throws Exception {
        System.out.print("ID paciente: ");
        int idP = Integer.parseInt(scanner.nextLine());
        System.out.print("ID medico: ");
        int idM = Integer.parseInt(scanner.nextLine());

        controller.registrarAtencion(idP, idM);
        System.out.println("Atencion registrada.");
    }

    private void deshacerAccion() throws Exception {
        String accion = controller.deshacerAccion();
        System.out.println("Accion borrada: " + accion);
    }

    private void agregarPacienteEspera() throws Exception {
        System.out.print("ID paciente: ");
        int id = Integer.parseInt(scanner.nextLine());
        Paciente p = controller.buscarPaciente(id);
        if (p == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }
        controller.agregarPacienteEspera(p);
        System.out.println("Paciente agregado a lista de espera.");
    }

    private void atenderPacienteEspera() throws Exception {
        Paciente p = controller.atenderPacienteEspera();
        System.out.println("Paciente atendido: " + p.getNombre() + " (ID: " + p.getIdPaciente() + ")");
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.iniciar();
    }
}
