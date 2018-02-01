package dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Empleados;

@Repository("JdbcEmpleadoDao")
public class JdbcEmpleadoDao implements IEmpleado {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insertEmpleado(Empleados empleado) {
		String sql = "INSERT INTO empleados(emp_no,apellido,oficio,dir,fecha_alta,salario,comision,dept_no) VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,
				new Object[] { empleado.getEmp_no(), empleado.getApellido(), empleado.getOficio(), empleado.getDir(),
						empleado.getFecha_alta().toString(), empleado.getSalario(), empleado.getComision(),
						empleado.getDept_no() });
	}

	@Override
	public void updateEmpleado(Empleados empleado) {
		String sql = "UPDATE EMPLEADOS SET APELLIDO=?,OFICIO=?,DIR=?,FECHA_ALTA=?,SALARIO=?,COMISION=?,DEPT_NO=? WHERE EMP_NO=?";
		jdbcTemplate.update(sql,
				new Object[] { empleado.getApellido(), empleado.getOficio(), empleado.getDir(),
						empleado.getFecha_alta().toString(), empleado.getSalario(), empleado.getComision(),
						empleado.getDept_no(), empleado.getEmp_no() });
	}

	@Override
	public void deleteEmpleado(Empleados empleado) {
		String sql = "DELETE FROM EMPLEADOS WHERE EMP_NO=?";
		jdbcTemplate.update(sql, empleado.getEmp_no());
	}

	@Override
	public Empleados findById(int emp_no) {
		String sql = "SELECT * FROM EMPLEADOS WHERE EMP_NO=?";
		return jdbcTemplate.queryForObject(sql, new EmpleadoMapper(), emp_no);
	}

	@Override
	public List<Empleados> getAll() {
		String sql = "SELECT * FROM EMPLEADOS";
		return jdbcTemplate.query(sql, new EmpleadoMapper());
	}

	/**
	 * Devuelve una lista con los empleados de un departamento especificado
	 * 
	 * @param nombreDepartamento
	 *            del que se desean listar los empleados
	 * @return un listado de empleados
	 */
	public List<Empleados> getEmpleadosDelDepartamento(String nombreDepartamento) {
		String sql = "SELECT * FROM empleados INNER JOIN departamentos ON empleados.dept_no = departamentos.dept_no WHERE departamentos.dnombre= ?;";
		return jdbcTemplate.query(sql, new EmpleadoMapper(), nombreDepartamento);
	}

	/**
	 * Funcion para obtener un listado de empleados con mayor salario
	 * 
	 * @return un listado de empleados
	 */
	public List<Empleados> getEmpleadosMayorSalario() {
		String sql = "SELECT * FROM empleados	WHERE salario = (SELECT MAX(salario) FROM empleados); ";
		return jdbcTemplate.query(sql, new EmpleadoMapper());

	}

	/**
	 * Muestra los empleados que llevan mas de dos años en la empresa
	 * 
	 * @return un listado de empleados
	 */
	public List<Empleados> getEmpleadosConAntiguedad() {
		String sql = "SELECT * FROM `empleados` WHERE  fecha_alta <='2016-02-01'";
		return jdbcTemplate.query(sql, new EmpleadoMapper());

	}

}
