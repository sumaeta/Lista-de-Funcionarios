package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select vendedor. *, departamento.Nome as depName "
					+ "from vendedor inner join departamento " 
					+ "on vendedor.DepartamentoId = departamento.Id "
					+ "where vendedor.Id = ?"
					);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Departamento dep = instantiateDepartamento(rs);
				
				Vendedor obj = instantiateVendedor(rs, dep);
				return obj;
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatemant(st);
			DB.closeResultSet(rs);
		}
		
	}

	private Vendedor instantiateVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor obj = new Vendedor();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Nome"));
		obj.setEmail(rs.getString("Email"));
		obj.setSalarioBase(rs.getDouble("SalarioBase"));
		obj.setDataNascimento(rs.getDate("DataNascimento"));
		obj.setDepartamento(dep);
		return obj;
	}

	private Departamento instantiateDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartamentoId"));
		dep.setNome(rs.getString("depName"));
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select vendedor. *, departamento.Nome as depName " +
					"from vendedor inner join departamento " + 
					"on vendedor.DepartamentoId = departamento.Id " +
					"order by Nome ");
			
			rs = st.executeQuery();
			List<Vendedor> list = new ArrayList<>();
			
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("DepartamentoId"));
				
				if(dep == null) {
				dep = instantiateDepartamento(rs);
				map.put(rs.getInt("DepartamentoId"), dep);
				}
				Vendedor obj = instantiateVendedor(rs, dep);
				list.add(obj);
			}
			return list;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatemant(st);
			DB.closeResultSet(rs);
		}
	}


	@Override
	public List<Vendedor> findByDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select vendedor. *, departamento.Nome as depName " +
					"from vendedor inner join departamento " + 
					"on vendedor.DepartamentoId = departamento.Id " +
					"where DepartamentoId = ? " +
					"order by Nome ");
			
			st.setInt(1, departamento.getId());
			rs = st.executeQuery();
			List<Vendedor> list = new ArrayList<>();
			
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("DepartamentoId"));
				
				if(dep == null) {
				dep = instantiateDepartamento(rs);
				map.put(rs.getInt("DepartamentoId"), dep);
				}
				Vendedor obj = instantiateVendedor(rs, dep);
				list.add(obj);
			}
			return list;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatemant(st);
			DB.closeResultSet(rs);
		}
	}

}
