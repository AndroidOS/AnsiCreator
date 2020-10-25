package com.manuelcarvalho.ansicreator.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.manuelcarvalho.ansicreator.R
import com.manuelcarvalho.ansicreator.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import java.io.File


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {


    private val STORAGE_PERMISSION_CODE = 101
    private val CAMERA_PERMISSION_CODE = 105
    private val PHOTO_PERMISSION_CODE = 106

    private val filepath = "MyFileStorage"
    internal var myExternalFile: File? = null

    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = ViewModelProviders.of(this)[AppViewModel::class.java]
        viewModel.seekBarValue.value = 1

        checkPermission(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            STORAGE_PERMISSION_CODE
        )

        checkPermission(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            STORAGE_PERMISSION_CODE
        )



        observeViewModel()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_camera -> {
                capturePhoto()
                return true
            }
            R.id.action_image -> {
                captureImage()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_PERMISSION_CODE && data != null) {
            val newPhoto = (data.extras?.get("data") as Bitmap)
            imageView.setImageBitmap(newPhoto)
            viewModel.decodeBitmap(newPhoto)

        }

        if (resultCode == Activity.RESULT_OK && requestCode == PHOTO_PERMISSION_CODE && data != null) {

            val imageUri = data.data
            val bitmap =
                MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
            imageView.setImageBitmap(bitmap)
        }
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this@MainActivity, permission)
            == PackageManager.PERMISSION_DENIED
        ) {

            // Requesting the permission
            ActivityCompat.requestPermissions(
                this@MainActivity, arrayOf(permission),
                requestCode
            )
        } else {
            Toast.makeText(
                this@MainActivity,
                "Permission already granted",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    private fun capturePhoto() {

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_PERMISSION_CODE)
    }

    private fun captureImage() {
        Log.d(TAG, "captureImage fired")
        val icon = BitmapFactory.decodeResource(
            this.resources,
            R.drawable.bart
        )
        imageView.setImageBitmap(icon)
        viewModel.decodeBitmap2(icon)
    }

    private fun observeViewModel() {
        Log.d(TAG, "ObserveViewModel started")
        viewModel.imageArray.observe(this, Observer { image ->
            image?.let {

                Log.d(TAG, "observeViewModel fired")
            }
        })

        viewModel.seekBarValue.observe(this, Observer { progress ->
            progress?.let {

                Log.d(TAG, "Progress changed ${it}")
            }
        })

    }
}