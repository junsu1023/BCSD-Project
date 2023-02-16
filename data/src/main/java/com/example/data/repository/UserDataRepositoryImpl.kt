package com.example.data.repository

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.data.user.User
import com.example.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    private var auth : FirebaseAuth? = null
    private lateinit var databaseReference: DatabaseReference

    override fun signUpWithEmail(email: String, psw: String, name: String, activity: AppCompatActivity) {
        if (email.isNotEmpty() && psw.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, psw)
                ?.addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = FirebaseAuth.getInstance().currentUser

                        var user = User(
                            idToken = firebaseUser?.uid.toString(),
                            emailId = firebaseUser?.email.toString(),
                            password = psw,
                            name = name
                        )

                        databaseReference.child(firebaseUser?.uid.toString()).setValue(user)
                        //파이어베이스 데이터베이스에 값을 저장

                        Toast.makeText(activity, "계정 생성 완료.", Toast.LENGTH_SHORT).show()
                        activity.finish()
                    }
                    else {
                        Toast.makeText(
                            activity, "계정 생성 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    override fun signInWithEmail(email: String, psw: String , activity: AppCompatActivity): FirebaseUser? {
        var curUser : FirebaseUser? = null

        if (email.isNotEmpty() && psw.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, psw)?.addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        activity, "로그인에 성공 하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    curUser = auth?.currentUser // 이거 없애고
                } else {
                    Toast.makeText(
                        activity, "로그인에 실패 하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            //return auth?.currentUser 이게 되는지 나중에 확인
            return curUser
        }
        else
        {
            return null
        }
    }

    override fun changeUserPsw(user: User, new_password: String, new_password_check: String, activity: AppCompatActivity) {
        if(new_password == new_password_check)
        {
            auth?.currentUser?.updatePassword(new_password)
                ?.addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        var userData: User = User(
                            idToken = user.idToken,
                            emailId = user.emailId,
                            password = new_password,
                            name = user.name
                        )
                        databaseReference.child(user.idToken).setValue(userData)

                        Toast.makeText(
                            activity, "비밀번호가 변경되었습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
        else{
            Toast.makeText(
                activity, "비밀번호가 다릅니다.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun deleteUser(uid: String, activity: AppCompatActivity) {
        auth?.currentUser?.delete()?.addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {

                databaseReference.child(uid).removeValue()

                Toast.makeText(
                    activity, "회원탈퇴가 완료되었습니다",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
