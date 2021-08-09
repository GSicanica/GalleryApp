package com.jabukaprog.family.ui

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.family.R
import pub.devrel.easypermissions.EasyPermissions

class PermissionCheckFragment : Fragment()  {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
            }
        }
    }

    companion object {
        private const val STORAGE_PERMISSION_CODE = 101
    }

    override fun onResume() {
        super.onResume()
        checkStoragePermission()
    }

    private fun checkStoragePermission() {
        context?.let {
            if (EasyPermissions.hasPermissions(it, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                findNavController().navigate(R.id.action_permissionFragment_to_baseFragment)
            } else {
                EasyPermissions.requestPermissions(
                    this, getString(R.string.need_access_for_storage),
                    STORAGE_PERMISSION_CODE, Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

}