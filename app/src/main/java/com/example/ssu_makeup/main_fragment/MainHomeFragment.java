package com.example.ssu_makeup.main_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ssu_makeup.GridProductAdaptor;
import com.example.ssu_makeup.Product;
import com.example.ssu_makeup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainHomeFragment extends Fragment {
    ArrayList<Product> productRecommendArrayList;
    TextView recommendationMessage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_home, container, false);

        FirebaseAuth mfirebase = FirebaseAuth.getInstance();
        String uid = mfirebase.getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

        databaseReference.child(uid).child("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(uid).child("lastName").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                        databaseReference.child(uid).child("skinType").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot3) {
                                String firstName = snapshot.getValue(String.class);
                                String lastName = snapshot2.getValue(String.class);
                                String skinType = snapshot3.getValue(String.class);
                                recommendationMessage = root.findViewById(R.id.recommendation_message);
                                recommendationMessage.setText(getString(R.string.recommendation_message, skinType, lastName, firstName));
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        productRecommendArrayList= new ArrayList<>(); //이 배열은 네개만 추천하는 걸로
        //while here
        productRecommendArrayList.add(new Product("닥터지", "https://image.oliveyoung.co.kr/uploads/images/goods/400/10/0000/0014/A00000014557811ko.jpg?l=ko", "닥터지 레드 블레미쉬 포 맨 올인원 플루이드 (본품150ml+30ml 추가 증정)", "정제수, 나이아신아마이드, 아이소도데케인, 카프릴릴메티콘, 메틸프로판다이올, 1,2-헥산다이올, 다이프로필렌글라이콜, 프로판다이올, 글리세린, 카프릴릭/카프릭트라이글리세라이드, 하이드로제네이티드폴리(C6-14올레핀), 다이메티콘/비닐다이메티콘크로스폴리머, 폴리글리세릴-2스테아레이트, C14-22알코올, C12-16알코올, 글리세릴스테아레이트, 스테아릴알코올, 부틸렌글라이콜, 아크릴레이트/C10-30알킬아크릴레이트크로스폴리머, 암모늄아크릴로일다이메틸타우레이트/브이피코폴리머, C12-20알킬글루코사이드, 소듐폴리아크릴로일다이메틸타우레이트, 하이드로제네이티드레시틴, 팔미틱애씨드, 소듐하이알루로네이트, 아데노신, 트로메타민, 병풀추출물, 호장근뿌리추출물, 잔탄검, 황금추출물, 다이소듐이디티에이, 녹차추출물, 스페인감초뿌리추출물, 마트리카리아꽃추출물, 로즈마리잎추출물, 글루코오스, 글루코노락톤, 세라마이드엔피, 티트리잎오일, 말토덱스트린, 마데카소사이드, 사카라이드하이드롤리세이트, 아보카도열매추출물, 아시아티코사이드, 아시아틱애씨드, 마데카식애씨드, 다시마추출물, 당느릅나무뿌리추출물, 알로에베라잎추출물, 참마뿌리추출물, 제비꽃꽃추추물"));
        productRecommendArrayList.add(new Product("test", "", "test", "test"));
        productRecommendArrayList.add(new Product("test", "", "test", "test"));
        productRecommendArrayList.add(new Product("test", "", "test", "test"));
        //end while

        RecyclerView recyclerView = root.findViewById(R.id.main_product_recommend_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        GridProductAdaptor girdProductAdaptor = new GridProductAdaptor(productRecommendArrayList, requireContext());
        girdProductAdaptor.setOnItemClickListener(product -> {
            MainProductFragment mainItemFragment = new MainProductFragment();
            Bundle bundle = new Bundle(1);
            bundle.putSerializable("selected_product", product);
            mainItemFragment.setArguments(bundle);
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.home_recommend_item_container, mainItemFragment)
                    .addToBackStack(null)
                    .commit();
        });
        recyclerView.setAdapter(girdProductAdaptor);
        return root;
    }
}