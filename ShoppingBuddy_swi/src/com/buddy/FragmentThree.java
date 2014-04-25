package com.buddy;
 
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
 
public class FragmentThree extends Fragment {
 
    
      TextView tvItemName;
 
      
      public static final String ITEM_NAME = "itemName";
 
      public FragmentThree() {
 
      }
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
 
            View view = inflater.inflate(R.layout.fragment_layout_three, container,
                        false);
 
           
            tvItemName = (TextView) view.findViewById(R.id.frag3_text);
 
            tvItemName.setText(getArguments().getString(ITEM_NAME));
            
            return view;
      }
 
}