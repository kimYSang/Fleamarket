package com.example.skuniv.fleamarket2.view.categoryView;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.skuniv.fleamarket2.adapter.CategoryListAdapter;
import com.example.skuniv.fleamarket2.R;
import com.example.skuniv.fleamarket2.databinding.ActivityCategoryListBinding;
import com.example.skuniv.fleamarket2.databinding.CategoryItemBinding;
import com.example.skuniv.fleamarket2.model.categoryModel.CategoryModel;
import com.example.skuniv.fleamarket2.viewModel.categoryViewmodel.CategoryCommand;
import com.example.skuniv.fleamarket2.viewModel.categoryViewmodel.CategoryShopViewModel;
import com.example.skuniv.fleamarket2.viewModel.categoryViewmodel.CategoryShopsViewModel;


public class CategoryListActivity extends AppCompatActivity implements CategoryListAdapter.OnLoadMoreListener{
    static ActivityCategoryListBinding categoryListBinding;
    CategoryModel categoryModel;
    static Context context;
    static CategoryShopsViewModel categoryShopsViewModel;
    static CategoryCommand categoryCommand;
    CategoryItemBinding categoryItemBinding;
    static CategoryListAdapter adapter;
    static LinearLayoutManager llm;
    static CategoryListAdapter.OnLoadMoreListener onLoadMoreListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryListBinding = DataBindingUtil.setContentView(this, R.layout.activity_category_list);

        /*categoryItemBinding = (CategoryItemBinding)
                DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.category_item, null, false);*/
        categoryModel = new CategoryModel(getIntent().getStringExtra("category"),1);

        categoryShopsViewModel = new CategoryShopsViewModel();
        // 카테고리모델 셋팅
        categoryListBinding.setCategoryModel(categoryModel);
        categoryListBinding.setShopsList(categoryShopsViewModel.getShops());

        categoryCommand = new CategoryCommand(this, categoryListBinding, categoryModel, categoryShopsViewModel);

        onLoadMoreListener = this;

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        //Log.i("getAdapter","============");

        categoryListBinding.recyclerId2.setLayoutManager(llm);

        /*categoryItemBinding.mapId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
               });*/
        //categoryCommand.getGoodsList();
        categoryCommand.jsonPaser(getJson(categoryModel.getPageNum()));
    }

    @Override
    public void onLoadMore() {
        Log.d("MainActivity_", "onLoadMore");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ///////이부분에을 자신의 프로젝트에 맞게 설정하면 됨
                //다음 페이지? 내용을 불러오는 부분
                //categoryCommand.scrollListener();
                categoryModel.setPageNum(categoryModel.getPageNum()+1);
                categoryCommand.jsonPaser(getJson(categoryModel.getPageNum()));
                adapter.setMoreLoading(false);
                //////////////////////////////////////////////////
                //mAdapter.setMoreLoading(false);
            }
        }, 2000);
        /*categoryModel.setPageNum(categoryModel.getPageNum()+1);
        categoryCommand.jsonPaser(getJson(categoryModel.getPageNum()));
        adapter.setMoreLoading(false);*/
    }

    //listView adapter 생성
    @BindingAdapter("app:items")
    public static void setShopList(RecyclerView recyclerView, ObservableArrayList<CategoryShopViewModel> shops){
        //adapter 없을 때 adapter 생성
        System.out.println("setShopList=============");
        if(recyclerView.getAdapter() == null){
            System.out.println("new categoryListAdapter");
            adapter = new CategoryListAdapter(shops, context,categoryCommand,onLoadMoreListener);
            adapter.setLinearLayoutManager(llm);
            recyclerView.setAdapter(adapter);
            adapter.setRecyclerView(categoryListBinding.recyclerId2);
        }
        else {
            // 있으면 getAdapter
            adapter = (CategoryListAdapter) recyclerView.getAdapter();
            adapter.setShopsList(categoryShopsViewModel.getShops());
            System.out.println("get adapter");
        }
        //adapter.addAll(shops);
        //Log.i("adapter", shops.get(0).getGoods().get(0).getImage());
    }

    public String getJson(int page){
        String json="";
        System.out.println("getJson page======"+ page);
        if(page == 1) {
            json = "[\n" +
                    "{\n" +
                    "\"no\": 1,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A01\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 2,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A02\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 3,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A03\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 4,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A04\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 5,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A05\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 6,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A06\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 7,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A07\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 8,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A08\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 9,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A09\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 10,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A10\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 11,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A11\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 12,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A12\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "}\n" +
                    "]";
        }
        else if(page == 2){
            json = "[\n" +
                    "{\n" +
                    "\"no\": 13,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A11\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 14,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A12\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 15,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A13\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 16,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A14\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 17,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A15\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 18,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A16\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 19,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A17\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 20,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A18\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 21,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A19\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 22,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A20\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "}\n" +
                    "]";
        }
        else if(page ==3){
            json ="[\n" +
                    "{\n" +
                    "\"no\": 23,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A31\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 24,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A32\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 25,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A33\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 26,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A34\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 27,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A35\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 28,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A36\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 29,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A37\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 30,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A38\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 31,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A39\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 32,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A40\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "}\n" +
                    "]";
        }else if(page ==4){
            json ="[\n" +
                    "{\n" +
                    "\"no\": 33,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A31\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 34,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A32\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 35,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A33\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 36,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A34\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 37,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A35\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 38,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A36\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 39,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A37\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 40,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A38\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 41,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A39\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "},\n" +
                    "{\n" +
                    "\"no\": 42,\n" +
                    "\"location\": \"a\",\n" +
                    "\"shop\": \"A40\",\n" +
                    "\"good\": {\n" +
                    "\"name\": \"polo 카라티\",\n" +
                    "\"price\": 5000,\n" +
                    "\"quantity\": 2,\n" +
                    "\"category\": \"1\",\n" +
                    "\"image\": \"http://13.125.128.130/static/bazaar_img/a/1-1.jpg\"\n" +
                    "}\n" +
                    "}\n" +
                    "]";
        } else{
            System.out.println("마지막 페이지 입니다.");
        }

        return json;

    }
}
