package parra.mario.loginchilo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_contrasena.*

class ContrasenaActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrasena)

        mAuth = FirebaseAuth.getInstance()

        btn_restablecer.setOnClickListener{
            var correo = et_correo_cont.text.toString()

            if (!correo.isNullOrEmpty()){

                mAuth?.sendPasswordResetEmail(correo)
                    ?.addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Se envió un correo a $correo",
                                Toast.LENGTH_LONG).show()

                            finish()
                        }else{
                            Toast.makeText(this, "Error al enviar correo",
                                Toast.LENGTH_SHORT).show()
                        }

                    }


            }else{
                Toast.makeText(this, "Ingresar correo",
                    Toast.LENGTH_SHORT).show()
            }


        }
    }
}
