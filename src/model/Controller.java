package model;

import structures.TablaHashGenerica;
import structures.PilaGenerica;
import structures.ColaGenerica;

public class Controller {

    private TablaHashGenerica<Medico> tablaMedicos;
    private TablaHashGenerica<Paciente> tablaPacientes;
    private PilaGenerica<String> pilaAcciones;
    private ColaGenerica<Paciente> colaEspera;

    public Controller(int capacidad) {
        this.tablaMedicos = new TablaHashGenerica<>(capacidad);
        this.tablaPacientes = new TablaHashGenerica<>(capacidad);
        this.pilaAcciones = new PilaGenerica<>(100);
        this.colaEspera = new ColaGenerica<>(100);
    }

    public void registrarMedico(int idMedico, String nombre, String especialidad, String telefono, String email, String consultorio) throws Exception {
        Medico medico = new Medico(idMedico, nombre, especialidad, telefono, email, consultorio);
        tablaMedicos.insert(idMedico, medico);
        pilaAcciones.Push("Registro exitoso!! de medico con ID: " + idMedico);
    }

    public void registrarPaciente(int idPaciente, String nombre, int edad, String telefono, String direccion, String correoElectronico) throws Exception {
        Paciente paciente = new Paciente(idPaciente, nombre, edad, telefono, direccion, correoElectronico);
        tablaPacientes.insert(idPaciente, paciente);
        pilaAcciones.Push("Registro  exitoso !! de paciente con ID: " + idPaciente);
    }

    public Medico buscarMedico(int idMedico) throws Exception {
        return tablaMedicos.searchValueByKey(idMedico);
    }

    public Paciente buscarPaciente(int idPaciente) throws Exception {
        return tablaPacientes.searchValueByKey(idPaciente);
    }

    public void registrarAtencion(int idPaciente, int idMedico) throws Exception {
        Paciente p = buscarPaciente(idPaciente);
        Medico m = buscarMedico(idMedico);
        if (p == null || m == null) {
            throw new Exception("Paciente o medico no se pudo encontrar");
        }
        String accion = "Atencion paciente ,  tiene ID: " + idPaciente + " por medico con ID: " + idMedico;
        pilaAcciones.Push(accion);
    }

    public String deshacerAccion() throws Exception {
        if (pilaAcciones.getTop() == 0) {
            throw new Exception("NO hay acciones para deshacer...");
        }
        return pilaAcciones.Pop();
    }

    public void agregarPacienteEspera(Paciente paciente) throws Exception {
        colaEspera.enqueue(paciente);
        pilaAcciones.Push("Paciente en espera , tiene ID: " + paciente.getIdPaciente());
    }

    public Paciente atenderPacienteEspera() throws Exception {
        if (colaEspera.getSize() == 0) {
            throw new Exception("No hay pacientes en espera.");
        }
        Paciente atendido = colaEspera.dequeue();
        pilaAcciones.Push("Paciente atendido fila, tiene espera ID: " + atendido.getIdPaciente());
        return atendido;
    }

}
