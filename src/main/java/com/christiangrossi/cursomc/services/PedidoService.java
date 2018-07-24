package com.christiangrossi.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.christiangrossi.cursomc.domain.Pedido;
import com.christiangrossi.cursomc.repositories.PedidoRepository;
import com.christiangrossi.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;
	
	public Pedido buscar(Integer id){
		Optional<Pedido> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
		}
}
