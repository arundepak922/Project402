package userScriptBeanDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import connection.SQLConnection;

public class User_Script_DAO {

	
	
	
	public boolean addUserRefreshScript(User_Script_Bean usb){
		
		String query=" insert into user_script"
						+" ("
						+ "cobrand_id,"
						+ "error_code,"
						+ "mem_item_id,"
						+ "mem_site_acc_id,"
						+"method_list,"
						+ "refresh_timestamp,"
						+ "refresh_type,"
						+ "site_id,"
						+ "sum_info_id"
						+ ")" 
						+" values(?,?,?,?,?,?,?,?,?)";
		SQLConnection sqlConn=new SQLConnection();
		Connection conn=sqlConn.getConnection();
		PreparedStatement pstmt = null;	
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, usb.getCobrand_id());
			pstmt.setInt(2, usb.getCode());
			pstmt.setString(3, usb.getMem_item_id());
			pstmt.setString(4, usb.getMem_site_acc_id());
			pstmt.setString(5, usb.getMethod_list());
			pstmt.setTimestamp(6, usb.getTimestamp());
			pstmt.setString(7, usb.getRefresh_type());
			pstmt.setInt(8, usb.getSite_id());
			pstmt.setInt(9, usb.getSum_info_id());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			System.out.println("********done,,,");
			return true;
		}catch(SQLException sqlexe){
			System.out.println("*********exception:::"+sqlexe.getMessage());
			try {
				
				conn.rollback();
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	}
	
	
	public List<User_Script_Bean> getAllRefreshesList() throws SQLException{
		String query=" select "
				+"REFRESH_ID,"
				+ "cobrand_id,"
				+ "error_code,"
				+ "mem_item_id,"
				+ "mem_site_acc_id,"
				+ "method_list,"
				+ "refresh_timestamp,"
				+ "refresh_type,"
				+ "site_id,"
				+ "sum_info_id "
				+ "from USER_SCRIPT"; 
		SQLConnection sqlConn=new SQLConnection();
		Connection conn=sqlConn.getConnection();
		PreparedStatement pstmt = null;	
		pstmt = conn.prepareStatement(query);
		ResultSet rs=pstmt.executeQuery();
		List<User_Script_Bean> usbList=new ArrayList<User_Script_Bean>();
		while(rs.next()){
			User_Script_Bean bean=new User_Script_Bean();
			bean.setRefresh_id(rs.getInt("REFRESH_ID"));
			bean.setCobrand_id(rs.getString("cobrand_id"));
			bean.setCode(rs.getInt("error_code"));
			bean.setMem_item_id(rs.getString("mem_item_id"));
			bean.setMem_site_acc_id(rs.getString("mem_site_acc_id"));
			bean.setMethod_list(rs.getString("method_list"));
			bean.setTimestamp(rs.getTimestamp("refresh_timestamp"));
			bean.setRefresh_type(rs.getString("refresh_type"));
			bean.setSite_id(rs.getInt("site_id"));
			bean.setSum_info_id(rs.getInt("sum_info_id"));
			usbList.add(bean);
		}
		rs.close();
		conn.commit();
		pstmt.close();
		return usbList;
	}
	

	public List<User_Script_Bean> getAllRefreshesForCode(int code){
		return null;
	}
	
	
	public List<User_Script_Bean> getAllRefreshesForSite(int site_id){
		return null;
	}
	public List<User_Script_Bean> getAllRefreshesForSiteWithCode(int site, int code){
		return null;
	}
	
	
	public List<User_Script_Bean> getAllRefreshesForSuminfo(int sum_info_id){
		return null;
	}
	public List<User_Script_Bean> getAllRefreshesForSuminfoWithCode(int sum_info_id, int code){
		return null;
	}
	
	public List<User_Script_Bean> getAllRefreshesForMemSiteAccIDWithCode(String mem_site_Acc_id, int code){
		return null;
	}
	
	public List<User_Script_Bean> getAllRefreshesForMemSiteAccID(String mem_site_Acc_id){
		return null;
	}
	
	public List<User_Script_Bean> getAllRefreshesForMemItemID(String mem_item_id){
		return null;
	}
	public List<User_Script_Bean> getAllRefreshesForMemItemIDWithCode(String mem_item_id, int code){
		return null;
	}
	
	
	
//	public static void main(String[] args) throws ParseException {
//		User_Script_Bean usb=new User_Script_Bean();
//		User_Script_DAO usd=new User_Script_DAO();
//		usb.setCobrand_id("12345");
//		usb.setCode(0);
//		usb.setMem_item_id("12355");
//		usb.setMem_site_acc_id("12345");
//		usb.setMethod_list("methodtest");
//		usb.setRefresh_type("C");
//		usb.setSite_id(124);
//		usb.setSum_info_id(1234);
//		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//		java.util.Date date = format.parse(format.format(new Date()));
//		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
//		usb.setTimestamp(timestamp);
//		usd.addUserRefreshScript(usb);
//		System.out.println("added in DB");
//		
//		
//		
//	}
	
	
	public static void main(String[] args) throws SQLException {
		User_Script_DAO usd=new User_Script_DAO();
		List<User_Script_Bean> beans=usd.getAllRefreshesList();
		Iterator<User_Script_Bean> it=beans.iterator();
		while(it.hasNext()){
			User_Script_Bean bean=it.next();
			System.out.print("id:"+bean.getRefresh_id());
			System.out.print("|cobrand:"+bean.getCobrand_id());
			System.out.print("|code:"+bean.getCode());
			System.out.print("|mii:"+bean.getMem_item_id());
			System.out.print("|msa:"+bean.getMem_site_acc_id());
			System.out.print("|methods:"+bean.getMethod_list());
			System.out.print("|refreshtype:"+bean.getRefresh_type());
			System.out.print("|siteid:"+bean.getSite_id());
			System.out.print("|sii:"+bean.getSum_info_id());
			System.out.print("|time:"+bean.getTimestamp());
			System.out.println();
		}
		
	}
}
