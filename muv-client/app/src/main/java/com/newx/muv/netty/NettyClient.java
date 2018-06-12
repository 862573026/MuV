package com.newx.muv.netty;

import android.os.Handler;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by xuzhijian on 2018/5/30 0030.
 * Netty客户端
 */

public class NettyClient {

    private String host = "10.0.3.2";
    private int port = 8090;

    private NioEventLoopGroup group;
    public Channel channel;
    private ChannelFuture channelFuture;

    public void connect(final Handler handler) {
        new Thread() {
            @Override
            public void run() {
                try {
                    group = new NioEventLoopGroup();

                    Bootstrap bootstrap = new Bootstrap();
                    bootstrap.group(group);
                    bootstrap.channel(NioSocketChannel.class);
                    bootstrap.handler(new NettyInitializer(handler));
                    bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
                    bootstrap.option(ChannelOption.TCP_NODELAY, true);

                    channelFuture = bootstrap.connect(new InetSocketAddress(host, port));
                    channel = channelFuture.sync().channel();
                    channelFuture.addListener((ChannelFutureListener) channelFuture -> handler.obtainMessage(0x00).sendToTarget());

                    channel.closeFuture().sync();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
