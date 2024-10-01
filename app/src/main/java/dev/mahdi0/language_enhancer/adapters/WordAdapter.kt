package dev.mahdi0.language_enhancer.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.mahdi0.language_enhancer.R
import dev.mahdi0.language_enhancer.data.Word

class WordAdapter(private val context: Context, private val data: List<Word>) :
    RecyclerView.Adapter<WordAdapter.WordHolder>() {
    class WordHolder(v: View) : RecyclerView.ViewHolder(v) {
        var wordImg: ImageView = v.findViewById(R.id.word_img)
        var wordAndMean: TextView = v.findViewById(R.id.word_and_mean)
        var wordStar: soup.neumorphism.NeumorphImageButton = v.findViewById(R.id.word_star)
        var wordSpacer: View = v.findViewById(R.id.word_space)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return WordHolder(inflate)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val item = data[position]
        holder.wordAndMean.text = "${item.word}: ${item.translation}"

        if (position == data.size - 1) {
            holder.wordSpacer.visibility = View.GONE
        }

        changeStarImg(holder, item)

        holder.wordStar.setOnClickListener {
            item.stared = !item.stared
            changeStarImg(holder, item)
        }
        Glide
            .with(context)
            .load("https://picsum.photos/70?rand=${System.currentTimeMillis()}")
            .centerCrop()
            .placeholder(R.drawable.baseline_text_fields_24)
            .into(holder.wordImg);
    }

    private fun changeStarImg(
        holder: WordHolder,
        item: Word
    ) {
        holder.wordStar.setImageResource(
            if (item.stared) {
                R.drawable.baseline_star_24
            } else {
                R.drawable.baseline_star_border_24
            }
        )
    }
}
