package com.example.mastermind.ui.adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mastermind.R
import com.example.mastermind.ui.Note.NoteFragmentDelRecyclerDirections
import com.example.mastermind.ui.model.Notes



class NoteAdapterDel : RecyclerView.Adapter<NoteAdapterDel.DelViewHolder>(){

    private var notes1 = emptyList<Notes>()

    inner class DelViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val title: TextView = itemView.findViewById(R.id.text_view_1)
        val desc : TextView = itemView.findViewById(R.id.text_view_2)
        val date : TextView = itemView.findViewById(R.id.text_view_3)

        val rowdelayout: View? = itemview.findViewById(R.id.rowdelayout)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapterDel.DelViewHolder {
        return DelViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_noterowdel, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DelViewHolder, position: Int) {
        val noteList = notes1[position]

        holder.title.text=noteList.title
        holder.desc.text=noteList.desc
        holder.date.text=noteList.date.toString()


        holder.rowdelayout!!.setOnClickListener {
            val action = NoteFragmentDelRecyclerDirections.actionNoteFragmentDelRecyclerToNavNote()
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
      return notes1.size
    }

    fun setData(data:List<Notes>){
        this.notes1 = data
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        notes1 = notes1.filterIndexed{ index, _ -> index!=position }
        notifyItemRemoved(position)
    }
    fun getData(): List<Notes> = notes1
}