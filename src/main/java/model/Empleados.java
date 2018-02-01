package model;

import java.time.LocalDate;

public class Empleados {

	private int emp_no, dir, dept_no;
	private String apellido, oficio;
	private LocalDate fecha_alta;
	private float salario, comision;

	/*
	 * Información
	 */
	@Override
	public String toString() {
		String cadena = "";
		cadena += "\t[Empleado] " + this.emp_no;
		cadena += " [Apellido] " + this.apellido;
		cadena += " [Oficio] " + this.oficio;
		cadena += " [Dir] " + this.dir;
		cadena += " [Fecha Alta] " + this.fecha_alta;
		cadena += "[Salario] " + this.salario;
		cadena += "[Comisión] " + this.comision;
		cadena += "[Departamento]" + this.dept_no;
		return cadena;
	}

	/**
	 * Constructores
	 */

	public Empleados() {
	}
	public Empleados(int emp_no) {
		this.emp_no = emp_no;
	
	}
	public Empleados(String apellido, String oficio, int dir, LocalDate fecha_alta, float salario, float comision,
			int dept_no) {
		this.dir = dir;
		this.dept_no = dept_no;
		this.apellido = apellido;
		this.oficio = oficio;
		this.fecha_alta = fecha_alta;
		this.salario = salario;
		this.comision = comision;
	}

	public Empleados(int emp_no, String apellido, String oficio, int dir, LocalDate fecha_alta, float salario,
			float comision, int dept_no) {
		this.emp_no = emp_no;
		this.dir = dir;
		this.dept_no = dept_no;
		this.apellido = apellido;
		this.oficio = oficio;
		this.fecha_alta = fecha_alta;
		this.salario = salario;
		this.comision = comision;
	}

	/*
	 * Get and Set
	 */
	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getDept_no() {
		return dept_no;
	}

	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public LocalDate getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(LocalDate fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

}
