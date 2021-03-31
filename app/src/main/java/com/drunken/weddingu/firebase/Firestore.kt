package com.drunken.weddingu.firebase

import android.app.Activity
import android.util.Log
import com.drunken.weddingu.activities.LoginActivity
import com.drunken.weddingu.activities.MainActivity
import com.drunken.weddingu.activities.ProfileActivity
import com.drunken.weddingu.activities.RegisterActivity
import com.drunken.weddingu.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class Firestore {

    private var firestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo : User){
        firestore.collection("Users").document(getCurrentUserID()).set(userInfo, SetOptions.merge()).addOnSuccessListener {
            activity.userRegisteredSuccess()
        }
    }

    fun signInUser(activity: Activity){
        firestore.collection("Users").document(getCurrentUserID()).get().addOnSuccessListener { document ->
            val loggedInUser = document.toObject(User::class.java)
            when(activity){
                is MainActivity -> {
                    if(loggedInUser != null) {
                        activity.updateImage(loggedInUser)
                    }
                }
                is LoginActivity -> {
                    if(loggedInUser != null) {
                        activity.loginSuccess(loggedInUser)
                    }
                }
                is ProfileActivity -> {
                    if(loggedInUser != null) {
                        activity.updateUserDetails(loggedInUser)
                    }
                }
            }
        }.addOnFailureListener {
            Log.e("FirestoreClass - Login", "Error writing document to database")
            when(activity){
                is LoginActivity -> {
                    activity.hideProgressDialog()
                }
                is ProfileActivity -> {
                    activity.hideProgressDialog()
                }
            }

        }
    }

    fun getCurrentUserID() : String {
        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

}