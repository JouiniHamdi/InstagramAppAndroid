package com.hamdi.myapplication.presentation.homeScreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hamdi.myapplication.R
import com.hamdi.myapplication.domain.model.Photo
import com.hamdi.myapplication.presentation.homeScreen.adapters.UserHorizontalListAdapter
import com.hamdi.myapplication.presentation.homeScreen.adapters.UserVerticalListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: PhotosListViewModel by viewModels()

    lateinit var userHorizontalListAdapter: UserHorizontalListAdapter
    lateinit var recyclerViewHorizontalList: RecyclerView
    lateinit var tvNoResultFound: TextView
    lateinit var tvNoResultFoundForVerticalList: TextView


    lateinit var userVerticalListAdapter: UserVerticalListAdapter
    lateinit var recyclerViewVerticalList: RecyclerView
    lateinit var searchEditText: EditText

    var photosList: List<Photo> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initVerticalRecycleView()
        initHorizontalRecycleView()
        observePhotosList()
        searchEditTextChangedListener()
    }

    private fun observePhotosList() {
        viewModel.photosList.removeObservers(viewLifecycleOwner)
        viewModel.photosList.observe(viewLifecycleOwner) { list ->
            photosList = list
            userHorizontalListAdapter.setPhotosHorizontalList(list)
            userVerticalListAdapter.setPhotosVerticalList(list)
        }
    }


    private fun searchEditTextChangedListener() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val newList = photosList.filter { it.user!!.name.contains(s.toString(), true) }
                userVerticalListAdapter.setPhotosVerticalList(newList)
                userHorizontalListAdapter.setPhotosHorizontalList(newList)

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }


    private fun initHorizontalRecycleView() {
        view?.let { view ->
            recyclerViewHorizontalList =
                view.findViewById<RecyclerView>(R.id.rv_horizontal_photos_list)
            userHorizontalListAdapter = UserHorizontalListAdapter(emptyList())
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            userHorizontalListAdapter.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.ALLOW
            recyclerViewHorizontalList.layoutManager = layoutManager
            recyclerViewHorizontalList.adapter = userHorizontalListAdapter

            //-----------------
            userHorizontalListAdapter.itemClickListener = { position, photoUrl, photoId ->
                viewModel.makePhotoAlreadyVisible(photoId)
                val arguments = Bundle().apply {
                    putString("photoUrl", photoUrl)
                }

                findNavController().navigate(
                    R.id.action_homeFragment_to_fullScreenPhotoFragment,
                    arguments
                )
            }

        }
    }

    private fun initVerticalRecycleView() {
        view?.let { view ->
            recyclerViewVerticalList = view.findViewById(R.id.rv_vertical_photos_list)
            userVerticalListAdapter = UserVerticalListAdapter(emptyList())
            val layoutManager = GridLayoutManager(context, 2)
            recyclerViewVerticalList.layoutManager = layoutManager
            recyclerViewVerticalList.adapter = userVerticalListAdapter

            //----------------------
            userVerticalListAdapter.itemClickListener = { position, photo ->

                val arguments = Bundle().apply {
                    putString("photoUrl", photo.urls?.regular)
                    putString("userName", photo.user?.name)
                    putString("userProfilePicture", photo.user?.profileImage?.small)
                    putString("createdAt", photo.createdAt)
                    putString("updatedAt", photo.updatedAt)
                }

                findNavController().navigate(
                    R.id.action_homeFragment_to_detailPhotoFragment,
                    arguments
                )
            }


        }
    }

    private fun initViews() {
        view?.let { view ->
            searchEditText = view.findViewById(R.id.ed_search)
        }

    }


}