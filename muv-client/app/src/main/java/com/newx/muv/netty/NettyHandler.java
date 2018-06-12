package com.newx.muv.netty;

import android.os.Handler;
import android.os.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by xuzhijian on 2018/5/30 0030.
 */

public class NettyHandler extends SimpleChannelInboundHandler<String> {

    final Handler handler;
    public NettyHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Message message = handler.obtainMessage(0x01);
        message.obj = "[SYSTEM] - " + s;
        message.sendToTarget();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Message message = handler.obtainMessage(0x01);
        message.obj = "[SYSTEM] - CLIENT ACTIVE";
        message.sendToTarget();
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Message message = handler.obtainMessage(0x01);
        message.obj = "[SYSTEM] - CLIENT INACTIVE";
        message.sendToTarget();
        super.channelInactive(ctx);
    }
}
