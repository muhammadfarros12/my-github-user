package com.farroos.mygithubuser

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farroos.mygithubuser.databinding.ItemUserBinding

class UserAdapter(private val context: Context) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    internal var listUsers = arrayListOf<User>()

    // untuk ke detail
    private lateinit var mListener: OnItemClickListener

    // untuk ke detail
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    // untuk ke detail
    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    inner class ViewHolder(binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgPhoto = binding.imgAvatar
        val username = binding.txtUsername
        val name = binding.txtNama
        val repository = binding.txtSumRepository
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemUserBinding =
            ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUsers[position]

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(RequestOptions())
            .into(holder.imgPhoto)

        holder.username.text = user.username
        holder.name.text = user.name
        holder.repository.text = user.repository.toString()

        // untuk ke detail
        holder.itemView.setOnClickListener {
            mListener.onItemClick(position)
        }
    }


    override fun getItemCount(): Int {
        return listUsers.size
    }
}