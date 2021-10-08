package com.example.aoppart4chapter03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aoppart4chapter03.databinding.ViewholderSearchResultItemBinding
import com.example.aoppart4chapter03.model.SearchResultEntity

class SearchRecyclerviewAdapter: RecyclerView.Adapter<SearchRecyclerviewAdapter.SearchResultItemViewHolder>() {

    private var searchResultList: List<SearchResultEntity> = listOf()
    private lateinit var searchResultClickListener: (SearchResultEntity) -> Unit

    class SearchResultItemViewHolder(
        val binding: ViewholderSearchResultItemBinding,
        val searchResultClickListener: (SearchResultEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: SearchResultEntity) = with(binding) {
            textTxt.text = data.name
            subTxt.text = data.fullAddress
        }

        fun bindViews(data: SearchResultEntity) {
            binding.root.setOnClickListener {
                searchResultClickListener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultItemViewHolder {
        val view = ViewholderSearchResultItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchResultItemViewHolder(view, searchResultClickListener)

    }

    override fun onBindViewHolder(holder: SearchResultItemViewHolder, position: Int) {
        holder.bindData(searchResultList[position])
        holder.bindViews(searchResultList[position])
    }

    override fun getItemCount(): Int = searchResultList.size

    fun setSearchResultList(searchResultList: List<SearchResultEntity>, searchResultClickListener: (SearchResultEntity) -> Unit) {
        this.searchResultList = searchResultList
        this.searchResultClickListener = searchResultClickListener
        notifyDataSetChanged()
    }
}