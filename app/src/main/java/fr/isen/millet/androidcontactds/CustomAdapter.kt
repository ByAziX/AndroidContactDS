import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

        /*if (itemsViewModel.picture != "") {
            Picasso.get().load(itemsViewModel.images[0]).into(holder.imageView)
            Log.d("image", itemsViewModel.images[0])

        }*/
        holder.itemView.setOnClickListener{
            OnItemClickListener(itemsViewModel)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(newList: Results) {
        list.clear()
        list.addAll(listOf(newList))
        notifyDataSetChanged()
    }
    // return the number of the items in the list
    override fun getItemCount(): Int = list.size

}

