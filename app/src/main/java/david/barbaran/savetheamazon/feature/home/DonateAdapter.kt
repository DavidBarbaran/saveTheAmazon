package david.barbaran.savetheamazon.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import david.barbaran.savetheamazon.R
import david.barbaran.savetheamazon.model.Donate
import kotlinx.android.synthetic.main.item_donate.view.*

class DonateAdapter : RecyclerView.Adapter<DonateAdapter.DonateViewHolder>() {

    var list = mutableListOf<Donate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DonateViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_donate,
                parent,
                false
            )
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DonateViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class DonateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(donate: Donate) {
            itemView.apply {
                amountText.text = donate.amount
                descriptionText.text = donate.description
                Glide.with(itemView.context).load(donate.image).transition(
                    withCrossFade(
                        DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                    )
                ).into(donateImage)
            }
        }
    }
}