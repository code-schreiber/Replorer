package com.toolslab.replorer.view.main

import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toolslab.replorer.R
import com.toolslab.replorer.base_repository.model.RepositoryViewModel
import kotlinx.android.synthetic.main.item_repository.view.*
import java.text.SimpleDateFormat
import java.util.*

internal class RepositoriesAdapter(private val viewModels: List<RepositoryViewModel>)
    : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    @VisibleForTesting
    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val name = view.item_repository_name
        internal val description = view.item_repository_description
        internal val updatedAt = view.item_repository_updated_at
        internal val language = view.item_repository_language
        internal val license = view.item_repository_license
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val viewModel = viewModels[position]
        viewHolder.apply {
            name.text = viewModel.name
            description.text = viewModel.description
            updatedAt.text = extractRelativeTimeSpan(viewModel.updatedAt)
            language.text = viewModel.language
            license.text = viewModel.license
        }
    }

    override fun getItemCount() = viewModels.size

    @VisibleForTesting
    internal fun extractRelativeTimeSpan(date: String): String {
        if (date.isEmpty()) return ""
        return try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val time = simpleDateFormat.parse(date).time
            DateUtils.getRelativeTimeSpanString(time) as String
        } catch (e: Exception) {
            ""
        }
    }

}
