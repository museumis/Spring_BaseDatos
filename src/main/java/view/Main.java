package view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.JdbcDepartamentoDao;
import dao.JdbcEmpleadoDao;
import model.Departamento;
import model.Empleados;

public class Main {

	static ApplicationContext context;
	static JdbcDepartamentoDao jdbcDepartamento;
	static JdbcEmpleadoDao jdbcEmpleado;

	// Opciones de menu
	private static String[] opcionesMenuPrincipal = { "\n - MENU PRINCIPAL - ", " 1. Tratar tabla Departamento",
			" 2. Tratar tabla Empleados", " 3.Salir" };
	private static String[] opcionesTratarDepartamento = { "\n - DEPARTAMENTO - ", " 1. Insertar registro",
			" 2. Modificar registro", " 3. Borrar registro", " 4. Ver todo", " 5. Ver registro por ID",
			" 6. Ir a Menú Principal" };
	private static String[] opcionesTratarEmpleado = { "\n - EMPLEADO - ", " 1. Insertar registro",
			" 2. Modificar registro", " 3. Borrar registro", " 4. Ver todo", " 5. Ver registro por ID",
			" 6. Ver todos los empleados de un departamento",
			" 7. Ver empleados con mas de dos años de antiguedad" ,
			" 8. Ver empleados con mayor sueldo" ,
			" 9. Ir a Menú Principal" };

	/**
	 * Metodo que incia el programa
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Contexto
		context = new ClassPathXmlApplicationContext("contexto-bean.xml");
		// Crear conexion
		jdbcDepartamento = (JdbcDepartamentoDao) context.getBean("DepartamentoJDBCTemplate");
		jdbcEmpleado = (JdbcEmpleadoDao) context.getBean("EmpleadoJDBCTemplate");

		gestionMenuPrincipal();
	}

	/**
	 * Menu para tratar la tabla departamento
	 */
	public static void tratarDepartamento() {

		// Gestion
		switch (generarMenu(opcionesTratarDepartamento)) {
		case 1: {
			Departamento departamento = new Departamento(pedirInt(" - Número de departamento: "),
					pedirTexto(" - Nombre: "), pedirTexto(" - Localidad: "));
			jdbcDepartamento.insertDepartamento(departamento);
			System.out.println("-> Registro insertado.");
			break;
		}
		case 2: {
			Departamento departamento = new Departamento(pedirInt(" - DEPT_NO a modificar: "),
					pedirTexto(" - Nombre: "), pedirTexto(" - Localidad: "));
			jdbcDepartamento.updateDepartamento(departamento);
			System.out.println("-> Registro Actualizado.");

			break;
		}
		case 3: {
			Departamento departamento = new Departamento(pedirInt(" - DEPT_NO a borrar: "));
			jdbcDepartamento.deleteDepartamento(departamento);
			System.out.println("-> Registro Borrado.");

			break;
		}
		case 4: {
			List<Departamento> departamentos = jdbcDepartamento.getAll();
			for (Departamento data : departamentos) {
				System.out.println(data);
			}
			System.out.println("-> Fin.");
			break;
		}
		case 5: {
			Departamento departamento = jdbcDepartamento.findById(pedirInt(" - DEPT_NO a Buscar: "));
			System.out.println("-> " + departamento);

			break;
		}
		case 6: {
			gestionMenuPrincipal();
			break;
		}

		default:
			tratarDepartamento();
			break;
		}
		tratarDepartamento();
	}

