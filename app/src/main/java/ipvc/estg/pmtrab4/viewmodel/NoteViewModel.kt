package ipvc.estg.pmtrab4.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ipvc.estg.pmtrab4.database.NoteDB
import ipvc.estg.pmtrab4.entity.Note
import ipvc.estg.pmtrab4.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application){
    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>
    init {
        val notesDao = NoteDB.getDatabase(application, viewModelScope).noteDao()
        repository = NoteRepository(notesDao)
        allNotes = repository.allNotes
    }
    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
    fun deleteAll()= viewModelScope.launch(Dispatchers.IO){
        repository.deleteAll()
    }
    fun updateNote(note: Note) = viewModelScope.launch{
        repository.updateNote(note)
    }
    fun deleteNote(note: Note) = viewModelScope.launch{
        repository.deleteNote(note)
    }


}