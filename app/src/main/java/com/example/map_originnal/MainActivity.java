package com.example.map_originnal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener , MainCallbacks{

    BottomNavigationView bottomNavigationView;
    BottomSheetDialog bottomSheetDialogListFamily;
    BottomSheetDialog bottomSheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.map);
    }


    User user_activity = new User();
    Map map_activity = new Map();
    Home home_activity = new Home();
    Family family_activity = new Family();
//    Profile profile = new Profile();
    ListFamilys listFamilys = new ListFamilys();
    HideMap hideMap = new HideMap();
    DanhSachBroad danhSachBroad = new DanhSachBroad();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.person:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, danhSachBroad).commit();
                return true;

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, home_activity).commit();
                return true;

            case R.id.family:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragmentMenu, listFamilys).commit();
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, family_activity).commit();
//                bottomNavigationView.setVisibility(View.INVISIBLE);
                chuyenSangListFamilys();


                return true;

            case R.id.map:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, map_activity).commit();
                return true;
        }
        return false;
    }

    private void chuyenSangListFamilys() {

        ArrayList<MemberFamily> objects;
        RecyclerView lvFamilys;

        bottomSheetDialogListFamily = new BottomSheetDialog(
                MainActivity.this
        );
        View bottomSheetView  = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.list_family,
                (LinearLayout) findViewById(R.id.bottomSheetContainerFamily)
        );



        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this , LinearLayoutManager.HORIZONTAL, false);

        objects= giaLapDuLieu();
        lvFamilys = bottomSheetView.findViewById(R.id.lvFamily);
        lvFamilys.setLayoutManager(layoutManager);
        AdapterListFamily adapterListFamily = new AdapterListFamily(MainActivity.this, objects);
        lvFamilys.setAdapter(adapterListFamily);

        bottomSheetDialogListFamily.setContentView(bottomSheetView);
        bottomSheetDialogListFamily.show();

//        lvFamilys
    }

    private ArrayList<MemberFamily> giaLapDuLieu() {
        ArrayList<MemberFamily> a = new ArrayList<>();

        a.add(new MemberFamily("Son","http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg"));
        a.add(new MemberFamily("Anh","http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg"));
        a.add(new MemberFamily("Vu","http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg"));
        a.add(new MemberFamily("Viet","http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg"));
        a.add(new MemberFamily("Son","http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg"));
        a.add(new MemberFamily("Anh","http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg"));
        a.add(new MemberFamily("Vu","http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg"));
        a.add(new MemberFamily("Viet","http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg"));
        a.add(new MemberFamily("Son","http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg"));
        a.add(new MemberFamily("Anh","http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg"));
        a.add(new MemberFamily("Vu","http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg"));
        a.add(new MemberFamily("Viet","http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg"));

        return a;
    }

    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
        if (sender.equals("Profile-Frag") && strValue.equals("Back")){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, user_activity).commit();
        }
        if (sender.equals("User-Frag") && strValue.equals("Profile")){
            chuyenSangProflie();
//            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profile).commit();
        }
        if (sender.equals("Map-Frag") && strValue.equals("ShowMap")){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, hideMap).commit();
        }
        if (sender.equals("List-Family") && strValue.equals("Back")){
            bottomSheetDialogListFamily.hide();
        }
    }

    private void chuyenSangProflie() {
        bottomSheetDialog = new BottomSheetDialog(
                MainActivity.this,R.style.BottomSheetDialogTheme
        );
        View bottomSheetView  = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.fragment_profile,
                (LinearLayout) findViewById(R.id.bottomSheetContainer)
        );
        bottomSheetView.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Share",Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}