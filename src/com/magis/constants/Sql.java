
package com.magis.constants;

/*
 * 	ALTER SEQUENCE test_id_seq RESTART WITH 10;
 *  CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
 */

public class Sql {
//postGre
	
	
	
	public static final class crons {

		public static final String  GET_APPLICATION_PARAMETERS = "SELECT parameter_name,parameter_value FROM application_parameters  WHERE TO_CHAR(date_time,'YYYY/MM/DD hh24:mi:ss')>?";
		public static final String  GET_APPLICATION_PARAMETERS_MAX_DATE = "SELECT MAX(TO_CHAR(date_time,'YYYY/MM/DD hh24:mi:ss')) AS dt  FROM application_parameters";
		
		public static final String  GET_APPLICATION_MESSAGES_FIRST = "SELECT * FROM messages WHERE TO_CHAR(date_time,'YYYY/MM/DD hh24:mi:ss')>? LIMIT 1";
		public static final String  GET_APPLICATION_MESSAGES_MAX_DATE = "SELECT MAX(TO_CHAR(date_time,'YYYY/MM/DD hh24:mi:ss')) AS dt FROM messages";
		public static final String  GET_APPLICATION_MESSAGES = "SELECT * FROM messages";
				
		public static final String  SET_APPLICATION_PARAMETER = "update application_parameters set parameter_value=?, date_time=now()  WHERE parameter_name=?";
		
	}
	public static final class user {
		public static final String  INSERT_USER_WITHOUT_IMAGE= "INSERT INTO users(username, password, name, surname, email, avatar, company, phone, mobile_phone, is_deleted)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, false)";
		public static final String  UPDATE_USER_WITHOUT_IMAGE= "UPDATE users set password=?, name=?, surname=?, "
				+ "email=?, avatar=?, company=?, phone=?, mobile_phone=? "
				+ "where username=?";
		public static final String  UPDATE_USER_IS_DELETED= "UPDATE users set is_deleted=? where username=?";
		public static final String  UPDATE_USER_PASSWORD= "UPDATE users set password=? where username=?";

		public static final String  DELETE_USER_ROLES= "DELETE FROM user_roles where username=?";
		public static final String  INSERT_USER_ROLE= "INSERT INTO user_roles(username, role_name) VALUES (?, ?)";
	}
	
	public static final class logins {
		public static final String  SELECT_USER= "select * from users, user_roles where " + 
				 "users.username=user_roles.username  and lower(users.username)=lower(COALESCE(?,'Â½')) and "
				 + "users.password=COALESCE(?,'Â½') and is_deleted=false";

		public static final String  SELECT_USER_BY_USERNAME= "select * from users, user_roles where " + 
				 "users.username=user_roles.username  and lower(users.username)=lower(COALESCE(?,'Â½'))";

		public static final String  SELECT_ACTIVE_USER_NAMES= "select distinct(username) as userName from users where is_deleted=false order by username";
		public static final String  SELECT_DELETED_USER_NAMES= "select distinct(username) as userName from users where is_deleted=true order by username";
		
		public static final String  UPDATE_USER_INFO_BY_USERNAME= "update users "
				+ " set password=?, name=?, surname=?, email=?, company=?,  phone=?, mobile_phone=? "
				+ "where lower(users.username)=lower(COALESCE(?,'Â½')) returning *";

		public static final String  UPDATE_USER_AVATAR_BY_USERNAME= "update users "
				+ " set avatar=?"
				+ "where lower(users.username)=lower(COALESCE(?,'Â½'))";

		
		public static final String  UPDATE_LOGIN_USER = "update users set last_login_ip=?, last_login_date = now() where lower(username)=lower(COALESCE(?,'Â½'))";
		
		public static final String  LOGIN_WITH_PWD = "update user set token=?, last_request_date = now() where lower(email)=lower(COALESCE(?,'Â½')) and password=COALESCE(?,'Â½') returning *";
		public static final String  LOGIN_WITH_TOKEN = "update web_users set last_request_date = now() where token = ? returning *";
		public static final String  LOGOUT = "update web_users set token='', last_request_date = now() where token = ? returning *";
	
	}

	public static final class systemParameters {
		public static final String  SELECT_HOSPITAL_NAMES = "select * from hospital";
		public static final String  SELECT_HOSPITAL_BY_ID = "select * from hospital where hospital_id=?::bigint";
		public static final String  INSERT_HOSPITAL = "INSERT INTO hospital( " + 
				"hospital_id, hospital_name, hospital_address, hospital_phone, hospital_email, is_deleted, insert_date) " +
				"VALUES ((select coalesce((Select Max(hospital_id) from hospital),0) + 1), ?, ?, ?, ?, ?, now())";
		public static final String  UPDATE_HOSPITAL = "UPDATE hospital set " + 
				"hospital_name=?, hospital_address=?, hospital_phone=?, hospital_email=?, is_deleted=?, update_date=now() " +
				"where hospital_id=?::bigint";
	}

}
