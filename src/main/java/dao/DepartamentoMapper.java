package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Departamento;
import model.Empleados;

public class DepartamentoMapper  implements RowMapper<Departamento>{
	// Metodo para mapear el resultado de la consulta sql a un objeto
	public Departamento mapRow(ResultSet rs, int arg1) throws SQLException {
		Departamento departamento = new Departamento();
		departamento.setDept_no(rs.getInt("dept_no"));
		departamento.setDnombre(rs.getString("dnombre"));
		departamento.setLoc(rs.getString("loc"));
		return departamento;
	}
}
