package com.example.data.repository

import com.example.domain.data.user.User
import com.example.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserRepositoryImpl() :UserRepository{
    private var auth : FirebaseAuth? = null
    private lateinit var databaseReference: DatabaseReference

    override fun signUpWithEmail(email: String, psw: String, name: String) : Flow<Result<User>> = callbackFlow {
        if (email.isNotEmpty() && psw.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, psw)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = FirebaseAuth.getInstance().currentUser
                        if(firebaseUser==null) trySend(Result.failure(RuntimeException("사용자 없음!")))

                        var user = User(
                            idToken = firebaseUser?.uid.toString(),
                            emailId = firebaseUser?.email.toString(),
                            password = psw,
                            name = name
                        )

                        databaseReference.child(firebaseUser?.uid.toString()).setValue(user)
                        //파이어베이스 데이터베이스에 값을 저장

                        trySend(Result.success(user))

                    } else {
                        trySend(Result.failure(RuntimeException("회원가입 실패!")))
                    }
                }
        }
    }

    override fun signInWithEmail(email: String, psw: String): Flow<Result<Unit>> = callbackFlow {
        if (email.isNotEmpty() && psw.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, psw)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(Result.success(Unit))
                } else {
                    trySend(Result.failure(RuntimeException("로그인 실패!")))
                }
            }
        }
    }

    override fun changeUserPsw(user: User, new_password: String, new_password_check: String): Flow<Result<Unit>> = callbackFlow {
        if(new_password == new_password_check)
        {
            auth?.currentUser?.updatePassword(new_password)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var userData: User = User(
                            idToken = user.idToken,
                            emailId = user.emailId,
                            password = new_password,
                            name = user.name
                        )
                        databaseReference.child(userData.idToken).setValue(userData)
                        trySend(Result.success(Unit))
                    }
                }
        }
        else{
            trySend(Result.failure(RuntimeException("비밀번호 변경 실패!")))
        }
    }

    override fun deleteUser(uid: String): Flow<Result<Unit>> = callbackFlow {
        auth?.currentUser?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                databaseReference.child(uid).removeValue()
                trySend(Result.success(Unit))

            } else {
                trySend(Result.failure(RuntimeException("회원탈퇴 실패")))
            }
        }
    }
}