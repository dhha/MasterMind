package com.example.mastermind.ui.adapter

import android.annotation.SuppressLint
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mastermind.R
import com.example.mastermind.ui.Note.NoteFragmentRecyclerDirections
import com.example.mastermind.ui.model.Notes



class NoteAdapter:RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {
    private var notes = emptyList<Notes>()

    class MyViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val title: TextView = itemView.findViewById(R.id.text_view_1)
        val desc : TextView = itemView.findViewById(R.id.text_view_2)
        val date : TextView= itemView.findViewById(R.id.text_view_3)
        val rowlayout: View? = itemview.findViewById(R.id.rowLayout)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.MyViewHolder {
      return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_noterow,parent,false))
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: NoteAdapter.MyViewHolder, position: Int) {
        val noteList = notes[position]



        holder.title.text=noteList.title
        holder.desc.text=noteList.desc
        holder.date.text=noteList.date.toString()
        holder.rowlayout?.setOnClickListener {
         val action = NoteFragmentRecyclerDirections.actionNoteFragmentRecyclerToUpdateFragment(noteList)


            holder.itemView.findNavController().navigate(action)
        }

        }


    override fun getItemCount(): Int {
        return notes.size
    }

    fun setData(data:List<Notes>){
        this.notes = data
        notifyDataSetChanged()
    }
}