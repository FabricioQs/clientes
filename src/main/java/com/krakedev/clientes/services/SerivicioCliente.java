package com.krakedev.clientes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.clientes.entidades.Cliente;



@Service
public class SerivicioCliente {
	
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	
	
	
	//Metodo para vuscar por cedula al cliente
	public Cliente buscarPorCedula(String cedula) {
		
		for (Cliente c: clientes) {
			if(c.getCedula().equals(cedula)) {
				return c;
			}
		}
		return null;
	}
	
	
	
	
	//Metodo para crear un cliente si existe o no
	public Cliente crear(Cliente cliente){
		Cliente existente = buscarPorCedula(cliente.getCedula());
		
		if(existente != null) {
			return null;
		}else {
			clientes.add(cliente);
			return cliente;
		}		
	}
		
	
	
	
	
	//metodo para en listar a los clientes
	public List<Cliente> listar(){
		return clientes;
	}
	
	
	
	
	//Metodo de actulizar
	
	public Cliente actualizar (String cedula, Cliente clienteActualizado) {
	
	Cliente cliente = buscarPorCedula(cedula);
	
	if(cliente != null) {
		cliente.setNombre(clienteActualizado.getNombre());
		cliente.setApellido(clienteActualizado.getApellido());
		cliente.setEmail(clienteActualizado.getEmail());
		
	}
	
	return cliente;
	
}
	
	
	//Metodo de eliminar
	
	public boolean eliminar (String cedula) {
		
		Cliente cliente = buscarPorCedula(cedula);
		
		if(cliente != null) {
			clientes.remove(cliente);
			return true;			
		}else {
			return false;
		}
	}
	

	

}
