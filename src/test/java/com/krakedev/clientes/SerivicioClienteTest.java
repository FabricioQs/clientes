package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.services.SerivicioCliente;

class SerivicioClienteTest {

	private SerivicioCliente servicio;

	// Se ejecuta antes de cada prueba para inicializar una nueva instancia de SerivicioCliente
	// con el ArrayList vacío, evitando que los datos de un test afecten a otro.
	@BeforeEach
	void setUp() {
		servicio = new SerivicioCliente();
	}

	// Prueba que el método crear() agregue un cliente nuevo cuando su cédula no existe previamente.
	// Verifica que retorne el objeto Cliente creado y que la lista aumente a 1 elemento.
	@Test
	void testCrearClienteExitoso() {
		Cliente cliente = new Cliente("1712345678", "Juan", "Perez");
		Cliente resultado = servicio.crear(cliente);

		assertNotNull(resultado, "Error: El método crear() retornó null para un cliente nuevo.");
		assertEquals("1712345678", resultado.getCedula(), "Error: La cédula del cliente retornado no coincide.");
		assertEquals(1, servicio.listar().size(), "Error: El cliente no se agregó correctamente a la lista.");
	}

	// Prueba que el método crear() rechace un cliente si ya existe otro con la misma cédula.
	// Verifica que retorne null y que no se duplique el registro en el ArrayList.
	@Test
	void testCrearClienteDuplicado() {
		Cliente cliente1 = new Cliente("1712345678", "Juan", "Perez");
		Cliente cliente2 = new Cliente("1712345678", "Maria", "Gomez");

		servicio.crear(cliente1);
		Cliente resultado = servicio.crear(cliente2);

		assertNull(resultado, "Error: El método crear() debería retornar null cuando la cédula ya existe.");
		assertEquals(1, servicio.listar().size(), "Error: Se agregó un cliente duplicado a la lista.");
	}

	// Prueba que el método buscarPorCedula() encuentre y retorne al cliente correcto
	// cuando la cédula buscada está registrada en la lista.
	@Test
	void testBuscarPorCedulaExistente() {
		Cliente cliente = new Cliente("1798765432", "Ana", "Lopez");
		servicio.crear(cliente);

		Cliente encontrado = servicio.buscarPorCedula("1798765432");

		assertNotNull(encontrado, "Error: No se encontró un cliente que sí existe en la lista.");
		assertEquals("Ana", encontrado.getNombre(), "Error: El nombre del cliente encontrado no coincide.");
		assertEquals("Lopez", encontrado.getApellido(), "Error: El apellido del cliente encontrado no coincide.");
	}

	// Prueba que el método buscarPorCedula() retorne null cuando se busca una cédula
	// que no ha sido registrada en la lista.
	@Test
	void testBuscarPorCedulaNoExistente() {
		Cliente encontrado = servicio.buscarPorCedula("0000000000");

		assertNull(encontrado, "Error: El método buscarPorCedula() debería retornar null si el cliente no existe.");
	}

	// Prueba que el método listar() retorne todos los clientes almacenados en memoria.
	// Verifica tanto cuando la lista está vacía como después de agregar varios clientes.
	@Test
	void testListar() {
		assertEquals(0, servicio.listar().size(), "Error: La lista debería iniciar vacía.");

		servicio.crear(new Cliente("1111111111", "Carlos", "Ruiz"));
		servicio.crear(new Cliente("2222222222", "Laura", "Mora"));

		List<Cliente> lista = servicio.listar();

		assertEquals(2, lista.size(), "Error: El tamaño de la lista no coincide con los clientes agregados.");
	}

	// Prueba que el método actualizar() modifique el nombre y apellido de un cliente existente
	// y retorne el objeto Cliente con los datos ya actualizados.
	@Test
	void testActualizarClienteExistente() {
		servicio.crear(new Cliente("1234567890", "Pedro", "Diaz"));

		Cliente datosNuevos = new Cliente("1234567890", "Pedro Pablo", "Diaz Vega");
		Cliente actualizado = servicio.actualizar("1234567890", datosNuevos);

		assertNotNull(actualizado, "Error: El método actualizar() retornó null para un cliente existente.");
		assertEquals("Pedro Pablo", actualizado.getNombre(), "Error: El nombre no se actualizó.");
		assertEquals("Diaz Vega", actualizado.getApellido(), "Error: El apellido no se actualizó.");
	}

	// Prueba que el método actualizar() retorne null si se intenta actualizar
	// una cédula que no existe en la lista.
	@Test
	void testActualizarClienteNoExistente() {
		Cliente datosNuevos = new Cliente("9999999999", "Nombre", "Apellido");
		Cliente resultado = servicio.actualizar("9999999999", datosNuevos);

		assertNull(resultado, "Error: El método actualizar() debería retornar null si la cédula no existe.");
	}

	// Prueba que el método eliminar() remueva al cliente de la lista y retorne true
	// cuando la cédula existe.
	@Test
	void testEliminarClienteExistente() {
		servicio.crear(new Cliente("5555555555", "Sofia", "Castro"));

		boolean eliminado = servicio.eliminar("5555555555");

		assertTrue(eliminado, "Error: El método eliminar() debería retornar true al eliminar un cliente existente.");
		assertNull(servicio.buscarPorCedula("5555555555"), "Error: El cliente sigue apareciendo en la lista tras eliminarlo.");
	}

	// Prueba que el método eliminar() retorne false cuando se intenta eliminar
	// un cliente cuya cédula no existe en la lista.
	@Test
	void testEliminarClienteNoExistente() {
		boolean eliminado = servicio.eliminar("8888888888");

		assertFalse(eliminado, "Error: El método eliminar() debería retornar false si el cliente no existe.");
	}
}