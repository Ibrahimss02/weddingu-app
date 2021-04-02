package com.drunken.weddingu.firebase

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.drunken.weddingu.activities.*
import com.drunken.weddingu.models.GedungModel
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

    fun putGedungModel(activity: Activity, gedungModel: ArrayList<GedungModel>){
        gedungModel.forEach {
            firestore.collection("Gedung Model").document(it.id.toString()).set(it, SetOptions.merge())
        }
    }

    fun addToCartGedung(activity: Activity, gedungModel: GedungModel){
        firestore.collection("Users").document(getCurrentUserID()).update("gedungModel", gedungModel.id).addOnSuccessListener {
            Toast.makeText(activity, "Added to Cart", Toast.LENGTH_SHORT).show()
            activity.onBackPressed()
        }
    }

    fun getUserData(activity: Activity){
        firestore.collection("Users").document(getCurrentUserID()).get().addOnSuccessListener { document ->
            val loggedInUser = document.toObject(User::class.java)
            when(activity){
                is KeranjangActivity -> {
                    if (loggedInUser != null){
                        activity.setGedungToList(loggedInUser)
                    }
                }
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
                is CheckoutActivity -> {
                    if (loggedInUser != null) {
                        activity.setUserData(loggedInUser)
                        activity.setGedungModel(loggedInUser)
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