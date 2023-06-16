package com.example.notestakingapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ZoomControls
import com.example.notestakingapp.GlobalData
import com.example.notestakingapp.R

class DisplayImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_display_image, container, false)
        val args= DisplayImageFragmentArgs.fromBundle(requireArguments())
        val imageView=view.findViewById<ImageView>(R.id.imgDisplay)
        imageView.setImageURI(GlobalData.imgList[args.i][args.j])
        val zoomControls = view.findViewById<ZoomControls>(R.id.zoomControls)
        var currentZoom = 1.0f // Initial zoom level
        val maxZoom = 3.0f // Maximum zoom level

        zoomControls.setOnZoomInClickListener {
            if (currentZoom < maxZoom) {
                currentZoom += 0.1f
                imageView.scaleX = currentZoom
                imageView.scaleY = currentZoom
            }
        }

        zoomControls.setOnZoomOutClickListener {
            if (currentZoom > 1.0f) {
                currentZoom -= 0.1f
                imageView.scaleX = currentZoom
                imageView.scaleY = currentZoom
            }
        }

        return view
    }


}