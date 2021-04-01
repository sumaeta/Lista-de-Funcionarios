package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFabrica;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
	
		VendedorDao vendedorDao = DaoFabrica.criarVendedorDao();
		
		System.out.println("=== teste Filtrando por id ===");
		
		Vendedor vendedor = vendedorDao.findById(3);
		
		System.out.println(vendedor);
		
		
		System.out.println("=== teste 2 Filtrando por Departamento===");
		Departamento departamento = new Departamento(2, null);
		List<Vendedor> list = vendedorDao.findByDepartamento(departamento);
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
		
		
		System.out.println("=== teste 3 retirando o filtro===");
		list = vendedorDao.findAll();
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("=== teste 3 inserindo vendedor ===");
		Vendedor newVendedor = new Vendedor(null, "Greg", "Greg@gmail.com", new Date(), 4.000, departamento);
		vendedorDao.insert(newVendedor);
		System.out.println("Inserindo novo id: " + newVendedor.getId());
		
	}

}
