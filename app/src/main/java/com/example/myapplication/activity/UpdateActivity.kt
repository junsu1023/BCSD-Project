package com.example.myapplication.activity

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityUpdateItemBinding
import com.example.myapplication.util.OpenSettings
import com.example.myapplication.viewmodel.ImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateActivity: AppCompatActivity() {
    private lateinit var binding: ActivityUpdateItemBinding
    private val imageViewModel: ImageViewModel by viewModel()

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            when(isGranted) {
                true -> loadImage()
                else -> {
                    when(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        true -> permissionDialog(true)
                        else -> permissionDialog(false)
                    }
                }
            }
        }

    private val openSettings =
        registerForActivityResult(OpenSettings()) {
            checkPermission()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_item)

        binding.editEquipmentImageView.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                checkPermission()
            }
            else loadImage()
        }

        binding.editAddItemButton.setOnClickListener {

        }

        binding.imageViewModel = imageViewModel
        binding.lifecycleOwner = this
    }

    private fun loadImage() {
        imageViewModel.loadImage()
    }

    private fun checkPermission() {
        requestPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun permissionDialog(isDeniedOnce: Boolean) {
        val builder = AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.permission_dialog_title))
            setMessage(getString(R.string.permission_dialog_message))
            setPositiveButton(getString(R.string.permission_ok)) { _, _ ->
                if(isDeniedOnce) checkPermission()
                else openSettings.launch(null)
            }
            setNegativeButton(getString(R.string.permission_denied)) { dialog, _ ->
                dialog.dismiss()
                finish()
            }
        }
        builder.show()
    }
}