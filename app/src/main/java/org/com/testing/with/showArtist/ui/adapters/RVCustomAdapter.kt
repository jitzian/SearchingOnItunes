package org.com.testing.with.showArtist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.com.testing.with.musicartistsample.R
import org.com.testing.with.showArtist.model.db.model.ArtistAlbum

//TODO: UPdate this with viewbinding
class RVCustomAdapter : RecyclerView.Adapter<RVCustomAdapter.ViewHolder>() {

    private var lstRes: List<ArtistAlbum>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.artist_detail_card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        lstRes?.get(position)?.let {
            holder.bindData(it)
        }
    }

    override fun getItemCount(): Int {
        return lstRes?.size ?: 0
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mImageViewArtistDetailArtWork =
            itemView.findViewById<ImageView>(R.id.mImageViewArtistDetailArtWork)
        private val mTextViewArtistDetailArtistName =
            itemView.findViewById<TextView>(R.id.mTextViewArtistDetailArtistName)
        private val mTextViewArtistDetailTrackName =
            itemView.findViewById<TextView>(R.id.mTextViewArtistDetailTrackName)
        private val mTextViewArtistDetailReleaseDate =
            itemView.findViewById<TextView>(R.id.mTextViewArtistDetailReleaseDate)
        private val mTextViewArtistDetailPrimaryGenreName =
            itemView.findViewById<TextView>(R.id.mTextViewArtistDetailPrimaryGenreName)
        private val mTextViewArtistDetailTrackPrice =
            itemView.findViewById<TextView>(R.id.mTextViewArtistDetailTrackPrice)

        fun bindData(data: ArtistAlbum) {

            if (checkArtWork(data).isNotEmpty()) {
                Picasso.get()
                    .load(checkArtWork(data))
                    .into(mImageViewArtistDetailArtWork)
            } else {
                Picasso.get()
                    .load(R.drawable.not_available_image_resource)
                    .into(mImageViewArtistDetailArtWork)
            }

            mTextViewArtistDetailArtistName.text = data.artistName
            mTextViewArtistDetailTrackName.text = data.trackName
            mTextViewArtistDetailReleaseDate.text = data.releaseDate
            mTextViewArtistDetailPrimaryGenreName.text = data.primaryGenreName
            mTextViewArtistDetailTrackPrice.text = data.trackPrice.toString()
        }

        private fun checkArtWork(data: ArtistAlbum): String {
            var safeArtWork = ""

            with(data) {
                if (!artworkUrl100.isNullOrEmpty()) {
                    safeArtWork = artworkUrl100
                } else if (!artworkUrl60.isNullOrEmpty()) {
                    safeArtWork = artworkUrl60
                } else if (!artworkUrl30.isNullOrEmpty()) {
                    safeArtWork = artworkUrl30
                }
            }
            return safeArtWork
        }
    }

    fun setData(lstRes: List<ArtistAlbum>?) {
        this.lstRes = lstRes
        notifyDataSetChanged()
    }

}