package com.farroos.mygithubuser

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.farroos.mygithubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvUser: RecyclerView

    private var list: ArrayList<User> = arrayListOf()
    private lateinit var adapter: UserAdapter

    private lateinit var dataUsername: Array<String>
    private lateinit var dataName: Array<String>
    private lateinit var dataPhoto: TypedArray
    private lateinit var dataCompany: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataLocation: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        rvUser = binding.rcvUsers
        rvUser.setHasFixedSize(true)


        prepare()

        showRecyclerView()

        addItem()

        // dibuat setelah Activity detail selesai dibuat
        adapter.setOnItemClickListener(object : UserAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val userDetail = User(
                    list[position].username,
                    list[position].name,
                    list[position].location,
                    list[position].repository,
                    list[position].company,
                    list[position].follower,
                    list[position].avatar,
                    list[position].following
                )

                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, userDetail)
                startActivity(intent)
            }
        })
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val user = User(
                dataUsername[position],
                dataName[position],
                dataLocation[position],
                dataRepository[position].toInt(),
                dataCompany[position],
                dataFollower[position].toInt(),
                dataPhoto.getResourceId(position, -1),
                dataFollowing[position].toInt()
            )
            list.add(user)
        }
        adapter.listUsers = list
    }

    private fun showRecyclerView() {
        rvUser.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(this)
        rvUser.adapter = adapter
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataPhoto = resources.obtainTypedArray(R.array.avatar)
        dataCompany = resources.getStringArray(R.array.company)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
        dataFollower = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
    }
}