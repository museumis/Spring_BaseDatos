package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mysql.jdbc.ResultSetMetaData;

import model.Empleados;

public class EmpleadoMapper implements RowMapper<Empleados>{
	public Empleados mapRow(ResultSet rs, int arg1) throws SQLException {
		 Empleados empleado = new  Empleados();
		empleado.setEmp_no(rs.getInt("emp_no"));
		empleado.setApellido(rs.getString("apellido"));
		empleado.setOficio(rs.getString("oficio"));
		empleado.setDir(rs.getInt("dir"));
		empleado.setFecha_alta(rs.getDate("fecha_alta").toLocalDate());
		empleado.setSalario(rs.getFloat("salario"));
		empleado.setComision(rs.getFloat("comision"));
		//rs.getDate("fecha_alta") -> pero empleado .setfecha espera localdate -> hay que poner: rs.getDate("fecha_alta").toLocalDate();
		if (ExisteColumna(rs, "dept_no")) {
			// si hemos recuperado en la consulta el campo dept_no lo mapeamos, sino será null
			empleado.setDept_no(rs.getInt("dept_no"));
		}
		return empleado;
	}

	public static boolean ExisteColumna(ResultSet rs, String columnName) throws	SQLException {
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		int columns = rsmd.getColumnCount();
		for (int x = 1; x <= columns; x++) {
			if (columnName.equalsIgnoreCase(rsmd.getColumnName(x))) {
				return true;
			}
		}
		return false;
	}
}
