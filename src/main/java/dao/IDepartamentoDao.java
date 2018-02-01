package dao;

import java.util.List;

import model.Departamento;

public interface IDepartamentoDao {
	public void insertDepartamento(Departamento departamento);
	public void updateDepartamento(Departamento departamento);
	public void deleteDepartamento(Departamento departamento);
	public Departamento findById(int cod);
	public List<Departamento> getAll();
}
