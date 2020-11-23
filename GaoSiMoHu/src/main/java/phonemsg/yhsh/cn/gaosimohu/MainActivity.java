package phonemsg.yhsh.cn.gaosimohu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author DELL
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 正常加载图片
     *
     * @param view view
     */
    public void showImage(View view) {
        Intent intent = new Intent(this, ShowImageActivity.class);
        intent.putExtra("position", 1);
        intent.putExtra("tittle", "加载网络图片");
        startActivity(intent);
    }

    /**
     * 高斯模糊加载图片
     *
     * @param view view
     */
    public void gaoSiMoHu(View view) {
        Intent intent = new Intent(this, ShowImageActivity.class);
        intent.putExtra("position", 2);
        intent.putExtra("tittle", "高斯模糊");
        startActivity(intent);
    }
}
