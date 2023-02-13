package com.example.data.repository

import com.example.data.entity.UserInfoEntity
import com.example.domain.data.user.UserInfo
import com.example.domain.repository.UserDataRepository

class UserDataRepositoryImpl : UserDataRepository {
    private var auth : FirebaseAuth? = null
    private lateinit var databaseReference: DatabaseReference

    override fun signUpWithEmail(email: String, psw: String, name: String) :Boolean {
        auth?.createUserWithEmailAndPassword(email, psw)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    lateinit var userInfo: UserInfoEntity
                    val firebaseUser = FirebaseAuth.getInstance().currentUser
                    userInfo = UserInfoEntity(
                        idToken = firebaseUser?.uid.toString(),
                        emailId = firebaseUser?.email.toString(),
                        password = psw,
                        name = name
                    )
                    databaseReference.child(firebaseUser?.uid.toString()).setValue(userInfo)
                    //파이어베이스 데이터베이스에 값을 저장
                }
            }
        return auth?.currentUser != null
    }

    override fun signInWithEmail(email: String, psw: String): FirebaseUser?
    {
        auth?.signInWithEmailAndPassword(email, psw)?.addOnCompleteListener(this)
        return auth?.currentUser
    }
    
    override fun getUserName(uid: String): String
    {
        lateinit var userName: String
        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        //데이터 베이스 경로 저장
        databaseReference.child(uid).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val group: UserInfoEntity? = snapshot.getValue(UserInfoEntity::class.java)
                userName = group?.name.toString()
            }
           override fun onCancelled(error: DatabaseError) {
                userName = "알수 없음"
            }
        } )
        return userName
    }

    override fun changeUserPsw(uid: String, email: String, name: String, new_password: String, new_password_check: String): Boolean
    {
        if(new_password == new_password_check)
        {
            var checkChangePsw = 0
            auth?.currentUser?.updatePassword(new_password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        var userInfo: UserInfoEntity = UserInfoEntity(
                            idToken = uid,
                            emailId = email,
                            password = new_password,
                            name = name
                        )
                        databaseReference.child(uid).setValue(userInfo)
                        checkChangePsw = 1
                    }
                }
            return checkChangePsw == 1
        }
        else{
            return false
        }
    }
    override fun deleteUser(uid: String, psw: String, curPsw: String): Boolean
    {
        return if(psw == curPsw) {
            var isUserDelete = 0
            auth?.currentUser?.delete()?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    databaseReference.child(uid).removeValue()
                    isUserDelete = 1
                }
            }
            isUserDelete == 1
        } else{
            false
        }
    }
}