package Servicios;

import Modelo.Departamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartamentoServicios {
    private static List<Departamento> departamentos;
    private static final Scanner scanner = new Scanner(System.in);

    public DepartamentoServicios() {
        departamentos = new ArrayList<>();
    }

    public static void registrarDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    public static void actualizarDepartamento(Departamento departamentoActualizado) {
        // Buscar el departamento existente por nombre y actualizarlo
        for (int i = 0; i < departamentos.size(); i++) {
            if (Integer.parseInt(new String(String.valueOf(departamentos.get(i).getCodigo()))) == departamentoActualizado.getCodigo()) {
                departamentos.set(i, departamentoActualizado);
                break;
            }
        }
    }

    public static void eliminarDepartamento(int codigo) {
        departamentos.removeIf(dep -> Integer.parseInt(new String(String.valueOf(dep.getCodigo()))) == codigo);
    }

    public Departamento buscarDepartamentoPorCodigo(int codigo) {
        for (Departamento dep : departamentos) {
            if (Integer.parseInt(new String(String.valueOf(dep.getCodigo()))) == codigo) {
                return dep;
            }
        }
        return null;
    }

    public List<Departamento> getDepartamentos() {
        return new ArrayList<>(departamentos);
    }

    public void inicializarDatos(){
        Departamento rrhh = new Departamento(1, "Recursos Humanos", "Administrativo", "Piso 2", "123-456-7890");
        departamentos.add(rrhh);
    }
}