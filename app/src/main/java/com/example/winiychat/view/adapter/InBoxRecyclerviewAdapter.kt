package com.example.winiychat.view.adapter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chad.library.adapter4.BaseQuickAdapter
import com.example.winiychat.R

import com.example.winiychat.databinding.ItemRecyclerviewMessageBinding
import com.example.winiychat.model.bean.InBoxRecyclerviewBean
import com.example.winiychat.extension_utils.ExpandableCardBackgroundDrawable
import com.example.winiychat.view.adapter.InBoxRecyclerviewAdapter.MyViewHolder


class InBoxRecyclerviewAdapter() : BaseQuickAdapter<InBoxRecyclerviewBean, MyViewHolder>() {
    override fun onBindViewHolder(
        holder: MyViewHolder, position: Int, item: InBoxRecyclerviewBean?
    ) {
        holder.bind(item!!)
    }

    override fun onCreateViewHolder(
        context: Context, parent: ViewGroup, viewType: Int
    ): MyViewHolder {
        val binding = ItemRecyclerviewMessageBinding.bind(
            LayoutInflater.from(context).inflate(R.layout.item_recyclerview_message, parent, false)
        )
        return MyViewHolder(binding)
    }


    class MyViewHolder(val _binding: ItemRecyclerviewMessageBinding) : ViewHolder(_binding.root) {
        private fun getAnimation(view: View): AnimatorSet {
            val xAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.5f)
            val yAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.5f)
            val xAnimator_ = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1f)
            val yAnimator_ = ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1f)
            val animatorSet = AnimatorSet()
            val animatorSet_ = AnimatorSet()
            val animatorSet__ = AnimatorSet()
            animatorSet.playTogether(xAnimator, yAnimator)
            animatorSet_.playTogether(xAnimator_, yAnimator_)
            animatorSet__.playSequentially(animatorSet, animatorSet_)
            animatorSet__.duration = 200  // 设置动画持续时间
            animatorSet__.interpolator = AccelerateDecelerateInterpolator()
            return animatorSet__
        }

        fun bind(data: InBoxRecyclerviewBean) {
            _binding.textViewContent.text = data.content
            _binding.textViewTime.text = data.time
            _binding.textViewSubject.text = data.subject
            _binding.textViewName.text = data.name
            _binding.checkBoxCollect.isChecked = data.isCollected
            //初始化动画
            val buttonSet = getAnimation(_binding.checkBoxCollect)
            val avatar = getAnimation(_binding.imageViewInBox)
            //设置Button
            var isCollect = false
            _binding.checkBoxCollect.setOnClickListener {
                if (isCollect) {
                    _binding.checkBoxCollect.setIconResource(R.drawable.ic_star_fill)
                    buttonSet.reverse()
                } else {
                    _binding.checkBoxCollect.setIconResource(R.drawable.ic_star)
                    buttonSet.start()
                }
                isCollect = !isCollect
            }
            //初始化Drawable
            var backgroundDrawable: ExpandableCardBackgroundDrawable =
                ExpandableCardBackgroundDrawable(
                    Color.parseColor("#00FFFFFF"),
                    Color.parseColor("#99D6FF"),
                    0.2f,
                    initialDrawColor = Color.parseColor("#D3D3D3"),
                )
            _binding.constraintLayoutInboxRecyclerviewItem.background = backgroundDrawable
            //是否阅读
            if (!data.isRead) {
                backgroundDrawable.setInitialDrawColor(Color.parseColor("#BFEFFF"))
            }
            //是否选中
            var isEditor = false
            val animator = ValueAnimator.ofFloat(0f, 1f)
            animator.apply {
                duration = 200
                interpolator = DecelerateInterpolator()
                addUpdateListener { animation ->
                    val progress = animation.animatedValue as Float
                    backgroundDrawable.setProgress(progress)
                }
            }

            _binding.imageViewInBox.setOnClickListener {
                if (isEditor) {
                    _binding.imageViewInBox.setImageResource(R.drawable.avatar)
                    avatar.reverse()
                    animator.reverse()
                } else {
                    _binding.imageViewInBox.setImageResource(R.drawable.selected)
                    avatar.start()
                    animator.start()
                }
                isEditor = !isEditor
            }
        }
    }
}