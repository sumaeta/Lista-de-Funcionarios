package model.dao;

import java.util.List;

import model.entities.Departamento;
import model.entities.Vendedor;

public interface VendedorDao {

		void insert(Vendedor obj);
		void update(Vendedor obj);
		void deleteById(Vendedor obj);
		Vendedor findById(Integer id);
		List<Vendedor> findAll();
		List<Vendedor> findByDepartamento(Departamento departamento);
		void deleteById(Integer id);
		
		
		
}
