import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.millet.androidcontactds.R
import fr.isen.millet.androidcontactds.modelData.Results


class CustomAdapter(private val list: ArrayList<Results>, private val OnItemClickListener: (Results) -> Unit) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    // adapter conteneur
    // RecyclerView contenu
    // Holds the views for adding it to text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        // val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val nameView: TextView = itemView.findViewById(R.id.nameView)
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val emailView: TextView = itemView.findViewById(R.id.emailView)
        val locationView: TextView = itemView.findViewById(R.id.locationView)
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = list[position]
        holder.nameView.text = itemsViewModel.name?.first + " " + (itemsViewModel.name?.last ?: "")
        holder.emailView.text = itemsViewModel.email
        holder.locationView.text = itemsViewModel.location?.street?.name + " " + itemsViewModel.location?.street?.number + ", " + itemsViewModel.location?.city + ", " + itemsViewModel.location?.state + ", " + itemsViewModel.location?.country + ", " + itemsViewModel.location?.postcode

        if (itemsViewModel.picture?.medium != "") {
            Picasso.get().load(itemsViewModel.picture?.medium).into(holder.imageView)
            Log.d("image", itemsViewModel.picture?.medium.toString())

        }
        holder.itemView.setOnClickListener{
            OnItemClickListener(itemsViewModel)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(newList: List<Results>?) {
        list.clear()
        list.addAll(newList!!)
        notifyDataSetChanged()
    }
    // return the number of the items in the list
    override fun getItemCount(): Int = list.size

}

