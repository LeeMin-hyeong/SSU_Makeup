package com.example.ssu_makeup.main_fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ssu_makeup.Product;
import com.example.ssu_makeup.LinearProductAdaptor;
import com.example.ssu_makeup.R;

import java.util.ArrayList;

public class MainSearchResultFragment extends Fragment {
    String searchingItem;
    ArrayList<Product> productArrayList;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            FragmentManager fragmentManager = getChildFragmentManager();
            fragmentManager.popBackStack();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_search_result, container, false);

        //TODO: Firebase DB 에서 searchingItem 에 대한 결과 받아서 productArrayList 에 넣기
        assert getArguments() != null;
        searchingItem = getArguments().getString("searching_item");

        productArrayList= new ArrayList<>();
        //while here
        productArrayList.add(new Product("닥터지", "https://image.oliveyoung.co.kr/uploads/images/goods/400/10/0000/0014/A00000014557811ko.jpg?l=ko", "닥터지 레드 블레미쉬 포 맨 올인원 플루이드 (본품150ml+30ml 추가 증정)", "정제수, 나이아신아마이드, 아이소도데케인, 카프릴릴메티콘, 메틸프로판다이올, 1,2-헥산다이올, 다이프로필렌글라이콜, 프로판다이올, 글리세린, 카프릴릭/카프릭트라이글리세라이드, 하이드로제네이티드폴리(C6-14올레핀), 다이메티콘/비닐다이메티콘크로스폴리머, 폴리글리세릴-2스테아레이트, C14-22알코올, C12-16알코올, 글리세릴스테아레이트, 스테아릴알코올, 부틸렌글라이콜, 아크릴레이트/C10-30알킬아크릴레이트크로스폴리머, 암모늄아크릴로일다이메틸타우레이트/브이피코폴리머, C12-20알킬글루코사이드, 소듐폴리아크릴로일다이메틸타우레이트, 하이드로제네이티드레시틴, 팔미틱애씨드, 소듐하이알루로네이트, 아데노신, 트로메타민, 병풀추출물, 호장근뿌리추출물, 잔탄검, 황금추출물, 다이소듐이디티에이, 녹차추출물, 스페인감초뿌리추출물, 마트리카리아꽃추출물, 로즈마리잎추출물, 글루코오스, 글루코노락톤, 세라마이드엔피, 티트리잎오일, 말토덱스트린, 마데카소사이드, 사카라이드하이드롤리세이트, 아보카도열매추출물, 아시아티코사이드, 아시아틱애씨드, 마데카식애씨드, 다시마추출물, 당느릅나무뿌리추출물, 알로에베라잎추출물, 참마뿌리추출물, 제비꽃꽃추추물"));
        productArrayList.add(new Product("test2", "", "test", "test"));
        productArrayList.add(new Product("test3", "", "test", "test"));
        productArrayList.add(new Product("test4", "", "test", "test"));
        productArrayList.add(new Product("test5", "", "test", "test"));
        productArrayList.add(new Product("test6", "", "test", "test"));
        productArrayList.add(new Product("test7", "", "test", "test"));
        productArrayList.add(new Product("test8", "", "test", "test"));
        productArrayList.add(new Product("test9", "", "test", "test"));
        //end while
        ((TextView)root.findViewById(R.id.search_result_count)).setText(getString(R.string.search_result_count, searchingItem, productArrayList.size()));

        RecyclerView recyclerView = root.findViewById(R.id.search_result_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearProductAdaptor linearProductAdaptor = new LinearProductAdaptor(productArrayList, requireContext());
        linearProductAdaptor.setOnItemClickListener(product -> {
            MainProductFragment mainItemFragment = new MainProductFragment();
            Bundle bundle = new Bundle(1);
            bundle.putSerializable("selected_product", product);
            mainItemFragment.setArguments(bundle);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.search_result_container, mainItemFragment)
                    .addToBackStack(null)
                    .commit();
        });
        recyclerView.setAdapter(linearProductAdaptor);
        //TODO: 자식 fragment에 대한 뒤로가기 구현. 현재 MainActivity의 뒤로가기만 먹혀서 상품 상세 정보를 봤다가 나올 수 없음
        return  root;
    }
}