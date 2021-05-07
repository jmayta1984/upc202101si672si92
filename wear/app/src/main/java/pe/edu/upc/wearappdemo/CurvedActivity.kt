package pe.edu.upc.wearappdemo

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import androidx.wear.widget.WearableLinearLayoutManager
import pe.edu.upc.wearappdemo.databinding.CurvedLayoutBinding

class CurvedActivity : WearableActivity() {

    private lateinit var binding: CurvedLayoutBinding
    private var menuItems = ArrayList<MenuItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadMenuItems()

        binding = CurvedLayoutBinding.inflate(layoutInflater)
        //setContentView(R.layout.curved_layout)

        binding.wrLayout.apply {
            isEdgeItemsCenteringEnabled = true

            adapter = MenuAdapter(menuItems, this@CurvedActivity)
            layoutManager = WearableLinearLayoutManager(this@CurvedActivity)
        }


        // Enables Always-on
        setAmbientEnabled()
    }

    private fun loadMenuItems() {
        menuItems.add(MenuItem("Add", android.R.drawable.ic_menu_add))
        menuItems.add(MenuItem("Camera", android.R.drawable.ic_menu_camera))
        menuItems.add(MenuItem("Gallery", android.R.drawable.ic_menu_gallery))

    }
}