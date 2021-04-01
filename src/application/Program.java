package application;

import java.util.List;

import model.dao.DaoFabrica;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
	
		VendedorDao vendedorDao = DaoFabrica.criarVendedorDao();
		
		System.out.println("=== teste ===");
		
		Vendedor vendedor = vendedorDao.findById(3);
		
		System.out.println(vendedor);
		
		
		System.out.println("=== teste 2 ===");
		Departamento departamento = new Departamento(2, null);
		List<Vendedor> list = vendedorDao.findByDepartamento(departamento);
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
		
		
		System.out.println("=== teste 3 ===");
		list = vendedorDao.findAll();
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
		
	}

}
