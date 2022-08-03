import java.sql.*; 
import groovy.sql.*;
import com.mysql.jdbc.*;

public class TestGro{	


	public static void main (String[] args){


		def host = 'jdbc:mysql://sellinamysql.c2szfgab1pix.ap-south-1.rds.amazonaws.com:3306'
		def db = 'db_jtiaiph'
		def hostUrl = host+'/'+db
		def userName = 'ckread'
		def password = 'CKRead!23#$%'



		println("Hello....This is a grrovy file")


		Connection con=DriverManager.getConnection(hostUrl, userName, password) 
		Map<String, Data> dataMap = new HashMap<>()

		if(con != null){

			Statement stmt=con.createStatement()

			def query = 'select discriminator , value, cadence_key cKey, cadence_type cType, field_name fName, sum, alt_grp_name gName, alt_grp_value gValue, id from ck_generic_aggregation where field_name = \'valid_calls\' and cadence_type = \'MONTHLY\' and cadence_key = \'2022-06\' limit 1;'

			ResultSet rs=stmt.executeQuery(query)

			while(rs.next())
			{ 


				// println('Id : '+ rs.getString('id'))
				// println('gValue : '+rs.getString(gValue))
				// println('gName : '+rs.getString(gName))
				// println('discriminator : '+rs.getString(discriminator))
				// println('value : '+rs.getString(value))
				// println('cKey : '+rs.getString(cKey))
				// println('cType : '+rs.getString(cType))
				// println('fName : '+rs.getString(fName))
				// println('sum : '+rs.getString(sum)) 


				Data d = new Data()
				d.setId(rs.getString('id'))
				d.setgValue(rs.getString('gValue'))
				d.setgName(rs.getString('gName'))
				d.setDiscriminator(rs.getString('discriminator'))
				d.setValue(rs.getString('value'))
				d.setcKey(rs.getString('cKey'))
				d.setcType(rs.getString('cType'))
				d.setfName(rs.getString('fName'))
				d.setSum(rs.getString('sum'))

				dataMap.put(d.getId(), d)

			}
		}else
			println('Failed to make connection with the database : '+ db)


		con.close()


		for (var entry : dataMap.entrySet()) {
			println(entry.getKey() + " : " + entry.getValue().toString())
		}

		println("Bye....This is a grrovy file")

	}

}


public class Data{

	def id , gValue, gName, discriminator, value , cKey , cType, fName, sum

}