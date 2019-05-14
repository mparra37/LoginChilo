package parra.mario.loginchilo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro.*

import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.R.attr.password




class RegistroActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        mAuth = FirebaseAuth.getInstance()

        btn_registrar.setOnClickListener{
            registrar()
        }
    }


    private fun registrar(){
        var correo = et_correo_reg.text.toString()
        var contra1 = et_contra_reg.text.toString()
        var contra2 = et_contra2_reg.text.toString()

        //Validaciones
        if(!correo.isNullOrEmpty() && !contra1.isNullOrEmpty() && !contra2.isNullOrEmpty()){
            if (contra1 == contra2){
                registrarFirebase(correo, contra1)
            }else{
                Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this,"Ingresar datos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registrarFirebase(correo: String, contra: String){

        mAuth?.createUserWithEmailAndPassword(correo, contra)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = mAuth?.getCurrentUser()

                    Toast.makeText(
                        this, "${user?.email} ha sido registrado exitosamente",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()

                } else {

                    Toast.makeText(
                        this, "Error al registrase.",
                        Toast.LENGTH_SHORT
                    ).show()

                }


            }
    }

}
