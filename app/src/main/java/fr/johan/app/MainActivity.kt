package fr.johan.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.johan.app.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //charger notre vin repository
        val repo = VinRepository()

        // mettre à jour la liste de vins
        repo.updateData {
        // Injecter le fragment
            val transaction= supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HomeFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}