package fr.johan.app.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.johan.app.MainActivity
import fr.johan.app.R
import fr.johan.app.VinModel
import fr.johan.app.VinRepository

class VinAdapter (
    private val context: MainActivity,
    private val vinList: List <VinModel>,
    private val layoutId: Int,
    ): RecyclerView.Adapter<VinAdapter.ViewHolder> (){

    // boite pour ranger les composants à controler
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val vineImage= view.findViewById<ImageView>(R.id.image_item)
        val vinName: TextView?= view.findViewById(R.id.name_item)
        val vinDescription: TextView?= view.findViewById(R.id.description_vin)
        val starIcon =view.findViewById<ImageView>(R.id.star_icon)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les infos de la plante
        val currentVin = vinList [position]

        // recupérer le repository
        val repo = VinRepository ()

        // utliser glide pour recuperer l'image à partir de son lien ->composant
        Glide.with(context).load(Uri.parse(currentVin.imageUrl)).into(holder.vineImage)

        // mettre à jour le nom du vin
        holder.vinName?.text = currentVin.name

        // mettre à jour la description
        holder.vinDescription?.text= currentVin.description

        // verifier si le vin a été liké
        if(currentVin.liked){
          holder.starIcon.setImageResource(R.drawable.ic_star)
        }
        else {
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }

        //rajouter une interaction sur cette etoile
        holder.starIcon.setOnClickListener{
                    // inverse si le bouton est ou non
                     currentVin.liked=!currentVin.liked
                    // mettre à jour objet vin
                    repo.updatVin(currentVin)


        }






    }

    override fun getItemCount(): Int = vinList.size

}


