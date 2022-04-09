package fr.johan.app

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.johan.app.VinRepository.Singleton.databaseRef
import fr.johan.app.VinRepository.Singleton.vinList

class VinRepository {

    object Singleton{

    // se connecter à la référence vin
    val databaseRef = FirebaseDatabase.getInstance().getReference("vins")


    // créer une liste qui va contenir nos vins
    val vinList = arrayListOf<VinModel>()
}
    fun updateData(callback: () ->Unit){
        //absorber les données depuis la data base
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciens
                vinList.clear()
                // recolter la liste
                for(ds in snapshot.children){
                    // contruire un objet vin
                    val vin= ds.getValue(VinModel::class.java)

                    // vérifier que le vin n'est pas null
                    if(vin!=null){
                        // ajouter le vin à notre liste
                        vinList.add(vin)

                    }
                }
                // actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError){}


        })



    }
    // mettre à jour un objet vin en bdd
    fun updatVin(vin:VinModel){
        databaseRef.child(vin.id).setValue(vin)
    }
}