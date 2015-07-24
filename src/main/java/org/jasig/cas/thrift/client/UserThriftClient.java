package org.jasig.cas.thrift.client;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.jasig.cas.thrift.server.TimedOutException;
import org.jasig.cas.thrift.server.User;
import org.jasig.cas.thrift.server.UserServiceThrift.Client;

/**
 * thrift server Client
 * @author dongtian
 * @date   2015年6月4日 上午11:20:14
 */
public class UserThriftClient {

	//thrift server host
	private String host;
	//thrift server port
	private int port;
	
	public UserThriftClient(String host, int port) {
		
		this.host = host;
		this.port = port;
	}
	
	/***
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 * @throws TimedOutException
	 * @throws org.apache.thrift.TException
	 */
	public User getUserByUserName(String userName)  {
		
		User user = null;
		
		if(StringUtils.isNotBlank(userName)) {
			
			TTransport transport = null;
			try {
				
				TTransport socket = new TSocket(host, port);
				transport = new TFramedTransport(socket);
				TProtocol protocol = new TBinaryProtocol(transport);	
				Client client = new Client(protocol);
				transport.open();
				user = client.getUserByUserName(userName);
				System.err.println("user is " + user);
				return user;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(transport != null) {
					transport.close();
				}
			}
		} 
		return user;
		
	}
	/***
	 * 检查用户名是否被占用
	 * @param userName
	 * @param id
	 * @return
	 */
	public boolean checkUserName(String userName,int id) {
		
		boolean has = false;
		
		if(StringUtils.isNotBlank(userName)) {
			
			TTransport transport = null;
			try {
				
				TTransport socket = new TSocket(host, port);
				transport = new TFramedTransport(socket);
				TProtocol protocol = new TBinaryProtocol(transport);	
				Client client = new Client(protocol);
				transport.open();
				has = client.checkUserName(userName, id);
				return has;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(transport != null) {
					transport.close();
				}
			}
		} 
		return has;
		
	}
	
	/***
	 * 检查手机是否已经被占用
	 * @param mobile
	 * @param id
	 * @return
	 */
	public boolean checkMobile(String mobile,int id) {
		
		boolean has = false;
		
		if(StringUtils.isNotBlank(mobile)) {
			
			TTransport transport = null;
			try {
				
				TTransport socket = new TSocket(host, port);
				transport = new TFramedTransport(socket);
				TProtocol protocol = new TBinaryProtocol(transport);	
				Client client = new Client(protocol);
				transport.open();
				has = client.checkMobile(mobile, id);
				return has;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(transport != null) {
					transport.close();
				}
			}
		} 
		return has;
		
	}
	
	/***
	 * 检查邮箱是否存在
	 * @param email
	 * @param id
	 * @return
	 */
	public boolean checkEmail(String email,int id) {
		
		boolean has = false;
		
		if(StringUtils.isNotBlank(email)) {
			
			TTransport transport = null;
			try {
				
				TTransport socket = new TSocket(host, port);
				transport = new TFramedTransport(socket);
				TProtocol protocol = new TBinaryProtocol(transport);	
				Client client = new Client(protocol);
				transport.open();
				has = client.checkEmail(email, id);
				return has;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(transport != null) {
					transport.close();
				}
			}
		} 
		return has;
		
	}
	/***
	 * 检查密码是否正确
	 * @param password
	 * @param id
	 * @return
	 */
	public boolean chechPassword(String password,int id) {
		
		boolean has = false;
		
		if(StringUtils.isNotBlank(password)) {
			
			TTransport transport = null;
			try {
				
				TTransport socket = new TSocket(host, port);
				transport = new TFramedTransport(socket);
				TProtocol protocol = new TBinaryProtocol(transport);	
				Client client = new Client(protocol);
				transport.open();
				has = client.checkPassword(password, id);
				return has;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(transport != null) {
					transport.close();
				}
			}
		} 
		return has;
		
	}
	/***
	 * 检查昵称是否存在
	 * @param nickName
	 * @param id
	 * @return
	 */
	public boolean checkNickName(String nickName,int id) {
		
		boolean has = false;
		
		if(StringUtils.isNotBlank(nickName)) {
			
			TTransport transport = null;
			try {
				
				TTransport socket = new TSocket(host, port);
				transport = new TFramedTransport(socket);
				TProtocol protocol = new TBinaryProtocol(transport);	
				Client client = new Client(protocol);
				transport.open();
				has = client.checkNickName(nickName, id);
				return has;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(transport != null) {
					transport.close();
				}
			}
		} 
		return has;
		
	}
	/***
	 * 新增用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user) {
		
		boolean has = false;
		
		if(StringUtils.isNotBlank(user.getUserName())) {
			
			TTransport transport = null;
			try {
				
				TTransport socket = new TSocket(host, port);
				transport = new TFramedTransport(socket);
				TProtocol protocol = new TBinaryProtocol(transport);	
				Client client = new Client(protocol);
				transport.open();
				has = client.addNewUser(user);
				return has;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(transport != null) {
					transport.close();
				}
			}
		} 
		return has;
		
	}
	
	/***
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user) {
		
		boolean has = false;
		
		if(StringUtils.isNotBlank(user.getUserName())) {
			
			TTransport transport = null;
			try {
				
				TTransport socket = new TSocket(host, port);
				transport = new TFramedTransport(socket);
				TProtocol protocol = new TBinaryProtocol(transport);	
				Client client = new Client(protocol);
				transport.open();
				has = client.updateUser(user);
				return has;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(transport != null) {
					transport.close();
				}
			}
		} 
		return has;
		
	}
	/***
	 * 获取用户总数
	 * @return
	 */
	public int findUserCount() {
		
		int  count = 0;
			TTransport transport = null;
			try {
				
				TTransport socket = new TSocket(host, port);
				transport = new TFramedTransport(socket);
				TProtocol protocol = new TBinaryProtocol(transport);	
				Client client = new Client(protocol);
				transport.open();
				count = client.findUserCount();
				return count;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(transport != null) {
					transport.close();
				}
			}
		return count;
		
	}
	
	/***
	 * 分页获取用户信息详情列表
	 * @param startIndex
	 * @param rowCount
	 * @return
	 */
	public List<User> findUserList(int startIndex,int rowCount) {
		
		List<User> users = null ;
		if(rowCount > 0 && startIndex >= 0) {
			TTransport transport = null;
			try {
				
				TTransport socket = new TSocket(host, port);
				transport = new TFramedTransport(socket);
				TProtocol protocol = new TBinaryProtocol(transport);	
				Client client = new Client(protocol);
				transport.open();
				users = client.findUserList(startIndex, rowCount);
				return users;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(transport != null) {
					transport.close();
				}
			}
		} 
		return users;
	}
	
	
	
	
	public static void main(String[] args) {
		UserThriftClient thriftClient = new UserThriftClient("192.168.1.30", 9999);
			thriftClient.getUserByUserName("admin");
	}
}
