package com.christiangrossi.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.christiangrossi.cursomc.domain.Produto;
import com.christiangrossi.cursomc.dto.CategoriaDTO;
import com.christiangrossi.cursomc.dto.ProdutoDTO;
import com.christiangrossi.cursomc.resources.utils.URL;
import com.christiangrossi.cursomc.services.ProdutoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) throws ObjectNotFoundException {
		return ResponseEntity.ok(service.find(id));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "nome", defaultValue = "24") String nome,
			@RequestParam(value = "categorias", defaultValue = "24") String categorias,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(URL.decodeParam(nome), ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(elem -> new ProdutoDTO(elem));
		return ResponseEntity.ok().body(listDTO);
	}

}
