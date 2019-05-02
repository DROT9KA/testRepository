package ua.study.awesome.androidlessons.testtask_skysoft.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ua.study.awesome.androidlessons.testtask_skysoft.R;
import ua.study.awesome.androidlessons.testtask_skysoft.data.comparators.DeviceDescriptionComparator;
import ua.study.awesome.androidlessons.testtask_skysoft.data.comparators.DeviceIdComparator;
import ua.study.awesome.androidlessons.testtask_skysoft.data.comparators.DeviceNameComparator;
import ua.study.awesome.androidlessons.testtask_skysoft.data.entity.DeviceEntity;
import ua.study.awesome.androidlessons.testtask_skysoft.ui.MainActivity;
import ua.study.awesome.androidlessons.testtask_skysoft.ui.adapter.DeviceAdapter;

public class DeviceFragment extends Fragment {

    private static final String TAG = "myLogs";

    private DeviceEntity deviceEntityOne;
    private DeviceEntity deviceEntityTwo;
    private DeviceEntity deviceEntityThree;
    private DeviceEntity deviceEntityFour;
    private DeviceEntity deviceEntityFive;

    private ArrayList<DeviceEntity> deviceEntities = new ArrayList<>();

    DeviceAdapter adapter;

    @BindView(R.id.recycler_device)
    RecyclerView recyclerDevice;

    @BindView(R.id.edt_search)
    EditText editSearch;

    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_device, container, false);

        Unbinder unbinder = ButterKnife.bind(this, v);

        setHasOptionsMenu(true);

        settingDeviceValue();

        settingListDevices();

        init();

        searchDevice();

        return v;
    }

    private void init() {
//        deviceFilter = new DeviceFilter(deviceEntities);
        adapter = new DeviceAdapter(getContext());
        adapter.setDeviceEntities(deviceEntities);

        recyclerDevice.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerDevice.setAdapter(adapter);

        Objects.requireNonNull(((MainActivity) Objects.requireNonNull(getActivity())).
                getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_menu_white);
    }

    public void searchDevice(){

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s.toString(), new Filter.FilterListener() {
                    @Override
                    public void onFilterComplete(int count) {

//                        adapter.setDeviceEntities(deviceFilter.getFilterableDeviceEntities());

                        if (adapter.getDeviceEntities().isEmpty()){
                            tvEmpty.setVisibility(View.VISIBLE);
                        }else {
                            tvEmpty.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.sort_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sort_by_id:
                sortById();
                break;
            case R.id.sort_by_name:
                sortByName();
                break;
            case R.id.sort_by_description:
                sortByDescription();
                break;
            case android.R.id.home:
                ((MainActivity) Objects.requireNonNull(getActivity())).drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sortById(){
        adapter.removeDevices();
        settingListDevices();
        Collections.sort(deviceEntities, new DeviceIdComparator());
        adapter.setDeviceEntities(deviceEntities);
        recyclerDevice.setAdapter(adapter);
    }

    public void sortByName(){
        adapter.removeDevices();
        settingListDevices();
        Collections.sort(deviceEntities, new DeviceNameComparator());
        adapter.setDeviceEntities(deviceEntities);
        recyclerDevice.setAdapter(adapter);
    }

    public void sortByDescription(){
        adapter.removeDevices();
        settingListDevices();
        Collections.sort(deviceEntities, new DeviceDescriptionComparator());
        adapter.setDeviceEntities(deviceEntities);
        recyclerDevice.setAdapter(adapter);
    }

    public void settingDeviceValue() {
        deviceEntityOne = new DeviceEntity(11111111, "Xiaomi", getString(R.string.xiaomi_description));
        deviceEntityTwo = new DeviceEntity(22222222, "Samsung", getResources().getString(R.string.samsung_description));
        deviceEntityThree = new DeviceEntity(33333333, "IPhone", getResources().getString(R.string.iphone_description));
        deviceEntityFour = new DeviceEntity(44444444, "Huawei", getResources().getString(R.string.huawei_description));
        deviceEntityFive = new DeviceEntity(55555555, "Nokia", getResources().getString(R.string.nokia_description));
    }

    int number = 1000;
    public void settingListDevices() {
        Random randomaizer = new Random();

        for (int i = 0; i < number; i++) {

            int random = randomaizer.nextInt(6);

            switch (random) {
                case 1:
                    deviceEntities.add(deviceEntityOne);
                    break;
                case 2:
                    deviceEntities.add(deviceEntityTwo);
                    break;
                case 3:
                    deviceEntities.add(deviceEntityThree);
                    break;
                case 4:
                    deviceEntities.add(deviceEntityFour);
                    break;
                case 5:
                    deviceEntities.add(deviceEntityFive);
                    break;
            }
        }
    }
}