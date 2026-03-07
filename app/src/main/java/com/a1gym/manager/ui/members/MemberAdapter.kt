package com.a1gym.manager.ui.members

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.a1gym.manager.data.entity.Member
import com.a1gym.manager.databinding.ItemMemberBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.bumptech.glide.Glide

class MemberAdapter(private val onItemClick: (Member) -> Unit) :
    ListAdapter<Member, MemberAdapter.MemberViewHolder>(MemberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MemberViewHolder(
        private val binding: ItemMemberBinding,
        private val onItemClick: (Member) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        fun bind(member: Member) {
            binding.tvMemberName.text = member.name
            binding.tvMemberPhone.text = member.phone
            binding.tvExpiryDate.text = "Expires: ${dateFormat.format(Date(member.endDate))}"
            binding.tvStatus.text = member.status
            
            if (!member.photoUri.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load(member.photoUri)
                    .circleCrop()
                    .placeholder(android.R.drawable.ic_menu_camera)
                    .into(binding.ivMemberPhoto)
            } else {
                binding.ivMemberPhoto.setImageResource(android.R.drawable.ic_menu_camera)
            }
            
            val statusColor = when (member.status) {
                "Active" -> Color.parseColor("#4CAF50")
                "Expired" -> Color.parseColor("#F44336")
                "Expiring Soon" -> Color.parseColor("#FF9800")
                else -> Color.GRAY
            }
            
            val background = binding.tvStatus.background as? GradientDrawable
            if (background != null) {
                background.setColor(statusColor)
            } else {
                val newBg = GradientDrawable()
                newBg.setColor(statusColor)
                newBg.cornerRadius = 16f
                binding.tvStatus.background = newBg
            }

            binding.root.setOnClickListener {
                onItemClick(member)
            }
        }
    }
}

class MemberDiffCallback : DiffUtil.ItemCallback<Member>() {
    override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem == newItem
    }
}
