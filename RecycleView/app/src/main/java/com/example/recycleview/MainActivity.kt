package com.example.recycleview

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.ui.theme.RecycleViewTheme

class MainActivity : ComponentActivity(), MyAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>


    var values= arrayListOf<String>("item1", "item2" , "item3", "item4", "item5")
    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked",
            Toast.LENGTH_SHORT).show()
        values[position]="Clicked"
        myAdapter.notifyItemChanged(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activty_main)
        manager = LinearLayoutManager(this)
        myAdapter = MyAdapter(values,this)

        // myAdapter = MyAdapter(values,this)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = manager
            adapter = myAdapter
        }
        val btn=findViewById<Button>(R.id.buttonadd)

        // Vous pouvez maintenant configurer des actions sur le bouton, par exemple, ajouter un écouteur de clic
        btn.setOnClickListener {
            var n:Int = values.size + 1
            values.add("item$n") //Ajout dans la source de données
            myAdapter.notifyItemInserted(values.size)//Rafraichissement de l’adapter

        }
    }
    private fun getGouvernorats(): List<Gouvernorat> {
        // Remplacez ces données factices par les données réelles des gouvernorats avec des images
        val gouvernorats = mutableListOf<Gouvernorat>()
        gouvernorats.add(Gouvernorat("Tunis", R.drawable.ic_launcher_background))
        // Ajoutez les autres gouvernorats de la même manière

        return gouvernorats
    }
}

