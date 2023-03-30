package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Producto;
import util.MySqlDBConexion;

public class ProductoModel {

	private static Logger log = Logger.getLogger(ProductoModel.class.getName());

	// ------------------------------------------------------------------------------------------------------------------
	public int insertaSala(Producto obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();

			String sql = "insert into producto values(null,?,curtime(),?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setInt(2, obj.getStock());
			pstm.setString(3, obj.getTipo());

			log.info("sql --> " + pstm);

			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	// ------------------------------------------------------------------------------------------------------------------
	public int eliminaSala(int idSala) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			// Se crea la conexión
			conn = MySqlDBConexion.getConexion();

			// Se prepara el SQL
			String sql = "delete from producto where idProducto = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idSala);

			log.info(">>> " + pstm);

			// Ejecutamos la BD
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
		return salida;
	}

	// ------------------------------------------------------------------------------------------------------------------
	public int actualizaSala(Producto oSala) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			// Se crea la conexión
			conn = MySqlDBConexion.getConexion();

			// Se prepara el SQL
			String sql = "update producto set nombres=?, stock=?, tipo=? where idProducto=?";
			pstm = conn.prepareCall(sql);
			pstm.setString(1, oSala.getNombre());
			pstm.setInt(2, oSala.getStock());
			pstm.setString(3, oSala.getTipo());
			pstm.setInt(4, oSala.getIdProducto());

			log.info(">>> " + pstm);

			// Ejecutamos a la base de datos
			salida = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
		return salida;
	}

	// ------------------------------------------------------------------------------------------------------------------
	public List<Producto> listaSala() {
		ArrayList<Producto> listado = new ArrayList<Producto>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = MySqlDBConexion.getConexion();

			String sql = "select * from producto";
			pstm = conn.prepareStatement(sql);

			rs = pstm.executeQuery();
			Producto oSala = null;
			while (rs.next()) {
				oSala = new Producto();
				oSala.setIdProducto(rs.getInt(1));
				oSala.setNombre(rs.getString(2));
				oSala.setFechaRegistro(rs.getDate(3));
				oSala.setNumStock(rs.getInt(4));
				oSala.setTipo(rs.getString(5));
				listado.add(oSala);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
		return listado;
	}

	// ------------------------------------------------------------------------------------------------------------------
	public List<Producto> listaProductoNombreLike(String filtro){
		ArrayList<Producto> salida =new ArrayList<Producto>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//recepciona data
		ResultSet rs = null;
		
		try {
			//1 se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el sql 
			String sql = "SELECT * FROM producto where nombres like ?; ";
		 pstm = conn.prepareStatement(sql);
		 pstm.setString(1, "%"+filtro +"%");
		 
		 //siem mandar log
		 log.info(">>> " + pstm);
		 
		 //se ejecuta el sql en la base de datos
		 rs = pstm.executeQuery();
		 Producto obj = null;
		 
		 while(rs.next()) {
			 obj = new Producto();
			 obj.setIdProducto(rs.getInt("idProducto"));
			 obj.setNombre(rs.getString("nombres"));
			 obj.setFechaRegistro(rs.getDate("fechaRegistro"));
			 obj.setNumStock(rs.getInt("stock"));
			 obj.setTipo(rs.getString("tipo"));
			 salida.add(obj);
			 
		 }
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (rs !=null) rs.close();
				if (pstm !=null) pstm.close();
				if (conn !=null) conn.close();
			} catch (Exception e2) {    }	
		}
		return salida;
		}

}