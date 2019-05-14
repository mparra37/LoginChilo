package parra.mario.loginchilo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        btn_registrarse.setOnClickListener{
            var intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        tv_olvidasteContra.setOnClickListener{
            var intent = Intent(this, ContrasenaActivity::class.java)
            startActivity(intent)
        }

        btn_ingresar.setOnClickListener{
            ingresar()
        }
    }


    private fun ingresar(){
        var correo = et_correo.text.toString()
        var contra = et_contra.text.toString()

        if(!correo.isNullOrEmpty() && !contra.isNullOrEmpty()){

            mAuth?.signInWithEmailAndPassword(correo, contra)
                ?.addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        var intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("usuario", correo)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this,"Error al iniciar sesión",
                            Toast.LENGTH_SHORT).show()
                    }

                }


        }else{
            Toast.makeText(this,"Ingresar datos",
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()

        var usuario = mAuth?.currentUser

        if(usuario != null){
            var intent = Intent(this, MainActivity::class.java)
            var correo = usuario.email
            intent.putExtra("usuario",correo)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()

        mAuth?.signOut()
    }



}
