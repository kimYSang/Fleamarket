package com.example.skuniv.fleamarket2.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.skuniv.fleamarket2.R;
import com.example.skuniv.fleamarket2.model.AdminSellerModel.UserModel;
import com.example.skuniv.fleamarket2.view.categoryView.CategoryActivity;
import com.example.skuniv.fleamarket2.databinding.ActivityMainBinding;
import com.example.skuniv.fleamarket2.view.locationView.LocationActivity;
import com.example.skuniv.fleamarket2.view.noticeView.NoticeActivity;
import com.example.skuniv.fleamarket2.view.sellerView.SellerGoodsList;
import com.example.skuniv.fleamarket2.view.sellerView.SignInDialog;
import com.example.skuniv.fleamarket2.viewModel.AdminSellerViewModel.MainCommand;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private final int MY_PERMISSIONS_REQUEST = 100;
    UserModel userModel;
    Context context;
    public Drawer result;
    MainCommand mainCommand;
    SharedPreferences loginSetting;

    public PrimaryDrawerItem signInItem = new PrimaryDrawerItem().withIdentifier(1).withName("로그인").withIcon(R.drawable.material_drawer_circle_mask).withIconTintingEnabled(true);
    public PrimaryDrawerItem currentApplyItem = new PrimaryDrawerItem().withIdentifier(2).withName("현재 신청 내역").withIcon(R.drawable.material_drawer_circle_mask).withIconTintingEnabled(true);
    public PrimaryDrawerItem goodsListItem = new PrimaryDrawerItem().withIdentifier(3).withName("상품조회").withIcon(R.drawable.material_drawer_circle_mask).withIconTintingEnabled(true);
    public PrimaryDrawerItem applyItem = new PrimaryDrawerItem().withIdentifier(4).withName("신청하기").withIcon(R.drawable.material_drawer_circle_mask).withIconTintingEnabled(true);
    public PrimaryDrawerItem signout = new PrimaryDrawerItem().withIdentifier(5).withName("로그아웃").withIcon(R.drawable.material_drawer_circle_mask).withIconTintingEnabled(true);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        userModel = new UserModel();
        mainCommand = new MainCommand(context, userModel,this);

        loginSetting = context.getSharedPreferences("loginSetting", MODE_PRIVATE);


        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(binding.toolbar)
                .addDrawerItems(
                        signInItem
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if(drawerItem == signInItem){ // 로그인
                            SignInDialog signInDialog = new SignInDialog(context, mainCommand);
                            signInDialog.show();
                        } else if(drawerItem == currentApplyItem){ // 현재 신청한 판매자 목록
                            mainCommand.moveCurrentApply();
                        }else if(drawerItem == goodsListItem){  // 상품 조회
                            mainCommand.moveGoodsList();
                        }else if(drawerItem == applyItem){  // 신청서 작성

                        }else if(drawerItem == signout){  // 로그아웃
                            mainCommand.signOut();
                        }
                        return true;
                    }
                })
                .build();

        if(!loginSetting.getString("id","").equals("")){
            mainCommand.autoLogin();
        }


        binding.locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LocationActivity.class);
                startActivity(intent);
            }
        });

        binding.categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        binding.noticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NoticeActivity.class);
                startActivity(intent);
            }
        });

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST);
        }
    }
}