	/**
	 * Menu para tratar la tabla Empleado
	 */
	public static void tratarEmpleado() {
		// Gestion
		switch (generarMenu(opcionesTratarEmpleado)) {
		case 1: {
			Empleados empleado = new Empleados(pedirTexto(" - Apellido: "), pedirTexto(" - Oficio: "),
					pedirInt(" - Dir: "),
					LocalDate.of(pedirInt(" - Fecha alta\n · Año: "), pedirInt(" · Mes: "), pedirInt(" · dia: ")),
					pedirFloat(" - Salario: "), pedirFloat(" - Comision: "), pedirInt(" - Número de departamento: "));
			jdbcEmpleado.insertEmpleado(empleado);
			System.out.println("-> Registro insertado.");

			break;
		}
		case 2: {
			Empleados empleado = new Empleados(pedirInt("Número de Empleado a modificar: "),
					pedirTexto(" - Apellido: "), pedirTexto(" - Oficio: "), pedirInt(" - Dir: "),
					LocalDate.of(pedirInt(" - Fecha alta\n · Año: "), pedirInt(" · Mes: "), pedirInt(" · dia: ")),
					pedirFloat(" - Salario: "), pedirFloat(" - Comision: "), pedirInt(" - Número de departamento: "));
			jdbcEmpleado.updateEmpleado(empleado);
			System.out.println("-> Registro Actualizado.");

			break;
		}
		case 3: {
			Empleados empleado = new Empleados(pedirInt(" - Número de Empleado a borrar: "));
			jdbcEmpleado.deleteEmpleado(empleado);
			System.out.println("-> Registro Borrado.");
			break;
		}
		case 4: {
			List<Empleados> empleados = jdbcEmpleado.getAll();
			for (Empleados data : empleados) {
				System.out.println(data);
			}
			System.out.println("-> Fin.");
			break;
		}
		case 5: {
			Empleados empleado = jdbcEmpleado.findById(pedirInt(" - Número de empleado a buscar: "));
			System.out.println("-> " + empleado);
			break;
		}
		case 6: {
			List<Empleados> empleados = jdbcEmpleado.getEmpleadosDelDepartamento(pedirTexto(" - Nombre del departamento: "));
			for (Empleados data : empleados) {
				System.out.println(data);
			}
			System.out.println("-> Fin.");
			break;
		}
		case 7: {
			List<Empleados> empleados = jdbcEmpleado.getEmpleadosConAntiguedad();
			for (Empleados data : empleados) {
				System.out.println(data);
			}
			System.out.println("-> Fin.");
			break;
		}
		case 8: {
			List<Empleados> empleados = jdbcEmpleado.getEmpleadosMayorSalario();
			for (Empleados data : empleados) {
				System.out.println(data);
			}
			System.out.println("-> Fin.");			break;
		}
		case 9: {
			gestionMenuPrincipal();
			break;
		}

		default:
			tratarEmpleado();
			break;
		}
		tratarEmpleado();
	}

	/**
	 * Metodo para gestionar el programa
	 */
	public static void gestionMenuPrincipal() {

		switch (generarMenu(opcionesMenuPrincipal)) {
		case 1: {
			tratarDepartamento();
			break;
		}
		case 2: {
			tratarEmpleado();

			break;
		}
		case 3: {
			System.out.println("¡Hasta la próxima!");
			System.exit(0);
			break;
		}

		default:
			gestionMenuPrincipal();
			break;
		}
		gestionMenuPrincipal();
	}

	/**
	 * Metodo para generar menus
	 * 
	 * @param opciones
	 *            del menu
	 * @return respuesta
	 */
	public static int generarMenu(String[] opciones) {
		Scanner teclado = new Scanner(System.in);
		int respuesta = -1;
		for (int i = 0; i < opciones.length; i++) {
			System.out.println(opciones[i]);

		}
		System.out.print("Opcion: ");
		try {
			respuesta = teclado.nextInt();
		} catch (Exception e) {
			System.out.println("Opción incorrecta.");
			generarMenu(opciones);
		}

		if ((respuesta < 1) || (respuesta > opciones.length)) {
			System.out.println("Opción incorrecta.");
			generarMenu(opciones);
		}
		return respuesta;

	}// Fin de generar Menu

	/**
	 * Metodo para obtener texto por teclado
	 * 
	 * @param pregunta
	 * @return respuesta
	 */
	public static String pedirTexto(String pregunta) {
		Scanner teclado = new Scanner(System.in);
		System.out.print(pregunta);
		return teclado.nextLine();
	}// Fin de pedir texto

	/**
	 * Metodo para obtener enteros por teclado
	 * 
	 * @param pregunta
	 * @return respuesta
	 */
	public static int pedirInt(String pregunta) {
		Scanner teclado = new Scanner(System.in);
		System.out.print(pregunta);
		int numero = -1;
		try {
			numero = teclado.nextInt();
		} catch (Exception e) {
			System.out.println("*** Introduce un número.");
		}
		return numero;
	}// Fin de pedir texto

	/**
	 * Metodo para obtener float por teclado
	 * 
	 * @param pregunta
	 * @return respuesta
	 */
	public static float pedirFloat(String pregunta) {
		Scanner teclado = new Scanner(System.in);
		System.out.print(pregunta);
		float numero = -1;
		try {
			numero = teclado.nextFloat();
		} catch (Exception e) {
			System.out.println("*** Introduce un número.");
		}
		return numero;
	}// Fin de pedir texto

}
