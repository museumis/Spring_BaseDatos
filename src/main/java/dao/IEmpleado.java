package dao;

import java.util.List;

import model.Empleados;

public interface IEmpleado {
	public void insertEmpleado(Empleados empleado);
	public void updateEmpleado(Empleados empleado);
	public void deleteEmpleado(Empleados empleado);
	public Empleados findById(int cod);
	public List<Empleados> getAll();
}
