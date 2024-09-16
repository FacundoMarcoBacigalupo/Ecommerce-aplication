package com.facundo.ecommerce.Seller
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facundo.ecommerce.Constantss
import com.facundo.ecommerce.databinding.ActivityRegisterSellerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterSellerActivity : AppCompatActivity() {

   private lateinit var binding : ActivityRegisterSellerBinding
   private lateinit var firebaseAuth: FirebaseAuth
   private lateinit var progressDialog : ProgressDialog

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityRegisterSellerBinding.inflate(layoutInflater)
      setContentView(binding.root)

      firebaseAuth = FirebaseAuth.getInstance()

      progressDialog = ProgressDialog(this)
      progressDialog.setTitle("Wait please")
      progressDialog.setCanceledOnTouchOutside(false)

      binding.btnRegisterV.setOnClickListener {
         validateInformation()
      }
   }

   //Input from register the user (seller)
   private var names = ""
   private var email = ""
   private var password = ""
   private var cpassword = ""
   private fun validateInformation() {
      names = binding.etNamesV.text.toString().trim()
      email = binding.etEmailV.text.toString().trim()
      password = binding.etPasswordV.text.toString().trim()
      cpassword = binding.etCPasswordV.text.toString().trim()

      //Validating data from user
      when{
         names.isEmpty() -> {
            binding.etNamesV.error = "Enter your name"
            binding.etNamesV.requestFocus()
         }

         email.isEmpty() ->{
            binding.etEmailV.error = "Enter your Email"
            binding.etEmailV.requestFocus()
         }
         !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
            binding.etEmailV.error = "Email not valid"
            binding.etEmailV.requestFocus()
         }

         password.isEmpty() -> {
            binding.etPasswordV.error = "Enter your password"
            binding.etPasswordV.requestFocus()
         }
         password.length < 6 -> {
            binding.etPasswordV.error = "Need 6 or more characters"
            binding.etPasswordV.requestFocus()
         }
         cpassword.isEmpty() -> {
            binding.etCPasswordV.error = "Confirm password"
            binding.etCPasswordV.requestFocus()
         }
         password != cpassword ->{
            binding.etCPasswordV.error = "Password are not same"
            binding.etCPasswordV.requestFocus()
         }

         //All ok register seller
         else -> registerSeller()
      }
   }

   //After validating input, register seller
   private fun registerSeller() {
      progressDialog.setMessage("Creating acount")
      progressDialog.show()

      firebaseAuth.createUserWithEmailAndPassword(email, password)
         .addOnSuccessListener {
            insertInfoBD()
         }

         .addOnFailureListener { e ->
            Toast.makeText(this, "Failed the register for ${e.message}", Toast.LENGTH_SHORT).show()
         }
   }

   //Firebase Realtime Database
   private fun insertInfoBD() {
      progressDialog.setMessage("Saveing information")

      val uidBD = firebaseAuth.uid
      val namesBD = names //Name seller
      val emailBD = email //Email seller
      val timeBD = Constantss.getTimeDevice()

      //Create data seller
      val dataSeller = HashMap<String, Any>()
      dataSeller["uid"] = "$uidBD"
      dataSeller["names"] = "$namesBD"
      dataSeller["email"] = "$emailBD"
      dataSeller["typeOfUser"] = "seller"
      dataSeller["time_register"] = timeBD

      val references = FirebaseDatabase.getInstance().getReference("Users") //Name of Database
      references.child(uidBD!!) //Order by id
         .setValue(dataSeller)
         .addOnSuccessListener {
            progressDialog.dismiss() //Occult
            startActivity(Intent(this, MainActivitySeller::class.java))
            finish() //Delete for pila
         }

         .addOnFailureListener { e ->
            progressDialog.dismiss() //Occult
            Toast.makeText(this, "Failed the register in Database for ${e.message}", Toast.LENGTH_SHORT).show()
         }
   }

}