package com.example.tmnt.viewpagertransforms;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.ToxicBakery.viewpager.transforms.FlipVerticalTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.ScaleInOutTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.TabletTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutTranformer;

import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements ActionBar.OnNavigationListener {

    @InjectView(R.id.container)
    ViewPager container;

    private static final String KEY_SELECTED_PAGE = "KEY_SELECTED_PAGE";
    private static final String KEY_SELECTED_CLASS = "KEY_SELECTED_CLASS";
    private static final ArrayList<TransformerItem> TRANSFORM_CLASSES;
    private int mSelectedItem=0;
    static {
        TRANSFORM_CLASSES = new ArrayList<>();
        TRANSFORM_CLASSES.add(new TransformerItem(DefaultTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(AccordionTransformer.class));//手风琴
        TRANSFORM_CLASSES.add(new TransformerItem(BackgroundToForegroundTransformer.class));//后台变前台
        TRANSFORM_CLASSES.add(new TransformerItem(CubeInTransformer.class));//闪烁
        TRANSFORM_CLASSES.add(new TransformerItem(CubeOutTransformer.class));//立体
        TRANSFORM_CLASSES.add(new TransformerItem(DepthPageTransformer.class));//深度渐变
        TRANSFORM_CLASSES.add(new TransformerItem(FlipHorizontalTransformer.class));//水平折叠
        TRANSFORM_CLASSES.add(new TransformerItem(FlipVerticalTransformer.class));//垂直折叠
        TRANSFORM_CLASSES.add(new TransformerItem(ForegroundToBackgroundTransformer.class));//前台变后台
        TRANSFORM_CLASSES.add(new TransformerItem(RotateDownTransformer.class));//旋转（下）
        TRANSFORM_CLASSES.add(new TransformerItem(RotateUpTransformer.class));//旋转（上）
        TRANSFORM_CLASSES.add(new TransformerItem(ScaleInOutTransformer.class));//放大
        TRANSFORM_CLASSES.add(new TransformerItem(StackTransformer.class));//栈
        TRANSFORM_CLASSES.add(new TransformerItem(TabletTransformer.class));//翻页
        TRANSFORM_CLASSES.add(new TransformerItem(ZoomInTransformer.class));//中心放大
        TRANSFORM_CLASSES.add(new TransformerItem(ZoomOutSlideTransformer.class));//虚化
        TRANSFORM_CLASSES.add(new TransformerItem(ZoomOutTranformer.class));//渐变
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ArrayAdapter<TransformerItem> actionBarAdapter = new ArrayAdapter<TransformerItem>(
                getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, TRANSFORM_CLASSES);

        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        MyAdapter adapter=new MyAdapter(getSupportFragmentManager());
        container.setAdapter(adapter);
        Random random=new Random();
        int p=random.nextInt(16);
        try {
            container.setPageTransformer(true,TRANSFORM_CLASSES.get(p).clazz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        try {
            container.setPageTransformer(true,TRANSFORM_CLASSES.get(itemPosition).clazz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }


    private static final class TransformerItem {

        final String title;
        final Class<? extends ViewPager.PageTransformer> clazz;

        public TransformerItem(Class<? extends ViewPager.PageTransformer> clazz) {
            this.clazz = clazz;
            title = clazz.getSimpleName();
        }

        @Override
        public String toString() {
            return title;
        }

    }
}
