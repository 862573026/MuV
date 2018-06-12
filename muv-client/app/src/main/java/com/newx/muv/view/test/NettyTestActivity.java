package com.newx.muv.view.test;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.support.mvvm.NxBindingActivity;
import com.newx.base.frameworks.widget.toast.ToastUtil;
import com.newx.muv.R;
import com.newx.muv.databinding.ActivityNettyTestBinding;
import com.newx.muv.entity.dto.EchoFile;
import com.newx.muv.entity.dto.EchoMessage;
import com.newx.muv.netty.NettyClient;
import com.newx.muv.view.route.ACTIVITY;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * Created by xuzhijian on 2018/5/30 0030.
 */
@Route(path = ACTIVITY.NettyTestActivity)
public class NettyTestActivity extends NxBindingActivity<ActivityNettyTestBinding> {

    NettyClient client = new NettyClient();

    Uri uri;

    private int dataLength = 1024;
    private int sumCountPackage = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client.connect(handler);

        mBinding.btnSend.setOnClickListener(v -> {
            String msg = String.valueOf(mBinding.etMsg.getText()) + "\r\n";
            if (msg.length() != 0) {
                handler.obtainMessage(0x03).sendToTarget();
            }
        });

        mBinding.btnPic.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            uri = data.getData();
            Log.e("uri", uri.toString());

            //ContentResolver cr = this.getContentResolver();
            //try {
            //    Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

            //ImageView imageView = (ImageView) findViewById(R.id.iv01);
            ///* 将Bitmap设定到ImageView */
            //imageView.setImageBitmap(bitmap);

            handler.obtainMessage(0x04).sendToTarget();

            //} catch (FileNotFoundException e) {
            //    e.printStackTrace();
            //} catch (IOException e) {
            //    e.printStackTrace();
            //}
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String m = msg.obj + "";
            switch (msg.what) {
                case 0x00:
                    //online

                    String hello = new String("I'm in!");

                    //ByteBuf buf = Unpooled.buffer(hello.length());
                    //buf.readBytes(hello.getBytes());
                    //channel.writeAndFlush(buf);
                    //channel.read();

                    EchoMessage em = new EchoMessage();
                    byte[] b = hello.getBytes();
                    em.setBytes(b);
                    em.setSumCountPackage(b.length);
                    em.setCountPackage(1);
                    em.setSend_time(System.currentTimeMillis());

                    client.channel.writeAndFlush(em);

                    break;
                case 0x01:
                    //receive
                    mBinding.etScroll.setText(mBinding.etScroll.getText() + m + "\r\n");
                    break;
                case 0x02:
                    //send complete
                    mBinding.etMsg.setText("");
                    break;
                case 0x03:
                    //send txt
                    String mmm = String.valueOf(mBinding.etMsg.getText() + "");
                    if (mmm.length() == 0)
                        return;

                    EchoMessage emm = new EchoMessage();
                    emm.setSend_time(System.currentTimeMillis());

                    byte[] bb = mmm.getBytes();
                    emm.setBytes(bb);
                    emm.setSumCountPackage(bb.length);
                    emm.setCountPackage(1);
                    emm.setSend_time(System.currentTimeMillis());

                    //判空
                    client.channel.writeAndFlush(emm).addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            handler.obtainMessage(0x02).sendToTarget();
                        }
                    });
                    break;
                case 0x04:
                    //send pic

                    try {

                        ContentResolver resolver = getContentResolver();
                        InputStream reader = resolver.openInputStream(uri);
                        File file = new File(Environment
                                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                                .getAbsolutePath() + "/Android.pdf");
//                        reader = new FileInputStream(file);
                        byte[] bytes = new byte[reader.available()];

                        reader.read(bytes);
                        reader.close();

                        //byte[] bytes=toByteArray(filePath);

                        if ((bytes.length % dataLength == 0))
                            sumCountPackage = bytes.length / dataLength;
                        else
                            sumCountPackage = (bytes.length / dataLength) + 1;

                        //LOGGER.debug("文件总长度:" + randomAccessFile.length());
                        Log.i("TAG", "文件总长度:" + bytes.length);

                        //if (randomAccessFile.read(bytes) != -1) {
                        //for (int i = 0; i < bytes.length; i += dataLength) {
                        EchoFile msgFile = new EchoFile();
                        msgFile.setSumCountPackage(sumCountPackage);
                        //msgFile.setCountPackage(i);
                        msgFile.setCountPackage(1);

                        //byte[] b = new byte[dataLength];

                        //for (int j = i; j < dataLength; j++) {
                        //    b[i] = bytes[j];
                        //}

                        msgFile.setBytes(bytes);
                        //msgFile.setFile_md5("Iknowyournew.jpg");
                        msgFile.setFile_name(Build.MANUFACTURER + "-" + UUID.randomUUID() + ".jpg");
                        client.channel.writeAndFlush(msgFile);
                        //}
                        //} else {


                        System.out.println("文件已经读完");
                        //}
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException i) {
                        i.printStackTrace();
                    }

                    post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                ContentResolver resolver = getContentResolver();
                                InputStream reader = resolver.openInputStream(uri);
                                byte[] bytes = new byte[reader.available()];

                                //reader.read(buffer, 0, buffer.length);

                                //while ((len=reader.read(buffer)) != 0) {
                                //channel.writeAndFlush(buffer + "\r\n");

                                //Environment.getExternalStorageDirectory()
                                //File f = new File(uri.toString());
                                //channel.writeAndFlush(new ChunkedFile(f, (int) f.length()) + "\r\n");
                                //}

                                //final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));


                                reader.read(bytes);
                                reader.close();

                                client.channel.writeAndFlush("filelength:" + bytes.length + "\r\n");
                                client.channel.flush();
                                client.channel.read();

                                //final ByteBuf buff = Unpooled.copiedBuffer(bytes);
                                //channel.writeAndFlush(buff.readBytes(bytes));
                                client.channel.writeAndFlush(bytes);

                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                client.channel.flush();
                                client.channel.read();
                            }
                        }
                    });
                    break;
                default:
                    ToastUtil.showShort("UNKNOWN MSG: " + m);
                    break;
            }
        }
    };

    @Override
    public int initContentView() {
        return R.layout.activity_netty_test;
    }
}
