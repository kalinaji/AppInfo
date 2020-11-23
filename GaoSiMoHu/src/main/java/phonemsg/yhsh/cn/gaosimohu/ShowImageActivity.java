package phonemsg.yhsh.cn.gaosimohu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * @author DELL
 */
public class ShowImageActivity extends Activity {

    private int position;
    String imageUrl1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564140604531&di=866a69099c6673c21020730d60758c73&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171207%2Ff741cfd00d494cc8a388793351043ee4.jpeg";
    String imageUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564139010069&di=c6f0e79ca23a07055cc4a03430aa9eeb&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171207%2Fbc9df807558444c9953258164a72779e.jpeg";
    String imageUrl3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564140954192&di=5e63e3e58b1862f5b47fbae003d709e6&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171207%2Ffd8964e3623d443880f089c5fcb7cd08.jpeg";
    String imageUrl4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564140988695&di=c57ee1eea220afe89f91974d03e2b748&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171207%2F43710b039cbb49dd816d49b21ca679d8.jpeg";
    String imageUrl5 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564735743&di=724ab81be67a95b87aed1d99d58fd6a7&imgtype=jpg&er=1&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171207%2F9e678555ba344ee0b1dd4c505f396f9d.jpeg";
    private ImageView ivShowImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除状态栏的方法
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去除默认标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image);
        ivShowImage = findViewById(R.id.iv_show_image);
        position = getIntent().getIntExtra("position", 0);
        String tittle = getIntent().getStringExtra("tittle");
        setTitle(tittle);
        initData();
    }

    private void initData() {
        if (position == 1) {
            Glide.with(this).load(imageUrl1).into(ivShowImage);

        } else if (position == 2) {
            Glide.with(this).load(imageUrl2)
                    //这里面25代表模糊的角度,1代表模糊的程度模糊的比例，值越大越模糊，值越小越清晰，值必须大于0
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 1)))
                    .into(ivShowImage);

        }

    }
}
