package ru.test.nytn.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class News(
    val abstract: String,
    @SerializedName("web_url")
    val webUrl: String,
    val snippet: String,
    @SerializedName("lead_paragraph")
    val leadParagraph: String,
    @SerializedName("print_section")
    val printSection: String,
    @SerializedName("print_page")
    val printPage: String,
    val source: String,
    val multimedia: ArrayList<MediaItem>,
    val headline : Headline,
    val keywords : ArrayList<Keyword>,
    @SerializedName("pub_date")
    val pubDate : String,
    @SerializedName("document_type")
    val documentType : String,
    @SerializedName("news_desk")
    val newsDesk : String,
    @SerializedName("section_name")
    val section_name : String,
    val byline : Byline,
    @SerializedName("type_of_material")
    val typeOfMaterial : String,
    val _id: String,
    @SerializedName("word_count")
    val wordCount : Int,
    val uri : String
)