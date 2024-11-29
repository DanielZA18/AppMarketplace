package com.zevallosdaniel.appmarketplace

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.zevallosdaniel.appmarketplace.Anuncios.CrearAnuncio
import com.zevallosdaniel.appmarketplace.Fragmentos.FragmentChats
import com.zevallosdaniel.appmarketplace.Fragmentos.FragmentCuenta
import com.zevallosdaniel.appmarketplace.Fragmentos.FragmentInicio
import com.zevallosdaniel.appmarketplace.Fragmentos.FragmentMisAnuncios
import com.zevallosdaniel.appmarketplace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firebaseAuth = FirebaseAuth.getInstance()
        comprobarSesion()


        verFragmentInicio()

        binding.BottomNV.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.Item_Inicio->{
                    verFragmentInicio()
                    true
                }

                R.id.Item_Chats->{
                    verFragmentChats()
                    true
                }

                R.id.Item_Mis_Anuncios->{
                    verFragmentMisAnuncios()
                    true
                }

                R.id.Item_Cuenta ->{
                    verFragmentCuenta()
                    true
                }
                else ->{
                    false
                }
            }
        }

        binding.FAB.setOnClickListener {
            val intent = Intent(this, CrearAnuncio::class.java)
            intent.putExtra("Edicion", false)
            startActivity(intent)
        }

    }

    private fun comprobarSesion(){
        if (firebaseAuth.currentUser == null){
            startActivity(Intent(this, OpcionesLogin::class.java))
            finishAffinity()
        }
    }

    private fun verFragmentInicio(){
        binding.TituloRL.text = "Inicio"
        val fragment = FragmentInicio()
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(binding.FragmentL1.id,fragment,"FragmentInicio")
        fragmentTransition.commit()
    }

    private fun verFragmentChats(){
        binding.TituloRL.text = "Funciones"
        val fragment = FragmentChats()
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(binding.FragmentL1.id,fragment,"FragmentChats")
        fragmentTransition.commit()
    }

    private fun verFragmentMisAnuncios(){
        binding.TituloRL.text = "Anuncios"
        val fragment = FragmentMisAnuncios()
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(binding.FragmentL1.id,fragment,"FragmentMisAnuncios")
        fragmentTransition.commit()
    }

    private fun verFragmentCuenta(){
        binding.TituloRL.text = "Cuenta"
        val fragment = FragmentCuenta()
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(binding.FragmentL1.id,fragment,"FragmentCuenta")
        fragmentTransition.commit()
    }




}