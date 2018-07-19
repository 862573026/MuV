package com.newx.net;



import com.newx.common.proxy.ResourceProxy;
import com.newx.net.entity.ServersEntity;
import com.newx.net.http.HttpUtil;
import com.newx.utils.config.AssetsManager;
import com.newx.utils.def.CONSTANT;
import com.newx.utils.text.GsonUtil;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xuzhijian on 2018/4/20 0020.
 * 服务器管理类
 */

public class ServerManager {

    private ServersEntity mServers;

    private ServersEntity.ServerBean mSelectedServer;

    /**
     * 加载地址配置文件
     */
    public void loadServer() {
        String servers = AssetsManager.getInstance().readFileFromAssets(ResourceProxy.getContext(), CONSTANT.ASSET_SERVER);
        mServers = GsonUtil.toEntity(servers, ServersEntity.class);

        Flowable
                .create(emitter -> {
                    while (mSelectedServer == null) {
                        for (int i = 0; i < mServers.getServers().size(); i++) {
                            mSelectedServer = mServers.getServers().get(i);
                            HttpUtil.setBaseURL(mSelectedServer.getIp(), mSelectedServer.getHttp_port());
                            if (true
//                                        NetUtil.isReachable(mSelectedServer.getIp(), mSelectedServer.getHttp_port())
//                        && NetUtil.isReachble(mSelectedServer.getIp(),mSelectedServer.getTcp_port())
                                    ) {
                                break;
                            } else {
                                mSelectedServer = null;
                            }
                        }
                    }
                }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .subscribe();

    }

    public List<ServersEntity.ServerBean> getServers() {
        return mServers.getServers();
    }

    public String getSelectedHttpServer() {
        return mSelectedServer.getIp() + ":" + mSelectedServer.getHttp_port();
    }
}
