package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entidad.Empleado;
import util.MySqlDBConexion;

public class EmpleadoModel {
	
	private static Logger log = Logger.getLogger(EmpleadoModel.class.getName());
	//---------------------------------------------------------------------------------------------------------
	public int insertarAlumno (Empleado al) {
		
		log.info(">> Inicio >> InsertarEmpleado()");
		int salida = -1;
		
		Connection cn = null;
		PreparedStatement prep = null;
		
		try {
			cn = MySqlDBConexion.getConexion();
			
			String sql = "insert into empleado values(null,?,?,?,?,?,?,curtime())";
			prep = cn.prepareStatement(sql);
			prep.setString(1, al.getNombre());
			prep.setString(2, al.getApellido());
			prep.setString(3, al.getDni());
			prep.setString(4, al.getPais());
			prep.setString(5, al.getCorreo());
			prep.setDate(6, al.getFechanac());
			
			log.info(">> SQL >>" + prep);
			
			salida = prep.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (cn != null) cn.close();
				if (prep != null) prep.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		log.info(">> Fin >> InsertarEmpleado()");
		return salida;
	}
	//---------------------------------------------------------------------------------------------------------
	public int eliminateAlumno (int idAlumno) {
		
		log.info(">> Inicio >> eliminateEmpleado()");
		int salida = -1;
		
		Connection cn = null;
		PreparedStatement prep = null;
		
		try {
			cn = MySqlDBConexion.getConexion();
			
			String sql = "delete from empleado where idEmpleado = ?";
			prep = cn.prepareStatement(sql);
			prep.setInt(1, idAlumno);
			
			log.info(">> SQL >>" + prep);
			
			salida = prep.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (cn != null) cn.close();
				if (prep != null) prep.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		log.info(">> Fin >> eliminateEmpleado()");
		return salida;
	}
	
	//---------------------------------------------------------------------------------------------------------
	public int ModifyAlumno (Empleado al) {
		
		log.info(">> Inicio >> ModificaEmpleado()");
		int salida = -1;
		
		Connection cn = null;
		PreparedStatement prep = null;
		
		try {
			cn = MySqlDBConexion.getConexion();
			
			String sql = "update empleado set nombres=?, apellidos=?, dni=?, pais=?, correo=?, fechaNacimiento=? where idEmpleado=?";
			prep = cn.prepareStatement(sql);
			prep.setString(1, al.getNombre());
			prep.setString(2, al.getApellido());
			prep.setString(3, al.getDni());
			prep.setString(4, al.getPais());
			prep.setString(5, al.getCorreo());
			prep.setDate(6, al.getFechanac());
			prep.setInt(7, al.getIdAlumno());
			
			log.info(">> SQL >>" + prep);
			
			salida = prep.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (cn != null) cn.close();
				if (prep != null) prep.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		log.info(">> Fin >> ModificaEmpleado()");
		return salida;
	}
	//---------------------------------------------------------------------------------------------------------
	public List <Empleado> listaAlumno(){
		ArrayList <Empleado> salida = new ArrayList <Empleado>();
		
		Connection cn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlDBConexion.getConexion();
			
			String sql = "select * from empleado";
			prep = cn.prepareStatement(sql);
			
			log.info(">> SQL >>" + prep);
			
			rs = prep.executeQuery();
			Empleado al = null;
			while(rs.next()) {
			al= new Empleado();
			al.setIdAlumno(rs.getInt(1));
			al.setNombre(rs.getString(2));
			al.setApellido(rs.getString(3));
			al.setDni(rs.getString(4));
			al.setPais(rs.getString(5));
			al.setCorreo(rs.getString(6));
			al.setFechanac(rs.getDate(7));
			al.setFechareg(rs.getDate(8));
			salida.add(al);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
	} finally {
		
		try {
			if (cn != null) cn.close();
			if (prep != null) prep.close();
			if (rs != null) rs.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
		return salida;
	}
	//---------------------------------------------------------------------------------------------------------
	public List<Empleado> listaEmpleadoNombreLike(String filtro){
		ArrayList<Empleado> salida =new ArrayList<Empleado>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//recepciona data
		ResultSet rs = null;
		
		try {
			//1 se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el sql 
			String sql = "SELECT * FROM empleado where nombres like ?; ";
		 pstm = conn.prepareStatement(sql);
		 pstm.setString(1, "%"+filtro +"%");
		 
		 //siem mandar log
		 log.info(">>> " + pstm);
		 
		 //se ejecuta el sql en la base de datos
		 rs = pstm.executeQuery();
		 Empleado obj = null;
		 
		 while(rs.next()) {
			 obj = new Empleado();
			 obj.setIdAlumno(rs.getInt("idEmpleado"));
			 obj.setNombre(rs.getString("nombres"));
			 obj.setApellido(rs.getString("apellidos"));
			 obj.setDni(rs.getString("dni"));
			 obj.setPais(rs.getString("pais"));
			 obj.setCorreo(rs.getString("correo"));
			 obj.setFechanac(rs.getDate("fechaNacimiento"));
			 obj.setFechareg(rs.getDate("fechaRegistro"));
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
