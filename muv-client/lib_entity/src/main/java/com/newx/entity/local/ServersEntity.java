package com.newx.entity.local;

import java.util.List;

/**
 * Created by xuzhijian on 2018/4/20 0020.
 * 服务器
 */

public class ServersEntity {


    private List<ServerBean> servers;

    public List<ServerBean> getServers() {
        return servers;
    }

    public static class ServerBean {
        /**
         * name : 测试服务器
         * ip : 127.0.0.1
         * http_port : 8080
         * tcp_port : 6666
         * enable : true
         * visible : true
         */

        private String name;
        private String ip;
        private int http_port;
        private int tcp_port;
        private boolean enable;
        private boolean visible;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getHttp_port() {
            return http_port;
        }

        public void setHttp_port(int http_port) {
            this.http_port = http_port;
        }

        public int getTcp_port() {
            return tcp_port;
        }

        public void setTcp_port(int tcp_port) {
            this.tcp_port = tcp_port;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
