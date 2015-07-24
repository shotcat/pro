namespace  java org.jasig.cas.thrift.server

struct User {

	1: i32		id
	2: string	userName
	3: string	nickName
	4: string	mobile
	5: string 	email
	6: string	idCard
	7: string	jobId
	8: i32	        state
	9: string	password

} 


exception TimedOutException {


}


service  UserServiceThrift {

    User  getUserByUserName(1: string userName) throws (1: TimedOutException te)

    bool  checkUserName(1: string userName 2: i32 id) throws (1: TimedOutException te)

    bool  checkMobile(1:string mobile 2: i32 id)  throws (1: TimedOutException te)

    bool  checkEmail(1:string email 2: i32 id)   throws (1: TimedOutException te)

    bool  checkIdCard(1:string idCard 2: i32 id) throws (1: TimedOutException te)

    bool  checkJobId(1:string jobId 2: i32 id)   throws (1: TimedOutException te)

    bool  addNewUser(1: User user) throws (1: TimedOutException te)

    bool  addNewUserList(1: list<User> userList) throws (1: TimedOutException te)
   
    bool  updateUser(1: User user) throws (1: TimedOutException te)

    list<User>  lookOnlineUserList() throws (1: TimedOutException te)
 
    bool checkPassword(1:string password 2: i32 id) throws (1: TimedOutException te)
    
    bool checkNickName(1: string nickName 2: i32 id) throws (1: TimedOutException te)
   
    i32 findUserCount()	throws (1: TimedOutException te)

    list<User> findUserList(1:i32 startIndex 2:i32 rowCount) throws (1: TimedOutException te)

}



