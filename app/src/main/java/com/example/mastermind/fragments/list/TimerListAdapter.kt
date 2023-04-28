package com.example.mastermind.fragments.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mastermind.R
import com.example.mastermind.model.Timer

class TimerListAdapter : RecyclerView.Adapter<TimerListAdapter.MyViewHolder>() {

    private var timers = emptyList<Timer>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val tvTimerTitle : TextView = itemView.findViewById(R.id.tvTimerTitle)
        val tvTimerDate : TextView = itemView.findViewById(R.id.tvTimerDate)
        val tvTimerStartTime : TextView = itemView.findViewById(R.id.tvTimerStartTime)
        val tvTimerEndTime : TextView = itemView.findViewById(R.id.tvTimerEndTime)
        val rowTimer : View? = itemView.findViewById(R.id.rowTimer)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.timer_row,parent,false))
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentTimer = timers[position]

        holder.tvTimerTitle.text = currentTimer.timerTitle
        holder.tvTimerDate.text = currentTimer.timerDate
        holder.tvTimerStartTime.text = currentTimer.timerStart
        holder.tvTimerEndTime.text = currentTimer.timerEnd

        holder.rowTimer?.setOnClickListener{
//            val action = ListCourseFragmentDirections.actionFromListCourseToListTimer(currentTimer)
//            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount() = timers.size

    fun setData(timers : List<Timer>){
        this.timers = timers
        notifyDataSetChanged()
    }
}