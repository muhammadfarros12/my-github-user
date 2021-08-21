package com.farroos.mygithubuser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farroos.mygithubuser.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        val actionBar = supportActionBar

        actionBar!!.title = "Detail User"

        binding.apply {
            txtName.text = user.name
            txtUsername.text = user.username
            txtCompany.text = user.company
            txtLocation.text = user.location

            Glide.with(this@DetailActivity)
                .load(user.avatar)
                .apply(RequestOptions())
                .into(binding.imgAvatar)

            txtFollower.text = user.follower.toString()
            txtRepository.text = user.repository.toString()
            txtFollowing.text = user.following.toString()
        }

    }
}