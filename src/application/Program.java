package application;

import model.dao.DaoFabrica;
import model.dao.VendedorDao;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
	
		VendedorDao vendedorDao = DaoFabrica.criarVendedorDao();
		
		System.out.println("=== teste === ");
		
		Vendedor vendedor = vendedorDao.findById(3);
		
		System.out.println(vendedor);
		
		
		
		
		
	}

}
