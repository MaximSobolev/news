package ru.test.nytn.recyclerView

import androidx.recyclerview.widget.DiffUtil
import ru.test.nytn.dto.News

class NewsDiffCallback : DiffUtil.ItemCallback<News>() {

    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.abstract == newItem.abstract
                && oldItem.webUrl == newItem.webUrl
                && oldItem.leadParagraph == newItem.leadParagraph
                && oldItem.multimedia == newItem.multimedia
                && oldItem._id == newItem._id
    }
}