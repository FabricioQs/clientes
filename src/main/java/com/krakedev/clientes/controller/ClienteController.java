package com.krakedev.clientes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.services.SerivicioCliente;

@RestController

@RequestMapping("/clientes")

public class ClienteController {
	
	
	//vamos a poder llamar a los metodos de Servicio cliente
	//podemos usar metodos de crear, listar, actulizar o eliminar
	//para ponerle un pat o una url
	private final SerivicioCliente servicioCliente = new SerivicioCliente();
	

	//vamos a definir la ruta para crear un cliente
	
	@PostMapping
	public Cliente crear(@RequestBody Cliente cliente) {
		return servicioCliente.crear(cliente);
	}
	
	
	//metodo para obtener la lista de clientes 
	
	@GetMapping
	public List<Cliente> listar (){
		return servicioCliente.listar();
	}
	
	//metodo buscar por cedula
	//puede exitir una url www.cliente/1256852.com
	//si queremos recuperar ese dato de la url
	//para hacer eso necesitmaos utiizar una anotacion de 
	//@PathVaribale que extrae el valor de la url
	//y lo pasa como parametro
	
	@GetMapping("/{cedula}") //aqui las llaves indican que ese segmento es la variable
	public Cliente buscar(@PathVariable String cedula) {
		
		return servicioCliente.buscarPorCedula(cedula);
	}
	
	
	//Creamos el metodo puth para actuulizar los recursos que ya tenemos
	// en este caso el objeto cliente
	
	
	@PutMapping("/{cedula}")
	public Cliente actualizar(@PathVariable String cedula, @RequestBody Cliente clienteActualizado ) {
		return servicioCliente.actualizar(cedula, clienteActualizado);
	}
	
	
	//Metodo eliminar
	
	@DeleteMapping("/{cedula}")
	public boolean eliminar(@PathVariable String cedula) {
		
		return servicioCliente.eliminar(cedula);
		
	}
	
}
