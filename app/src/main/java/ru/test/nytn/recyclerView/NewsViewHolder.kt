package ru.test.nytn.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import ru.test.nytn.R
import ru.test.nytn.databinding.CardNewsBinding
import ru.test.nytn.dto.MediaItem
import ru.test.nytn.dto.News
import ru.test.nytn.utills.Constants

class NewsViewHolder(
    private val binding: CardNewsBinding,
    private val listener: NewsListener
) : ViewHolder(binding.root) {

    private var itemNews: News? = null

    private val buttonOnClickListener: View.OnClickListener =
        View.OnClickListener { itemNews?.let { listener.readMore(it.webUrl) } }

    init {
        binding.readMore.setOnClickListener(buttonOnClickListener)
    }

    fun bind(news: News) {
        binding.apply {
            val imageUrl = getFirstImageUrl(news.multimedia)
            imageUrl?.let {
                Glide.with(newsImage)
                    .load(Constants.GET_IMAGE_BASE_URL + imageUrl)
                    .error(R.drawable.news_error)
                    .timeout(10_000)
                    .into(newsImage)
            } ?: newsImage.setImageResource(R.drawable.news_error)

            newsHeader.text = news.abstract
            newsDescription.text = news.leadParagraph
        }
        itemNews = news
    }

    private fun getFirstImageUrl(multimedia: ArrayList<MediaItem>): String? {
        for (item in multimedia) {
            if (item.type == Constants.IMAGE) {
                return item.url
            }
        }
        return null
    }
}