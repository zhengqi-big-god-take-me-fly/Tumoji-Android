package com.tumoji.tumoji.memes.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tumoji.tumoji.R;
import com.tumoji.tumoji.data.meme.model.MemeModel;
import com.tumoji.tumoji.data.tag.model.TagModel;
import com.tumoji.tumoji.memes.contract.MemesContract;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemesFragment extends Fragment implements MemesContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MemesContract.Presenter mPresenter;


    public MemesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemesFragment newInstance(String param1, String param2) {
        MemesFragment fragment = new MemesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memes, container, false);
    }

    @Override
    public void refershPopularMemesList(List list, int offset) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void showHdMeme(MemeModel memeModel) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void setSelectedTag(TagModel tagModel) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void refreshTopTagsList(List list) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void refreshNewMemesList(List list, int offset) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void setPresenter(MemesContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
