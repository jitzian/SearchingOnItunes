package org.com.testing.with.showArtist.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.com.testing.with.musicartistsample.R
import org.com.testing.with.musicartistsample.databinding.ArtistDetailCardViewBinding
import org.com.testing.with.showArtist.model.db.model.ArtistAlbum

class RVCustomAdapter : RecyclerView.Adapter<RVCustomAdapter.ViewHolder>() {

    private var lstRes: List<ArtistAlbum>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ArtistDetailCardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        lstRes?.get(position)?.let {
            holder.bindData(it)
        }
    }

    override fun getItemCount(): Int {
        return lstRes?.size ?: 0
    }

    inner class ViewHolder(private val binding: ArtistDetailCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ArtistAlbum) {

            if (checkArtWork(data).isNotEmpty()) {
                Picasso.get()
                    .load(checkArtWork(data))
                    .into(binding.mImageViewArtistDetailArtWork)
            } else {
                Picasso.get()
                    .load(R.drawable.not_available_image_resource)
                    .into(binding.mImageViewArtistDetailArtWork)
            }

            binding.mTextViewArtistDetailArtistName.text = data.artistName
            binding.mTextViewArtistDetailTrackName.text = data.trackName
            binding.mTextViewArtistDetailReleaseDate.text = data.releaseDate
            binding.mTextViewArtistDetailPrimaryGenreName.text = data.primaryGenreName
            binding.mTextViewArtistDetailTrackPrice.text = data.trackPrice.toString()
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