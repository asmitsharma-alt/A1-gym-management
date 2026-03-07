package com.a1gym.manager.ui.plans

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.a1gym.manager.data.entity.Plan
import com.a1gym.manager.databinding.ItemPlanBinding

class PlanAdapter(private val onPlanClick: (Plan) -> Unit) :
    ListAdapter<Plan, PlanAdapter.PlanViewHolder>(PlanDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val binding = ItemPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanViewHolder(binding, onPlanClick)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PlanViewHolder(
        private val binding: ItemPlanBinding,
        private val onPlanClick: (Plan) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(plan: Plan) {
            binding.tvPlanName.text = plan.planName
            binding.tvDuration.text = "Duration: ${plan.durationDays} Days"
            binding.tvPrice.text = "₹${plan.price}"
            
            binding.root.setOnClickListener {
                onPlanClick(plan)
            }
        }
    }
}

class PlanDiffCallback : DiffUtil.ItemCallback<Plan>() {
    override fun areItemsTheSame(oldItem: Plan, newItem: Plan): Boolean {
        return oldItem.planId == newItem.planId
    }

    override fun areContentsTheSame(oldItem: Plan, newItem: Plan): Boolean {
        return oldItem == newItem
    }
}
