package dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Departamento;

@Repository("JdbcDepartamentoDao")
public class JdbcDepartamentoDao implements IDepartamentoDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void insertDepartamento(Departamento departamento) {
		this.jdbcTemplate.update("INSERT INTO DEPARTAMENTOS(DEPT_NO,DNOMBRE,LOC)VALUES(?,?,?)",
				new Object[] { departamento.getDept_no(), departamento.getDnombre(), departamento.getLoc() });

	}

	@Override
	public void updateDepartamento(Departamento departamento) {
		this.jdbcTemplate.update("UPDATE DEPARTAMENTOS SET DNOMBRE=?,LOC=? WHERE DEPT_NO=?",
				new Object[] { departamento.getDnombre(), departamento.getLoc(), departamento.getDept_no() });
	}

	@Override
	public void deleteDepartamento(Departamento departamento) {
		this.jdbcTemplate.update("DELETE FROM DEPARTAMENTOS WHERE DEPT_NO=?", departamento.getDept_no());
	}

	@Override
	public Departamento findById(int dept_no) {
		String sql = "SELECT * FROM DEPARTAMENTOS WHERE DEPT_NO=?";
		return this.jdbcTemplate.queryForObject(sql, new DepartamentoMapper(), dept_no);

	}

	@Override
	public List<Departamento> getAll() {
		String sql = "SELECT * FROM DEPARTAMENTOS";
		return this.jdbcTemplate.query(sql,new DepartamentoMapper());
	}

}
