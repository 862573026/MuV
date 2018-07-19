package debug;

import android.os.Bundle;

import com.newx.base.route.RP;
import com.newx.common.frameworks.support.activity.NxActivity;
import com.newx.common.helper.JumpHelper;

/**
 * Created by xuzhijian on 2018/7/11 0011.
 */

public class MainActivity extends NxActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JumpHelper.containerFragment(RP.PopularFragment).navigation();
        finish();
    }
}
