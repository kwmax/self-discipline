package com.kwmax.self.discipline.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.kwmax.self.discipline.R;
import com.kwmax.self.discipline.TomatoAddDialog;
import com.kwmax.self.discipline.TomatoTodolistAdapter;
import com.kwmax.self.discipline.model.TomatoTodo;
import com.kwmax.self.discipline.operateTomatoListener;
import com.kwmax.self.discipline.widget.TomatoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keweimeng on 2019/3/10.
 */
public class TomatoTabFragment extends BasicFragment implements View.OnClickListener {


    private RecyclerView tomatoRecycler;
    private List<TomatoTodo> tomatoTodoList;

    private ImageView toolbarAdd;
    private ImageView toolbarDelete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tomato = inflater.inflate(R.layout.fragment_tomato, container, false);
        initView(tomato);
        return tomato;
    }

    private void initView(View view) {

        TextView title = view.findViewById(R.id.top_text);
        title.setText("番茄");
        toolbarAdd = view.findViewById(R.id.menu1);
        toolbarAdd.setVisibility(View.VISIBLE);
        toolbarDelete = view.findViewById(R.id.menu2);
        toolbarDelete.setVisibility(View.VISIBLE);

        tomatoRecycler = view.findViewById(R.id.tomato_todolist);
        tomatoTodoList = new ArrayList<>();

        toolbarAdd.setOnClickListener(this);
        toolbarDelete.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        tomatoRecycler.setLayoutManager(manager);
        TomatoTodolistAdapter adapter = new TomatoTodolistAdapter(getContext(), tomatoTodoList);
        tomatoRecycler.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.menu1:
                final TomatoAddDialog dialogFragment = TomatoAddDialog.getInstance("添加番茄任务");
                dialogFragment.show(getFragmentManager(), new operateTomatoListener() {
                    @Override
                    public void addTomato(String tomato, String minCount) {
                        tomatoTodoList.add(new TomatoTodo(tomato,minCount));
                        tomatoRecycler.invalidate();
                        dialogFragment.dismiss();
                    }

                    @Override
                    public void cancal() {
                        dialogFragment.dismiss();
                    }
                });
                break;
            case R.id.menu2:
                break;
            case R.id.tomato_pause:
                break;
            case R.id.tomato_stop:
                break;
            default:
                break;
        }
    }


}
